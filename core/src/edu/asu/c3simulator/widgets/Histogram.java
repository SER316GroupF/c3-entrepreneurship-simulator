package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import edu.asu.c3simulator.C3Simulator;
import edu.asu.c3simulator.util.Association;

/**
 * Histogram chart. See <a href="http://en.wikipedia.org/wiki/Histogram">Histogram</a>
 * <p>
 * Contains keys and values (weights) for each key. This data will be graphed relative to
 * other keys displayed by this chart.
 * 
 * @author Moore, Zachary
 * @see AbstractChart
 *
 * @param <K>
 *            Key type
 */
public class Histogram<K> extends AbstractChart<K, Float>
{
	/** Ratio of space on either side of a segment {@link #segmentWidth} */
	private static final float DEFAULT_SEGMENT_SPACING_RATIO = 0.5f;
	
	/** Ratio of {@link #getHeight()} to {@link #textHeight} */
	private static final float DEFAULT_TEXT_HEIGHT_RATIO = 0.1f;
	
	/** The amount of space between the x-axis and text-top is text.height * this ratio */
	private static final float TEXT_SPACING_RATIO = 0.3f;
	
	/** Ratio of {@link #getHeight()} to {@link #textHeight} */
	private float textHeightRatio;
	
	/** Ratio of space on either side of a segment {@link #segmentWidth} */
	private float segmentSpacingRatio;
	
	/** Width of each segment, in pixels */
	private float segmentWidth;
	
	/** Space on either side of a segment, in pixels */
	private float segmentSpacing;
	
	private float textHeight;
	
	@SafeVarargs
	public Histogram(Association<K, Float> firstValue,
			Association<K, Float>... additionalValues)
	{
		super();
		this.segmentSpacingRatio = DEFAULT_SEGMENT_SPACING_RATIO;
		this.textHeightRatio = DEFAULT_TEXT_HEIGHT_RATIO;
		this.add(firstValue.getKey(), firstValue.getValue());
		
		for (Association<K, Float> value : additionalValues)
		{
			this.add(value.getKey(), value.getValue());
		}
	}
	
	@Override
	protected SimpleTextField createLabel(K key)
	{
		String text = key.toString();
		SimpleTextField label = new SimpleTextField(text);
		
		return sizeLabel(label);
	}
	
	/**
	 * Resizes the given {@link SimpleTextField} to exist within the acceptable label
	 * bounds
	 * 
	 * @param label
	 *            Label to resize
	 * @return The label that was resized, for chaining
	 */
	private SimpleTextField sizeLabel(SimpleTextField label)
	{
		label.setSize(Integer.MAX_VALUE, textHeight);
		
		float maxWidth = (segmentSpacing / 2) + segmentWidth;
		
		if (label.getPrefWidth() > maxWidth)
		{
			label.setWidth(maxWidth);
			label.setHeight(label.getPrefHeight());
		}
		else
		{
			label.setWidth(label.getPrefWidth());
			label.setHeight(label.getPrefHeight());
		}
		
		return label;
	}
	
	@Override
	protected void sizeChanged()
	{
		
		// @formatter:off
		 /* Math for calculating chart measurements:
		  * segmentSpacingRatio is considered constant in this context
		  * 
		  * let s  = segmentWidth
		  * let r  = segmentSpacingRatio
		  * let s' = segmentSpacing = s * r
		  * 
		  * 2*s' + s = spacePerSegment = 2*s*r + s = s(2r + 1)
		  * -> s = spacePerSegment / (2r + 1)
		  */
		// @formatter:on
		
		int numberOfSegments = this.values.size();
		float spacePerSegment = getWidth() / numberOfSegments;
		
		segmentWidth = spacePerSegment / ((2 * segmentSpacingRatio) + 1);
		segmentSpacing = segmentWidth * segmentSpacingRatio;
		
		textHeight = getHeight() * textHeightRatio;
		
		for (SimpleTextField label : labels.values())
		{
			sizeLabel(label);
		}
	}
	
	/**
	 * @return The sum of all values held by this chart
	 */
	public float getTotalValue()
	{
		float total = 0.0f;
		
		for (Float value : values.values())
		{
			total += value;
		}
		
		return total;
	}
	
	/**
	 * @return The maximum value held by this chart
	 */
	public float getMaximumValue()
	{
		float max = Float.NEGATIVE_INFINITY;
		
		for (Float value : values.values())
		{
			if (value > max)
			{
				max = value;
			}
		}
		
		return max;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		ShapeRenderer renderer = C3Simulator.rendererLine;
		
		// Draw Axis
		renderer.setColor(Color.WHITE);
		renderer.line(getX(), getY(), getX() + getWidth(), getY());
		renderer.line(getX(), getY(), getX(), getY() + getHeight());
		
		renderer = C3Simulator.rendererFilled;
		
		float totalWeight = getMaximumValue();
		float x = getX() + segmentSpacing;
		
		for (Color color : colorValues.keySet())
		{
			K key = colorValues.get(color);
			float weight = values.get(key);
			float percentageValue = weight / totalWeight;
			float segmentHeight = getHeight() * percentageValue;
			
			renderer.setColor(color);
			renderer.rect(x, getY(), segmentWidth, segmentHeight);
			
			float xCenter = x + (segmentWidth / 2);
			drawLabel(key, xCenter, batch, parentAlpha);
			
			x += segmentWidth;
			x += 2 * segmentSpacing;
		}
	}
	
	/**
	 * Positions and draws the provided label to/at the provided position
	 * 
	 * @param key
	 *            Used to retrieve the label
	 * @param xCenter
	 *            Location at which to center this label
	 * @param batch
	 *            Used to draw the label
	 * @param parentAlpha
	 *            Used to draw the label
	 */
	private void drawLabel(K key, float xCenter, Batch batch, float parentAlpha)
	{
		SimpleTextField label = labels.get(key);
		float x = xCenter - (label.getPrefWidth() / 2);
		float y = getY() - (label.getPrefHeight() * (1 + TEXT_SPACING_RATIO));
		
		label.setPosition(x, y);
		label.draw(batch, parentAlpha);
	}
	
}
