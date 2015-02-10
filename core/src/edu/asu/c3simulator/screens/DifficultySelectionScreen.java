package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.WidgetFactory;

/**
 * Allows the user to select the game difficulty. Each available difficulty is displayed
 * along with the details of the difficulty. Clicking an option will transition the player
 * to the main hub
 * 
 * @author Moore, Zachary
 * 
 */
public class DifficultySelectionScreen implements Screen
{
	private static final String ADVISOR_TEXT = "This is a test of TextAreaX. This is intended to cover multiple lines at a width of 200px. This is the second extention";
	
	/**
	 * Width and Height at which this screen was designed. Can be used in resize
	 * operations
	 */
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	
	/** Convenience constants */
	private static final int DESIGN_SCREEN_CENTER_X = DESIGN_WIDTH / 2;
	private static final int DESIGN_SCREEN_CENTER_Y = DESIGN_HEIGHT / 2;
	
	/** Used to transition to other game screens */
	@SuppressWarnings("unused")
	private Game game;
	
	/** Handles all components of this screen (rendering, updating, resizing, etc) */
	private Stage stage;
	
	/** Specifies textures to use for default widgets such as Buttons and Labels */
	private Skin skin;
	
	/**
	 * @param game
	 *            Will be used to call {@link Game#setScreen(Screen)} when transitioning
	 */
	public DifficultySelectionScreen(Game game)
	{
		this.game = game;
		
		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH, DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		Table choices = new Table();
		
		// REFACTOR: load from file
		String[] descriptionEasy = new String[] { "$8 000 startup", "Tips Display",
				"Modified Competition" };
		String[] descriptionHard = new String[] { "$2 000 startup",
				"Realistic Competition" };
		
		WidgetFactory factory = new WidgetFactory(skin);
		Actor difficultyChoiceEasy = factory.createVerticalListTitled("Easy",
				descriptionEasy);
		Actor difficultyChoiceHard = factory.createVerticalListTitled("Hard",
				descriptionHard);
		
		// REFACTOR: Generalize these listeners, and/or outsource them to a factory
		difficultyChoiceEasy.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				// TODO: Transition to main hub
				// TODO: Instantiate game instance
				System.out.println("Easy");
			}
		});
		
		// REFACTOR: Generalize these listeners, and/or outsource them to a factory
		difficultyChoiceHard.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				// TODO: Transition to main hub
				// TODO: Instantiate game instance
				System.out.println("Hard");
			}
		});
		
		choices.add(difficultyChoiceEasy).top();
		choices.add(difficultyChoiceHard).top().spaceLeft(75);
		
		choices.setTransform(true);
		choices.setPosition(DESIGN_SCREEN_CENTER_X, DESIGN_SCREEN_CENTER_Y);
		
		CornerAdvisor advisor = new CornerAdvisor(ADVISOR_TEXT);
		float padding = 0.01f * DESIGN_HEIGHT;
		float advisorLeft = DESIGN_WIDTH - advisor.getPrefWidth() - padding;
		float advisorBottom = DESIGN_HEIGHT - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);
		
		stage.addActor(advisor);
		stage.addActor(choices);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#dispose()
	 */
	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#hide()
	 */
	@Override
	public void hide()
	{
		// Do not register input if this screen is not active
		Gdx.input.setInputProcessor(null);
	}
	
	/*
	 * (non-Javadoc) Disables input detection for this screen
	 * 
	 * @see com.badlogic.gdx.Screen#pause()
	 */
	@Override
	public void pause()
	{
		Gdx.input.setInputProcessor(null);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#render(float)
	 */
	@Override
	public void render(float delta)
	{
		stage.act(delta);
		stage.draw();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resize(int, int)
	 */
	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#resume()
	 */
	@Override
	public void resume()
	{
		Gdx.input.setInputProcessor(stage);
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.badlogic.gdx.Screen#show()
	 */
	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}
	
}
