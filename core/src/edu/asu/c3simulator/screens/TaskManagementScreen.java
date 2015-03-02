package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import edu.asu.c3simulator.widgets.NavigationPanel;

/**
 * This screen creates and displays an instance of the Task Management class for the
 * Council Section.
 * 
 * @author Alyahya, Mohammed
 */
public class TaskManagementScreen implements Screen
{
	@SuppressWarnings("unused")
	private Game game;
	private Stage stage;
	private Skin skin;
	
	public TaskManagementScreen(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		TaskManagement taskManagementComponents = new TaskManagement(stage, skin);
		
		// TODO add Corner Advisor
		// TODO add Home Button
		
		// TODO add screens
		NavigationPanel navigationPanel = new NavigationPanel(game, skin);
		navigationPanel.addButton("Company", null);
		navigationPanel.addSubButton("Company", "Business", null);
		navigationPanel.addSubButton("Company", "Assets", null);
		navigationPanel.addButton("Tasks", null);
		navigationPanel.addSubButton("Tasks", "Manage", null);
		navigationPanel.addSubButton("Tasks", "Completed", new CompletedTasks(game));
		navigationPanel.showSubButtonsFor("Tasks");
		
		navigationPanel.setPosition(0.01f * stage.getWidth(), stage.getHeight()
				- (0.3f * stage.getHeight()));
		
		stage.addActor(navigationPanel);
		stage.addActor(taskManagementComponents);
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
