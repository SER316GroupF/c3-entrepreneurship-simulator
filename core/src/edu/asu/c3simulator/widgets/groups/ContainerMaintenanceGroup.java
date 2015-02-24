package edu.asu.c3simulator.widgets.groups;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

public class ContainerMaintenanceGroup<T extends Actor> extends Container<T>
{
	@Override
	public void setSize(float width, float height)
	{
		super.setSize(width, height);
		
		for (Actor child : getChildren())
		{
			if (child instanceof WidgetGroup)
			{
				child.setSize(width, height);
			}
		}
	}
}
