package edu.asu.c3simulator.screens;

import java.util.Hashtable;
import java.util.Set;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import edu.asu.c3simulator.widgets.Location;

/**
 * Parents class containing methods that interact with the business creation guide.
 * 
 * @author Alyahya, Mohammed
 */
public class BusinessCreationGuideScreens
{
	protected Hashtable<Actor, Location> screenActorslist;
	public BusinessCreationGuideScreens()
	{
		screenActorslist = new Hashtable<Actor, Location>();
	}
	
	protected void addActor(Actor actor, Location location)
	{
		screenActorslist.put(actor, location);
	}
	
	public void showScreen(Stage stage)
	{
		Set<Actor> screenActorslistKeys = screenActorslist.keySet();
		for(Actor actor : screenActorslistKeys)
		{
			stage.addActor(actor);
			actor.setPosition(screenActorslist.get(actor).getX(), screenActorslist.get(actor).getY());
		}
	}
	
	public void hideScreen(Stage stage)
	{
		Set<Actor> screenActorslistKeys = screenActorslist.keySet();
		for(Actor actor : screenActorslistKeys)
		{
			actor.remove();
		}
	}
}
