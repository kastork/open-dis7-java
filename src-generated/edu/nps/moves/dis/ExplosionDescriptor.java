/**
 * Copyright (c) 2008-2019, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 */

package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.dis.enumerations.*;

/**
 * Explosion of a non-munition. Section 6.2.19.3
 * IEEE Std 1278.1-2012, IEEE Standard for Distributed Interactive Simulation—Application Protocols
 */
public class ExplosionDescriptor extends Object implements Serializable
{
   /** Type of the object that exploded. See 6.2.30 */
   protected EntityType  explodingObject = new EntityType(); 

   /** Material that exploded. Can be grain dust, tnt, gasoline, etc. Enumeration uid 310 */
   protected ExplosiveMaterialCategories explosiveMaterial = ExplosiveMaterialCategories.values()[0];

   /** padding */
   protected short  padding = (short)0;

   /** Force of explosion, in equivalent KG of TNT */
   protected float  explosiveForce;


/** Constructor */
 public ExplosionDescriptor()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize += explodingObject.getMarshalledSize();
   marshalSize += explosiveMaterial.getMarshalledSize();
   marshalSize += 2;  // padding
   marshalSize += 4;  // explosiveForce

   return marshalSize;
}


public ExplosionDescriptor setExplodingObject(EntityType pExplodingObject)
{
    explodingObject = pExplodingObject;
    return this;
}

public EntityType getExplodingObject()
{
    return explodingObject; 
}

public ExplosionDescriptor setExplosiveMaterial(ExplosiveMaterialCategories pExplosiveMaterial)
{
    explosiveMaterial = pExplosiveMaterial;
    return this;
}

public ExplosiveMaterialCategories getExplosiveMaterial()
{
    return explosiveMaterial; 
}

public ExplosionDescriptor setPadding(short pPadding)
{
    padding = pPadding;
    return this;
}

public short getPadding()
{
    return padding; 
}

public ExplosionDescriptor setExplosiveForce(float pExplosiveForce)
{
    explosiveForce = pExplosiveForce;
    return this;
}

public float getExplosiveForce()
{
    return explosiveForce; 
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
       explodingObject.marshal(dos);
       explosiveMaterial.marshal(dos);
       dos.writeShort( (short)padding);
       dos.writeFloat( (float)explosiveForce);
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
        uPosition += explodingObject.unmarshal(dis);
        explosiveMaterial = ExplosiveMaterialCategories.unmarshalEnum(dis);
        uPosition += explosiveMaterial.getMarshalledSize();
        padding = (short)dis.readUnsignedShort();
        uPosition += 2;
        explosiveForce = dis.readFloat();
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
   explodingObject.marshal(buff);
   explosiveMaterial.marshal(buff);
   buff.putShort( (short)padding);
   buff.putFloat( (float)explosiveForce);
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
    explodingObject.unmarshal(buff);
    explosiveMaterial = ExplosiveMaterialCategories.unmarshalEnum(buff);
    padding = (short)(buff.getShort() & 0xFFFF);
    explosiveForce = buff.getFloat();
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

    if(!(obj instanceof ExplosionDescriptor))
        return false;

     final ExplosionDescriptor rhs = (ExplosionDescriptor)obj;

     if( ! (explodingObject.equals( rhs.explodingObject) )) ivarsEqual = false;
     if( ! (explosiveMaterial == rhs.explosiveMaterial)) ivarsEqual = false;
     if( ! (padding == rhs.padding)) ivarsEqual = false;
     if( ! (explosiveForce == rhs.explosiveForce)) ivarsEqual = false;
    return ivarsEqual;
 }
} // end of class