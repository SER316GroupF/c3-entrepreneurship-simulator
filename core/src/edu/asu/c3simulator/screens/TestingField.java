package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Player;
import edu.asu.c3simulator.util.Observer;
import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.Padding;
import edu.asu.c3simulator.widgets.PlayerStatusDisplay;
import edu.asu.c3simulator.widgets.TextAreaX;
import edu.asu.c3simulator.widgets.WidgetFactory;

/**
 * Development screen to play with and test new features and graphic elements before using
 * them in production code.
 * 
 * This class should not be deployed.
 * 
 * @author Moore, Zachary
 * 
 */
public class TestingField implements Screen
{
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	
	@SuppressWarnings("unused")
	private Game game;
	
	private Stage stage;
	@SuppressWarnings("unused")
	private Skin skin;
	
	public TestingField(Game game)
	{
		this.game = game;
		
		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH, DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		// sandboxTestAreaX();
		// sandboxCornerAdvisor();
		// sandboxPlayerStatusDisplay();
		sandboxBulletedList();
	}
	
	private void sandboxBulletedList()
	{
		String texturePath = "images/bullet_white.png";
		Texture bulletTexture = new Texture(texturePath);
		Actor list = WidgetFactory.createBulletedList(bulletTexture, "Item 1", "Item 2", "Item 3");
		
		list.setPosition(500, 500);
		stage.addActor(list);
	}
	
	private void sandboxPlayerStatusDisplay()
	{
		PlayerStatusDisplay display = new PlayerStatusDisplay(new Player() {
			
			@Override
			public void registerObserver(Observer<Player> observer)
			{
				
			}
			
			@Override
			public String getStatus()
			{
				return "testStatus";
			}
			
			@Override
			public C3Simulation getSimulation()
			{
				return new C3Simulation() {
					@Override
					public String getSimulationDate()
					{
						return "5 Jan, Year TEST";
					}
				};
			}
			
			@Override
			public int getNetWorth()
			{
				return 1000000;
			}
			
			@Override
			public int getCapital()
			{
				return 100000;
			}
		});
		
		display.left().bottom();
		float displayX = display.getX() + 30;
		float displayY = DESIGN_HEIGHT - display.getPrefHeight() - 20;
		display.setPosition(displayX, displayY);
		stage.addActor(display);
	}
	
	private void sandboxCornerAdvisor()
	{
		String text = "This is a test of TextAreaX. This is intended to cover multiple lines at a width of 200px. This is the second extention";
		CornerAdvisor advisor = new CornerAdvisor(text);
		
		advisor.debug();
		advisor.setPosition(500, 500);
		
		stage.addActor(advisor);
	}
	
	private void sandboxTestAreaX()
	{
		String text = "This is a test of TextAreaX. This is intended to cover multiple lines at a width of 200px. This is the second extention";
		TextAreaX textArea = new TextAreaX(text, 200, 0.5f);
		textArea.setBackground("images/text-bubble-white.png");
		textArea.setBorderPadding(new Padding(20, 50, 15, 10));
		
		Table debug = new Table();
		debug.setPosition(500, 500);
		debug.add(textArea);
		debug.debug();
		
		TextAreaX textArea2 = new TextAreaX(text, 400);
		textArea2.setBackground("images/text-bubble-white.png");
		textArea2.setBorderPadding(new Padding(20, 50, 15, 10));
		textArea2.setPosition(800, 500);
		
		stage.addActor(debug);
		stage.addActor(textArea2);
	}
	
	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
	}
	
	@Override
	public void hide()
	{
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void render(float delta)
	{
		stage.act(delta);
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
	}
	
	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
	}
	
	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}
	
}
