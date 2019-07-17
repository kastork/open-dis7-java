package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.dis.enumerations.*;


/**
 * 16 bit fixed binaries
 *
 * Copyright (c) 2008-2019, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class LiveDeadReckoningParameters extends Object implements Serializable
{
   /**  uid 44 */
   protected DeadReckoningAlgorithm deadReckoningAlgorithm = DeadReckoningAlgorithm.values()[0];

   protected LEVector3FixedByte  entityLinearAcceleration = new LEVector3FixedByte(); 

   protected LEVector3FixedByte  entityAngularVelocity = new LEVector3FixedByte(); 


/** Constructor */
 public LiveDeadReckoningParameters()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize += deadReckoningAlgorithm.getMarshalledSize();
   marshalSize += entityLinearAcceleration.getMarshalledSize();
   marshalSize += entityAngularVelocity.getMarshalledSize();

   return marshalSize;
}


public LiveDeadReckoningParameters setDeadReckoningAlgorithm(DeadReckoningAlgorithm pDeadReckoningAlgorithm)
{
    deadReckoningAlgorithm = pDeadReckoningAlgorithm;
    return this;
}

public DeadReckoningAlgorithm getDeadReckoningAlgorithm()
{
    return deadReckoningAlgorithm; 
}

public LiveDeadReckoningParameters setEntityLinearAcceleration(LEVector3FixedByte pEntityLinearAcceleration)
{
    entityLinearAcceleration = pEntityLinearAcceleration;
    return this;
}

public LEVector3FixedByte getEntityLinearAcceleration()
{
    return entityLinearAcceleration; 
}

public LiveDeadReckoningParameters setEntityAngularVelocity(LEVector3FixedByte pEntityAngularVelocity)
{
    entityAngularVelocity = pEntityAngularVelocity;
    return this;
}

public LEVector3FixedByte getEntityAngularVelocity()
{
    return entityAngularVelocity; 
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
       deadReckoningAlgorithm.marshal(dos);
       entityLinearAcceleration.marshal(dos);
       entityAngularVelocity.marshal(dos);
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
        deadReckoningAlgorithm = DeadReckoningAlgorithm.unmarshalEnum(dis);
        uPosition += deadReckoningAlgorithm.getMarshalledSize();
        uPosition += entityLinearAcceleration.unmarshal(dis);
        uPosition += entityAngularVelocity.unmarshal(dis);
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
   deadReckoningAlgorithm.marshal(buff);
   entityLinearAcceleration.marshal(buff);
   entityAngularVelocity.marshal(buff);
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
    deadReckoningAlgorithm = DeadReckoningAlgorithm.unmarshalEnum(buff);
    entityLinearAcceleration.unmarshal(buff);
    entityAngularVelocity.unmarshal(buff);
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

    if(!(obj instanceof LiveDeadReckoningParameters))
        return false;

     final LiveDeadReckoningParameters rhs = (LiveDeadReckoningParameters)obj;

     if( ! (deadReckoningAlgorithm == rhs.deadReckoningAlgorithm)) ivarsEqual = false;
     if( ! (entityLinearAcceleration.equals( rhs.entityLinearAcceleration) )) ivarsEqual = false;
     if( ! (entityAngularVelocity.equals( rhs.entityAngularVelocity) )) ivarsEqual = false;
    return ivarsEqual;
 }
} // end of class
