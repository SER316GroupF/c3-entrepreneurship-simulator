package edu.asu.c3simulator.util;

import java.util.Deque;
import java.util.LinkedList;

import com.badlogic.gdx.graphics.Color;

/**
 * Utility to provide a variety of differentiated colors. When {@link #getNextColor()} is
 * called, {@link ColorDecider} will attempt to find a color that is visually distinct
 * from colors that it has already returned.
 * 
 * {@link ColorDecider} will attempt to keep the colors given as diverse as possible.
 * However, the more colors generated with {@link ColorDecider}, the less distinct they
 * will be from one another.
 * 
 * Note: {@link ColorDecider} is currently unsuitable for systems which require large
 * amounts of visually distinct colors (>25), however it can still be used to generate
 * colors in quantities larger than that number.
 * 
 * @author Moore, Zachary
 *
 */
public class ColorDecider
{
	private Deque<Float> processedHues;
	private Deque<Float> unprocessedHues;
	
	/** The highest processed value, which functions as the lowest unprocessed value */
	private float low;
	
	/**
	 * Creates a new color that is different from previously generated colors. This
	 * algorithm will attempt to select a color that is visually distinct from those
	 * previously generated.
	 * 
	 * @return A color different from any previously returned color
	 */
	public Color getNextColor()
	{
		// Use only the outer ring of the top layer of the HSV cylinder
		final float saturation = 1.0f;
		final float value = 1.0f;
		
		// Hue, in degrees (0, 360]
		float hue;
		
		if (processedHues == null)
		{
			processedHues = new LinkedList<>();
			unprocessedHues = new LinkedList<>();
			
			hue = 360f;
			unprocessedHues.add(hue);
		}
		else
		{
			if (unprocessedHues.isEmpty())
			{
				unprocessedHues = processedHues;
				processedHues = new LinkedList<>();
				low = 0;
			}
			
			// Find a value in the middle of two preciously returned hues
			float high = unprocessedHues.poll();
			hue = (high + low) / 2;
			processedHues.add(hue);
			processedHues.add(high);
			
			low = high;
		}
		
		return hsvToRGB(hue, saturation, value);
	}
	
	/**
	 * Converts a color in the HSV system to the RGB system using the color's chroma and
	 * intermediate values. See <a
	 * href="http://en.wikipedia.org/wiki/HSL_and_HSV#Converting_to_RGB" /a>
	 * 
	 * @param hue
	 *            H-value of HSV, in degrees. This algorithm will convert the value to a
	 *            value in the range [0, 360) if it is not already in this range
	 * @param saturation
	 *            S-value of HSV, in the range [0, 1]
	 * @param value
	 *            V-value of HSV, in the range [0, 1]
	 * @return A color in the RGB system specified by the above values in the HSV system.
	 */
	private Color hsvToRGB(float hue, float saturation, float value)
	{
		// Work with hue [0, 360)
		if (hue >= 360)
		{
			hue = hue % 360;
		}
		
		float chroma = value * saturation;
		float primedHue = hue / 60;
		float intermediate = chroma * (1 - Math.abs((primedHue % 2) - 1));
		Color initialColor;
		
		// Piecewise function
		if (primedHue < 1)
		{
			initialColor = new Color(chroma, intermediate, 0, 1.0f);
		}
		else if (primedHue < 2)
		{
			initialColor = new Color(intermediate, chroma, 0, 1.0f);
		}
		else if (primedHue < 3)
		{
			initialColor = new Color(0, chroma, intermediate, 1.0f);
		}
		else if (primedHue < 4)
		{
			initialColor = new Color(0, intermediate, chroma, 1.0f);
		}
		else if (primedHue < 5)
		{
			initialColor = new Color(intermediate, 0, chroma, 1.0f);
		}
		else if (primedHue < 6)
		{
			initialColor = new Color(chroma, 0, intermediate, 1.0f);
		}
		else
		{
			initialColor = new Color(0, 0, 0, 1.0f);
		}
		
		intermediate = value - chroma;
		Color addend = new Color(intermediate, intermediate, intermediate, 1.0f);
		return initialColor.add(addend);
	}
}
