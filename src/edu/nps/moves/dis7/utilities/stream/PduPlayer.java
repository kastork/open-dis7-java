/**
 * Copyright (c) 2008-2020, MOVES Institute, Naval Postgraduate School (NPS). All rights reserved.
 * This work is provided under a BSD open-source license, see project license.html and license.txt
 */
package edu.nps.moves.dis7.utilities.stream;

import com.google.common.primitives.Longs;
import edu.nps.moves.dis7.enumerations.DISPDUType;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.regex.Pattern;

/** Utility to play back log files of recorded PDUs. These PDUs can then be resent
 * over a multicast group address, or processed locally.
 * 
 * @author Mike Bailey, jmbailey@nps.edu
 */
public class PduPlayer {

    /** PDU listener interface */
    public interface RawListener {
        void receiveBytes(byte[] ba);
    }
    
    private Path disLogDirectory;
    private String ip;
    private int port;
    private Thread thrd;

    static final String ENCODING_BASE64 = "ENCODING_BASE64";
    static final String ENCODING_PLAINTEXT = "ENCODING_PLAINTEXT";
    static final String ENCODING_BINARY = "ENCODING_BINARY";  // TODO likely requires different code path
    static final String ENCODING_XML = "ENCODING_XML";     // TODO, repeat Open-DIS version 4 effort
    static final String ENCODING_EXI = "ENCODING_EXI";     // TODO, use Exificient or Nagasena libraries
    static final String ENCODING_JSON = "ENCODING_JSON";    // TODO, repeat Open-DIS version 4 effort
    static final String ENCODING_MAK_DATA_LOGGER = "ENCODING_MAK_DATA_LOGGER";        // verbose pretty-print. perhaps output only (MAK format itself is binary)
    static final String ENCODING_WIRESHARK_DATA_LOGGER = "ENCODING_WIRESHARK_DATA_LOGGER"; // 
    static final String ENCODING_CDIS = "ENCODING_CDIS";    // future work based on new SISO standard

    private static String pduLogEncoding = ENCODING_PLAINTEXT; // TODO use Java enumerations, generalize/share across library

    /** Constructor that spawns the player thread.
     * 
     * @param ip the multicast group address to utilize
     * @param port the multicast port to utilize
     * @param disLogDirectory a path to the directory containing PDU log files
     * @param sendToNet to capture X3D interpolator values - if desired
     * @throws IOException if something goes wrong processing files
     */
    public PduPlayer(String ip, int port, Path disLogDirectory, boolean sendToNet) throws IOException {
        this.disLogDirectory = disLogDirectory;
        this.ip = ip;
        this.port = port;
        this.netSend = sendToNet;

        thrd = new Thread(() -> begin());
        thrd.setPriority(Thread.NORM_PRIORITY);
        thrd.setName("PlayerThread");
        thrd.setDaemon(true);
        thrd.start();
    }

    private Integer scenarioPduCount = null;
    private boolean showPduCountsOneTime = false;
    private int pduCount = 0;
    private DatagramSocket datagramSocket;
    private Long startNanoTime = null;
    private boolean paused = false;
    private boolean netSend = true;
    private RawListener rawListener = null;

    //ToDo: Add X3d for BASE64 Encoding
    // -------------------- Begin Variables for X3D autogenerated code
    private X3dCreateInterpolators x3dInterpolators = new X3dCreateInterpolators();
    private X3dCreateLineSet x3dLineSet = new X3dCreateLineSet();
    private byte[] globalByteBufferForX3dInterPolators = null;
    // -------------------- End Variables for X3D autogenerated code
    
    public void addRawListener(RawListener lis) {
        rawListener = lis;
    }

