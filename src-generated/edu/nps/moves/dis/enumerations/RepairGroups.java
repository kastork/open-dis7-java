package edu.nps.moves.dis.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis.*;

/**
 * Generated from XML, SISO-REF-010-v25, 2018-08-29<br>
 * UID 272 marshal size 8<br>
 * Repair Groups
 */
public enum RepairGroups 
{
    /** General Repair Codes */
    GENERAL_REPAIR_CODES (0, "General Repair Codes"),
    /** Drive Train */
    DRIVE_TRAIN (1, "Drive Train"),
    /** Hull/Airframe/Body */
    HULL_AIRFRAME_BODY (2, "Hull/Airframe/Body"),
    /** Interfaces with Environment */
    INTERFACES_WITH_ENVIRONMENT (3, "Interfaces with Environment"),
    /** Weapons */
    WEAPONS (4, "Weapons"),
    /** Fuel Systems */
    FUEL_SYSTEMS (5, "Fuel Systems"),
    /** Electronics */
    ELECTRONICS (6, "Electronics"),
    /** Life Support Systems */
    LIFE_SUPPORT_SYSTEMS (7, "Life Support Systems"),
    /** Hydraulic Systems and Actuators */
    HYDRAULIC_SYSTEMS_AND_ACTUATORS (8, "Hydraulic Systems and Actuators"),
    /** Auxiliary Craft */
    AUXILIARY_CRAFT (9, "Auxiliary Craft");

    private int value;
    private final String description;

    RepairGroups(int value, String description)
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

    public static RepairGroups getEnumForValue(int i)
    {
       for(RepairGroups val: RepairGroups.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration RepairGroups");
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

    public static RepairGroups unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue((int)dis.readByte());
    } 

    public static RepairGroups unmarshalEnum(ByteBuffer buff) throws Exception
    {
        /*
        try {
            value = (int)buff.get();
        }
        catch(Exception ex) {
            showError(ex);
        }
        */
        return getEnumForValue((int)buff.get());
    }

    public int getMarshalledSize()
    {
        return 1; // 8 bits
    }
}