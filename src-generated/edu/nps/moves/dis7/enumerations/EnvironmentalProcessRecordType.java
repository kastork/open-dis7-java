package edu.nps.moves.dis7.enumerations;

import java.nio.ByteBuffer;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import edu.nps.moves.dis7.*;

/**
 * Generated from XML, SISO-REF-010-v28, 2020-05-07<br>
 * UID 250 marshal size 32<br>
 * _________________________________
 */
public enum EnvironmentalProcessRecordType 
{
    /** COMBIC State */
    COMBIC_STATE (256, "COMBIC State"),
    /** Flare State */
    FLARE_STATE (259, "Flare State"),
    /** Bounding Sphere Record */
    BOUNDING_SPHERE_RECORD (65536, "Bounding Sphere Record"),
    /** Uniform Geometry Record */
    UNIFORM_GEOMETRY_RECORD (327680, "Uniform Geometry Record"),
    /** Point Record 1 */
    POINT_RECORD_1 (655360, "Point Record 1"),
    /** Line Record 1 */
    LINE_RECORD_1 (786432, "Line Record 1"),
    /** Sphere Record 1 */
    SPHERE_RECORD_1 (851968, "Sphere Record 1"),
    /** Ellipsoid Record 1 */
    ELLIPSOID_RECORD_1 (1048576, "Ellipsoid Record 1"),
    /** Cone Record 1 */
    CONE_RECORD_1 (3145728, "Cone Record 1"),
    /** Rectangular Volume Record 1 */
    RECTANGULAR_VOLUME_RECORD_1 (5242880, "Rectangular Volume Record 1"),
    /** Rectangular Volume Record 3 */
    RECTANGULAR_VOLUME_RECORD_3 (83886080, "Rectangular Volume Record 3"),
    /** Point Record 2 */
    POINT_RECORD_2 (167772160, "Point Record 2"),
    /** Line Record 2 */
    LINE_RECORD_2 (201326592, "Line Record 2"),
    /** Sphere Record 2 */
    SPHERE_RECORD_2 (218103808, "Sphere Record 2"),
    /** Ellipsoid Record 2 */
    ELLIPSOID_RECORD_2 (268435456, "Ellipsoid Record 2"),
    /** Cone Record 2 */
    CONE_RECORD_2 (805306368, "Cone Record 2"),
    /** Rectangular Volume Record 2 */
    RECTANGULAR_VOLUME_RECORD_2 (1342177280, "Rectangular Volume Record 2"),
    /** Gaussian Plume Record */
    GAUSSIAN_PLUME_RECORD (1610612736, "Gaussian Plume Record"),
    /** Gaussian Puff Record */
    GAUSSIAN_PUFF_RECORD (1879048192, "Gaussian Puff Record");

    private int value;
    private final String description;

    EnvironmentalProcessRecordType(int value, String description)
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
      return 32;
    }

    public static EnvironmentalProcessRecordType getEnumForValue(int i)
    {
       for(EnvironmentalProcessRecordType val: EnvironmentalProcessRecordType.values()) {
          if(val.getValue()==i)
              return val;
       }
       System.err.println("No enumeration found for value " + i + " of enumeration EnvironmentalProcessRecordType");
       return null;
    }


    public void marshal(DataOutputStream dos) throws IOException
    {
        dos.writeInt(getValue());
    }

    public void marshal(ByteBuffer buff)
    {
        buff.putInt(getValue());
    }

    public static EnvironmentalProcessRecordType unmarshalEnum (DataInputStream dis) throws Exception
    {
        return getEnumForValue(dis.readInt());
    } 

    public static EnvironmentalProcessRecordType unmarshalEnum (ByteBuffer buff)
    {
        return getEnumForValue(buff.getInt());
    }

  /**
   * Returns size of this serialized object in bytes
   * @return size in bytes
   */
    public int getMarshalledSize()
    {
        return 4; // 32 bits
    }
    
    @Override
    public String toString()
    {
        return "EnvironmentalProcessRecordType: " + name() + ": " + getValue();
    }
}
