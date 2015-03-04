package tests;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.graphics.Color;

import edu.asu.c3simulator.util.ColorDecider;

public class TestColorDecider
{
	private ColorDecider decider;
	
	@Before
	public void setUp() throws Exception
	{
		decider = new ColorDecider();
	}
	
	@After
	public void tearDown() throws Exception
	{
		decider = null;
	}
	
	/**
	 * Ensure {@link ColorDecider} can generate at least 50 unique colors
	 */
	@Test
	public void testUniqueness()
	{
		List<Color> generatedColors = new LinkedList<>();
		
		for (int numberGenerated = 0; numberGenerated < 50; numberGenerated++)
		{
			Color newColor = decider.getNextColor();
			
			for (Color color : generatedColors)
			{
				assertNotNull(color);
				assertNotEquals("New colors must be unique", color, newColor);
			}
			
			generatedColors.add(newColor);
		}
	}
	
	// TODO: testColorDiversity
	
}
