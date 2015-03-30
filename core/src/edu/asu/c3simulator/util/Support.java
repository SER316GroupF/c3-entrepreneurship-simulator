package edu.asu.c3simulator.util;

public class Support
{
	public static float validatedAddition(float numberA, float numberB)
	{
		float sum = numberA + numberB;
		
		if (sum <= numberA && numberB > 0)
		{
			throw new IllegalArgumentException("Overflow");
		}
		else if (sum >= numberA && numberB < 0)
		{
			throw new IllegalArgumentException("Underflow");
		}
		
		return sum;
	}
}
