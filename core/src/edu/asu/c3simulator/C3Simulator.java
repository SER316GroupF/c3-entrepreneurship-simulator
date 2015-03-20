package edu.asu.c3simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import edu.asu.c3simulator.screens.DifficultySelectionScreen;
import edu.asu.c3simulator.simulation.C3Simulation;

/**
 * Main driver for C3 program.
 * 
 * @author Moore, Zachary
 *
 */
public class C3Simulator extends Game
{
	public static ShapeRenderer rendererFilled;
	public static ShapeRenderer rendererLine;
	public static ShapeRenderer rendererPoint;
	
	private C3Simulation simulation;
	
	@Override
	public void create()
	{
		rendererFilled = new ShapeRenderer();
		rendererLine = new ShapeRenderer();
		rendererPoint = new ShapeRenderer();
		Screen firstScreen = new DifficultySelectionScreen(this);
		this.setScreen(firstScreen);
	}
	
	@Override
	public void render()
	{
		beginRenderers();
		super.render();
		endRenderers();
		
		update();
	}
	
	private void update()
	{
		float delta = Gdx.graphics.getDeltaTime();
		simulation.update(delta);
	}
	
	/**
	 * Readies {@value #rendererFilled}, {@value #rendererLine}, and
	 * {@value #rendererPoint} for drawing. This should be called before each render
	 * cycle.
	 */
	private static void beginRenderers()
	{
		rendererFilled.begin(ShapeType.Filled);
		rendererLine.begin(ShapeType.Line);
		rendererPoint.begin(ShapeType.Point);
	}
	
	/**
	 * Concludes {@value #rendererFilled}, {@value #rendererLine}, and
	 * {@value #rendererPoint}'s drawing cycles. This should be called after each render
	 * cycle.
	 */
	private static void endRenderers()
	{
		rendererFilled.end();
		rendererLine.end();
		rendererPoint.end();
	}
}
