/**
 * Copyright (c) 2008-2019, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 */

package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.dis.enumerations.*;

/**
 * The Blanking Sector attribute record may be used to convey persistent areas within a scan volume where emitter power for a specific active emitter beam is reduced to an insignificant value. Section 6.2.21.2
 * IEEE Std 1278.1-2012, IEEE Standard for Distributed Interactive Simulation—Application Protocols
 */
public class BlankingSector extends Object implements Serializable
{
   protected int  recordType = (int)3500;

   protected short  recordLength = (short)40;

   protected short  padding = (short)0;

   protected byte  emitterNumber;

   protected byte  beamNumber;

   /**  uid 300 */
   protected EEAttributeStateIndicator stateIndicator = EEAttributeStateIndicator.values()[0];

   protected byte  padding2 = (byte)0;

   protected float  leftAzimuth;

   protected float  rightAzimuth;

   protected float  lowerElevation;

   protected float  upperElevation;

   protected float  residualPower;

   protected long  padding3 = (long)0;


/** Constructor */
 public BlankingSector()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize += 4;  // recordType
   marshalSize += 2;  // recordLength
   marshalSize += 2;  // padding
   marshalSize += 1;  // emitterNumber
   marshalSize += 1;  // beamNumber
   marshalSize += stateIndicator.getMarshalledSize();
   marshalSize += 1;  // padding2
   marshalSize += 4;  // leftAzimuth
   marshalSize += 4;  // rightAzimuth
   marshalSize += 4;  // lowerElevation
   marshalSize += 4;  // upperElevation
   marshalSize += 4;  // residualPower
   marshalSize += 8;  // padding3

   return marshalSize;
}


public BlankingSector setRecordType(int pRecordType)
{
    recordType = pRecordType;
    return this;
}

public int getRecordType()
{
    return recordType; 
}

public BlankingSector setRecordLength(short pRecordLength)
{
    recordLength = pRecordLength;
    return this;
}

public short getRecordLength()
{
    return recordLength; 
}

public BlankingSector setPadding(short pPadding)
{
    padding = pPadding;
    return this;
}

public short getPadding()
{
    return padding; 
}

public BlankingSector setEmitterNumber(byte pEmitterNumber)
{
    emitterNumber = pEmitterNumber;
    return this;
}

public byte getEmitterNumber()
{
    return emitterNumber; 
}

public BlankingSector setBeamNumber(byte pBeamNumber)
{
    beamNumber = pBeamNumber;
    return this;
}

public byte getBeamNumber()
{
    return beamNumber; 
}

public BlankingSector setStateIndicator(EEAttributeStateIndicator pStateIndicator)
{
    stateIndicator = pStateIndicator;
    return this;
}

public EEAttributeStateIndicator getStateIndicator()
{
    return stateIndicator; 
}

public BlankingSector setPadding2(byte pPadding2)
{
    padding2 = pPadding2;
    return this;
}

public byte getPadding2()
{
    return padding2; 
}

public BlankingSector setLeftAzimuth(float pLeftAzimuth)
{
    leftAzimuth = pLeftAzimuth;
    return this;
}

public float getLeftAzimuth()
{
    return leftAzimuth; 
}

public BlankingSector setRightAzimuth(float pRightAzimuth)
{
    rightAzimuth = pRightAzimuth;
    return this;
}

public float getRightAzimuth()
{
    return rightAzimuth; 
}

public BlankingSector setLowerElevation(float pLowerElevation)
{
    lowerElevation = pLowerElevation;
    return this;
}

public float getLowerElevation()
{
    return lowerElevation; 
}

public BlankingSector setUpperElevation(float pUpperElevation)
{
    upperElevation = pUpperElevation;
    return this;
}

public float getUpperElevation()
{
    return upperElevation; 
}

public BlankingSector setResidualPower(float pResidualPower)
{
    residualPower = pResidualPower;
    return this;
}

public float getResidualPower()
{
    return residualPower; 
}

public BlankingSector setPadding3(long pPadding3)
{
    padding3 = pPadding3;
    return this;
}

public long getPadding3()
{
    return padding3; 
}

/**
 * Serializes an object to a DataOutputStream.
 * @see java.io.DataOutputStream
 * @param dos The DataOutputStream
 */
public void marshal(DataOutputStream dos)
{
    try 
    {
       dos.writeInt( (int)recordType);
       dos.writeShort( (short)recordLength);
       dos.writeShort( (short)padding);
       dos.writeByte( (byte)emitterNumber);
       dos.writeByte( (byte)beamNumber);
       stateIndicator.marshal(dos);
       dos.writeByte( (byte)padding2);
       dos.writeFloat( (float)leftAzimuth);
       dos.writeFloat( (float)rightAzimuth);
       dos.writeFloat( (float)lowerElevation);
       dos.writeFloat( (float)upperElevation);
       dos.writeFloat( (float)residualPower);
       dos.writeLong( (long)padding3);
    }
    catch(Exception e)
    {
      System.err.println(e);
    }
}

/**
 * Unserializes an object from a DataInputStream.
 * @see java.io.DataInputStream
 * @param dis The DataInputStream
 * @return marshalled size
 */
