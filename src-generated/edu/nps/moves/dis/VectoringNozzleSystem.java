package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.dis.enumerations.*;


/**
 * Operational data for describing the vectoring nozzle systems Section 6.2.96
 *
 * Copyright (c) 2008-2019, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class VectoringNozzleSystem extends Object implements Serializable
{
   /** In degrees */
   protected float  horizontalDeflectionAngle;

   /** In degrees */
   protected float  verticalDeflectionAngle;


/** Constructor */
 public VectoringNozzleSystem()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize += 4;  // horizontalDeflectionAngle
   marshalSize += 4;  // verticalDeflectionAngle

   return marshalSize;
}


public VectoringNozzleSystem setHorizontalDeflectionAngle(float pHorizontalDeflectionAngle)
{
    horizontalDeflectionAngle = pHorizontalDeflectionAngle;
    return this;
}

public float getHorizontalDeflectionAngle()
{
    return horizontalDeflectionAngle; 
}

public VectoringNozzleSystem setVerticalDeflectionAngle(float pVerticalDeflectionAngle)
{
    verticalDeflectionAngle = pVerticalDeflectionAngle;
    return this;
}

public float getVerticalDeflectionAngle()
{
    return verticalDeflectionAngle; 
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
       dos.writeFloat( (float)horizontalDeflectionAngle);
       dos.writeFloat( (float)verticalDeflectionAngle);
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
        horizontalDeflectionAngle = dis.readFloat();
        uPosition += 4;
        verticalDeflectionAngle = dis.readFloat();
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
   buff.putFloat( (float)horizontalDeflectionAngle);
   buff.putFloat( (float)verticalDeflectionAngle);
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
    horizontalDeflectionAngle = buff.getFloat();
    verticalDeflectionAngle = buff.getFloat();
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

    if(!(obj instanceof VectoringNozzleSystem))
        return false;

     final VectoringNozzleSystem rhs = (VectoringNozzleSystem)obj;

     if( ! (horizontalDeflectionAngle == rhs.horizontalDeflectionAngle)) ivarsEqual = false;
     if( ! (verticalDeflectionAngle == rhs.verticalDeflectionAngle)) ivarsEqual = false;
    return ivarsEqual;
 }
} // end of class
