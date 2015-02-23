package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.NavigationPanel;

public class TaskManagement implements Screen
{
	private class addTasksButtonListener extends ClickListener
	{
		
		public addTasksButtonListener()
		{
			
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			//TODO implement adding of tasks
		}
	}
	
	private class removeTasksButtonListener extends ClickListener
	{
		
		public removeTasksButtonListener()
		{
			
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			//TODO implement adding of tasks
		}
	}
	
	private Game game;
	private Stage stage;
	private Skin skin;
	private List<String> actionBasedTasksList, nonActionBasedTasksList;
	private List<String> chosenTasksList;
	
	public TaskManagement(Game game)
	{
		this.game = game;
		this.stage = new Stage();
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		Table mainTable = new Table();
		mainTable.setSize(0.70f*stage.getWidth(), 0.70f*stage.getHeight());
		
		Table leftSection = new Table();
		Table rightSection = new Table();
		Table actionBased = new Table();
		Table nonActionBased = new Table();
		
		TextButton actionBasedLabel = new TextButton("Action Based", skin);
		actionBasedLabel.setDisabled(true);
		actionBasedLabel.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		TextButton nonActionBasedLabel = new TextButton("Non-Action Based", skin);
		nonActionBasedLabel.setDisabled(true);
		nonActionBasedLabel.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		TextButton addTasksButton = new TextButton("Add Tasks", skin);
		addTasksButton.addListener(new addTasksButtonListener());
		
		//Label actionBasedTasksList = new Label("Action Based", skin);
		//Label nonActionBasedTasksList = new Label("Non-Action Based", skin);
		actionBasedTasksList = new List<String>(skin);
		nonActionBasedTasksList = new List<String>(skin);
		ScrollPane actionBasedTasksScroll = new ScrollPane(actionBasedTasksList, skin);
		ScrollPane nonActionBasedTasksScroll = new ScrollPane(nonActionBasedTasksList, skin);
		
		actionBased.add(actionBasedLabel).fillX().center();
		actionBased.row();
		actionBased.add(actionBasedTasksScroll).expand().fill().center();
		
		nonActionBased.add(nonActionBasedLabel).fillX().center();
		nonActionBased.row();
		nonActionBased.add(nonActionBasedTasksScroll).expand().fill().center();
		
		rightSection.add(actionBased).expand().fill();
		rightSection.row();
		rightSection.add(nonActionBased).expand().fill();
		rightSection.row();
		rightSection.add(addTasksButton).fillX().center();
		
		TextButton tasksListLabel = new TextButton("Chosen Tasks", skin);
		tasksListLabel.setDisabled(true);
		tasksListLabel.setColor(0.5f, 0.5f, 0.5f, 1.0f);
		chosenTasksList = new List<String>(skin);
		ScrollPane chosenTasksScroll = new ScrollPane(chosenTasksList, skin);
		TextButton removeTasksButton = new TextButton("remove", skin);
		addTasksButton.addListener(new removeTasksButtonListener());
		
		leftSection.add(tasksListLabel).fillX().align(Align.center);
		leftSection.row();
		leftSection.add(chosenTasksScroll).expand().fill().center();
		leftSection.row();
		leftSection.add(removeTasksButton).center();

		mainTable.add(leftSection).expand().fill();
		mainTable.add(rightSection).expand().fill();
		mainTable.setPosition(stage.getWidth()/2 - mainTable.getWidth()/2, stage.getHeight()/2 - mainTable.getHeight()/2);
		
		CornerAdvisor advisor = new CornerAdvisor("Test");
		float padding = 0.01f * stage.getHeight();
		float advisorLeft = stage.getWidth() - advisor.getPrefWidth() - padding;
		float advisorBottom = stage.getHeight() - advisor.getPrefHeight() - padding;
		advisor.setPosition(advisorLeft, advisorBottom);
		
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
		actionBased.debug();
		nonActionBased.debug();
		
		updateAvailableTasks();
		updateChosenTasks();
		
		stage.addActor(navigationPanel);
		stage.addActor(advisor);
		stage.addActor(mainTable);
	}
	
	public void updateAvailableTasks()
	{
		String[] actionBasedList = {"task 1", "task 2", "task 3", "task 4", "task 5"};
		String[] nonActionBasedList = {"task 6", "task 7", "task 8", "task 9", "task 10"};
		
		actionBasedTasksList.clear();
		nonActionBasedTasksList.clear();
		
		actionBasedTasksList.setItems(actionBasedList);
		nonActionBasedTasksList.setItems(nonActionBasedList);
	}
	
	public void updateChosenTasks()
	{
		String[] chosenTasks = {"task 11", "task 12", "task 13", "task 14", "task 15", "task 16",
								"task 21", "task 22", "task 23", "task 24", "task 25", "task 26",
								"task 31", "task 32", "task 33", "task 34", "task 35", "task 36"};
		chosenTasksList.clear();
		chosenTasksList.setItems(chosenTasks);
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
