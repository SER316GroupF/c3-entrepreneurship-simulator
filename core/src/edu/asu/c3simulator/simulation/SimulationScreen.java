package edu.asu.c3simulator.simulation;

import com.badlogic.gdx.Screen;

/**
 * Ensures that screens have a createNavigationPanel method which can be called
 * by the enumation screen managment class after all screen are created.
 * 
 * @author Alyahya, Mohammed
 */
public interface SimulationScreen extends Screen
{
	/**
	 * The method creates the navigation panel that will be called by the
	 * enumation screen managment class after creating each screen.
	 * 
	 * edited by Justin
	 */
	void createNavigationPanel();
}
