package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 96 marshal size 8<br>
 * ____________________________________
 */
public enum IFFAlternateMode4ChallengeReply 
{
    /** No Statement */
    NO_STATEMENT (0, "No Statement"),
    /** Valid */
    VALID (1, "Valid"),
    /** Invalid */
    INVALID (2, "Invalid"),
    /** No response */
    NO_RESPONSE (3, "No response"),
    /** Unable to Verify */
    UNABLE_TO_VERIFY (4, "Unable to Verify");

    private int value;
    private final String description;

    IFFAlternateMode4ChallengeReply(int value, String description)
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

    public static IFFAlternateMode4ChallengeReply getEnumForValue(int i)
    {
       for(IFFAlternateMode4ChallengeReply val: IFFAlternateMode4ChallengeReply.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration IFFAlternateMode4ChallengeReply");
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

    public static IFFAlternateMode4ChallengeReply unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static IFFAlternateMode4ChallengeReply unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "IFFAlternateMode4ChallengeReply: " + name() + ": " + getValue(); 
    }
}
