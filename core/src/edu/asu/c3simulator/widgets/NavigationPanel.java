package edu.asu.c3simulator.widgets;

import java.util.LinkedHashMap;
import java.util.Set;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * This class creates a navigation panel by creating the buttons and linking them with
 * their screens. It also supports having one level of sub-buttons to a parent button. A
 * parent button will only show and hide sub-buttons when clicked. Only one of the parent
 * buttons can show its sub-buttons at the same time. If a parent button is clicked, the
 * already extended sub-buttons will collapse before extending the new set of sub-buttons.
 * 
 * @author Alyahya, Mohammed
 */
public class NavigationPanel extends Table
{
	private class NavigationButtonListener extends ClickListener
	{
		String screenName;
		Screen targetedScreen;
		
		public NavigationButtonListener(String screenName, Screen targetedScreen)
		{
			this.screenName = screenName;
			this.targetedScreen = targetedScreen;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			System.out.println(screenName);
			game.setScreen(targetedScreen);
		}
	}
	
	private class CollapsibleButtonListener extends ClickListener
	{
		String screenName;
		
		public CollapsibleButtonListener(String screenName)
		{
			this.screenName = screenName;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			if (displayedCollapsibleButton.equalsIgnoreCase(screenName))
				displayedCollapsibleButton = "";
			else
				displayedCollapsibleButton = screenName;
			recreateNavigationPanel();
		}
	}
	
	private Game game;
	private Skin skin;
	private LinkedHashMap<String, Screen> buttons;
	private LinkedHashMap<String, LinkedHashMap<String, Screen>> subButtons;
	private float panelWidth = 0;
	private float panelHeight = 0;
	private float leftEdgeX = 0;
	private float bottomEdgeY = 0;
	private int spaceBetweenButtons = 5;
	
	/** The name of the currently open collapsible button displaying its sub-buttons */
	private String displayedCollapsibleButton = "";
	
	public NavigationPanel(Game game, Skin skin)
	{
		super();
		this.game = game;
		this.skin = skin;
		buttons = new LinkedHashMap<String, Screen>();
		subButtons = new LinkedHashMap<String, LinkedHashMap<String, Screen>>();
	}
	
	/**
	 * This method adds a main button to the navigation panel
	 *
	 * @param buttonName
	 *            The string that will be displayed on the button.
	 * @param targetedScreen
	 *            The screen that the button maps to. If null, no actionListener will be
	 *            added to the button.
	 */
	public void addButton(String buttonName, Screen targetedScreen)
	{
		buttons.put(buttonName, targetedScreen);
		createAndLinkButton(buttonName, targetedScreen);
		row();
		setLocation();
	}
	
	/**
	 * This method adds a sub button to the one of the main buttons in the navigation
	 * panel. Parent buttons will not direct to screens but instead will show and hide
	 * sub-buttons when clicked
	 *
	 * @param parentButtonName
	 *            the name of the parent button which the sub-button will be under
	 * @param buttonName
	 *            The string that will be displayed on the button.
	 * @param targetedScreen
	 *            The screen that the button maps to. If null, no actionListener will be
	 *            added to the button.
	 */
	public boolean addSubButton(String parentButtonName, String buttonName,
			Screen targetedScreen)
	{
		String storedParentButtonName = storedButtonName(parentButtonName);
		if (!storedParentButtonName.equalsIgnoreCase(""))
		{
			if (subButtons.containsKey(storedParentButtonName))
			{
				LinkedHashMap<String, Screen> relativeSubButtons = subButtons
						.get(storedParentButtonName);
				relativeSubButtons.put(buttonName, targetedScreen);
			}
			else
			{
				LinkedHashMap<String, Screen> newSubButtons = new LinkedHashMap<String, Screen>();
				newSubButtons.put(buttonName, targetedScreen);
				subButtons.put(storedParentButtonName, newSubButtons);
			}
			recreateNavigationPanel();
			return true;
		}
		return false;
	}
	
	/**
	 * This method removes the button chosen from the navigation panel. When this method
	 * is called on a main parent button, it and all its children are removed. If a wrong
	 * string is passed that doesn't lead to a button, this method will do nothing.
	 *
	 * @param buttonName
	 *            The name of the button that will be removed.
	 */
	public void removeButton(String buttonName)
	{
		String storedButtonName = storedButtonName(buttonName);
		if (!storedButtonName.equalsIgnoreCase(""))
		{
			buttons.remove(buttonName);
			subButtons.remove(buttonName);
			recreateNavigationPanel();
		}
		else
			removeSubButton(buttonName);
	}
	
	private void removeSubButton(String buttonName)
	{
		Set<String> buttonNames = buttons.keySet();
		for (String storedButtonName : buttonNames)
		{
			LinkedHashMap<String, Screen> subButtonsHashtable = subButtons
					.get(storedButtonName);
			Set<String> subButtonNames = subButtonsHashtable.keySet();
			for (String subButtonName : subButtonNames)
			{
				if (subButtonName.equalsIgnoreCase(buttonName))
				{
					subButtonsHashtable.remove(subButtonName);
					recreateNavigationPanel();
				}
			}
		}
	}
	
