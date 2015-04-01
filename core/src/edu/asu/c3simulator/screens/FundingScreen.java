package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.simulation.SimulationScreen;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.NavigationPanelFactory;

/**
 * This screen still requires implementation. It was created and added to show the
 * functionality of the Business Creation Guide.
 * 
 * @author Alyahya, Mohammed
 */
public class FundingScreen implements SimulationScreen
{
	private Game game;
	private Stage stage;
	private Skin skin;
	
	public FundingScreen(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		Label ScreenDiscription = new Label("Funding Screen", skin);
		
		ScreenDiscription.setPosition(stage.getWidth() / 2, stage.getHeight() / 2);
		
		TextButton continueButton = new TextButton("Continue >", skin);
		continueButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				// TODO: Transition to Tasks Screen
				game.setScreen(AllManagementScreens.TASKS.getInstance());
			}
		});
		
		continueButton.setPosition(stage.getWidth() - continueButton.getPrefWidth()
				- (0.01f * stage.getWidth()), (0.01f * stage.getWidth()));
		
		// TODO add Corner Advisor
		HomeButton homeButton = new HomeButton(game);
		
		stage.addActor(homeButton);
		stage.addActor(continueButton);
		stage.addActor(ScreenDiscription);
	}
	
	@Override
	public void createNavigationPanel()
	{
		stage.addActor(NavigationPanelFactory.getBusinessCreationGuideNavigationPanel(
				game, stage));
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
		// do nothing
	}
	
	@Override
	public void resume()
	{
		// do nothing
	}
	
	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
	}
}
