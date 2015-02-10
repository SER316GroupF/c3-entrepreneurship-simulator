package edu.asu.c3simulator.util;

import java.util.Map;

/**
 * A two-way association between two variables. Often times a single association, or a
 * small associative array is needed where {@link Map} is unfit. This utility seeks to
 * fill that need.
 * 
 * Associations can be reset, by using {@link #set(Object, Object)}, however the key or
 * value of an association cannot be changed individually. 
 * 
 * @author Moore, Zachary
 * 
 * @param <Key>
 *            Associated type 1
 * @param <Value>
 *            Associated type 2
 */
public class Association<Key, Value>
{
	private Key key;
	private Value value;
	
	public Association(Key key, Value value)
	{
		super();
		this.key = key;
		this.value = value;
	}
	
	/**
	 * @return Associated value 1
	 */
	public Key getKey()
	{
		return this.key;
	}
	
	/**
	 * @return Associated value 2
	 */
	public Value getValue()
	{
		return this.value;
	}
	
	/**
	 * Resets this association, by assigning a new key-value pair
	 * 
	 * @param key Associated value 1
	 * @param value Associated value 2
	 */
	public void set(Key key, Value value)
	{
		this.key = key;
		this.value = value;
	}
}
