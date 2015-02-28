package edu.asu.c3simulator.screens;

import java.util.ArrayList;
import java.util.Hashtable;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import edu.asu.c3simulator.util.Task;

public class TaskManagement extends Table
{
	private class addTasksButtonListener extends ClickListener
	{	
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			Array<Button> checkedActionBasedTasks = actionBasedTasksGroup.getButtons();
			int numberOfButtons = checkedActionBasedTasks.size;
			for(int index=1; index<=numberOfButtons; index++)
			{
				Button checkedButton = checkedActionBasedTasks.get(numberOfButtons-index);
				if(checkedButton.isChecked())
				{
					chosenTasksList.addActorAt(0, checkedButton);
					chosenTasksGroup.add(checkedButton);
					actionBasedTasksList.removeActor(checkedButton);
					actionBasedTasksGroup.remove(checkedButton);
				}
			}
			
			Array<Button> checkedNonActionBasedTasks = nonActionBasedTasksGroup.getButtons();
			numberOfButtons = checkedNonActionBasedTasks.size;
			for(int index=1; index<=numberOfButtons; index++)
			{
				Button checkedButton = checkedNonActionBasedTasks.get(numberOfButtons-index);
				if(checkedButton.isChecked())
				{
					chosenTasksList.addActorAt(0, checkedButton);
					chosenTasksGroup.add(checkedButton);
					nonActionBasedTasksList.removeActor(checkedButton);
					nonActionBasedTasksGroup.remove(checkedButton);
				}
			}
			actionBasedTasksGroup.uncheckAll();
			nonActionBasedTasksGroup.uncheckAll();
			chosenTasksGroup.uncheckAll();
		}
	}
	
	private class removeTasksButtonListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			Array<Button> chosenTasks = chosenTasksGroup.getButtons();
			int numberOfButtons = chosenTasks.size;
			for(int index=1; index<=numberOfButtons; index++)
			{
				Button checkedButton = chosenTasks.get(numberOfButtons-index);
				CheckBox checkBoxButton = (CheckBox)checkedButton;
				if(checkedButton.isChecked())
				{
					if(displayedTasks.get(checkBoxButton).getType().equalsIgnoreCase("Action Based"))
					{
						actionBasedTasksList.addActor(checkedButton);
						actionBasedTasksGroup.add(checkedButton);
					}
					else
					{
						nonActionBasedTasksList.addActor(checkedButton);
						nonActionBasedTasksGroup.add(checkedButton);
					}
					chosenTasksList.removeActor(checkedButton);
					chosenTasksGroup.remove(checkedButton);
				}
			}
			actionBasedTasksGroup.uncheckAll();
			nonActionBasedTasksGroup.uncheckAll();
			chosenTasksGroup.uncheckAll();
		}
	}
	
	private Skin skin;
	private VerticalGroup actionBasedTasksList, nonActionBasedTasksList, chosenTasksList;
	private ButtonGroup actionBasedTasksGroup, nonActionBasedTasksGroup, chosenTasksGroup;
	private Hashtable<CheckBox, Task> displayedTasks;
	
	public TaskManagement(Stage stage, Skin skin)
	{
		this.skin = skin;
		
		displayedTasks =  new Hashtable<CheckBox, Task>();
		
		this.setSize(0.70f*stage.getWidth(), 0.70f*stage.getHeight());
		
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
		
		actionBasedTasksList = new VerticalGroup();
		nonActionBasedTasksList = new VerticalGroup();
		actionBasedTasksGroup = new ButtonGroup();
		actionBasedTasksGroup.setMaxCheckCount(-1);
		actionBasedTasksGroup.setMinCheckCount(0);
		nonActionBasedTasksGroup = new ButtonGroup();
		nonActionBasedTasksGroup.setMaxCheckCount(-1);
		nonActionBasedTasksGroup.setMinCheckCount(0);
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
		chosenTasksList = new VerticalGroup();
		chosenTasksGroup = new ButtonGroup();
		chosenTasksGroup.setMaxCheckCount(-1);
		chosenTasksGroup.setMinCheckCount(0);
		ScrollPane chosenTasksScroll = new ScrollPane(chosenTasksList, skin);
		TextButton removeTasksButton = new TextButton("remove", skin);
		removeTasksButton.addListener(new removeTasksButtonListener());
		
		leftSection.add(tasksListLabel).fillX().align(Align.center);
		leftSection.row();
		leftSection.add(chosenTasksScroll).expand().fill().center();
		leftSection.row();
		leftSection.add(removeTasksButton).fillX().center();

		this.add(leftSection).expand().fill();
		this.add(rightSection).expand().fill();
		this.setPosition(stage.getWidth()/2 - this.getWidth()/2, stage.getHeight()/2 - this.getHeight()/2);
		
		updateAvailableTasks();
		updateChosenTasks();
	}
	
	public void updateAvailableTasks()
	{
		ArrayList<Task> availableTasks = getAvailableTasks();
		actionBasedTasksList.clear();
		nonActionBasedTasksList.clear();
		
		for(Task task : availableTasks)
		{
			CheckBox newTaskCheckBox = new CheckBox(" "+task.getName(), skin);
			displayedTasks.put(newTaskCheckBox, task);
			if(task.getType().equalsIgnoreCase("Action Based"))
			{
				actionBasedTasksList.addActor(newTaskCheckBox);
				actionBasedTasksGroup.add(newTaskCheckBox);
			}
			else
			{
				nonActionBasedTasksList.addActor(newTaskCheckBox);
				nonActionBasedTasksGroup.add(newTaskCheckBox);
			}
		}
		
		actionBasedTasksList.left();
		nonActionBasedTasksList.left();
	}
	
	private ArrayList<Task> getAvailableTasks()
	{
		String[] actionBasedList = {"task 1.1", "task 2.1", "task 3.1", "task 4.1", "task 5.1", "task 6.1", "task 7.1", "task 8.1", "task 9.1", "task 10.1"};
		String[] nonActionBasedList = {"task 1.2", "task 2.2", "task 3.2", "task 4.2", "task 5.2", "task 6.2", "task 7.2", "task 8.2", "task 9.2", "task 10.2"};
		
		ArrayList<Task> availableTasks = new ArrayList<Task>(); 
		
		for(String taskName : actionBasedList)
			availableTasks.add(new Task(taskName, "Action Based"));
		
		for(String taskName : nonActionBasedList)
			availableTasks.add(new Task(taskName, "Non-Action Based"));
		
		return availableTasks;
	}
	
	public void updateChosenTasks()
	{
		//TODO get current chosen tasks 
	}
	
	public ArrayList<Task> getChosenTasks()
	{
		ArrayList<Task> chosenTasks = new ArrayList<Task>(); 
		
		Array<Button> chosenTaskButtons = chosenTasksGroup.getButtons();
		for(Button checkedButton : chosenTaskButtons)
		{
			CheckBox checkBoxButton = (CheckBox)checkedButton;
			chosenTasks.add(displayedTasks.get(checkBoxButton));
		}
		
		return chosenTasks;
	}
}
