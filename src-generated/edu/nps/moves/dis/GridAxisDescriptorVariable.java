/**
 * Copyright (c) 2008-2019, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 */

package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.dis.enumerations.*;

/**
 * Grid axis descriptor fo variable spacing axis data.
 * IEEE Std 1278.1-2012, IEEE Standard for Distributed Interactive Simulation—Application Protocols
 */
public class GridAxisDescriptorVariable extends GridAxisDescriptor implements Serializable
{
   /** Number of grid locations along Xi axis */
   protected short  numberOfPointsOnXiAxis;

   /** initial grid point for the current pdu */
   protected short  initialIndex;

   /** value that linearly scales the coordinates of the grid locations for the xi axis */
   protected double  coordinateScaleXi;

   /** The constant offset value that shall be applied to the grid locations for the xi axis */
   protected double  coordinateOffsetXi = (double)0.0;

   /** list of coordinates */
   protected short[]  xiValues = new short[0]; 

   private byte[] padding = new byte[0]; // pad to 64-bit boundary


/** Constructor */
 public GridAxisDescriptorVariable()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize = super.getMarshalledSize();
   marshalSize += 2;  // numberOfPointsOnXiAxis
   marshalSize += 2;  // initialIndex
   marshalSize += 8;  // coordinateScaleXi
   marshalSize += 8;  // coordinateOffsetXi
   marshalSize += xiValues.length * 2;
   marshalSize += padding.length;

   return marshalSize;
}


public GridAxisDescriptorVariable setNumberOfPointsOnXiAxis(short pNumberOfPointsOnXiAxis)
{
    numberOfPointsOnXiAxis = pNumberOfPointsOnXiAxis;
    return this;
}

public short getNumberOfPointsOnXiAxis()
{
    return numberOfPointsOnXiAxis; 
}

public GridAxisDescriptorVariable setInitialIndex(short pInitialIndex)
{
    initialIndex = pInitialIndex;
    return this;
}

public short getInitialIndex()
{
    return initialIndex; 
}

public GridAxisDescriptorVariable setCoordinateScaleXi(double pCoordinateScaleXi)
{
    coordinateScaleXi = pCoordinateScaleXi;
    return this;
}

public double getCoordinateScaleXi()
{
    return coordinateScaleXi; 
}

public GridAxisDescriptorVariable setCoordinateOffsetXi(double pCoordinateOffsetXi)
{
    coordinateOffsetXi = pCoordinateOffsetXi;
    return this;
}

public double getCoordinateOffsetXi()
{
    return coordinateOffsetXi; 
}

public GridAxisDescriptorVariable setXiValues(short[] pXiValues)
{
    xiValues = pXiValues;
    return this;
}

public short[] getXiValues()
{
    return xiValues; 
}

/**
 * Serializes an object to a DataOutputStream.
 * @see java.io.DataOutputStream
 * @param dos The DataOutputStream
 */
public void marshal(DataOutputStream dos)
{
    super.marshal(dos);
    try 
    {
       dos.writeShort( (short)numberOfPointsOnXiAxis);
       dos.writeShort( (short)initialIndex);
       dos.writeDouble( (double)coordinateScaleXi);
       dos.writeDouble( (double)coordinateOffsetXi);

       for(int idx = 0; idx < xiValues.length; idx++)
           dos.writeShort(xiValues[idx]);

       padding = new byte[Align.to64bits(dos)];
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
    uPosition += super.unmarshal(dis);

    try 
    {
        numberOfPointsOnXiAxis = (short)dis.readUnsignedShort();
        uPosition += 2;
        initialIndex = (short)dis.readUnsignedShort();
        uPosition += 2;
        coordinateScaleXi = dis.readDouble();
        uPosition += 4;
        coordinateOffsetXi = dis.readDouble();
        uPosition += 4;
        for(int idx = 0; idx < xiValues.length; idx++)
            xiValues[idx] = dis.readShort(); // mike check
        uPosition += xiValues.length; // todo, multiply by prim size mike
        padding = new byte[Align.from64bits(uPosition,dis)];
        uPosition += padding.length;
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
   super.marshal(buff);
   buff.putShort( (short)numberOfPointsOnXiAxis);
   buff.putShort( (short)initialIndex);
   buff.putDouble( (double)coordinateScaleXi);
   buff.putDouble( (double)coordinateOffsetXi);

   for(int idx = 0; idx < xiValues.length; idx++)
       buff.putShort((short)xiValues[idx]);

   padding = new byte[Align.to64bits(buff)];
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
    super.unmarshal(buff);

    numberOfPointsOnXiAxis = (short)(buff.getShort() & 0xFFFF);
    initialIndex = (short)(buff.getShort() & 0xFFFF);
    coordinateScaleXi = buff.getDouble();
    coordinateOffsetXi = buff.getDouble();
    for(int idx = 0; idx < xiValues.length; idx++)
        xiValues[idx] = buff.getShort();
    padding = new byte[Align.from64bits(buff)];
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

@Override
 public boolean equalsImpl(Object obj)
 {
     boolean ivarsEqual = true;

    if(!(obj instanceof GridAxisDescriptorVariable))
        return false;

     final GridAxisDescriptorVariable rhs = (GridAxisDescriptorVariable)obj;

     if( ! (numberOfPointsOnXiAxis == rhs.numberOfPointsOnXiAxis)) ivarsEqual = false;
     if( ! (initialIndex == rhs.initialIndex)) ivarsEqual = false;
     if( ! (coordinateScaleXi == rhs.coordinateScaleXi)) ivarsEqual = false;
     if( ! (coordinateOffsetXi == rhs.coordinateOffsetXi)) ivarsEqual = false;

     for(int idx = 0; idx < 0; idx++)
     {
          if(!(xiValues[idx] == rhs.xiValues[idx])) ivarsEqual = false;
     }

    return ivarsEqual && super.equalsImpl(rhs);
 }
} // end of class