package edu.asu.c3simulator.widgets;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.SnapshotArray;

public class NTable extends Table
{
	private float oldWidth;
	private float oldHeight;
	
	@Override
	protected void sizeChanged()
	{
		float xScale;
		float yScale;
		
		if (oldWidth == 0 || oldHeight == 0)
		{
			xScale = 1.0f;
			yScale = 1.0f;
		}
		else
		{
			xScale = getWidth() / oldWidth;
			yScale = getHeight() / oldHeight;
		}
		
		SnapshotArray<Actor> children = getChildren();
		for (Actor child : children)
		{
			float childWidth = child.getWidth() * xScale;
			float childHeight = child.getHeight() * yScale;
			
			child.setSize(childWidth, childHeight);
		}

		oldHeight = getHeight();
		oldWidth = getWidth();
	}
}