    /** Thread process for this class */
    @SuppressWarnings("StatementWithEmptyBody")
    public void begin() {
        try {
            System.out.println("Replaying DIS logs.");
            
            InetAddress addr = null;
            DatagramPacket datagramPacket;
            DISPDUType type;
            String tempString;
            String[] sa = null, splitString;
            String REGEX;
            Pattern pattern;
            byte[] pduTimeBytes = null, bufferShort = null;
            int[] arr;
            IntBuffer intBuffer;
            int tempInt;
            ByteBuffer byteBuffer1, byteBuffer2;
            long pduTimeInterval, targetSimTime, now, sleepTime;

            FilenameFilter filter = (dir, name) -> {
                return name.endsWith(PduRecorder.DISLOG_FILE_EXTENSION) && !name.startsWith(".");
            };

            File[] fs = disLogDirectory.toFile().listFiles(filter);
            if (fs == null) {
                fs = new File[0];
            }

            Arrays.sort(fs, (f1, f2) -> {
                return f1.getName().compareTo(f2.getName());
            });

            if (netSend) {
                addr = InetAddress.getByName(ip);
                datagramSocket = new DatagramSocket();
            }
            
            Base64.Decoder base64Decoder = Base64.getDecoder();

            for (File f : fs) {
                System.out.println("Replaying " + f.getAbsolutePath());
                List<String> lines = Files.readAllLines(Path.of(f.getAbsolutePath()));

                for (String line : lines) {
                    while (paused) {
                        sleep(1000l); // TODO confirm: full second, was half second
                    }
                    if (line.length() <= 0)
                        ; // blank lines ok
                    else if (line.trim().startsWith(PduRecorder.COMMENT_MARKER)) {
                        if (handleComment(line, f)) {
                            break;
                        }
                    } else {
                        
                        switch (pduLogEncoding) {
                            case "ENCODING_BASE64":
                                sa = line.split(",");
                                break;

                            case "ENCODING_PLAINTEXT":

                                if (line.contains(PduRecorder.COMMENT_MARKER)) {
                                    line = line.substring(0, line.indexOf(PduRecorder.COMMENT_MARKER) - 1); //Delete appended Comments
                                }
                                //Pattern splitting needed for playback of unencoded streams
                                REGEX = "\\],\\[";
                                pattern = Pattern.compile(REGEX);

                                sa = pattern.split(line);
                                //Add the "]" to the end of sa[0]. It was taken off by the split
                                sa[0] = sa[0].concat("]");
                                //Add the "]" to the end of sa[0]. It was taken off by the split
                                if (sa.length > 1)
                                    sa[1] = "[".concat(sa[1]);

                                break;

                            default:
                                System.err.println("Encoding'" + pduLogEncoding + " not recognized or supported");
                        }

                        if (sa != null && sa.length != 2) {
                            System.err.println("Error: parsing error.  Line follows.");
                            System.err.println(line);
                            byebye();
                        }

                        if (startNanoTime == null) {
                            startNanoTime = System.nanoTime();
                        }
                        
                        switch (pduLogEncoding) {
                            case "ENCODING_BASE64":
                                pduTimeBytes = base64Decoder.decode(sa[0]);
                                break;

                            case "ENCODING_PLAINTEXT":

                                //Split first String into multiple Strings cotaining integers
                                REGEX = ",";
                                pattern = Pattern.compile(REGEX);

                                sa[0] = sa[0].substring(1, sa[0].length() - 1);

                                splitString = pattern.split(sa[0]);

                                //Define an array to store the in values from the string and initalize it to a value drifferent from NULL
                                arr = new int[splitString.length];

                                //Test
                                for (int x = 0; x < splitString.length; x++) {

                                    tempString = splitString[x].trim();

                                    tempInt = Integer.parseInt(tempString);
                                    arr[x] = tempInt;
                                }
                                // Credit:  https://stackoverflow.com/questions/1086054/how-to-convert-int-to-byte
                                byteBuffer1 = ByteBuffer.allocate(arr.length * 4);
                                intBuffer = byteBuffer1.asIntBuffer();
                                intBuffer.put(arr);

                                pduTimeBytes = byteBuffer1.array();
                                break;

                            default:
                                System.err.println("Encoding'" + pduLogEncoding + " not recognized or supported");

                        }

                        pduTimeInterval = Longs.fromByteArray(pduTimeBytes);
                        // This is a relative number in nanoseconds of the time of packet reception minus first packet reception for scenario.

                        targetSimTime = startNanoTime + pduTimeInterval;  // when we should send the packet
                        now = System.nanoTime();
                        sleepTime = targetSimTime - now; //System.nanoTime(); // the difference between then and now

                        if (sleepTime > 20000000) { // 20 ms //
                            //System.out.println("sim interval = " + pduTimeInterval + ", sleeping for " + sleepTime/1000000l + " ms");
                            sleep(sleepTime / 1000000L, (int) (sleepTime % 1000000L));
                        }

                        byte[] buffer;

                        switch (pduLogEncoding) {
                            case "ENCODING_BASE64":
                                buffer = base64Decoder.decode(sa[1]);
                                if (netSend) {
                                    datagramPacket = new DatagramPacket(buffer, buffer.length, addr, port);
                                    datagramSocket.send(datagramPacket);
                                    type = DISPDUType.getEnumForValue(Byte.toUnsignedInt(buffer[2])); // 3rd byte
                                    System.out.println("Sent PDU: " + type);
                                }
                                break;

                            case "ENCODING_PLAINTEXT":

                                //---Code Tobi for Plain Text---
                                // Handle the second String
                                // Split second String into multiple Strings containing integers
                                REGEX = ",";
                                pattern = Pattern.compile(REGEX);

                                sa[1] = sa[1].substring(1, sa[1].length() - 1);

                                splitString = pattern.split(sa[1]);

                                //Define an array to store the in values from the string and initalize it to a value drifferent from NULL
                                arr = new int[splitString.length];

                                //Test
                                for (int x = 0; x < splitString.length; x++) {

                                    tempString = splitString[x].trim();

                                    tempInt = Integer.parseInt(tempString);
                                    arr[x] = tempInt;

                                    //System.out.println(tempInt);
                                }

                                // Credit:  https://stackoverflow.com/questions/1086054/how-to-convert-int-to-byte
                                byteBuffer2 = ByteBuffer.allocate(arr.length * 4);
                                intBuffer = byteBuffer2.asIntBuffer();
                                intBuffer.put(arr);

                                buffer = byteBuffer2.array();

                                //When the byteBuffer stores the array of Integers into the byte array it stores a 7 as 0 0 0 7.
                                //Therefore a shortBuffer is created where only every fourth value is stored.
                                //it must be done with modulo instead of testing for "0" because a "0" could be there as value and not as padding
                                bufferShort = new byte[byteBuffer2.array().length / 4];

                                int bufferShortCounter = 0;

                                for (int i = 1; i < byteBuffer2.array().length; i++) {

                                    if (((i + 1) % 4) == 0) {

                                        bufferShort[bufferShortCounter] = buffer[i];
                                        bufferShortCounter++;
                                    }
                                }
                                if (netSend) {
                                    datagramPacket = new DatagramPacket(bufferShort, bufferShort.length, addr, port);
                                    datagramSocket.send(datagramPacket);
                                    // Add Points to X3D Components
                                    globalByteBufferForX3dInterPolators = bufferShort.clone();
                                    x3dInterpolators.addPointsToMap(globalByteBufferForX3dInterPolators); // gets cloned again
                                    x3dLineSet.addPointsToMap(globalByteBufferForX3dInterPolators); // gets cloned again
                                    type = DISPDUType.getEnumForValue(Byte.toUnsignedInt(bufferShort[2])); // 3rd byte
                                    System.out.println("Sent PDU: " + type);
                                }
                                break;

                            default:
                                break;
                        }

                        //ToDo: Is this also necessary for buffershort? If yes, put it inside the switch/Case statement
                        if (rawListener != null) {
                            rawListener.receiveBytes(bufferShort);
                        }
                        pduCount++;
                        if (scenarioPduCount != null) {
                            scenarioPduCount++;
                        }

                        if (showPduCountsOneTime || pduCount % 5 == 0) {
                            showCounts();
                        }
                    }
                }
                
                //create X3D components - methods will create console output
                if (netSend) {
                    x3dInterpolators.makeX3dInterpolator();
                    x3dLineSet.makeX3dLineSet();
                }
            }
            if (rawListener != null) {
                rawListener.receiveBytes(null); // indicate the end
            }
        } catch (IOException ex) {
            System.err.println("Exception reading/writing pdus: " + ex.getClass().getSimpleName() + ": " + ex.getLocalizedMessage());
            thrd = null;
            closer();
        }
    }

