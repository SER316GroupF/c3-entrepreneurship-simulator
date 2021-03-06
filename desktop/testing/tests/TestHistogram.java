package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import edu.asu.c3simulator.testing.automated.IntegratedTest;
import edu.asu.c3simulator.util.Association;
import edu.asu.c3simulator.widgets.Histogram;

public class TestHistogram
{
	/** Subject of these tests */
	private Histogram<String> testTarget;
	
	/**
	 * @see TestTemplate#setUpBeforeClass()
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception
	{
		TestTemplate.setUpBeforeClass();
	}
	
	/**
	 * @see TestTemplate#tearDownAfterClass()
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception
	{
		TestTemplate.tearDownAfterClass();
	}
	
	@Test
	public void testNullConstruct()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				try
				{
					testTarget = new Histogram<>(null);
					fail();
				}
				catch (NullPointerException | IllegalArgumentException expected)
				{
					
				}
			}
			
			@Override
			protected void runAssertions()
			{
				
			}
		};
	}
	
	@Test
	public void testTotalValueZero()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				Association<String, Float> value = new Association<>("Test", 0.0f);
				testTarget = new Histogram<>(value);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(0.0f, testTarget.getTotalValue(), 0.1f);
			}
		};
	}
	
	@Test
	public void testTotalValueExpected()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				Association<String, Float> value = new Association<>("Test1", 5.0f);
				Association<String, Float> value2 = new Association<>("Test2", 2.0f);
				Association<String, Float> value3 = new Association<>("Test3", 1.0f);
				Association<String, Float> value4 = new Association<>("Test4", 9.0f);
				testTarget = new Histogram<>(value, value2, value3, value4);
			}
			
			@Override
			protected void runAssertions()
			{
				assertEquals(17.0f, testTarget.getTotalValue(), 0.1f);
			}
		};
	}
	
	@Test
	public void testTotalValueOverflow()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				Association<String, Float> value = new Association<>("Test1", Float.POSITIVE_INFINITY);
				Association<String, Float> value2 = new Association<>("Test2", Float.POSITIVE_INFINITY);
				Association<String, Float> value3 = new Association<>("Test3", 1.0f);
				Association<String, Float> value4 = new Association<>("Test4", 9.0f);
				testTarget = new Histogram<>(value, value2, value3, value4);
			}
			
			@Override
			protected void runAssertions()
			{
				try
				{
					testTarget.getTotalValue();
					fail();
				}
				catch (Exception expected)
				{
					
				}
			}
		};
	}
	
	@Test
	public void testDuplicateKeys()
	{
		new IntegratedTest() {
			boolean passed;
			
			@Override
			protected void runGLCode()
			{
				Association<String, Float> value = new Association<>("Test", -1.0f);
				Association<String, Float> value2 = new Association<>("Test", -1.0f);
				
				try
				{
					testTarget = new Histogram<>(value, value2);
					passed = false;
				}
				catch (IllegalArgumentException expected)
				{
					passed = true;
				}
			}
			
			@Override
			protected void runAssertions()
			{
				if (!passed)
				{
					fail();
				}
			}
		};
	}
	
	@Test
	public void testNegativeConstruct()
	{
		new IntegratedTest() {
			boolean passed;
			
			@Override
			protected void runGLCode()
			{
				Association<String, Float> value = new Association<>("Test", -1.0f);
				
				try
				{
					testTarget = new Histogram<>(value);
					passed = false;
				}
				catch (IllegalArgumentException expected)
				{
					passed = true;
				}
			}
			
			@Override
			protected void runAssertions()
			{
				if (!passed)
				{
					fail();
				}
			}
		};
	}
	
}
