package edu.asu.c3simulator.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.simulation.SimulationScreen;
import edu.asu.c3simulator.util.Task;
import edu.asu.c3simulator.widgets.NavigationPanel;

/**
 * This screen creates and displays an instance of the Task Management class for the last
 * step of the Business Creation Guide.
 * 
 * @author Alyahya, Mohammed
 */
public class TasksScreen implements SimulationScreen
{
	private Game game;
	private Stage stage;
	private Skin skin;
	private TaskManagement taskManagementComponents;
	
	public TasksScreen(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		taskManagementComponents = new TaskManagement(stage, skin);
		
		TextButton doneButton = new TextButton("Done >", skin);
		doneButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				BusinessCreationGuide.createNewBusiness();
			}
		});
		
		doneButton.setPosition(stage.getWidth() - doneButton.getPrefWidth()
				- (0.01f * stage.getWidth()), (0.01f * stage.getWidth()));
		
		// TODO add Corner Advisor
		// TODO add Home Button
		
		NavigationPanel navigationPanel = createNavigationPanel();
		
		stage.addActor(navigationPanel);
		stage.addActor(doneButton);
		stage.addActor(taskManagementComponents);
	}
	
	/**
	 * Call the same method in the task management object to get the current list in the
	 * chosen tasks column and return it in array form.
	 * 
	 * @return an ArrayList of the chosen tasks.
	 */
	public ArrayList<Task> getChosenTasks()
	{
		return taskManagementComponents.getChosenTasks();
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
