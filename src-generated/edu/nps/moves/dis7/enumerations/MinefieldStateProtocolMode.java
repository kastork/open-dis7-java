package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 336 marshal size 2<br>
 * _____________________________
 */
public enum MinefieldStateProtocolMode 
{
    /** Heartbeat Mode */
    HEARTBEAT_MODE (0, "Heartbeat Mode"),
    /** QRP Mode */
    QRP_MODE (1, "QRP Mode");

    private int value;
    private final String description;

    MinefieldStateProtocolMode(int value, String description)
    {
        this.value = value;
        this.description = description;
    }

    public int getValue()
    {
        return value;
    }

    public String getDescription()
    {
        return description;
    }
    
    public static int getEnumBitWidth()
    {
      return 2;
    }

    public static MinefieldStateProtocolMode getEnumForValue(int i)
    {
       for(MinefieldStateProtocolMode val: MinefieldStateProtocolMode.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration MinefieldStateProtocolMode");
       return null;
    }


    public void marshal(DataOutputStream dos) throws IOException
    {
        dos.writeByte(getValue());
    }
    
    public void marshal(ByteBuffer buff) throws Exception
    {
        buff.put((byte)getValue());
    }

    public static MinefieldStateProtocolMode unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static MinefieldStateProtocolMode unmarshalEnum(ByteBuffer buff) throws Exception
    {
        /*
        try {
            value = (int)buff.get();
        }
        catch(Exception ex) {
            showError(ex);
        }
        */
        return getEnumForValue(buff.get());
    }

    /** Returns the size of this serialized object in bytes
     *@return size in bytes*/  
    public int getMarshalledSize()
    {
        return 1; // 8 bits
    }
    
    @Override
    public String toString()
    {
        return "MinefieldStateProtocolMode: " + name() + ": " + getValue(); 
    }
}
