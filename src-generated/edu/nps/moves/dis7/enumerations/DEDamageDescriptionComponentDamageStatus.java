package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 315 marshal size 8<br>
 * _____________________________________________
 */
public enum DEDamageDescriptionComponentDamageStatus 
{
    /** No Damage */
    NO_DAMAGE (0, "No Damage"),
    /** Minor Damage */
    MINOR_DAMAGE (1, "Minor Damage"),
    /** Medium Damage */
    MEDIUM_DAMAGE (2, "Medium Damage"),
    /** Major Damage */
    MAJOR_DAMAGE (3, "Major Damage"),
    /** Destroyed */
    DESTROYED (4, "Destroyed");

    private int value;
    private final String description;

    DEDamageDescriptionComponentDamageStatus(int value, String description)
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
      return 8;
    }

    public static DEDamageDescriptionComponentDamageStatus getEnumForValue(int i)
    {
       for(DEDamageDescriptionComponentDamageStatus val: DEDamageDescriptionComponentDamageStatus.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration DEDamageDescriptionComponentDamageStatus");
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

    public static DEDamageDescriptionComponentDamageStatus unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static DEDamageDescriptionComponentDamageStatus unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "DEDamageDescriptionComponentDamageStatus: " + name() + ": " + getValue(); 
    }
}
