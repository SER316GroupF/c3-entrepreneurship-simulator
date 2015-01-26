package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class CornerAdvisor extends Table
{
	private String text;
	private TextAreaX textBubble;
	private Image advisorIcon;
	
	public CornerAdvisor(String text)
	{
		super();
		
		FileHandle iconLocation = Gdx.files.internal("images/placeholder-advisor-icon.png");
		Texture iconTexture = new Texture(iconLocation);
		this.advisorIcon = new Image(iconTexture);
		
		this.text = text;
		this.textBubble = new TextAreaX(text, 200, 0.5f);
		this.textBubble.setBackground("images/text-bubble-white.png");
		this.textBubble.setBorderPadding(new Padding(20, 50, 15, 10));
		
		this.setTransform(true);
		
		add(this.textBubble);
		add(advisorIcon).size(textBubble.getPrefHeight() * 1.3f);
	}

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
		this.textBubble.setText(text);
	}
	
	
	
}
