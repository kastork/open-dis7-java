package edu.nps.moves.dis;

import java.util.*;
import java.io.*;
import edu.nps.moves.dis.enumerations.*;


/**
 * Does not work, and causes failure in anything it is embedded in. Section 6.2.83
 *
 * Copyright (c) 2008-2019, MOVES Institute, Naval Postgraduate School. All rights reserved.
 * This work is licensed under the BSD open source license, available at https://www.movesinstitute.org/licenses/bsd.html
 *
 * @author DMcG
 */
public class StandardVariableSpecification extends Object implements Serializable
{
   /** Number of static variable records */
   protected short  numberOfStandardVariableRecords;

   /** variable length list of standard variables, The class type and length here are WRONG and will cause the incorrect serialization of any class in whihc it is embedded. */
   protected List< StandardVariableRecord > standardVariables = new ArrayList< StandardVariableRecord >();
 

/** Constructor */
 public StandardVariableSpecification()
 {
 }

public int getMarshalledSize()
{
   int marshalSize = 0; 

   marshalSize += 2;  // numberOfStandardVariableRecords
   for(int idx=0; idx < standardVariables.size(); idx++)
   {
        StandardVariableRecord listElement = standardVariables.get(idx);
        marshalSize += listElement.getMarshalledSize();
   }

   return marshalSize;
}


public short getNumberOfStandardVariableRecords()
{
    return (short)standardVariables.size(); 
}

/* Note that setting this value will not change the marshalled value. The list whose length this describes is used for that purpose.
 * The getnumberOfStandardVariableRecords method will also be based on the actual list length rather than this value. 
 * The method is simply here for java bean completeness.
 */
public StandardVariableSpecification setNumberOfStandardVariableRecords(short pNumberOfStandardVariableRecords)
{
    numberOfStandardVariableRecords = pNumberOfStandardVariableRecords;
    return this;
}

public StandardVariableSpecification setStandardVariables(List<StandardVariableRecord> pStandardVariables)
{
    standardVariables = pStandardVariables;
    return this;
}

public List<StandardVariableRecord> getStandardVariables()
{
    return standardVariables; 
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
       dos.writeShort( (short)standardVariables.size());

       for(int idx = 0; idx < standardVariables.size(); idx++)
       {
            StandardVariableRecord aStandardVariableRecord = standardVariables.get(idx);
            aStandardVariableRecord.marshal(dos);
       }

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
        numberOfStandardVariableRecords = (short)dis.readUnsignedShort();
        uPosition += 2;
        for(int idx = 0; idx < numberOfStandardVariableRecords; idx++)
        {
            StandardVariableRecord anX = new StandardVariableRecord();
            uPosition += anX.unmarshal(dis);
            standardVariables.add(anX);
        }

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
   buff.putShort( (short)standardVariables.size());

   for(int idx = 0; idx < standardVariables.size(); idx++)
   {
        StandardVariableRecord aStandardVariableRecord = (StandardVariableRecord)standardVariables.get(idx);
        aStandardVariableRecord.marshal(buff);
   }

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
    numberOfStandardVariableRecords = (short)(buff.getShort() & 0xFFFF);
    for(int idx = 0; idx < numberOfStandardVariableRecords; idx++)
    {
    StandardVariableRecord anX = new StandardVariableRecord();
    anX.unmarshal(buff);
    standardVariables.add(anX);
    }

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

    if(!(obj instanceof StandardVariableSpecification))
        return false;

     final StandardVariableSpecification rhs = (StandardVariableSpecification)obj;

     if( ! (numberOfStandardVariableRecords == rhs.numberOfStandardVariableRecords)) ivarsEqual = false;

     for(int idx = 0; idx < standardVariables.size(); idx++)
        if( ! ( standardVariables.get(idx).equals(rhs.standardVariables.get(idx)))) ivarsEqual = false;

    return ivarsEqual;
 }
} // end of class
