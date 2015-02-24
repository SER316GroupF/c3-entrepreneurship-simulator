package edu.asu.c3simulator.widgets.groups;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class VerticalMaintenanceGroup extends VerticalGroup
{
	@Override
	public void setSize(float width, float height)
	{
		super.setSize(width, height);
		System.out.println(getWidth());
		
		for (Actor child : getChildren())
		{
			if (child instanceof WidgetGroup)
			{
				child.setSize(width, height / getChildren().size);
			}
		}
	}
}
