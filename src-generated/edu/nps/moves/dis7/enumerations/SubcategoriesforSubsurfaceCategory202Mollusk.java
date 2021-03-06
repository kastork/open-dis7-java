package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 122 marshal size 8<br>
 * ___________________________________________________
 */
public enum SubcategoriesforSubsurfaceCategory202Mollusk implements SubCategory
{
    /** Snail */
    SNAIL (1, "Snail"),
    /** Slug */
    SLUG (10, "Slug"),
    /** Octopus */
    OCTOPUS (20, "Octopus"),
    /** Squid */
    SQUID (30, "Squid"),
    /** Cuttlefish */
    CUTTLEFISH (40, "Cuttlefish"),
    /** Clam */
    CLAM (50, "Clam"),
    /** Muscle */
    MUSCLE (60, "Muscle"),
    /** Oyster */
    OYSTER (70, "Oyster"),
    /** Scallop */
    SCALLOP (80, "Scallop");

    private int value;
    private final String description;

    SubcategoriesforSubsurfaceCategory202Mollusk(int value, String description)
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

    public static SubcategoriesforSubsurfaceCategory202Mollusk getEnumForValue(int i)
    {
       for(SubcategoriesforSubsurfaceCategory202Mollusk val: SubcategoriesforSubsurfaceCategory202Mollusk.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration SubcategoriesforSubsurfaceCategory202Mollusk");
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

    public static SubcategoriesforSubsurfaceCategory202Mollusk unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static SubcategoriesforSubsurfaceCategory202Mollusk unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "SubcategoriesforSubsurfaceCategory202Mollusk: " + name() + ": " + getValue(); 
    }
}