    private void showCounts() {
        // use carriage return \r for transient display output as a run-time developer diagnostic
        // (possibly as part of earlier diagnosis of threading-related problems with dropped packets)
        if (scenarioPduCount != null) {
            System.out.print(pduCount + " " + ++scenarioPduCount + "..." + "\r"); // TODO where are the ... ? not appearing in output
        } else {
            System.out.print(pduCount + "\r");
        }
        showPduCountsOneTime = false;
    }

    private void byebye() throws IOException {
        System.out.println("Replay stopped.");
        closer();
        throw new IOException("PduPlayer parsing error");
    }

    private void closer() {
        netSend = false;
        if (datagramSocket != null) {
            datagramSocket.close();
            datagramSocket = null;
        }
    }

    private boolean handleComment(String line, File f) //true if we're done
    {
        boolean returnValue = false;
        if (line.trim().startsWith(PduRecorder.START_COMMENT_MARKER)) {
            
            //Read Encoding from FileHeader
            String[] sa = line.split(",", 3);
            pduLogEncoding = sa[1].trim();
            System.err.println(pduLogEncoding);
//            line = line.substring(PduRecorder.START_COMMENT_MARKER.length());
//            System.out.println(line + "  ");
            showPduCountsOneTime = true; // get the first one in there
        } else if (line.trim().startsWith(PduRecorder.FINISH_COMMENT_MARKER)) {
            System.out.print("Total PDUs: ");
            showCounts();
            System.out.println();
            System.out.println("End of replay from " + f.getName());
//            System.out.println(line.substring(PduRecorder.FINISH_COMMENT_MARKER.length()));

            scenarioPduCount = 0;
            startNanoTime = null;
            returnValue = true;
        }
        return returnValue;
    }

    public void startResume() {
        paused = false;
    }

    public void stopPause() {
        paused = true;
    }

    public void end() {
        closer();
    }

    private void sleep(long ms) {
        sleep(ms, 0);
    }
    
    private void sleep(long ms, int ns) {
        // @formatter:off
        try {
            Thread.sleep(ms, ns);
        } catch (InterruptedException ex) {}
        // @formatter:on
    }
}
