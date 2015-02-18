package edu.asu.c3simulator.testing.automated;

import static org.junit.Assert.assertTrue;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.utils.Disposable;

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
public abstract class TestWidget<T extends Actor> implements GLTest, Disposable
{
	protected abstract T createTargetInstance();
	
	private abstract class StreamlinedUnitTest extends BasicActorUnitTest<T>
	{
		public StreamlinedUnitTest(String name)
		{
			super(name, createTargetInstance());
		}
	}
	
	private class TestResizing extends StreamlinedUnitTest
	{
		private float valueX;
		private float valueY;
		private float valueExpectedX;
		private float valueExpectedY;
		
		public TestResizing(String name, float valueX, float valueY, float expectedX,
				float expectedY)
		{
			super(name);
			this.valueX = valueX;
			this.valueY = valueY;
			this.valueExpectedX = expectedX;
			this.valueExpectedY = expectedY;
		}
		
		public TestResizing(String name, float valueXY, float expectedXY)
		{
			this(name, valueXY, valueXY, expectedXY, expectedXY);
		}
		
		public TestResizing(float valueX, float valueY, float expectedX, float expectedY)
		{
			this("Test resizing to (" + valueX + "," + valueY + "}", valueX, valueY,
					expectedX, expectedY);
		}
		
		@SuppressWarnings("unused")
		public TestResizing(float valueXY, float expectedXY)
		{
			this(valueXY, valueXY, expectedXY, expectedXY);
		}
		
		@Override
		public void validate()
		{
			assertTrue(testTarget.getWidth() == valueExpectedX);
			assertTrue(testTarget.getHeight() == valueExpectedY);
		}
		
		@Override
		public void performTestFunctionality()
		{
			testTarget.setSize(valueX, valueY);
		}
	}
	
	@Override
	public void dispose()
	{
		
	}
	
	@Override
	public List<GLTestUnit> getTestUnits()
	{
		List<GLTestUnit> testUnits = new LinkedList<>();
		
		String testName = "Test resizing zero";
		
		testUnits.add(new TestResizing(testName, 0, 0));
		
		testName = "Test resizing negative";
		testUnits.add(new TestResizing(testName, -10, -10));
		testName = "Test resizing normal";
		testUnits.add(new TestResizing(testName, 200, 200));
		
		testName = "Test resizing presision high";
		testUnits.add(new TestResizing(testName, Float.MAX_VALUE, Float.MAX_VALUE));
		
		testName = "Test resizing presision low";
		testUnits.add(new TestResizing(testName, Float.MIN_VALUE, Float.MIN_VALUE));
		
		testName = "Test resize boundry positive";
		testUnits.add(new TestResizing(testName, Float.POSITIVE_INFINITY,
				Float.POSITIVE_INFINITY));
		
		testName = "Test resize boundry negative";
		testUnits.add(new TestResizing(testName, Float.NEGATIVE_INFINITY,
				Float.NEGATIVE_INFINITY));
		return testUnits;
	}
	
}
