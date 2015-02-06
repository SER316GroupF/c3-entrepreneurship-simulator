package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

/**
 * Factory for producing various GUI component combinations, such as lists, bulleted
 * items, etc.
 * 
 * @author Moore, Zachary
 */
public class WidgetFactory
{
	private Skin skin;
	
	/**
	 * @param skin
	 *            Will be used to manufacture labels, buttons, and other basic widgets.
	 *            Skin is not guaranteed to be used by every manufacturing process, and is
	 *            not guaranteed to be used at all.
	 * @throws IllegalArgumentException
	 *             if skin is null
	 */
	public WidgetFactory(Skin skin)
	{
		super();
		this.skin = skin;
		
		if (skin == null)
			throw new IllegalArgumentException("Provided skin must be non-null");
	}
	
	/**
	 * @param bulletTexture
	 *            Texture to display to the left of the bulleted item
	 * @param item
	 *            Text to display to the right of the bullet
	 * @return A component that visually represents an item in a bulleted list.
	 */
	public static Actor createBulletedItem(Texture bulletTexture, String item)
	{
		Table bulletedItem = new Table();
		Image bullet = new Image(bulletTexture);
		Cell<Image> bulletCell = bulletedItem.add(bullet);
		
		SimpleTextField listItem = new SimpleTextField(item);
		bulletedItem.add(listItem);
		
		float bulletDimention = listItem.getPrefHeight();
		bulletCell.size(bulletDimention, bulletDimention);
		
		return bulletedItem;
	}
	
	/**
	 * Creates a component that displays list items (stacked vertically) with a bullet
	 * next to each entry.
	 * 
	 * @param bulletTexture
	 *            Texture to display to the left of each bulleted item
	 * @param listItems
	 *            Contents of the list. Items will be displayed with the first list item
	 *            on the top of the list, and the last item on the bottom.
	 * @return Bulleted list component
	 */
	public static Actor createBulletedList(Texture bulletTexture, String... listItems)
	{
		VerticalGroup bulletList = new VerticalGroup();
		
		for (String item : listItems)
		{
			Actor bulletedItem = createBulletedItem(bulletTexture, item);
			bulletList.addActor(bulletedItem);
		}
		
		return bulletList;
	}
	
	/**
	 * @param listItems
	 *            Contents of the list. Items will be displayed with the first list item
	 *            on the top of the list, and the last item on the bottom.
	 * @return An un-bulleted, vertically stacked list
	 */
	public Actor createVerticalList(String... listItems)
	{
		VerticalGroup verticalList = new VerticalGroup();
		
		for (String item : listItems)
		{
			Label line = new Label(item, skin);
			line.setAlignment(Align.center);
			verticalList.addActor(line);
		}
		
		return verticalList;
	}
	
	/**
	 * Calls {@link #createVerticalList(String...)} followed by
	 * {@link #createVerticalListTitled(String, Actor)}
	 * 
	 * @see WidgetFactory#createVerticalListTitled(String, Actor)
	 */
	public Actor createVerticalListTitled(String title, String... listItems)
	{
		Actor listBody = createVerticalList(listItems);
		
		return createVerticalListTitled(title, listBody);
	}
	
	/**
	 * This method can be used to create titled components, including titled bulleted
	 * lists, if used in conjunction with {@link #createBulletedList(Texture, String...)}
	 * 
	 * @param title
	 *            The title to be displayed above the list
	 * @param listBody
	 *            An actor to be used as the body of the list
	 * @return A new component, containing a title component stacked above listBody
	 */
	public Actor createVerticalListTitled(String title, Actor listBody)
	{
		VerticalGroup verticalList = new VerticalGroup();
		Actor listTitle = createTitle(verticalList, title);
		
		verticalList.addActor(listTitle);
		verticalList.addActor(listBody);
		
		verticalList.setTransform(true);
		
		return verticalList;
	}
	
	/**
	 * Creates a label that stretches to it's parent's width, and has a background
	 * specified by {@link #skin}
	 * 
	 * @param parent
	 *            The Layout in which this component will exist
	 * @param titleText
	 *            The text to display
	 * @return A new component, as described above
	 */
	private Actor createTitle(Layout parent, String titleText)
	{
		TextButton title = new StretchTextButton(titleText, skin, parent);
		title.align(Align.center);
		title.setDisabled(true);
		
		return title;
	}
}
