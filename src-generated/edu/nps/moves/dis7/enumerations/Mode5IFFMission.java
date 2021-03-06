package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 346 marshal size 3<br>
 * __________________
 */
public enum Mode5IFFMission 
{
    /** No Statement */
    NO_STATEMENT (0, "No Statement"),
    /** Surveillance SHORAD */
    SURVEILLANCE_SHORAD (1, "Surveillance SHORAD"),
    /** SHORAD associated with a Weapons System */
    SHORAD_ASSOCIATED_WITH_A_WEAPONS_SYSTEM (2, "SHORAD associated with a Weapons System"),
    /** Weapon System */
    WEAPON_SYSTEM (3, "Weapon System"),
    /** Airborne and Surface (Ship) Surveillance Platforms */
    AIRBORNE_AND_SURFACE_SHIP_SURVEILLANCE_PLATFORMS (4, "Airborne and Surface (Ship) Surveillance Platforms"),
    /** Airborne and Surface (Ship) Weapons Platforms */
    AIRBORNE_AND_SURFACE_SHIP_WEAPONS_PLATFORMS (5, "Airborne and Surface (Ship) Weapons Platforms"),
    /** Ground-to-Ground */
    GROUND_TO_GROUND (6, "Ground-to-Ground");

    private int value;
    private final String description;

    Mode5IFFMission(int value, String description)
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
      return 3;
    }

    public static Mode5IFFMission getEnumForValue(int i)
    {
       for(Mode5IFFMission val: Mode5IFFMission.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration Mode5IFFMission");
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

    public static Mode5IFFMission unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static Mode5IFFMission unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "Mode5IFFMission: " + name() + ": " + getValue(); 
    }
}
