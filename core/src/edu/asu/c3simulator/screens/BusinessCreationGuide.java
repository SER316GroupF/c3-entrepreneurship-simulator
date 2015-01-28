package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.widgets.CornerAdvisor;

/**
 * Display a panel consisting of steps necessary to create a business and navigates through them. 
 * Includes a button to continue on to the next step.
 * 
 * @author Alyahya, Mohammed
 * 
 */
public class BusinessCreationGuide implements Screen
{
	private class navigationButtonListener extends ClickListener
	{
		Screen targetedScreen;
		String screenName;
		
		public navigationButtonListener(Screen targetedScreen, String screenName)
		{
			this.targetedScreen = targetedScreen;
			this.screenName = screenName;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			System.out.println(screenName);
			game.setScreen(targetedScreen);
		}
	}
	
	private static final String ADVISOR_TEXT = "This is a test of TextAreaX. This is intended to cover multiple lines at a width of 200px. This is the second extention";
	protected Game game;
	protected Stage stage;
	protected Skin skin;
	protected static Screen industryScreen, directonScreen, fundingScreen, tasksScreen;
	
	public BusinessCreationGuide(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		industryScreen = new BusinessDirectonScreen(); //IndustryScreen();
		directonScreen = new BusinessDirectonScreen();
		fundingScreen = new BusinessDirectonScreen(); //FundingScreen();
		tasksScreen = new BusinessDirectonScreen(); //TasksScreen();
		
		this.game.setScreen(directonScreen);
	}
	
	public BusinessCreationGuide()
	{
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		TextButton industryButton = new TextButton("Industry", skin);
		TextButton directonButton = new TextButton("Directon", skin);
		TextButton fundingButton = new TextButton("Funding", skin);
		TextButton tasksButton = new TextButton("Tasks", skin);
		
		industryButton.addListener(new navigationButtonListener(industryScreen, "Industry"));
		directonButton.addListener(new navigationButtonListener(directonScreen, "Directon"));
		fundingButton.addListener(new navigationButtonListener(fundingScreen, "Funding"));
		tasksButton.addListener(new navigationButtonListener(tasksScreen, "Tasks"));
		
		VerticalGroup navigationButtons = new VerticalGroup();
		navigationButtons.addActor(industryButton);
		navigationButtons.addActor(directonButton);
		navigationButtons.addActor(fundingButton);
		navigationButtons.addActor(tasksButton);
		
		navigationButtons.center();
		navigationButtons.space(10);
		
		float navigationButtonsLeft = 0.05f * stage.getWidth();
		float navigationButtonsBottom = (stage.getHeight()/2) + (navigationButtons.getPrefHeight());
		navigationButtons.setTransform(true);
		navigationButtons.setPosition(navigationButtonsLeft, navigationButtonsBottom);
		
		TextButton continueButton;
		if(this instanceof BusinessDirectonScreen) //TasksScreen)
		{
			continueButton = new TextButton("Done >", skin);
			continueButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					resetBusinessCreationGuide();
					// TODO: Transition to main hub
					// TODO: pass user choices to data layer
				}
			});
		}
		else
		{
			continueButton = new TextButton("Continue >", skin);
			continueButton.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					System.out.println("Continue Pressed");
					if(game.getScreen() == industryScreen)
						game.setScreen(directonScreen);
					if(game.getScreen() == directonScreen)
						game.setScreen(fundingScreen);
					if(game.getScreen() == fundingScreen)
						game.setScreen(tasksScreen);
				}
			});
		}
		
		continueButton.setPosition(stage.getWidth()-continueButton.getPrefWidth() - (0.01f * stage.getWidth()),(0.01f * stage.getWidth()));
		
		CornerAdvisor advisor = new CornerAdvisor(ADVISOR_TEXT);
		float padding = 0.01f * stage.getHeight();
		float advisorLeft = stage.getWidth() - advisor.getPrefWidth() - padding;
		float advisorBottom = stage.getHeight() - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);
		
		stage.addActor(advisor);
		stage.addActor(navigationButtons);
		stage.addActor(continueButton);
	}
	
	void resetBusinessCreationGuide()
	{
		industryScreen = null;
		directonScreen = null;
		fundingScreen = null;
		tasksScreen = null;
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
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
		
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
		//throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		//throw new UnsupportedOperationException("The method is not implemented yet.");
	}

	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
		
	}
	
}