public int unmarshal(DataInputStream dis)
{
    int uPosition = 0;
    try 
    {
        recordType = dis.readInt();
        uPosition += 4;
        recordLength = (short)dis.readUnsignedShort();
        uPosition += 2;
        padding = (short)dis.readUnsignedShort();
        uPosition += 2;
        emitterNumber = (byte)dis.readUnsignedByte();
        uPosition += 1;
        beamNumber = (byte)dis.readUnsignedByte();
        uPosition += 1;
        stateIndicator = EEAttributeStateIndicator.unmarshalEnum(dis);
        uPosition += stateIndicator.getMarshalledSize();
        padding2 = (byte)dis.readUnsignedByte();
        uPosition += 1;
        leftAzimuth = dis.readFloat();
        uPosition += 4;
        rightAzimuth = dis.readFloat();
        uPosition += 4;
        lowerElevation = dis.readFloat();
        uPosition += 4;
        upperElevation = dis.readFloat();
        uPosition += 4;
        residualPower = dis.readFloat();
        uPosition += 4;
        padding3 = dis.readLong();
        uPosition += 4;
    }
    catch(Exception e)
    { 
      System.out.println(e); 
    }
    return getMarshalledSize();
}

/**
 * Packs an object into the ByteBuffer.
 * @throws java.nio.BufferOverflowException if buff is too small
 * @throws java.nio.ReadOnlyBufferException if buff is read only
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin writing
 * @throws Exception ByteBuffer-generated exception
 */
public void marshal(java.nio.ByteBuffer buff) throws Exception
{
   buff.putInt( (int)recordType);
   buff.putShort( (short)recordLength);
   buff.putShort( (short)padding);
   buff.put( (byte)emitterNumber);
   buff.put( (byte)beamNumber);
   stateIndicator.marshal(buff);
   buff.put( (byte)padding2);
   buff.putFloat( (float)leftAzimuth);
   buff.putFloat( (float)rightAzimuth);
   buff.putFloat( (float)lowerElevation);
   buff.putFloat( (float)upperElevation);
   buff.putFloat( (float)residualPower);
   buff.putLong( (long)padding3);
}

/**
 * Unpacks a Pdu from the underlying data.
 * @throws java.nio.BufferUnderflowException if buff is too small
 * @see java.nio.ByteBuffer
 * @param buff The ByteBuffer at the position to begin reading
 * @return marshalled size
 * @throws Exception ByteBuffer-generated exception
 */
public int unmarshal(java.nio.ByteBuffer buff) throws Exception
{
    recordType = buff.getInt();
    recordLength = (short)(buff.getShort() & 0xFFFF);
    padding = (short)(buff.getShort() & 0xFFFF);
    emitterNumber = (byte)(buff.get() & 0xFF);
    beamNumber = (byte)(buff.get() & 0xFF);
    stateIndicator = EEAttributeStateIndicator.unmarshalEnum(buff);
    padding2 = (byte)(buff.get() & 0xFF);
    leftAzimuth = buff.getFloat();
    rightAzimuth = buff.getFloat();
    lowerElevation = buff.getFloat();
    upperElevation = buff.getFloat();
    residualPower = buff.getFloat();
    padding3 = buff.getLong();
    return getMarshalledSize();
}

 /*
  * The equals method doesn't always work--mostly it works only on classes that consist only of primitives. Be careful.
  */
@Override
 public boolean equals(Object obj)
 {
    if(this == obj)
      return true;

    if(obj == null)
       return false;

    if(getClass() != obj.getClass())
        return false;

    return equalsImpl(obj);
 }

 /**
  * Compare all fields that contribute to the state, ignoring
  * transient and static fields, for <code>this</code> and the supplied object
  * @param obj the object to compare to
  * @return true if the objects are equal, false otherwise.
  */
 public boolean equalsImpl(Object obj)
 {
     boolean ivarsEqual = true;

    if(!(obj instanceof BlankingSector))
        return false;

     final BlankingSector rhs = (BlankingSector)obj;

     if( ! (recordType == rhs.recordType)) ivarsEqual = false;
     if( ! (recordLength == rhs.recordLength)) ivarsEqual = false;
     if( ! (padding == rhs.padding)) ivarsEqual = false;
     if( ! (emitterNumber == rhs.emitterNumber)) ivarsEqual = false;
     if( ! (beamNumber == rhs.beamNumber)) ivarsEqual = false;
     if( ! (stateIndicator == rhs.stateIndicator)) ivarsEqual = false;
     if( ! (padding2 == rhs.padding2)) ivarsEqual = false;
     if( ! (leftAzimuth == rhs.leftAzimuth)) ivarsEqual = false;
     if( ! (rightAzimuth == rhs.rightAzimuth)) ivarsEqual = false;
     if( ! (lowerElevation == rhs.lowerElevation)) ivarsEqual = false;
     if( ! (upperElevation == rhs.upperElevation)) ivarsEqual = false;
     if( ! (residualPower == rhs.residualPower)) ivarsEqual = false;
     if( ! (padding3 == rhs.padding3)) ivarsEqual = false;
    return ivarsEqual;
 }
} // end of class