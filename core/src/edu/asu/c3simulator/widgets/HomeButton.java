package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class HomeButton extends Table{
	
	private Image homeIcon;
	public float homeLeft;
	public float homeBottom;
	
	public HomeButton()
	{
		super();
		
		FileHandle iconLocation = Gdx.files.internal("images/home-button.png");
		Texture iconTexture = new Texture(iconLocation);
		this.homeIcon = new Image(iconTexture);
		add(homeIcon).size(150);
		homeLeft = this.getPrefWidth()/2;
		homeBottom = this.getPrefHeight()/2;
		this.setPosition(homeLeft, homeBottom);
	}

}
