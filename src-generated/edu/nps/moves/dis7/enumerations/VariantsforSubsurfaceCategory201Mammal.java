package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 139 marshal size 8<br>
 * _____________________________________________
 */
public enum VariantsforSubsurfaceCategory201Mammal 
{
    /** Singing */
    SINGING (1, "Singing"),
    /** Spouting */
    SPOUTING (2, "Spouting");

    private int value;
    private final String description;

    VariantsforSubsurfaceCategory201Mammal(int value, String description)
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

    public static VariantsforSubsurfaceCategory201Mammal getEnumForValue(int i)
    {
       for(VariantsforSubsurfaceCategory201Mammal val: VariantsforSubsurfaceCategory201Mammal.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration VariantsforSubsurfaceCategory201Mammal");
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

    public static VariantsforSubsurfaceCategory201Mammal unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static VariantsforSubsurfaceCategory201Mammal unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "VariantsforSubsurfaceCategory201Mammal: " + name() + ": " + getValue(); 
    }
}
