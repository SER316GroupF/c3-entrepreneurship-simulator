package edu.asu.c3simulator.util;

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
	
	public Key getKey()
	{
		return this.key;
	}
	
	public Value getValue()
	{
		return this.value;
	}
	
	public void set(Key key, Value value)
	{
		this.key = key;
		this.value = value;
	}
}
