package edu.asu.c3simulator.widgets.groups;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

import edu.asu.c3simulator.widgets.SimpleTextField;

/**
 * A component that visually represents an item in a bulleted list.
 * 
 * @author Moore, Zachary
 *
 */
public class BulletedItem extends Table
{
	/** Text to be displayed to the right of the bullet */
	private SimpleTextField listItem;
	
	/**
	 * @param bulletTexture Visualization of a bullet point
	 * @param text Text to display to the right of the bullet
	 */
	public BulletedItem(Texture bulletTexture, String text)
	{
		Image bullet = new Image(bulletTexture);
		Cell<Image> bulletCell = add(bullet);
		
		this.listItem = new SimpleTextField(text);
		add(listItem);
		
		float bulletDimention = listItem.getPrefHeight();
		bulletCell.size(bulletDimention, bulletDimention);
	}
	
	@Override
	public void setSize(float width, float height)
	{
		super.setSize(width, height);
		
		Cell<?> bulletCell = this.getCells().get(0);
		listItem.setSize(width - height, height);
		float bulletDimention = listItem.getPrefHeight();
		bulletCell.size(bulletDimention, bulletDimention);
		
		for (Actor child : getChildren())
		{
			if (child instanceof WidgetGroup)
			{
				child.setSize(width, height);
			}
		}
	}
}
