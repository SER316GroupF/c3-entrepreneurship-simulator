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
import edu.asu.c3simulator.widgets.NavigationPanel;

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
				game.setScreen(new TasksScreen(game));
			}
		});
		
		continueButton.setPosition(stage.getWidth() - continueButton.getPrefWidth()
				- (0.01f * stage.getWidth()), (0.01f * stage.getWidth()));
		
		// TODO add Corner Advisor
		// TODO add Home Button
		
		NavigationPanel navigationPanel = createNavigationPanel();
		
		stage.addActor(navigationPanel);
		stage.addActor(continueButton);
		stage.addActor(ScreenDiscription);
	}
	
	@Override
	public NavigationPanel createNavigationPanel()
	{
		// TODO add screens
		NavigationPanel navigationPanel = new NavigationPanel(game, skin);
		navigationPanel.addButton("Industry", null);
		navigationPanel.addButton("Direction", null);
		navigationPanel.addButton("Funding", null);
		navigationPanel.addButton("Tasks", null);
		
		navigationPanel.setPosition(0.01f * stage.getWidth(), stage.getHeight()
				- (0.3f * stage.getHeight()));
		
		return navigationPanel;
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
