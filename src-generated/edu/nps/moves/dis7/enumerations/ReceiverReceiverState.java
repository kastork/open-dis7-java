package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 179 marshal size 16<br>
 * _______________________
 */
public enum ReceiverReceiverState 
{
    /** Off */
    OFF (0, "Off"),
    /** On but not receiving */
    ON_BUT_NOT_RECEIVING (1, "On but not receiving"),
    /** On and receiving */
    ON_AND_RECEIVING (2, "On and receiving");

    private int value;
    private final String description;

    ReceiverReceiverState(int value, String description)
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
      return 16;
    }

    public static ReceiverReceiverState getEnumForValue(int i)
    {
       for(ReceiverReceiverState val: ReceiverReceiverState.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration ReceiverReceiverState");
       return null;
    }


    public void marshal(DataOutputStream dos) throws IOException
    {
        dos.writeShort(getValue());
    }

    public void marshal(ByteBuffer buff) throws Exception
    {
        buff.putShort((short)getValue());
    }

    public static ReceiverReceiverState unmarshalEnum (DataInputStream dis) throws Exception
    {
        return getEnumForValue(dis.readUnsignedShort());
    } 

    public static ReceiverReceiverState unmarshalEnum (ByteBuffer buff) throws Exception
    {
        return getEnumForValue(buff.getShort());
    }   

  /**
   * Returns size of this serialized object in bytes
   * @return size in bytes
   */
    public int getMarshalledSize()
    {
        return 2; // 16 bits
    }
    
    @Override
    public String toString()
    {
        return "ReceiverReceiverState: " + name() + ": " + getValue(); 
    }
}
