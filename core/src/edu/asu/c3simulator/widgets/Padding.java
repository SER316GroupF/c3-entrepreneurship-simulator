package edu.asu.c3simulator.widgets;

/**
 * Structure to hold the padding values for a GUI component. For instance, a text field
 * may require blank space (i.e. padding) around its edges. This class stores values, as
 * floats, for the top, bottom, left, and right areas of a component.
 * 
 * @author Moore, Zachary
 *
 */
public class Padding
{
	/** Enumerated constant */
	public static final int LEFT = 0;
	
	/** Enumerated constant */
	public static final int RIGHT = 1;
	
	/** Enumerated constant */
	public static final int TOP = 2;
	
	/** Enumerated constant */
	public static final int BOTTOM = 3;
	
	/**
	 * Padding values. Each enumerated constant value corresponds to an index in this
	 * array. As such, the size of this array should always equal 4
	 */
	private float[] padding;
	
	/**
	 * Creates a padding object using the default padding value (0)
	 */
	public Padding()
	{
		this.padding = new float[4];
	}
	
	/**
	 * Creates a padding object with the given padding values
	 * 
	 * @param left
	 *            Value of {@link #padding} {@link #LEFT}
	 * @param right
	 *            Value of {@link #padding} {@link #RIGHT}
	 * @param top
	 *            Value of {@link #padding} {@link #TOP}
	 * @param bottom
	 *            Value of {@link #padding} {@link #TOP}
	 */
	public Padding(float left, float right, float top, float bottom)
	{
		this();
		
		set(LEFT, left);
		set(RIGHT, right);
		set(TOP, top);
		set(BOTTOM, bottom);
	}
	
	/**
	 * Sets the specified padding value
	 * 
	 * @param paddingType
	 *            An enumerated constant value such as {@link #TOP}, {@link #BOTTOM},
	 *            {@link #LEFT}, or {@link #RIGHT}
	 * @param value
	 *            The value of the specified padding
	 */
	public void set(int paddingType, float value)
	{
		padding[paddingType] = value;
	}
	
	/**
	 * @param paddingType
	 *            An enumerated constant value such as {@link #TOP}, {@link #BOTTOM},
	 *            {@link #LEFT}, or {@link #RIGHT}
	 * @return The value of the specified padding
	 */
	public float get(int paddingType)
	{
		return padding[paddingType];
	}
}
