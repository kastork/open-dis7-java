/**
 * Copyright (c) 2008-2019, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 */

package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.dis.enumerations.*;

/**
 * 5.8.6 Conveys the audio or digital data that is used to communicate between simulated intercom devices
 * IEEE Std 1278.1-2012, IEEE Standard for Distributed Interactive Simulation—Application Protocols
 */
public class IntercomSignalPdu extends RadioCommunicationsFamilyPdu implements Serializable
{
   protected IntercomReferenceID  intercomReferenceID = new IntercomReferenceID(); 

   /** ID of communications device */
   protected short  intercomNumber;

   /** encoding scheme */
   protected short  encodingScheme;

   /** tactical data link type uid 178 */
   protected SignalTDLType tdlType = SignalTDLType.values()[0];

   /** sample rate */
   protected int  sampleRate;

   /** data length */
   protected short  dataLength;

   /** samples */
   protected short  samples;

   /** data bytes */
   protected byte[]  data = new byte[0]; 

   private byte[] padTo32 = new byte[0]; // pad to 32-bit boundary


/** Constructor */
 public IntercomSignalPdu()
 {
    setPduType( DISPDUType.INTERCOM_SIGNAL );
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize += intercomReferenceID.getMarshalledSize();
   marshalSize += 2;  // intercomNumber
   marshalSize += 2;  // encodingScheme
   marshalSize += tdlType.getMarshalledSize();
   marshalSize += 4;  // sampleRate
   marshalSize += 2;  // dataLength
   marshalSize += 2;  // samples
   marshalSize += data.length * 1;
   marshalSize += padTo32.length;

   return marshalSize;
}


public IntercomSignalPdu setIntercomReferenceID(IntercomReferenceID pIntercomReferenceID)
{
    intercomReferenceID = pIntercomReferenceID;
    return this;
}

public IntercomReferenceID getIntercomReferenceID()
{
    return intercomReferenceID; 
}

public IntercomSignalPdu setIntercomNumber(short pIntercomNumber)
{
    intercomNumber = pIntercomNumber;
    return this;
}

public short getIntercomNumber()
{
    return intercomNumber; 
}

public IntercomSignalPdu setEncodingScheme(short pEncodingScheme)
{
    encodingScheme = pEncodingScheme;
    return this;
}

public short getEncodingScheme()
{
    return encodingScheme; 
}

public IntercomSignalPdu setTdlType(SignalTDLType pTdlType)
{
    tdlType = pTdlType;
    return this;
}

public SignalTDLType getTdlType()
{
    return tdlType; 
}

public IntercomSignalPdu setSampleRate(int pSampleRate)
{
    sampleRate = pSampleRate;
    return this;
}

public int getSampleRate()
{
    return sampleRate; 
}

public IntercomSignalPdu setSamples(short pSamples)
{
    samples = pSamples;
    return this;
}

public short getSamples()
{
    return samples; 
}

public IntercomSignalPdu setData(byte[] pData)
{
    data = pData;
    return this;
}

public byte[] getData()
{
    return data; 
}

/**
 * Serializes an object to a DataOutputStream.
 * @see java.io.DataOutputStream
 * @param dos The DataOutputStream
 */
public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       intercomReferenceID.marshal(dos);
       dos.writeShort( (short)intercomNumber);
       dos.writeShort( (short)encodingScheme);
       tdlType.marshal(dos);
       dos.writeInt( (int)sampleRate);
       dos.writeShort( (short)dataLength);
       dos.writeShort( (short)samples);

       for(int idx = 0; idx < data.length; idx++)
           dos.writeByte(data[idx]);

       padTo32 = new byte[Align.to32bits(dos)];
    }
    catch(Exception e)
    {
      System.err.println(e);
    }
}

/**
 * Unserializes an object from a DataInputStream.
 * @see java.io.DataInputStream
 * @param dis The DataInputStream
 * @return marshalled size
 */
