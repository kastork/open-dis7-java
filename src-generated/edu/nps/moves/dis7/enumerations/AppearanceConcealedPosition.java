package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 385 marshal size 1<br>
 * _____________________________
 */
public enum AppearanceConcealedPosition 
{
    /** Not Concealed */
    NOT_CONCEALED (0, "Not Concealed"),
    /** Prepared Concealed Position */
    PREPARED_CONCEALED_POSITION (1, "Prepared Concealed Position");

    private int value;
    private final String description;

    AppearanceConcealedPosition(int value, String description)
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
      return 1;
    }

    public static AppearanceConcealedPosition getEnumForValue(int i)
    {
       for(AppearanceConcealedPosition val: AppearanceConcealedPosition.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration AppearanceConcealedPosition");
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

    public static AppearanceConcealedPosition unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static AppearanceConcealedPosition unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "AppearanceConcealedPosition: " + name() + ": " + getValue(); 
    }
}
