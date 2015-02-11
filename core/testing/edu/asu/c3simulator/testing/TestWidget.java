package edu.asu.c3simulator.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import sun.misc.Launcher;

import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import edu.asu.c3simulator.widgets.SimpleTextField;

/**
 * A template for testing classes that extend {@link Widget}.
 * 
 * These tests are intended to help catch display errors automatically, and should apply
 * to any class that extends {@link Widget}. As such, all child classes of {@link Widget}
 * should be tested with this template, in addition to any unit tests required for the
 * specific class.
 * 
 * @author Moore, Zachary
 * 
 */
public class TestWidget extends Launcher implements WidgetTest
{
	protected Widget widget;
	
	public TestWidget()
	{
		System.out.println("2");
		widget = new SimpleTextField("Test");
	}
	
	@Override
	public void testResizingZero()
	{
		widget.setSize(0, 0);
		widget.validate();
		
		assertEquals(0, widget.getWidth(), 0);
		assertEquals(0, widget.getHeight(), 0);
	}
	
	@Override
	public void testResizingNegative()
	{
		widget.setSize(-10, -10);
		widget.validate();
		
		assertTrue(widget.getWidth() < 0);
		assertTrue(widget.getHeight() < 0);
	}
	
	@Override
	public void testResizingBoundaryUpper()
	{
		float value = Float.POSITIVE_INFINITY;
		
		widget.setSize(value, value);
		widget.validate();
		
		assertEquals(value, widget.getWidth(), 0);
		assertEquals(value, widget.getHeight(), 0);
	}
	
	@Override
	public void testResizingBoundaryLower()
	{
		float value = Float.NEGATIVE_INFINITY;
		
		widget.setSize(value, value);
		widget.validate();
		
		assertEquals(value, widget.getWidth(), 0);
		assertEquals(value, widget.getHeight(), 0);
	}
	
	@Override
	public void testResizingPrecisionUpper()
	{
		float value = Float.MAX_VALUE;
		
		widget.setSize(value, value);
		widget.validate();
		
		assertEquals(value, widget.getWidth(), 0);
		assertEquals(value, widget.getHeight(), 0);
	}
	
	@Override
	public void testResizingPrecisionLower()
	{
		float value = Float.MIN_VALUE;
		
		widget.setSize(value, value);
		widget.validate();
		
		assertEquals(value, widget.getWidth(), 0);
		assertEquals(value, widget.getHeight(), 0);
	}
	
}
