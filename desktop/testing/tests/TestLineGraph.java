package tests;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.asu.c3simulator.testing.automated.IntegratedTest;
import edu.asu.c3simulator.util.PrecisePoint;
import edu.asu.c3simulator.widgets.LineGraph;

public class TestLineGraph
{
	

	private LineGraph<String> testTarget;
	

	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		TestTemplate.setUpBeforeClass();
	}
	

	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		TestTemplate.tearDownAfterClass();
	}
	
	@Test
	public void testGetMaxAllXSame()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				ArrayList<PrecisePoint> lineData = new ArrayList<>();
				lineData.add(new PrecisePoint(6, 0));
				lineData.add(new PrecisePoint(6, 1));
				lineData.add(new PrecisePoint(6, 2));
				lineData.add(new PrecisePoint(6, 3));
				lineData.add(new PrecisePoint(6, 4));
				lineData.add(new PrecisePoint(6, 5));
				testTarget = new LineGraph<String>("My Key", lineData);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(6f, testTarget.getMaximumXValue(), 0.01f);
			}
		};
		
	}
	
	@Test
	public void testGetMaxAllYSame()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				ArrayList<PrecisePoint> lineData = new ArrayList<>();
				lineData.add(new PrecisePoint(0, 10));
				lineData.add(new PrecisePoint(1, 10));
				lineData.add(new PrecisePoint(2, 10));
				lineData.add(new PrecisePoint(3, 10));
				lineData.add(new PrecisePoint(4, 10));
				lineData.add(new PrecisePoint(5, 10));
				testTarget = new LineGraph<String>("My Key", lineData);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(10f, testTarget.getMaximumYValue(), 0.01f);
			}
		};
		
	}
	
	@Test
	public void testGetMaxAllXYSame()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				ArrayList<PrecisePoint> lineData = new ArrayList<>();
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				testTarget = new LineGraph<String>("My Key", lineData);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(0f, testTarget.getMaximumXValue(), 0.01f);
				assertEquals(0f, testTarget.getMaximumYValue(), 0.01f);
			}
		};
		
	}
	
	@Test
	public void testGetMinAllXSame()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				ArrayList<PrecisePoint> lineData = new ArrayList<>();
				lineData.add(new PrecisePoint(2, 3));
				lineData.add(new PrecisePoint(2, 4));
				lineData.add(new PrecisePoint(2, 5));
				lineData.add(new PrecisePoint(2, 6));
				lineData.add(new PrecisePoint(2, 7));
				lineData.add(new PrecisePoint(2, 8));
				testTarget = new LineGraph<String>("My Key", lineData);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(2f, testTarget.getMinimumXValue(), 0.01f);
			}
		};
		
	}
	
	@Test
	public void testGetMinAllYSame()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				ArrayList<PrecisePoint> lineData = new ArrayList<>();
				lineData.add(new PrecisePoint(0, -1));
				lineData.add(new PrecisePoint(1, -1));
				lineData.add(new PrecisePoint(2, -1));
				lineData.add(new PrecisePoint(3, -1));
				lineData.add(new PrecisePoint(4, -1));
				lineData.add(new PrecisePoint(5, -1));
				testTarget = new LineGraph<String>("My Key", lineData);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(-1f, testTarget.getMinimumYValue(), 0.01f);
			}
		};
		
	}
	
	@Test
	public void testGetMinAllXYSame()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				ArrayList<PrecisePoint> lineData = new ArrayList<>();
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				lineData.add(new PrecisePoint(0, 0));
				testTarget = new LineGraph<String>("My Key", lineData);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(0f, testTarget.getMinimumXValue(), 0.01f);
				assertEquals(0f, testTarget.getMinimumYValue(), 0.01f);
			}
		};
		
	}
	
	@Test
	public void testGetMinAllPositiveInf()
	{
		new IntegratedTest() {
			float positive_infinity = Float.POSITIVE_INFINITY;
			
			@Override
			protected void runGLCode()
			{
				ArrayList<PrecisePoint> lineData = new ArrayList<>();
				lineData.add(new PrecisePoint(positive_infinity, positive_infinity));
				lineData.add(new PrecisePoint(positive_infinity, positive_infinity));
				lineData.add(new PrecisePoint(positive_infinity, positive_infinity));
				lineData.add(new PrecisePoint(positive_infinity, positive_infinity));
				lineData.add(new PrecisePoint(positive_infinity, positive_infinity));
				lineData.add(new PrecisePoint(positive_infinity, positive_infinity));
				testTarget = new LineGraph<String>("My Key", lineData);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(positive_infinity, testTarget.getMinimumXValue(), 0.01f);
				assertEquals(positive_infinity, testTarget.getMinimumYValue(), 0.01f);
			}
		};
		
	}
	
	@Test
	public void testGetMaxAllNegativeInf()
	{
		new IntegratedTest() {
			float negative_infinity = Float.NEGATIVE_INFINITY;
			
			@Override
			protected void runGLCode()
			{
				ArrayList<PrecisePoint> lineData = new ArrayList<>();
				lineData.add(new PrecisePoint(negative_infinity, negative_infinity));
				lineData.add(new PrecisePoint(negative_infinity, negative_infinity));
				lineData.add(new PrecisePoint(negative_infinity, negative_infinity));
				lineData.add(new PrecisePoint(negative_infinity, negative_infinity));
				lineData.add(new PrecisePoint(negative_infinity, negative_infinity));
				lineData.add(new PrecisePoint(negative_infinity, negative_infinity));
				testTarget = new LineGraph<String>("My Key", lineData);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(negative_infinity, testTarget.getMinimumXValue(), 0.01f);
				assertEquals(negative_infinity, testTarget.getMinimumYValue(), 0.01f);
			}
		};
		
	}
	
}
