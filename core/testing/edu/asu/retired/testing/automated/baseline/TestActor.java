package edu.asu.retired.testing.automated.baseline;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import edu.asu.c3simulator.testing.automated.integrated.IntegratedTest;
import edu.asu.c3simulator.widgets.SimpleTextField;
import edu.asu.retired.testing.automated.IntegratedGLTestDriver;

/**
 * Baseline test to indicate whether or not all resources can be accessed. This test is
 * set up such that it will always pass unless prevented by a dependency not being met
 * (for instance an OpenGL context).
 * 
 * This test is intended to be run from a thread with an OpenGL context. See
 * {@link IntegratedGLTestDriver} for this functionality. Note: if run through the default
 * JUnit thread, this test will fail.
 * 
 * For a working test infrastructure that does work in the default JUnit thread, see
 * {@link IntegratedTest}
 * 
 * @author Moore, Zachary
 * @deprecated replaced by {@link IntegratedTest}
 * @see IntegratedTest
 *
 */
public class TestActor
{
	/**
	 * {@link SimpleTextField} object that always returns 0 for {@link Widget#getWidth()}
	 * 
	 * @author Moore, Zachary
	 *
	 */
	private class Baseline extends SimpleTextField
	{
		public Baseline()
		{
			super("baseline");
		}
		
		@Override
		public float getWidth()
		{
			return 0;
		}
	}
	
	@Test
	public void test()
	{
		Widget testTarget = new Baseline();
		
		assertTrue(testTarget.getWidth() == 0);
	}
	
}
