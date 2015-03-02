package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.scenes.scene2d.ui.Widget;

public class Cushion extends Widget
{
	public Cushion(float width, float height)
	{
		super();
		this.setSize(width, height);
	}
	
	@Override
	public float getPrefWidth()
	{
		return getWidth();
	}
	
	@Override
	public float getPrefHeight()
	{
		return getHeight();
	}
}
