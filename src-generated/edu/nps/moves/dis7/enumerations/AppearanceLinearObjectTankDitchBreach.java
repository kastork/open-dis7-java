package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 409 marshal size 2<br>
 * __________________________________________
 */
public enum AppearanceLinearObjectTankDitchBreach 
{
    /** No Breaching */
    NO_BREACHING (0, "No Breaching"),
    /** Slight Breaching */
    SLIGHT_BREACHING (1, "Slight Breaching"),
    /** Moderate Breached */
    MODERATE_BREACHED (2, "Moderate Breached"),
    /** Cleared */
    CLEARED (3, "Cleared");

    private int value;
    private final String description;

    AppearanceLinearObjectTankDitchBreach(int value, String description)
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

    public static AppearanceLinearObjectTankDitchBreach getEnumForValue(int i)
    {
       for(AppearanceLinearObjectTankDitchBreach val: AppearanceLinearObjectTankDitchBreach.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration AppearanceLinearObjectTankDitchBreach");
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

    public static AppearanceLinearObjectTankDitchBreach unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static AppearanceLinearObjectTankDitchBreach unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "AppearanceLinearObjectTankDitchBreach: " + name() + ": " + getValue(); 
    }
}
