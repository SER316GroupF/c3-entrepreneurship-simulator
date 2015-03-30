package edu.asu.c3simulator.widgets;

import java.awt.Rectangle;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Logically, {@link NavigationPanel} is a tree of buttons, each of which either expands a
 * branch of the tree (displays "sub-buttons"), or redirects the application to a
 * different screen.
 * <p>
 * A parent button will only show and hide sub-buttons when clicked. Only one of the
 * parent buttons can show its sub-buttons at the same time. If a parent button is
 * clicked, the already extended sub-buttons will collapse before extending the new set of
 * sub-buttons.
 * 
 * @author Alyahya, Mohammed
 * @author Moore, Zachary
 */
public class NavigationPanel extends Table
{
	/**
	 * Functions as a node for storing meta-data related to each button, including it's
	 * sub-buttons and actor, and functions as the button's listener.
	 */
	private class ButtonNodeListener extends ClickListener
	{
		Screen targetedScreen;
		Table targetedButton;
		List<ButtonNodeListener> subButtons;
		
		public ButtonNodeListener(Screen targetedScreen, Table targetedButton)
		{
			this.targetedScreen = targetedScreen;
			this.targetedButton = targetedButton;
			this.subButtons = new LinkedList<>();
		}
		
		public void addSubButton(ButtonNodeListener subButton)
		{
			subButtons.add(subButton);
			recreateNavigationPanel();
		}
		
		public void expand()
		{
			expandedButton = this;
			recreateNavigationPanel();
		}
		
		public void collapse()
		{
			expandedButton = null;
			recreateNavigationPanel();
		}
		
		public boolean isRedirect()
		{
			return subButtons.isEmpty();
		}
		
		public void activateRedirect()
		{
			if (targetedScreen != null)
				game.setScreen(targetedScreen);
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			if (this.isRedirect())
				activateRedirect();
			else if (this == expandedButton)
				collapse();
			else
				expand();
		}
	}
	
	private Game game;
	private Skin skin;
	
	/** Keeps track of all buttons currently */
	private Map<String, ButtonNodeListener> buttons;
	private List<ButtonNodeListener> topButtons;
	
	/** The physical bounds of this panel, in pixels, positioned by it's top-left corner */
	private Rectangle bounds;
	private int spaceBetweenButtons = 5;
	
	/** The name of the currently open collapsible button displaying its sub-buttons */
	private ButtonNodeListener expandedButton;
	
	public NavigationPanel(Game game, Skin skin)
	{
		super();
		this.game = game;
		this.skin = skin;
		buttons = new HashMap<>();
		topButtons = new LinkedList<>();
		bounds = new Rectangle();
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
		TextButton button = new TextButton(buttonName, skin);
		ButtonNodeListener node = new ButtonNodeListener(targetedScreen, button);
		button.addListener(node);
		
		buttons.put(buttonName, node);
		topButtons.add(node);
		
		recreateNavigationPanel();
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
	public void addSubButton(String parentButtonName, String buttonName,
			Screen targetedScreen)
	{
		ButtonNodeListener parent = buttons.get(parentButtonName);
		ButtonNodeListener subButton = createSubButton(buttonName, targetedScreen);
		parent.addSubButton(subButton);
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
		ButtonNodeListener node = buttons.get(buttonName);
		buttons.remove(buttonName);
		topButtons.remove(node);
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
		ButtonNodeListener node = buttons.get(buttonName);
		node.targetedScreen = targetedScreen;
	}
	
	private void recreateNavigationPanel()
	{
		clearChildren();
		bounds.width = 0;
		bounds.height = 0;
		
		for (ButtonNodeListener node : topButtons)
		{
			addButtonToPhysicalTable(node.targetedButton);
			if (node == expandedButton)
			{
				for (ButtonNodeListener subNode : node.subButtons)
				{
					addButtonToPhysicalTable(subNode.targetedButton);
				}
			}
		}
		
		setLocation();
	}
	
	private void addButtonToPhysicalTable(Table button)
	{
		adjustPanelSize(button.getPrefWidth(), button.getPrefHeight());
		add(button).expandX().fillX().spaceBottom(spaceBetweenButtons);
		row();
	}
	
	private void adjustPanelSize(float newButtonWidth, float newButtonHeight)
	{
		if (bounds.width < newButtonWidth)
			bounds.width = (int) newButtonWidth;
		bounds.height += newButtonHeight + spaceBetweenButtons;
		this.setSize(bounds.width, bounds.height);
	}
	
	private ButtonNodeListener createSubButton(String buttonName, Screen targetedScreen)
	{
		TextButton substance = new TextButton(buttonName, skin);
		TextButton indentation = new TextButton("", skin);
		substance.getColor().sub(0, 0, 0, 0.3f);
		
		Table button = new Table();
		button.add(indentation).fillY();
		button.add(substance).expandX().fillX();
		
		ButtonNodeListener node = new ButtonNodeListener(targetedScreen, button);
		button.addListener(node);
		
		buttons.put(buttonName, node);
		return node;
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
		ButtonNodeListener target = buttons.get(parentButton);
		target.expand();
	}
	
	/**
	 * Collapse all sub-buttons currently displayed by this panel
	 */
	public void hideAllSubButton()
	{
		expandedButton = null;
		recreateNavigationPanel();
	}
	
	public float getPanelWidth()
	{
		return bounds.width;
	}
	
	public float getPanelHeight()
	{
		return bounds.height;
	}
	
	/**
	 * Sets the position of the navigation panel, relative to this panel's top-left
	 * corner.
	 * <p>
	 * This is done so that the navigation panel will expand or contrast downward only
	 * when sub-buttons are shown or hidden.
	 *
	 * @param x
	 *            relative to the screen (0 is at the left edge of the screen).
	 * @param y
	 *            relative to the screen (0 is at the bottom edge of the screen).
	 */
	@Override
	public void setPosition(float x, float y)
	{
		bounds.x = (int) x;
		bounds.y = (int) y;
		setLocation();
	}
	
	/**
	 * Converts this panel's coordinate system (top-left) into the {@link Actor}
	 * coordinate system (bottom-left).
	 */
	private void setLocation()
	{
		super.setPosition(bounds.x, bounds.y - getPanelHeight());
	}
}
