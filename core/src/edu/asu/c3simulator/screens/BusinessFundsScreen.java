package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.C3Simulator;
import edu.asu.c3simulator.widgets.CountListener;
import edu.asu.c3simulator.widgets.Counter;
import edu.asu.c3simulator.widgets.WidgetFactory;

public class BusinessFundsScreen implements Screen
{
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
	public BusinessFundsScreen(Game game)
	{
		this.game = game;
		
		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH, DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		/*Counter counter = new Counter();
		counter.setPosition(500, 600);
		
		Actor labeledCounter = WidgetFactory.createLabeledCounter("Test", new Counter());
		labeledCounter.setPosition(100, 200);*/
		
		Actor category = createFundCategoryTitle("test", 150);
		category.setPosition(500, 500);
		
		String[] description = new String[] { "testA", "testB" };
		Actor descriptionDisplay = createFundCategoryDescription(description, 250);
		descriptionDisplay.setPosition(category.getX() + category.getWidth() * .2f, category.getY() - descriptionDisplay.getHeight() - category.getHeight() * .5f);
		
		/*stage.addActor(counter);
		stage.addActor(labeledCounter);*/
		
		stage.addActor(category);
		stage.addActor(descriptionDisplay);
	}
	
	private Actor createFundCategoryDescription(String[] body, float width)
	{
		Texture bulletTexture = new Texture(Gdx.files.internal("images/bullet_white.png"));
		return (WidgetFactory.createBulletedList(bulletTexture, body));
	}
	
	private Actor createFundCategoryTitle(String name, float width)
	{
		Counter counter = new Counter();
		Actor title = WidgetFactory.createLabeledCounter(name, counter, width);
		
		counter.addListener(new CountListener() {
			
			@Override
			public void countChanged(Counter source, int delta, int newCount)
			{
				adjustTotalSpentLabel(delta);
			}
		});
		
		return title;
	}
	
	private void adjustTotalSpentLabel(int amount)
	{
		
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
		// Do not register input if this screen is not active
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void pause()
	{
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void render(float delta)
	{
		C3Simulator.clearScreen();
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
		Gdx.input.setInputProcessor(stage);
	}
	
	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}
	
}
