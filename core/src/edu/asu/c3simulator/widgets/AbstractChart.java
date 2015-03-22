package edu.asu.c3simulator.widgets;

import java.util.HashMap;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;

import edu.asu.c3simulator.util.ColorDecider;

/**
 * Convenience class for visual charts (pie charts, line graphs, histograms, etc). Keys
 * are mapped to values, each of which is assigned a unique color, determined by
 * {@link ColorDecider}, and a label specified by key.toString();
 * 
 * @author Moore, Zachary
 *
 * @param <K>
 *            Key type
 * @param <V>
 *            Value type
 */
public abstract class AbstractChart<K, V> extends Actor
{
	protected HashMap<K, V> values;
	protected HashMap<Color, K> colorValues;
	protected HashMap<K, SimpleTextField> labels;
	protected ColorDecider colorDecider;
	
	protected abstract SimpleTextField createLabel(K key);
	
	public AbstractChart()
	{
		this.values = new HashMap<>();
		this.colorValues = new HashMap<>();
		this.labels = new HashMap<>();
		this.colorDecider = new ColorDecider();
	}
	
	/**
	 * Associates a new key with the given value. If the key already exists, an exception
	 * will be thrown. In this case, {@link #setValue(Object, Object)} should be used
	 * instead
	 * 
	 * @param key
	 * @param value
	 */
	public void add(K key, V value)
	{
		if (values.containsKey(key))
		{
			throw new IllegalArgumentException("Cannot contain duplicate keys");
		}
		
		this.values.put(key, value);
		this.labels.put(key, createLabel(key));
		this.colorValues.put(colorDecider.getNextColor(), key);
	}
	
	/**
	 * Sets the value of a given key. If no such key exists, an exception will be thrown.
	 * In this case, {@link #add(Object, Object)} should be used instead
	 * 
	 * @param key
	 *            Key
	 * @param value
	 *            Value to which to associate the given key
	 */
	public void setValue(K key, V value)
	{
		if (!values.containsKey(key))
		{
			throw new IllegalArgumentException("Cannot set non-existant key");
		}
		
		this.values.put(key, value);
	}
	
	/**
	 * @param key
	 *            Used to lookup the requested value
	 * @return The value associated with the given key
	 */
	public V getValue(K key)
	{
		return values.get(key);
	}
}
