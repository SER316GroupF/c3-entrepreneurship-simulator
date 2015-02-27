package edu.asu.c3simulator.widgets.groups;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;

/**
 * Specialized {@link Table} that requests a size change from all children when the
 * size of this group is set (as opposed to the default behaviour of {@link Table},
 * which adjusts its size based on the preferences of its children).
 * <p>
 * There are similar specializations of other groups. See
 * {@link edu.asu.c3simulator.widgets.groups} a complete list.
 * 
 * @author Moore, Zachary
 */
public class TableMaintenanceGroup extends Table
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
