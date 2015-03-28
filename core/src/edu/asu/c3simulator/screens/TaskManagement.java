package edu.asu.c3simulator.screens;

import java.util.ArrayList;
import java.util.Hashtable;

import com.badlogic.gdx.graphics.Color;
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

/**
 * Creates and displays the GUI component for managing tasks. The class separates
 * available tasks into Action Based and Non-Action Based categories. The user can check
 * all the tasks they want and press the add tasks button for tasks to be moved to the
 * selected tasks column. Also, it is possible to remove tasks from the current tasks back
 * to their categories by checking them and clicking the remove button.
 * 
 * @author Alyahya, Mohammed
 */
public class TaskManagement extends Table
{
	/**
	 * Listens to the "Add Tasks" button. Moves all the checked tasks to the chosen tasks
	 * list.
	 */
	private class AddTasksButtonListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			Array<Button> checkedActionBasedTasks = actionBasedTasksGroup.getButtons();
			int numberOfButtons = checkedActionBasedTasks.size;
			for (int index = 1; index <= numberOfButtons; index++)
			{
				Button checkedButton = checkedActionBasedTasks.get(numberOfButtons
						- index);
				if (checkedButton.isChecked())
				{
					chosenTasksList.addActorAt(0, checkedButton);
					chosenTasksGroup.add(checkedButton);
					actionBasedTasksList.removeActor(checkedButton);
					actionBasedTasksGroup.remove(checkedButton);
				}
			}
			
