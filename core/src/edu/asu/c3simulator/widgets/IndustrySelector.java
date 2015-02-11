package edu.asu.c3simulator.widgets;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import edu.asu.c3simulator.simulation.Industry;
import edu.asu.c3simulator.util.Association;
import edu.asu.c3simulator.widgets.groups.ContainerMaintenanceGroup;

public class IndustrySelector extends Widget
{
	private static class RelativeDisplaySpecification
	{
		private float xScale;
		private float yScale;
		private float xOffset;
		private float yOffset;
		
		public RelativeDisplaySpecification(float xOffset, float yOffset, float xScale,
				float yScale)
		{
			super();
			this.xScale = xScale;
			this.yScale = yScale;
			this.xOffset = xOffset;
			this.yOffset = yOffset;
		}
		
	}
	
	private static final RelativeDisplaySpecification LEFT_DISPLAY_SPECIFICATION = new RelativeDisplaySpecification(
			0.0f, 0.2f, 0.2f, 0.6f);
	private static final RelativeDisplaySpecification MIDDLE_DISPLAY_SPECIFICATION = new RelativeDisplaySpecification(
			0.35f, 0.0f, 0.3f, 1.0f);
	private static final RelativeDisplaySpecification RIGHT_DISPLAY_SPECIFICATION = new RelativeDisplaySpecification(
			0.8f, 0.2f, 0.2f, 0.6f);
	
	private List<Association<Actor, Industry>> contents;
	private int currentSelectionIndex;
	private ContainerMaintenanceGroup<Actor> leftSelectionDisplay;
	private ContainerMaintenanceGroup<Actor> middleSelectionDisplay;
	private ContainerMaintenanceGroup<Actor> rightSelectionDisplay;
	
	public IndustrySelector()
	{
		super();
		this.contents = new ArrayList<>();
		this.currentSelectionIndex = 0;
		this.leftSelectionDisplay = new ContainerMaintenanceGroup<>();
		this.middleSelectionDisplay = new ContainerMaintenanceGroup<>();
		this.rightSelectionDisplay = new ContainerMaintenanceGroup<>();
		
		leftSelectionDisplay.debug();
		middleSelectionDisplay.debug();
		rightSelectionDisplay.debug();
	}
	
	public IndustrySelector(Industry... industries)
	{
		this();
		for (Industry industry : industries)
		{
			this.add(industry);
		}
	}
	
	public void add(Industry industry)
	{
		Actor industryDisplay = createIndustryDisplay(industry);
		Association<Actor, Industry> value = new Association<>(industryDisplay, industry);
		contents.add(value);
		recalculateDisplayContents();
	}
	
	/**
	 * Updates the displays such that {@link #middleSelectionDisplay} displays the current
	 * option, {@link #leftSelectionDisplay} displays the previous option, and
	 * {@link #rightSelectionDisplay} displays the next option.
	 * 
	 * Options will be wrapped if the previous or next option is out of bounds
	 */
	private void recalculateDisplayContents()
	{
		int previousIndex = convertToValidSelectionIndex(currentSelectionIndex - 1);
		int nextIndex = convertToValidSelectionIndex(currentSelectionIndex + 1);
		int currentIndex = currentSelectionIndex;
		
		Association<Actor, Industry> previousSelection = contents.get(previousIndex);
		Actor previousSelectionDisplay = previousSelection.getKey();
		
		Association<Actor, Industry> nextSelection = contents.get(nextIndex);
		Actor nextSelectionDisplay = nextSelection.getKey();
		
		Association<Actor, Industry> currentSelection = contents.get(currentIndex);
		Actor currentSelectionDisplay = currentSelection.getKey();
		
		this.leftSelectionDisplay.setActor(previousSelectionDisplay);
		this.middleSelectionDisplay.setActor(currentSelectionDisplay);
		this.rightSelectionDisplay.setActor(nextSelectionDisplay);
	}
	
	private Actor createIndustryDisplay(Industry industry)
	{
		String title = industry.getName();
		String[] descriptionItems = industry.getDescription();
		
		WidgetFactory factory = new WidgetFactory();
		String texturePath = "images/bullet_white.png";
		Texture bulletTexture = new Texture(texturePath);
		
		return factory.createBulletedListTitled(title, bulletTexture, descriptionItems);
	}
	
	@Override
	public void setStage(Stage stage)
	{
		stage.addActor(leftSelectionDisplay);
		stage.addActor(rightSelectionDisplay);
		stage.addActor(middleSelectionDisplay);
		
		leftSelectionDisplay.align(Align.top);
		rightSelectionDisplay.align(Align.top);
		middleSelectionDisplay.align(Align.top);
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		
		/*
		 * leftSelectionDisplay.draw(batch, parentAlpha);
		 * middleSelectionDisplay.draw(batch, parentAlpha);
		 * rightSelectionDisplay.draw(batch, parentAlpha);
		 */
	}
	
	public void cycleRight()
	{
		currentSelectionIndex++;
		validateCurrentSelectionIndex();
	}
	
	public void cycleLeft()
	{
		currentSelectionIndex--;
		validateCurrentSelectionIndex();
	}
	
	private void validateCurrentSelectionIndex()
	{
		currentSelectionIndex = convertToValidSelectionIndex(currentSelectionIndex);
		recalculateDisplayContents();
	}
	
	private int convertToValidSelectionIndex(int index)
	{
		while (index >= contents.size())
		{
			index -= contents.size();
		}
		
		while (index < 0)
		{
			index += contents.size();
		}
		
		return index;
	}
	
	@Override
	protected void positionChanged()
	{
		repositionDisplays();
	}
	
	private void repositionDisplays()
	{
		repositionChild(leftSelectionDisplay, LEFT_DISPLAY_SPECIFICATION);
		repositionChild(middleSelectionDisplay, MIDDLE_DISPLAY_SPECIFICATION);
		repositionChild(rightSelectionDisplay, RIGHT_DISPLAY_SPECIFICATION);
	}
	
	private void repositionChild(Actor child, RelativeDisplaySpecification specification)
	{
		float xOffset = this.getWidth() * specification.xOffset;
		float yOffset = this.getHeight() * specification.yOffset;
		
		float x = this.getX() + xOffset;
		float y = this.getY() + yOffset;
		
		child.setPosition(x, y);
	}
	
	private void resizeChild(Actor child, RelativeDisplaySpecification specification)
	{
		float width = this.getWidth() * specification.xScale;
		float height = this.getHeight() * specification.yScale;
		
		child.setSize(width, height);
	}
	
	@Override
	protected void sizeChanged()
	{
		resizeDisplays();
		repositionDisplays();
	}
	
	private void resizeDisplays()
	{
		resizeChild(leftSelectionDisplay, LEFT_DISPLAY_SPECIFICATION);
		resizeChild(middleSelectionDisplay, MIDDLE_DISPLAY_SPECIFICATION);
		resizeChild(rightSelectionDisplay, RIGHT_DISPLAY_SPECIFICATION);
	}
	
}
