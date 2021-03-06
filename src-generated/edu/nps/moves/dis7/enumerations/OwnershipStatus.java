package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 332 marshal size 8<br>
 * ________________
 */
public enum OwnershipStatus 
{
    /** Other */
    OTHER (0, "Other"),
    /** New Owner */
    NEW_OWNER (1, "New Owner"),
    /** Ownership Query Response */
    OWNERSHIP_QUERY_RESPONSE (2, "Ownership Query Response"),
    /** Ownership Conflict */
    OWNERSHIP_CONFLICT (3, "Ownership Conflict"),
    /** Local Entity Cancelled - Auto Resolve Conflict */
    LOCAL_ENTITY_CANCELLED_AUTO_RESOLVE_CONFLICT (4, "Local Entity Cancelled - Auto Resolve Conflict"),
    /** Local Entity Cancelled - Manual Resolve Conflict */
    LOCAL_ENTITY_CANCELLED_MANUAL_RESOLVE_CONFLICT (5, "Local Entity Cancelled - Manual Resolve Conflict"),
    /** Local Entity Cancelled - Remove Entity TCR Received */
    LOCAL_ENTITY_CANCELLED_REMOVE_ENTITY_TCR_RECEIVED (6, "Local Entity Cancelled - Remove Entity TCR Received");

    private int value;
    private final String description;

    OwnershipStatus(int value, String description)
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

    public static OwnershipStatus getEnumForValue(int i)
    {
       for(OwnershipStatus val: OwnershipStatus.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration OwnershipStatus");
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

    public static OwnershipStatus unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static OwnershipStatus unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "OwnershipStatus: " + name() + ": " + getValue(); 
    }
}