			Array<Button> checkedNonActionBasedTasks = nonActionBasedTasksGroup
					.getButtons();
			numberOfButtons = checkedNonActionBasedTasks.size;
			for (int index = 1; index <= numberOfButtons; index++)
			{
				Button checkedButton = checkedNonActionBasedTasks.get(numberOfButtons
						- index);
				if (checkedButton.isChecked())
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
	
	/**
	 * Listens to the "Remove" button. Moves all the checked tasks to the avialable tasks
	 * list based on their type.
	 */
	private class RemoveTasksButtonListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			Array<Button> chosenTasks = chosenTasksGroup.getButtons();
			int numberOfButtons = chosenTasks.size;
			for (int index = 1; index <= numberOfButtons; index++)
			{
				Button checkedButton = chosenTasks.get(numberOfButtons - index);
				if (checkedButton.isChecked())
				{
					if (displayedTasks.get(checkedButton).getType()
							== Task.Type.ACTION_BASED)
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
	private ButtonGroup actionBasedTasksGroup, nonActionBasedTasksGroup,
			chosenTasksGroup;
	private Hashtable<Button, Task> displayedTasks;
	
	/**
	 * @param stage
	 *            The stage where the GUI will be displayed.
	 * @param skin
	 */
	public TaskManagement(Stage stage, Skin skin)
	{
		this.skin = skin;
		
		displayedTasks = new Hashtable<Button, Task>();
		
		this.setSize(0.70f * stage.getWidth(), 0.70f * stage.getHeight());
		
		Table leftSection = new Table();
		Table rightSection = new Table();
		Table actionBased = new Table();
		Table nonActionBased = new Table();
		
		TextButton actionBasedLabel = new TextButton("Action Based", skin);
		actionBasedLabel.setDisabled(true);
		actionBasedLabel.setColor(Color.GRAY);
		TextButton nonActionBasedLabel = new TextButton("Non-Action Based", skin);
		nonActionBasedLabel.setDisabled(true);
		nonActionBasedLabel.setColor(Color.GRAY);
		TextButton addTasksButton = new TextButton("Add Tasks", skin);
		addTasksButton.addListener(new AddTasksButtonListener());
		
		actionBasedTasksList = new VerticalGroup();
		nonActionBasedTasksList = new VerticalGroup();
		actionBasedTasksGroup = new ButtonGroup();
		actionBasedTasksGroup.setMaxCheckCount(-1);
		actionBasedTasksGroup.setMinCheckCount(0);
		nonActionBasedTasksGroup = new ButtonGroup();
		nonActionBasedTasksGroup.setMaxCheckCount(-1);
		nonActionBasedTasksGroup.setMinCheckCount(0);
		ScrollPane actionBasedTasksScroll = new ScrollPane(actionBasedTasksList, skin);
		ScrollPane nonActionBasedTasksScroll = new ScrollPane(nonActionBasedTasksList,
				skin);
		
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
		tasksListLabel.setColor(Color.GRAY);
		chosenTasksList = new VerticalGroup();
		chosenTasksGroup = new ButtonGroup();
		chosenTasksGroup.setMaxCheckCount(-1);
		chosenTasksGroup.setMinCheckCount(0);
		ScrollPane chosenTasksScroll = new ScrollPane(chosenTasksList, skin);
		TextButton removeTasksButton = new TextButton("remove", skin);
		removeTasksButton.addListener(new RemoveTasksButtonListener());
		
		leftSection.add(tasksListLabel).fillX().align(Align.center);
		leftSection.row();
		leftSection.add(chosenTasksScroll).expand().fill().center();
		leftSection.row();
		leftSection.add(removeTasksButton).fillX().center();
		
		this.add(leftSection).expand().fill();
		this.add(rightSection).expand().fill();
		this.setPosition(stage.getWidth() / 2 - this.getWidth() / 2, stage.getHeight()
				/ 2 - this.getHeight() / 2);
		
		updateAvailableTasks();
		updateChosenTasks();
	}
	
	/**
	 * This method is called to update the list of available tasks.
	 */
	public void updateAvailableTasks()
	{
		ArrayList<Task> availableTasks = getAvailableTasks();
		actionBasedTasksList.clear();
		nonActionBasedTasksList.clear();
		
		for (Task task : availableTasks)
		{
			CheckBox newTaskCheckBox = new CheckBox(" " + task.getName(), skin);
			displayedTasks.put(newTaskCheckBox, task);
			if (task.getType() == Task.Type.ACTION_BASED)
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
	
	/**
	 * This method creates a testing shell of different tasks to test and demo the GUI.
	 * 
	 * @return an ArrayList of the available tasks that are going to be displayed.
	 */
	private ArrayList<Task> getAvailableTasks()
	{
		String[] actionBasedList = { "task 1.1", "task 2.1", "task 3.1", "task 4.1",
				"task 5.1", "task 6.1", "task 7.1", "task 8.1", "task 9.1", "task 10.1" };
		String[] nonActionBasedList = { "task 1.2", "task 2.2", "task 3.2", "task 4.2",
				"task 5.2", "task 6.2", "task 7.2", "task 8.2", "task 9.2", "task 10.2" };
		
		// TODO replace with functional code that request completed tasks from company
		// datta layer when tasks are implemented.
		
		ArrayList<Task> availableTasks = new ArrayList<Task>();
		
		for (String taskName : actionBasedList)
			availableTasks.add(new Task(taskName, Task.Type.ACTION_BASED));
		
		for (String taskName : nonActionBasedList)
			availableTasks.add(new Task(taskName, Task.Type.NON_ACTION_BASED));
		
		return availableTasks;
	}
	
	/**
	 * This method deals with obtaining the currently chosen tasks by the user from the
	 * data layer and display it at the beginning.
	 */
	public void updateChosenTasks()
	{
		// TODO get current chosen tasks
	}
	
	/**
	 * This method gets the current list in the chosen tasks column and return it in array
	 * form.
	 * 
	 * @return an ArrayList of the chosen tasks.
	 */
	public ArrayList<Task> getChosenTasks()
	{
		ArrayList<Task> chosenTasks = new ArrayList<Task>();
		
		Array<Button> chosenTaskButtons = chosenTasksGroup.getButtons();
		for (Button checkedButton : chosenTaskButtons)
		{
			chosenTasks.add(displayedTasks.get(checkedButton));
		}
		
		return chosenTasks;
	}
}
