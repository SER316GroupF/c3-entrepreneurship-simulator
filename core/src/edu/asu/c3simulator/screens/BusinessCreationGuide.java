package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Display a panel consisting of steps necessary to create a business and navigates
 * through them. Includes a button to continue on to the next step.
 * 
 * @author Alyahya, Mohammed
 * 
 */
public class BusinessCreationGuide implements Screen
{
	private class navigationButtonListener extends ClickListener
	{
		BusinessCreationGuideScreens targetedScreen;
		String screenName;
		
		/**
		 * @param targetedScreen
		 *            The screen which the button will show.
		 * @param screenName
		 *            The name of the screen.
		 */
		public navigationButtonListener(BusinessCreationGuideScreens targetedScreen,
				String screenName)
		{
			this.targetedScreen = targetedScreen;
			this.screenName = screenName;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			System.out.println(screenName);
			showScreen(targetedScreen);
		}
	}
	
	private class progressButtonListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			if (currentScreen == industryScreen)
			{
				System.out.println("Transitioning to Direction Screen");
				showScreen(directionScreen);
			}
			else if (currentScreen == directionScreen)
			{
				System.out.println("Transitioning to funding Screen");
				showScreen(fundingScreen);
			}
			else if (currentScreen == fundingScreen)
			{
				System.out.println("Transitioning to tasks Screen");
				showScreen(tasksScreen);
			}
			else if (currentScreen == tasksScreen)
			{
				System.out.println("Transitioning to Home Screen");
				// TODO: Transition to main hub
				// TODO: pass user choices to data layer
			}
		}
	}
	
	@SuppressWarnings("unused")
	private Game game;
	private Stage stage;
	private Skin skin;
	private BusinessCreationGuideScreens industryScreen, directionScreen, fundingScreen,
			tasksScreen, currentScreen;
	TextButton progressButton;
	
	public BusinessCreationGuide(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		industryScreen = new IndustryScreen(stage); // IndustryScreen();
		directionScreen = new BusinessDirectonScreen(stage);
		fundingScreen = new FundingScreen(stage); // FundingScreen();
		tasksScreen = new TasksScreen(stage); // TasksScreen();
		
		TextButton industryButton = new TextButton("Industry", skin);
		TextButton directonButton = new TextButton("Directon", skin);
		TextButton fundingButton = new TextButton("Funding", skin);
		TextButton tasksButton = new TextButton("Tasks", skin);
		
		industryButton.addListener(new navigationButtonListener(industryScreen,
				"Industry"));
		directonButton.addListener(new navigationButtonListener(directionScreen,
				"Directon"));
		fundingButton.addListener(new navigationButtonListener(fundingScreen, "Funding"));
		tasksButton.addListener(new navigationButtonListener(tasksScreen, "Tasks"));
		
		Table navigationButtons = new Table();
		navigationButtons.add(industryButton).fillX().padTop(10);
		navigationButtons.row();
		navigationButtons.add(directonButton).fillX().padTop(10);
		navigationButtons.row();
		navigationButtons.add(fundingButton).fillX().padTop(10);
		navigationButtons.row();
		navigationButtons.add(tasksButton).fillX().padTop(10);
		
		navigationButtons.center();
		
		float navigationButtonsLeft = 0.05f * stage.getWidth();
		float navigationButtonsBottom = (stage.getHeight() / 2)
				+ (navigationButtons.getPrefHeight());
		navigationButtons.setTransform(true);
		navigationButtons.setPosition(navigationButtonsLeft, navigationButtonsBottom);
		
		progressButton = new TextButton("Continue >", skin);
		progressButton.addListener(new progressButtonListener());
		
		progressButton.setPosition(stage.getWidth() - progressButton.getPrefWidth()
				- (0.01f * stage.getWidth()), (0.01f * stage.getWidth()));
		
		// TODO add Corner Advisor
		// TODO add Home Button
		
		stage.addActor(navigationButtons);
		stage.addActor(progressButton);
		
		currentScreen = industryScreen;
		showScreen(industryScreen);
	}
	
	/**
	 * This methods hides the current screen before displaying the new one and displays
	 * the appropriate progress Button.
	 * 
	 * @param requestedScreen
	 *            The screen which will be displayed
	 */
	private void showScreen(BusinessCreationGuideScreens requestedScreen)
	{
		currentScreen.hideScreen();
		requestedScreen.showScreen(stage);
		currentScreen = requestedScreen;
		displayAppropriateProgressButton();
	}
	
	/**
	 * This displays the appropriate progress Button.
	 */
	private void displayAppropriateProgressButton()
	{
		if (currentScreen == tasksScreen)
			progressButton.setText("Done >");
		else
			progressButton.setText("Continue >");
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
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
		// throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		// throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
		
	}
	
}
