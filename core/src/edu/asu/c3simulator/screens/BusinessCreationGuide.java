package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;

/**
 * Display a panel consisting of steps necessary to create a business and navigates
 * through them. Includes a button to continue on to the next step.
 * 
 * @author Alyahya, Mohammed
 * 
 */
public class BusinessCreationGuide
{
	@SuppressWarnings("unused")
	private Game game;
	
	public BusinessCreationGuide(Game game)
	{
		
	}
	
	private static void resetAllGuideScreens()
	{
		// TODO reset all screens
		// ex. BusinessDirectionScreen.resetSelection();
	}
	
	public static void createNewBusiness()
	{
		// TODO: Get Selections and create new Business
		// TODO: Transition to main hub
		resetAllGuideScreens();
	}
}