public int unmarshal(DataInputStream dis)
{
    int uPosition = 0;
    uPosition += super.unmarshal(dis);

    try 
    {
        uPosition += intercomReferenceID.unmarshal(dis);
        intercomNumber = (short)dis.readUnsignedShort();
        uPosition += 2;
        encodingScheme = (short)dis.readUnsignedShort();
        uPosition += 2;
        tdlType = SignalTDLType.unmarshalEnum(dis);
        uPosition += tdlType.getMarshalledSize();
        sampleRate = dis.readInt();
        uPosition += 4;
        dataLength = (short)dis.readUnsignedShort();
        uPosition += 2;
        samples = (short)dis.readUnsignedShort();
        uPosition += 2;
        for(int idx = 0; idx < data.length; idx++)
            data[idx] = dis.readByte(); // mike check
        uPosition += data.length; // todo, multiply by prim size mike
        padTo32 = new byte[Align.from32bits(uPosition,dis)];
        uPosition += padTo32.length;
    }
    catch(Exception e)
    { 
      System.out.println(e); 
    }
    return getMarshalledSize();
}

/**
 * Packs an object into the ByteBuffer.
 * @throws java.nio.BufferOverflowException if buff is too small
 * @throws java.nio.ReadOnlyBufferException if buff is read only
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin writing
 * @throws Exception ByteBuffer-generated exception
 */
public void marshal(java.nio.ByteBuffer buff) throws Exception
{
   super.marshal(buff);
   intercomReferenceID.marshal(buff);
   buff.putShort( (short)intercomNumber);
   buff.putShort( (short)encodingScheme);
   tdlType.marshal(buff);
   buff.putInt( (int)sampleRate);
   buff.putShort( (short)dataLength);
   buff.putShort( (short)samples);

   for(int idx = 0; idx < data.length; idx++)
       buff.put((byte)data[idx]);

   padTo32 = new byte[Align.to32bits(buff)];
}

/**
 * Unpacks a Pdu from the underlying data.
 * @throws java.nio.BufferUnderflowException if buff is too small
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin reading
 * @return marshalled size
 * @throws Exception ByteBuffer-generated exception
 */
public int unmarshal(java.nio.ByteBuffer buff) throws Exception
{
    super.unmarshal(buff);

    intercomReferenceID.unmarshal(buff);
    intercomNumber = (short)(buff.getShort() & 0xFFFF);
    encodingScheme = (short)(buff.getShort() & 0xFFFF);
    tdlType = SignalTDLType.unmarshalEnum(buff);
    sampleRate = buff.getInt();
    dataLength = (short)(buff.getShort() & 0xFFFF);
    samples = (short)(buff.getShort() & 0xFFFF);
    for(int idx = 0; idx < data.length; idx++)
        data[idx] = buff.get();
    padTo32 = new byte[Align.from32bits(buff)];
    return getMarshalledSize();
}

 /*
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
@Override
 public boolean equals(Object obj)
 {
    if(this == obj)
      return true;

    if(obj == null)
       return false;

    if(getClass() != obj.getClass())
        return false;

    return equalsImpl(obj);
 }

@Override
 public boolean equalsImpl(Object obj)
 {
     boolean ivarsEqual = true;

    if(!(obj instanceof IntercomSignalPdu))
        return false;

     final IntercomSignalPdu rhs = (IntercomSignalPdu)obj;

     if( ! (intercomReferenceID.equals( rhs.intercomReferenceID) )) ivarsEqual = false;
     if( ! (intercomNumber == rhs.intercomNumber)) ivarsEqual = false;
     if( ! (encodingScheme == rhs.encodingScheme)) ivarsEqual = false;
     if( ! (tdlType == rhs.tdlType)) ivarsEqual = false;
     if( ! (sampleRate == rhs.sampleRate)) ivarsEqual = false;
     if( ! (dataLength == rhs.dataLength)) ivarsEqual = false;
     if( ! (samples == rhs.samples)) ivarsEqual = false;

     for(int idx = 0; idx < 0; idx++)
     {
          if(!(data[idx] == rhs.data[idx])) ivarsEqual = false;
     }

    return ivarsEqual && super.equalsImpl(rhs);
 }
} // end of class