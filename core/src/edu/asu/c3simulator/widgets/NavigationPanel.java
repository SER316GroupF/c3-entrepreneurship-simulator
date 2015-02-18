package edu.asu.c3simulator.widgets;

import java.util.Hashtable;
import java.util.Set;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NavigationPanel extends Table
{
	private class navigationButtonListener extends ClickListener
	{
		String screenName;
		Screen targetedScreen;
		
		public navigationButtonListener(String screenName, Screen targetedScreen)
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
	
	private Game game;
	private Skin skin;
	private Hashtable<String, Screen> buttons;
	private Hashtable<String, Hashtable<String, Screen>> subButtons;
	private float panelWidth,panelHeight;
	private int spaceBetweenButtons = 5;
	
	public NavigationPanel(Game game, Skin skin)
	{
		super();
		this.game = game;
		this.skin = skin;
		buttons = new Hashtable<String, Screen>();
		subButtons = new Hashtable<String, Hashtable<String, Screen>>();
		panelWidth = 0;
		panelHeight = 0;
	}
	
	public void addButton(String buttonName, Screen targetedScreen)
	{
		buttons.put(buttonName, targetedScreen);
		createAndLinkButton(buttonName, targetedScreen);
		row();
	}
	
	public boolean addSubButton(String parentButtonName, String buttonName, Screen targetedScreen)
	{
		String storedParentButtonName = storedButtonName(parentButtonName);
		if(storedParentButtonName != null)
		{
			if(subButtons.containsKey(storedParentButtonName))
			{
				Hashtable<String, Screen> relativeSubButtons = subButtons.get(storedParentButtonName);
				relativeSubButtons.put(buttonName, targetedScreen);
			}
			else
			{
				Hashtable<String, Screen> newSubButtons = new Hashtable<String, Screen>();
				newSubButtons.put(buttonName, targetedScreen);
				subButtons.put(storedParentButtonName, newSubButtons);
			}
			recreateNavigationPanel();
			return true;
		}
		return false;
	}
	
	public void removeButton(String buttonName)
	{
		String storedButtonName = storedButtonName(buttonName);
		if(storedButtonName != null)
		{
			buttons.remove(buttonName);
			subButtons.remove(buttonName);
			recreateNavigationPanel();
		}
	}
	
	private void recreateNavigationPanel()
	{
		clearChildren();
		panelWidth = 0;
		panelHeight = 0;
		Set<String> buttonNames = buttons.keySet();
		for(String buttonName : buttonNames)
		{
			createAndLinkButton(buttonName, buttons.get(buttonName));
			row();
			if(subButtons.containsKey(buttonName))
			{
				Hashtable<String, Screen> subButtonsHashtable = subButtons.get(buttonName);
				Set<String> subButtonNames = subButtonsHashtable.keySet();
				for(String subButtonName : subButtonNames)
				{
					createAndLinkSubButton(subButtonName, subButtonsHashtable.get(subButtonName));
					row();
				}
			}
		}
	}
	
	private void createAndLinkButton(String buttonName, Screen targetedScreen)
	{
		TextButton newButton = new TextButton(buttonName, skin);
		newButton.addListener(new navigationButtonListener(buttonName, targetedScreen));
		setPanelSize(newButton.getPrefWidth(), newButton.getPrefHeight());
		add(newButton).expandX().fillX().spaceBottom(spaceBetweenButtons);
	}
	
	private void createAndLinkSubButton(String buttonName, Screen targetedScreen)
	{
		Table newTable = new Table();
		TextButton newButton = new TextButton(buttonName, skin);
		TextButton indentation = new TextButton("", skin);
		newButton.addListener(new navigationButtonListener(buttonName, targetedScreen));
		setPanelSize(newButton.getPrefWidth(), newButton.getPrefHeight());
		newTable.add(indentation).fillY();
		newTable.add(newButton).expandX().fillX();
		add(newTable).expandX().fillX().spaceBottom(spaceBetweenButtons);
	}
	
	private void setPanelSize(float newButtonWidth, float newButtonHeight)
	{
		if(panelWidth < newButtonWidth)
			panelWidth = newButtonWidth;
		panelHeight += newButtonHeight+spaceBetweenButtons;
		this.setSize(panelWidth, panelHeight);
	}
	
	private String storedButtonName(String passedButtonName)
	{
		Set<String> buttonNames = buttons.keySet();
		for(String buttonName : buttonNames)
		{
			if(buttonName.equalsIgnoreCase(passedButtonName))
				return buttonName;
		}
		return null;
	}
	
	public float getPanelWidth()
	{
		return panelWidth;
	}
	
	public float getPanelHeight()
	{
		return panelHeight;
	}
}
