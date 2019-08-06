package edu.nps.moves.dis.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis.*;

/**
 * Generated from XML, SISO-REF-010-v25, 2018-08-29<br>
 * UID 20 marshal size 8<br>
 * Life Forms-Subcategory-German Weapons
 */
public enum LifeFormsSubcategoryGermanWeapons implements SubCategory
{
    /** G3 rifle */
    G3_RIFLE (1, "G3 rifle"),
    /** G11 rifle */
    G11_RIFLE (2, "G11 rifle"),
    /** P1 pistol */
    P1_PISTOL (3, "P1 pistol"),
    /** MG3 machine gun */
    MG3_MACHINE_GUN (4, "MG3 machine gun"),
    /** Milan missile */
    MILAN_MISSILE (5, "Milan missile"),
    /** MP1 Uzi submachine gun */
    MP1_UZI_SUBMACHINE_GUN (6, "MP1 Uzi submachine gun"),
    /** Panzerfaust 3 Light Anti-Tank Weapon */
    PANZERFAUST_3_LIGHT_ANTI_TANK_WEAPON (7, "Panzerfaust 3 Light Anti-Tank Weapon"),
    /** DM19 hand grenade */
    DM19_HAND_GRENADE (8, "DM19 hand grenade"),
    /** DM29 hand grenade */
    DM29_HAND_GRENADE (9, "DM29 hand grenade");

    private int value;
    private final String description;

    LifeFormsSubcategoryGermanWeapons(int value, String description)
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

    public static LifeFormsSubcategoryGermanWeapons getEnumForValue(int i)
    {
       for(LifeFormsSubcategoryGermanWeapons val: LifeFormsSubcategoryGermanWeapons.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration LifeFormsSubcategoryGermanWeapons");
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

    public static LifeFormsSubcategoryGermanWeapons unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue((int)dis.readByte());
    } 

    public static LifeFormsSubcategoryGermanWeapons unmarshalEnum(ByteBuffer buff) throws Exception
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