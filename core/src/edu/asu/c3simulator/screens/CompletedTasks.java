package edu.asu.c3simulator.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import edu.asu.c3simulator.simulation.SimulationScreen;
import edu.asu.c3simulator.util.Task;
import edu.asu.c3simulator.widgets.HomeButton;
import edu.asu.c3simulator.widgets.NavigationPanelFactory;

/**
 * This class displays a screen that shows a list of completed tasks divided into two
 * categories: Action Based and Non-Action Based.
 * 
 * @author Alyahya, Mohammed
 */
public class CompletedTasks implements SimulationScreen
{
	private Game game;
	private Stage stage;
	private Skin skin;
	private VerticalGroup actionBasedTasksList, nonActionBasedTasksList;
	private final Color LABEL_COLOR = Color.GRAY;
	
	public CompletedTasks(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		Table mainTable = new Table();
		mainTable.setSize(0.60f * stage.getWidth(), 0.60f * stage.getHeight());
		
		Table upperSection = new Table();
		Table lowerSection = new Table();
		Table leftSection = new Table();
		Table rightSection = new Table();
		
		Label completedTasksLabel = new Label("Completed Tasks", skin);
		upperSection.add(completedTasksLabel).expand().fill();
		
		TextButton actionBasedLabel = new TextButton("Action Based", skin);
		actionBasedLabel.setDisabled(true);
		actionBasedLabel.setColor(LABEL_COLOR);
		TextButton nonActionBasedLabel = new TextButton("Non-Action Based", skin);
		nonActionBasedLabel.setDisabled(true);
		nonActionBasedLabel.setColor(LABEL_COLOR);
		
		actionBasedTasksList = new VerticalGroup();
		nonActionBasedTasksList = new VerticalGroup();
		ScrollPane actionBasedTasksScroll = new ScrollPane(actionBasedTasksList, skin);
		ScrollPane nonActionBasedTasksScroll = new ScrollPane(nonActionBasedTasksList,
				skin);
		
		leftSection.add(actionBasedLabel).fillX().center();
		leftSection.row();
		leftSection.add(actionBasedTasksScroll).expand().fill().center();
		
		rightSection.add(nonActionBasedLabel).fillX().center();
		rightSection.row();
		rightSection.add(nonActionBasedTasksScroll).expand().fill().center();
		
		lowerSection.add(leftSection).expand().fill();
		lowerSection.add(rightSection).expand().fill();
		mainTable.add(upperSection).fillX().row();
		mainTable.add(lowerSection).expand().fill();
		mainTable.setPosition(stage.getWidth() / 2 - mainTable.getWidth() / 2,
				stage.getHeight() / 2 - mainTable.getHeight() / 2);
		
		// TODO add Corner Advisor
		HomeButton home = new HomeButton(game);
		
		updateCompletedTasks();
		
		stage.addActor(home);
		stage.addActor(mainTable);
	}
	
	/**
	 * This method is called to update the list of completed tasks.
	 */
	public void updateCompletedTasks()
	{
		ArrayList<Task> completedTasks = getCompletedTasks();
		actionBasedTasksList.clear();
		nonActionBasedTasksList.clear();
		
		for (Task task : completedTasks)
		{
			Label newCompletedTask = new Label(" " + task.getName(), skin);
			if (task.getType() == Task.Type.ACTION_BASED)
				actionBasedTasksList.addActor(newCompletedTask);
			else
				nonActionBasedTasksList.addActor(newCompletedTask);
		}
		
		actionBasedTasksList.left();
		nonActionBasedTasksList.left();
	}
	
	/**
	 * This method creates a testing shell of different tasks to test and demo the GUI.
	 * 
	 * @return an ArrayList of the completed tasks that are going to be displayed.
	 */
	private ArrayList<Task> getCompletedTasks()
	{
		String[] actionBasedList = { "task 1.1", "task 2.1", "task 3.1", "task 4.1",
				"task 5.1", "task 6.1", "task 7.1", "task 8.1", "task 9.1", "task 10.1",
				"task 11.1", "task 12.1", "task 13.1", "task 14.1", "task 15.1" };
		String[] nonActionBasedList = { "task 1.2", "task 2.2", "task 3.2", "task 4.2",
				"task 5.2", "task 6.2", "task 7.2", "task 8.2", "task 9.2", "task 10.2" };
		
		// TODO replace with functional code that request completed tasks from
		// company
		// datta layer when tasks are implemented.
		
		ArrayList<Task> availableTasks = new ArrayList<Task>();
		
		for (String taskName : actionBasedList)
			availableTasks.add(new Task(taskName, Task.Type.ACTION_BASED));
		
		for (String taskName : nonActionBasedList)
			availableTasks.add(new Task(taskName, Task.Type.NON_ACTION_BASED));
		
		return availableTasks;
	}
	
	@Override
	public void createNavigationPanel()
	{
		stage.addActor(NavigationPanelFactory.getCouncilNavigationPanel(game, stage));
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
