package edu.asu.c3simulator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

import edu.asu.c3simulator.screens.DifficultySelectionScreen;
import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Player;
import edu.asu.c3simulator.simulation.Simulation;
import edu.asu.c3simulator.testing.stubs.PlayerTestingStub;

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
	
	private static C3Simulation simulation;
	private static Player player;
	
	public C3Simulator()
	{
		player = new PlayerTestingStub();
		simulation = new Simulation(getPlayer(), 10000);
	}
	
	public static Player getPlayer()
	{
		return player;
	}
	
	public static C3Simulation getSimulation()
	{
		return simulation;
	}
	
	public static void clearScreen()
	{
		int clearMask = GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT;
		Gdx.graphics.getGL20().glClearColor(0, 0, 0, 1);
		Gdx.graphics.getGL20().glClear(clearMask);
	}
	
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
