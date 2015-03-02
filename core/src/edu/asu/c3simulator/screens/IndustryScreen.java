package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import edu.asu.c3simulator.widgets.Location;

/**
 * This screen still requires implementation. 
 * It was created and added to show the functionality of the Business Creation Guide.
 * 
 * @author Alyahya, Mohammed
 */
public class IndustryScreen extends BusinessCreationGuideScreens 
{
	Skin skin;
	Stage stage;
	public IndustryScreen(Stage stage)
	{
		this.stage = stage;
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		Table directions = new Table();
		
		Label ScreenDiscription = new Label("Industry Screen", skin);
		
		directions.add(ScreenDiscription);
		
		directions.setTransform(true);
		
		addActor(directions, new Location(stage.getWidth()/2, stage.getHeight()/2));
	}
}
