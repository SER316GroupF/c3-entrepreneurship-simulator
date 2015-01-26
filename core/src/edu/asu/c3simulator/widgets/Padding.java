package edu.asu.c3simulator.widgets;

public class Padding
{
	public static final int LEFT = 0;
	public static final int RIGHT = 1;
	public static final int TOP = 2;
	public static final int BOTTOM = 3;
	
	private float[] padding;
	
	public Padding()
	{
		this.padding = new float[4];
	}
	
	public Padding(float left, float right, float top, float bottom)
	{
		this();
		
		set(LEFT, left);
		set(RIGHT, right);
		set(TOP, top);
		set(BOTTOM, bottom);
	}
	
	public void set(int paddingType, float value)
	{
		padding[paddingType] = value;
	}
	
	public float get(int paddingType)
	{
		return padding[paddingType];
	}
}
