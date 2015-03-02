package edu.asu.c3simulator.screens;

import java.util.ArrayList;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

import edu.asu.c3simulator.util.Task;
import edu.asu.c3simulator.widgets.NavigationPanel;

public class CompletedTasks implements Screen
{
	@SuppressWarnings("unused")
	private Game game;
	private Stage stage;
	private Skin skin;
	private VerticalGroup actionBasedTasksList, nonActionBasedTasksList;
	
	public CompletedTasks(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		Table mainTable = new Table();
		mainTable.setSize(0.60f*stage.getWidth(), 0.60f*stage.getHeight());
		
		Table upperSection = new Table();
		Table lowerSection = new Table();
		Table leftSection = new Table();
		Table rightSection = new Table();
		
		Label completedTasksLabel = new Label("Completed Tasks", skin);
		upperSection.add(completedTasksLabel).expand().fill();
		
		TextButton actionBasedLabel = new TextButton("Action Based", skin);
		actionBasedLabel.setDisabled(true);
		actionBasedLabel.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		TextButton nonActionBasedLabel = new TextButton("Non-Action Based", skin);
		nonActionBasedLabel.setDisabled(true);
		nonActionBasedLabel.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		
		actionBasedTasksList = new VerticalGroup();
		nonActionBasedTasksList = new VerticalGroup();
		ScrollPane actionBasedTasksScroll = new ScrollPane(actionBasedTasksList, skin);
		ScrollPane nonActionBasedTasksScroll = new ScrollPane(nonActionBasedTasksList, skin);
		
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
		mainTable.setPosition(stage.getWidth()/2 - mainTable.getWidth()/2, stage.getHeight()/2 - mainTable.getHeight()/2);
		
		//TODO add Corner Advisor
		//TODO add Home Button
		
		//TODO add screens
		NavigationPanel navigationPanel = new NavigationPanel(game, skin);
		navigationPanel.addButton("Company", null);
		navigationPanel.addSubButton("Company", "Business", null);
		navigationPanel.addSubButton("Company", "Assets", null);
		navigationPanel.addButton("Tasks", null);
		navigationPanel.addSubButton("Tasks", "Manage", null);
		navigationPanel.addSubButton("Tasks", "Completed", null);
		navigationPanel.showSubButtonsFor("Tasks");
		
		navigationPanel.setPosition(0.01f * stage.getWidth(), stage.getHeight() - (0.3f * stage.getHeight()));
		
		mainTable.debug();
		leftSection.debug();
		rightSection.debug();
		
		updateCompletedTasks();
		
		stage.addActor(navigationPanel);
		stage.addActor(mainTable);
	}
	
	public void updateCompletedTasks()
	{
		ArrayList<Task> completedTasks = getCompletedTasks();
		actionBasedTasksList.clear();
		nonActionBasedTasksList.clear();
		
		for(Task task : completedTasks)
		{
			Label newCompletedTask = new Label(" "+task.getName(), skin);
			if(task.getType().equalsIgnoreCase("Action Based"))
				actionBasedTasksList.addActor(newCompletedTask);
			else
				nonActionBasedTasksList.addActor(newCompletedTask);
		}
		
		actionBasedTasksList.left();
		nonActionBasedTasksList.left();
	}
	
	private ArrayList<Task> getCompletedTasks()
	{
		String[] actionBasedList = {"task 1.1", "task 2.1", "task 3.1", "task 4.1", "task 5.1", "task 6.1", "task 7.1",
									"task 8.1", "task 9.1", "task 10.1", "task 11.1", "task 12.1", "task 13.1", "task 14.1", "task 15.1"};
		String[] nonActionBasedList = {"task 1.2", "task 2.2", "task 3.2", "task 4.2", "task 5.2", "task 6.2", "task 7.2", "task 8.2", "task 9.2", "task 10.2"};
		
		ArrayList<Task> availableTasks = new ArrayList<Task>(); 
		
		for(String taskName : actionBasedList)
			availableTasks.add(new Task(taskName, "Action Based"));
		
		for(String taskName : nonActionBasedList)
			availableTasks.add(new Task(taskName, "Non-Action Based"));
		
		return availableTasks;
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
