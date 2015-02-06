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

public class WidgetFactory
{
	private Skin skin;
	
	public WidgetFactory(Skin skin)
	{
		super();
		this.skin = skin;
	}
	
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
	
	public Actor createVerticalListTitled(String title, String... listItems)
	{
		VerticalGroup verticalList = new VerticalGroup();
		Actor listBody = createVerticalList(listItems);
		Actor listTitle = createTitle(verticalList, title);
		
		verticalList.addActor(listTitle);
		verticalList.addActor(listBody);
		
		verticalList.setTransform(true);
		
		return verticalList;
	}
	
	public Actor createVerticalListTitled(String title, Actor listBody)
	{
		VerticalGroup verticalList = new VerticalGroup();
		Actor listTitle = createTitle(verticalList, title);
		
		verticalList.addActor(listTitle);
		verticalList.addActor(listBody);
		
		verticalList.setTransform(true);
		
		return verticalList;
	}
	
	private Actor createTitle(Layout parent, String titleText)
	{
		TextButton title = new StretchTextButton(titleText, skin, parent);
		title.align(Align.center);
		title.setDisabled(true);
		
		return title;
	}
}
