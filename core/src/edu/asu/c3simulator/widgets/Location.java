package edu.asu.c3simulator.widgets;

/**
 * This class holds the x and y coordinates for any actor as a single object of type Location
 * 
 * @author Alyahya, Mohammed
 */
public class Location
{
	private float x,y;

	public Location(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public Location()
	{
		this.x = 0;
		this.y = 0;
	}
	
	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}
	
	public void setLocation(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
}
