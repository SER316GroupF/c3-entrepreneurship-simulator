package edu.asu.c3simulator.widgets.groups;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

/**
 * Specialized {@link Container} that requests a size change from all children when the
 * size of this group is set (as opposed to the default behaviour of {@link Container},
 * which adjusts its size based on the preferences of its children).
 * <p>
 * There are similar specializations of other groups. See
 * {@link edu.asu.c3simulator.widgets.groups} a complete list.
 * 
 * @author Moore, Zachary
 *
 * @param <T> Type of child contained in this {@link Container}
 */
public class ContainerMaintenanceGroup<T extends Actor> extends Container<T>
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
				child.setSize(width, height);
			}
		}
	}
}
