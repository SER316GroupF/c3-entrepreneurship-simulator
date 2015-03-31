package edu.asu.c3simulator.widgets;

import java.awt.Point;
import java.util.ArrayList;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import edu.asu.c3simulator.C3Simulator;
import edu.asu.c3simulator.util.PrecisePoint;

/**
 * Line graph display. See <a href="http://en.wikipedia.org/wiki/Line_graph">Line
 * Graph</a>
 * <p>
 * Each key represents one line on this graph. Each line has a collection of {@link Point}
 * s, modeled by {@link ArrayList}
 * <p>
 * Currently, negative values are not supported, and the only available axis scale is 1:1
 * <p>
 * 
 * @author Moore, Zachary
 *
 * @param <K>
 *            Key type
 */
public class LineGraph<K> extends AbstractChart<K, ArrayList<PrecisePoint>>
{
	/** Ratio of {@link #getHeight()} to {@link #textHeight} */
	private static final float DEFAULT_TEXT_HEIGHT_RATIO = 0.1f;
	
	/** Ratio of {@link #getHeight()} to {@link #textHeight} */
	private float textHeightRatio;
	
	/** Height of all labels associated with this chart */
	private float textHeight;
	
	public LineGraph(K firstKey, ArrayList<PrecisePoint> lineData)
	{
		super();
		this.textHeightRatio = DEFAULT_TEXT_HEIGHT_RATIO;
		this.add(firstKey, lineData);
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
		label.setWidth(label.getPrefWidth());
		
		return label;
	}
	
	@Override
	protected void sizeChanged()
	{
		textHeight = getHeight() * textHeightRatio;
		
		for (SimpleTextField label : labels.values())
		{
			sizeLabel(label);
		}
	}
	
	/**
	 * @return The maximum x-value displayed by this chart
	 */
	public float getMaximumXValue()
	{
		float max = Float.NEGATIVE_INFINITY;
		
		for (ArrayList<PrecisePoint> line : values.values())
		{
			for (PrecisePoint point : line)
			{
				if (point.x > max)
				{
					max = point.x;
				}
			}
		}
		
		return max;
	}
	
	/**
	 * @return The minimum x-value displayed by this chart
	 */
	public float getMinimumXValue()
	{
		float min = Float.POSITIVE_INFINITY;
		
		for (ArrayList<PrecisePoint> line : values.values())
		{
			for (PrecisePoint point : line)
			{
				if (point.x < min)
				{
					min = point.x;
				}
			}
		}
		
		return min;
	}
	
	/**
	 * @return The maximum x-value displayed by this chart
	 */
	public float getMaximumYValue()
	{
		float max = Float.NEGATIVE_INFINITY;
		
		for (ArrayList<PrecisePoint> line : values.values())
		{
			for (PrecisePoint point : line)
			{
				if (point.y > max)
				{
					max = point.y;
				}
			}
		}
		
		return max;
	}
	
	/**
	 * @return The minimum x-value displayed by this chart
	 */
	public float getMinimumYValue()
	{
		float min = Float.POSITIVE_INFINITY;
		
		for (ArrayList<PrecisePoint> line : values.values())
		{
			for (PrecisePoint point : line)
			{
				if (point.y < min)
				{
					min = point.y;
				}
			}
		}
		
		return min;
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		ShapeRenderer renderer = C3Simulator.rendererLine;
		
		// Draw Axis
		renderer.setColor(Color.WHITE);
		renderer.line(getX(), getY(), getX() + getWidth(), getY());
		renderer.line(getX(), getY(), getX(), getY() + getHeight());
		
		for (Color color : colorValues.keySet())
		{
			renderer.setColor(color);
			K key = colorValues.get(color);
			ArrayList<PrecisePoint> line = values.get(key);
			PrecisePoint previous = new PrecisePoint(getX(), getY());
			
			for (PrecisePoint point : line)
			{
				renderer.line(previous.x, previous.y, point.x, point.y);
				previous = point;
			}
			
			drawLabel(key, previous.x, previous.y, batch, parentAlpha);
		}
	}
	
	/**
	 * Positions and draws the provided label to/at the provided position
	 * 
	 * @param key
	 *            Used to retrieve the label
	 * @param x
	 *            Location at which to position the left side of this label
	 * @param y
	 *            Location at which to position the bottom of this label
	 * @param batch
	 *            Used to draw the label
	 * @param parentAlpha
	 *            Used to draw the label
	 */
	private void drawLabel(K key, float x, float y, Batch batch, float parentAlpha)
	{
		SimpleTextField label = labels.get(key);
		label.setPosition(x, y);
		label.draw(batch, parentAlpha);
	}
	
}
