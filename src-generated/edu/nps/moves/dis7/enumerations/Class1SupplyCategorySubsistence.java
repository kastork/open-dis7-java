package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 601 marshal size 8<br>
 * _____________________________________
 */
public enum Class1SupplyCategorySubsistence implements Category
{
    /** Other */
    OTHER (1, "Other"),
    /** A - Non-Perishable */
    A_NON_PERISHABLE (2, "A - Non-Perishable"),
    /** C - Combat Rations */
    C_COMBAT_RATIONS (3, "C - Combat Rations"),
    /** R - Refrigerated */
    R_REFRIGERATED (4, "R - Refrigerated"),
    /** S - Other Non-Refrigerated */
    S_OTHER_NON_REFRIGERATED (5, "S - Other Non-Refrigerated"),
    /** W - Water */
    W_WATER (6, "W - Water");

    private int value;
    private final String description;

    Class1SupplyCategorySubsistence(int value, String description)
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

    public static Class1SupplyCategorySubsistence getEnumForValue(int i)
    {
       for(Class1SupplyCategorySubsistence val: Class1SupplyCategorySubsistence.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration Class1SupplyCategorySubsistence");
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

    public static Class1SupplyCategorySubsistence unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static Class1SupplyCategorySubsistence unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "Class1SupplyCategorySubsistence: " + name() + ": " + getValue(); 
    }
}
