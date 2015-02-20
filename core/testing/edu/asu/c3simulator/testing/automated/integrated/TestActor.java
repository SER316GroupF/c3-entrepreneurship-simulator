package edu.asu.c3simulator.testing.automated.integrated;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;

import edu.asu.c3simulator.widgets.SimpleTextField;

public class TestActor
{
	protected Batch batch;
	protected Widget testTarget;
	
	@Test
	public void test()
	{
		testTarget = new SimpleTextField("Test");
		testTarget.setSize(0, 0);
		testTarget.validate();
		
		assertTrue(testTarget.getWidth() == 0);
		assertTrue(testTarget.getHeight() == 0);
	}
	
}
