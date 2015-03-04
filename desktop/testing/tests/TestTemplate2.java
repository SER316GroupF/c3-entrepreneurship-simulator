package tests;

import static org.junit.Assert.assertTrue;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import edu.asu.c3simulator.testing.automated.IntegratedTest;
import edu.asu.c3simulator.widgets.SimpleTextField;

public class TestTemplate2
{
	/** Subject of these tests */
	private Widget testTarget;
	
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
	public void testSample()
	{
		new IntegratedTest() {
			
			@Override
			protected void runGLCode()
			{
				testTarget = new SimpleTextField("Test");
				testTarget.setSize(0, 0);
				testTarget.validate();
			}
			
			@Override
			protected void runAssertions()
			{
				assertTrue(testTarget.getWidth() == 0);
				assertTrue(testTarget.getHeight() == 0);
			}
		};
		
	}
	
}
