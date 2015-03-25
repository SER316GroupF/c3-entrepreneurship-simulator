package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import edu.asu.c3simulator.C3Simulator;
import edu.asu.c3simulator.util.Association;

/**
 * Pie chart. See <a href="http://en.wikipedia.org/wiki/Pie_chart">Pie Chart</a>
 * <p>
 * Contains keys and values (weights) for each key. This data will be graphed relative to
 * other keys displayed by this chart.
 * <p>
 * The size of this actor is specified by it's radius, rather than width and height.
 * Calling {@link #setSize(float, float)} will use the lowest value between the two to
 * call {@link #setRadius(float)}
 * <p>
 * Also note: this actor is drawn from its center, not it's bottom left coordinate.
 * 
 * @author Moore, ZacharyS
 * @see AbstractChart
 *
 * @param <K>
 */
public class PieChart<K> extends AbstractChart<K, Float>
{
	private float radius;
	
	@SafeVarargs
	public PieChart(Association<K, Float> firstValue,
			Association<K, Float>... additionalValues)
	{
		super();
		
		this.radius = 50;
		
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
		label.setSize(Integer.MAX_VALUE, radius / 5);
		label.setWidth(label.getPrefWidth());
		
		return label;
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
	
	public void setRadius(float radius)
	{
		this.radius = radius;
		super.setSize(radius, radius);
	}
	
	/**
	 * Uses the lowest value between the two to call {@link #setRadius(float)}
	 * 
	 * @see com.badlogic.gdx.scenes.scene2d.Actor#setSize(float, float)
	 */
	@Override
	public void setSize(float width, float height)
	{
		setRadius(Math.min(width, height));
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		ShapeRenderer renderer = C3Simulator.rendererFilled;
		
		float totalWeight = getTotalValue();
		float startDegrees = 0;
		
		for (Color color : colorValues.keySet())
		{
			K key = colorValues.get(color);
			float weight = values.get(key);
			float percentageValue = weight / totalWeight;
			float degrees = 360 * percentageValue;
			
			renderer.setColor(color);
			renderer.arc(getX(), getY(), radius, startDegrees, degrees);
			
			float middleDegrees = (2 * startDegrees + degrees) / 2;
			drawLabel(key, middleDegrees, batch, parentAlpha);
			
			startDegrees += degrees;
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
	private void drawLabel(K key, float degrees, Batch batch, float parentAlpha)
	{
		double radians = Math.toRadians(degrees);
		SimpleTextField label = labels.get(key);
		float x = (float) (((radius) * (Math.cos(radians))) + getX());
		float y = (float) (((radius) * (Math.sin(radians))) + getY());
		
		if (x < getX())
		{
			x -= label.getWidth();
		}
		if (y < getY())
		{
			y -= label.getHeight();
		}
		
		label.setPosition(x, y);
		label.draw(batch, parentAlpha);
	}
}
