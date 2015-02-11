package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;

import edu.asu.c3simulator.widgets.groups.BulletedItem;
import edu.asu.c3simulator.widgets.groups.VerticalMaintenanceGroup;

/**
 * Factory for producing various GUI component combinations, such as lists, bulleted
 * items, etc.
 * 
 * @author Moore, Zachary
 */
public class WidgetFactory
{
	public static final Skin DEFAULT_SKIN = createSkin("skins/default/uiskin.json");
	private static final boolean DEFAULT_TRANSFORM_SETTING = false;
	private Skin skin;
	
	/** Used as the argument for {@link Group#setTransform(boolean)} */
	private boolean transformSetting;
	
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
		this.transformSetting = DEFAULT_TRANSFORM_SETTING;
		
		if (skin == null)
			throw new IllegalArgumentException("Provided skin must be non-null");
	}
	
	/**
	 * Creates a WidgetFactory using the skin specified by {@link #DEFAULT_SKIN}
	 */
	public WidgetFactory()
	{
		this(DEFAULT_SKIN);
	}
	
	private static Skin createSkin(String skinPath)
	{
		return new Skin(Gdx.files.internal(skinPath));
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
		BulletedItem bulletedItem = new BulletedItem(bulletTexture, item);
		
		bulletedItem.setTransform(DEFAULT_TRANSFORM_SETTING);
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
		VerticalMaintenanceGroup bulletList = new VerticalMaintenanceGroup();
		
		for (String item : listItems)
		{
			Actor bulletedItem = createBulletedItem(bulletTexture, item);
			bulletList.addActor(bulletedItem);
		}
		
		bulletList.setTransform(DEFAULT_TRANSFORM_SETTING);
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
		VerticalMaintenanceGroup verticalList = new VerticalMaintenanceGroup();
		
		for (String item : listItems)
		{
			Label line = new Label(item, skin);
			line.setAlignment(Align.center);
			verticalList.addActor(line);
		}
		
		verticalList.setTransform(transformSetting);
		return verticalList;
	}
	
	/**
	 * Calls {@link #createVerticalList(String...)} followed by
	 * {@link #prependTitle(String, Actor)}
	 * 
	 * @see WidgetFactory#prependTitle(String, Actor)
	 */
	public Actor createVerticalListTitled(String title, String... listItems)
	{
		Actor listBody = createVerticalList(listItems);
		
		return prependTitle(title, listBody);
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
	public Actor prependTitle(String title, Actor listBody)
	{
		VerticalMaintenanceGroup verticalList = new VerticalMaintenanceGroup();
		Actor listTitle = createTitle(verticalList, title);
		
		verticalList.addActor(listTitle);
		verticalList.addActor(listBody);
		
		verticalList.setTransform(transformSetting);
		
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
	public Actor createTitle(Layout parent, String titleText)
	{
		TextButton title = new StretchTextButton(titleText, skin, parent);
		title.align(Align.center);
		title.setDisabled(true);
		title.setTransform(transformSetting);
		
		return title;
	}
	
	/**
	 * Creates a bulleted list, and prepends a title to it. This function is equivalent to
	 * {@link #createBulletedList(Texture, String...)} followed by
	 * {@link #prependTitle(String, Actor)}
	 * 
	 * @param title
	 *            The title to be displayed above the list
	 * @param bulletTexture
	 *            Texture to display to the left of each bulleted item
	 * @param descriptionItems
	 *            Contents of the list. Items will be displayed with the first list item
	 *            on the top of the list, and the last item on the bottom.
	 * @return
	 */
	public Actor createBulletedListTitled(String title, Texture bulletTexture,
			String[] descriptionItems)
	{
		Actor listBody = WidgetFactory
				.createBulletedList(bulletTexture, descriptionItems);
		
		return prependTitle(title, listBody);
	}
}
