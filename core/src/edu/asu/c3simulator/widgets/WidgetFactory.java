package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;

public class WidgetFactory
{
	public static Actor createBulletedItem(Texture bulletTexture, String item)
	{
		Table bulletedItem = new Table();
		Image bullet = new Image(bulletTexture);
		Cell<Image> bulletCell = bulletedItem.add(bullet);
		
		SimpleTextField listItem = new SimpleTextField(item);
		bulletedItem.add(listItem);
		
		float bulletDimention = listItem.getPrefHeight();
		System.out.println("Bullet Dimention: " + bulletDimention);
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
}
