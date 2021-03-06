package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 197 marshal size 12<br>
 * _______________________________
 */
public enum MinefieldSensorTypesMagnetic 
{
    /** Generic */
    GENERIC (0, "Generic"),
    /** AN-PSS-11 */
    AN_PSS_11 (1, "AN-PSS-11"),
    /** AN-PSS-12 */
    AN_PSS_12 (2, "AN-PSS-12"),
    /** GSTAMIDS */
    GSTAMIDS (3, "GSTAMIDS");

    private int value;
    private final String description;

    MinefieldSensorTypesMagnetic(int value, String description)
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
      return 12;
    }

    public static MinefieldSensorTypesMagnetic getEnumForValue(int i)
    {
       for(MinefieldSensorTypesMagnetic val: MinefieldSensorTypesMagnetic.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration MinefieldSensorTypesMagnetic");
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

    public static MinefieldSensorTypesMagnetic unmarshalEnum (DataInputStream dis) throws Exception
    {
        return getEnumForValue(dis.readUnsignedShort());
    } 

    public static MinefieldSensorTypesMagnetic unmarshalEnum (ByteBuffer buff) throws Exception
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
        return "MinefieldSensorTypesMagnetic: " + name() + ": " + getValue(); 
    }
}
