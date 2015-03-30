package edu.asu.c3simulator.simulation;

import com.badlogic.gdx.Screen;

import edu.asu.c3simulator.widgets.NavigationPanel;

/**
 * Ensures that screens have a createNavigationPanel method which can be called by the
 * enumation screen managment class after all screen are created.
 * 
 * @author Alyahya, Mohammed
 */
public interface SimulationScreen extends Screen
{
	/**
	 * The method creates the navigation panel that will be called by the enumation screen
	 * managment class after creating each screen.
	 * 
	 * @return the NavigationPanel actor that was created.
	 */
	NavigationPanel createNavigationPanel();
}