	/**
	 * This method changes the targeted screen for the intended button.
	 *
	 * @param buttonName
	 *            The name of the button.
	 * @param targetedScreen
	 *            The new screen that the button will maps to. If null, the button won't
	 *            have an actionListener.
	 */
	public void changeTargetedScreen(String buttonName, Screen targetedScreen)
	{
		String storedButtonName = storedButtonName(buttonName);
		if (!storedButtonName.equalsIgnoreCase(""))
		{
			buttons.put(storedButtonName, targetedScreen);
			recreateNavigationPanel();
		}
		else
			changeSubButtonTargetedScreen(buttonName, targetedScreen);
	}
	
	private void changeSubButtonTargetedScreen(String buttonName, Screen targetedScreen)
	{
		Set<String> buttonNames = buttons.keySet();
		for (String storedButtonName : buttonNames)
		{
			LinkedHashMap<String, Screen> subButtonsHashtable = subButtons
					.get(storedButtonName);
			Set<String> subButtonNames = subButtonsHashtable.keySet();
			for (String subButtonName : subButtonNames)
			{
				if (subButtonName.equalsIgnoreCase(buttonName))
				{
					subButtonsHashtable.put(subButtonName, targetedScreen);
					recreateNavigationPanel();
				}
			}
		}
	}
	
	private void recreateNavigationPanel()
	{
		clearChildren();
		panelWidth = 0;
		panelHeight = 0;
		Set<String> buttonNames = buttons.keySet();
		for (String buttonName : buttonNames)
		{
			createAndLinkButton(buttonName, buttons.get(buttonName));
			row();
			if (displayedCollapsibleButton.equalsIgnoreCase(buttonName)
					&& subButtons.containsKey(buttonName))
			{
				LinkedHashMap<String, Screen> subButtonsHashtable = subButtons
						.get(buttonName);
				Set<String> subButtonNames = subButtonsHashtable.keySet();
				for (String subButtonName : subButtonNames)
				{
					createAndLinkSubButton(subButtonName,
							subButtonsHashtable.get(subButtonName));
					row();
				}
			}
		}
		setLocation();
	}
	
	private void createAndLinkButton(String buttonName, Screen targetedScreen)
	{
		TextButton newButton = new TextButton(buttonName, skin);
		
		if (subButtons.containsKey(buttonName))
			newButton.addListener(new CollapsibleButtonListener(buttonName));
		else if (targetedScreen != null)
			newButton
					.addListener(new NavigationButtonListener(buttonName, targetedScreen));
		
		setPanelSize(newButton.getPrefWidth(), newButton.getPrefHeight());
		add(newButton).expandX().fillX().spaceBottom(spaceBetweenButtons);
	}
	
	private void createAndLinkSubButton(String buttonName, Screen targetedScreen)
	{
		Table newTable = new Table();
		TextButton newButton = new TextButton(buttonName, skin);
		TextButton indentation = new TextButton("", skin);
		newButton.getColor().sub(0, 0, 0, 0.3f);
		// indentation.getColor().sub(0, 0, 0, 0.3f);
		if (targetedScreen != null)
			newButton
					.addListener(new NavigationButtonListener(buttonName, targetedScreen));
		setPanelSize(newButton.getPrefWidth(), newButton.getPrefHeight());
		newTable.add(indentation).fillY();
		newTable.add(newButton).expandX().fillX();
		add(newTable).expandX().fillX().spaceBottom(spaceBetweenButtons);
	}
	
	private void setPanelSize(float newButtonWidth, float newButtonHeight)
	{
		if (panelWidth < newButtonWidth)
			panelWidth = newButtonWidth;
		panelHeight += newButtonHeight + spaceBetweenButtons;
		this.setSize(panelWidth, panelHeight);
	}
	
	private String storedButtonName(String passedButtonName)
	{
		Set<String> buttonNames = buttons.keySet();
		for (String buttonName : buttonNames)
		{
			if (buttonName.equalsIgnoreCase(passedButtonName))
				return buttonName;
		}
		return "";
	}
	
	/**
	 * By default, all sub-buttons are hidden. This method show the sub-buttons for the
	 * chosen parent. If an empty string is passed, it will hide all sub-buttons
	 *
	 * @param parentButton
	 *            The name of the parent button of the sub-buttons that will be shown.
	 */
	public void showSubButtonsFor(String parentButton)
	{
		displayedCollapsibleButton = storedButtonName(parentButton);
		recreateNavigationPanel();
	}
	
	/**
	 * This method will hide all sub-buttons shown on screen
	 */
	public void hideAllSubButton()
	{
		displayedCollapsibleButton = "";
		recreateNavigationPanel();
	}
	
	public float getPanelWidth()
	{
		return panelWidth;
	}
	
	public float getPanelHeight()
	{
		return panelHeight;
	}
	
	/**
	 * This method will set the position of the navigation panel on the screen. The x and
	 * y coordinates passed will be at the top left point of the navigation panel. This is
	 * done so that the navigation panel will expand or contrast downward only when
	 * sub-buttons are shown or hidden.
	 *
	 * @param x
	 *            relative to the screen (0 is at the left edge of the screen).
	 * @param y
	 *            relative to the screen (0 is at the bottom edge of the screen).
	 */
	@Override
	public void setPosition(float x, float y)
	{
		leftEdgeX = x;
		bottomEdgeY = y;
		setLocation();
	}
	
	private void setLocation()
	{
		super.setPosition(leftEdgeX, bottomEdgeY - getPanelHeight());
	}
}
