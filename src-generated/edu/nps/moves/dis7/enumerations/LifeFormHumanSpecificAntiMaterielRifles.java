package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 510 marshal size 8<br>
 * _____________________________________________
 */
public enum LifeFormHumanSpecificAntiMaterielRifles 
{
    /** Other */
    OTHER (0, "Other"),
    /** 12.7mm AW50 */
    NAME_127MM_AW50 (10, "12.7mm AW50"),
    /** 12.7mm AW50F */
    NAME_127MM_AW50F (11, "12.7mm AW50F");

    private int value;
    private final String description;

    LifeFormHumanSpecificAntiMaterielRifles(int value, String description)
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

    public static LifeFormHumanSpecificAntiMaterielRifles getEnumForValue(int i)
    {
       for(LifeFormHumanSpecificAntiMaterielRifles val: LifeFormHumanSpecificAntiMaterielRifles.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration LifeFormHumanSpecificAntiMaterielRifles");
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

    public static LifeFormHumanSpecificAntiMaterielRifles unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static LifeFormHumanSpecificAntiMaterielRifles unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "LifeFormHumanSpecificAntiMaterielRifles: " + name() + ": " + getValue(); 
    }
}
