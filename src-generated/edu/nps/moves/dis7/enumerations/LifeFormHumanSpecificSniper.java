package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 481 marshal size 8<br>
 * _______________________________
 */
public enum LifeFormHumanSpecificSniper 
{
    /** Other */
    OTHER (0, "Other"),
    /** 5.8mm QBU-88 (Type 88) */
    NAME_58MM_QBU_88_TYPE_88 (1, "5.8mm QBU-88 (Type 88)"),
    /** 7.62mm C3 */
    NAME_762MM_C3 (30, "7.62mm C3"),
    /** 7.62mm FR F2 */
    NAME_762MM_FR_F2 (31, "7.62mm FR F2"),
    /** 7.62mm AWM-F (G22) */
    NAME_762MM_AWM_F_G22 (32, "7.62mm AWM-F (G22)"),
    /** 7.62mm G3 SG/1 */
    NAME_762MM_G3_SG_1 (33, "7.62mm G3 SG/1"),
    /** 7.62mm Galil Sniper */
    NAME_762MM_GALIL_SNIPER (34, "7.62mm Galil Sniper"),
    /** 7.62mm L96A1 */
    NAME_762MM_L96A1 (35, "7.62mm L96A1"),
    /** 7.62mm M14 DMR */
    NAME_762MM_M14_DMR (36, "7.62mm M14 DMR"),
    /** 7.62mm M24 Sniper Weapon System (SWS) */
    NAME_762MM_M24_SNIPER_WEAPON_SYSTEM_SWS (37, "7.62mm M24 Sniper Weapon System (SWS)"),
    /** 7.62mm M40A1/A3 */
    NAME_762MM_M40A1_A3 (38, "7.62mm M40A1/A3"),
    /** 7.62mm Steyr SSG 69 */
    NAME_762MM_STEYR_SSG_69 (39, "7.62mm Steyr SSG 69"),
    /** 7.62mm SVD (Dragunov) */
    NAME_762MM_SVD_DRAGUNOV (40, "7.62mm SVD (Dragunov)"),
    /** 7.62mm TYPE 79 */
    NAME_762MM_TYPE_79 (41, "7.62mm TYPE 79"),
    /** 7.62mm SR-25 MK11 */
    NAME_762MM_SR_25_MK11 (42, "7.62mm SR-25 MK11"),
    /** 7.62mm AW SR-98 */
    NAME_762MM_AW_SR_98 (43, "7.62mm AW SR-98"),
    /** 7.62mm Blaser R93 */
    NAME_762MM_BLASER_R93 (44, "7.62mm Blaser R93"),
    /** 7.7mm TYPE 99 */
    NAME_77MM_TYPE_99 (100, "7.7mm TYPE 99"),
    /** 8.58mm Blaser R93 Tactical 2 */
    NAME_858MM_BLASER_R93_TACTICAL_2 (105, "8.58mm Blaser R93 Tactical 2"),
    /** 9mm VSS Vintorez */
    NAME_9MM_VSS_VINTOREZ (110, "9mm VSS Vintorez"),
    /** 12.7mm Steyr HS .50 */
    NAME_127MM_STEYR_HS_50 (170, "12.7mm Steyr HS .50"),
    /** 12.7mm M82A1A Special Applications Scoped Rifle (SASR) */
    NAME_127MM_M82A1A_SPECIAL_APPLICATIONS_SCOPED_RIFLE_SASR (171, "12.7mm M82A1A Special Applications Scoped Rifle (SASR)"),
    /** 12.7mm NSV */
    NAME_127MM_NSV (172, "12.7mm NSV"),
    /** 12.7mm OSV-96 */
    NAME_127MM_OSV_96 (173, "12.7mm OSV-96"),
    /** 12.7mm Rangemaster 50 */
    NAME_127MM_RANGEMASTER_50 (174, "12.7mm Rangemaster 50"),
    /** 12.7mm V94 */
    NAME_127MM_V94 (175, "12.7mm V94"),
    /** 20mm Denel NTW-20 */
    NAME_20MM_DENEL_NTW_20 (200, "20mm Denel NTW-20");

    private int value;
    private final String description;

    LifeFormHumanSpecificSniper(int value, String description)
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

    public static LifeFormHumanSpecificSniper getEnumForValue(int i)
    {
       for(LifeFormHumanSpecificSniper val: LifeFormHumanSpecificSniper.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration LifeFormHumanSpecificSniper");
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

    public static LifeFormHumanSpecificSniper unmarshalEnum (DataInputStream dis) throws Exception
    {
       /* try {
            value = dis.readUnsignedByte();
        }
        catch(IOException ex) {
            showError(ex);
        } */
        
        return getEnumForValue(dis.readByte());
    } 

    public static LifeFormHumanSpecificSniper unmarshalEnum(ByteBuffer buff) throws Exception
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
        return "LifeFormHumanSpecificSniper: " + name() + ": " + getValue(); 
    }
}
