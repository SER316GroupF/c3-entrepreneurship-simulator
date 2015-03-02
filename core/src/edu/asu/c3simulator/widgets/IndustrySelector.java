package edu.asu.c3simulator.widgets;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.simulation.Industry;
import edu.asu.c3simulator.widgets.groups.ContainerMaintenanceGroup;

/**
 * Widget consisting of three containers and a list of {@link Industry} objects. Buttons
 * on either side of the {@link IndustrySelector} allow cycling through the list, which
 * alters which {@link Industry}s are displayed.
 * <p>
 * The selector will cycle through the industries, looping to the beginning of the list if
 * it reaches the end.
 * 
 * @author Moore, Zachary
 * 
 */
public class IndustrySelector extends Widget
{
	/**
	 * Structure to specify where to position each of the three containers, relative to
	 * the full selector widget
	 * 
	 * @author Moore, Zachary
	 * 
	 */
	private static class RelativeDisplaySpecification
	{
		private float xOffset;
		private float yOffset;
		private float xScale;
		private float yScale;
		
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
	
	/** Location and size of the left display, relative to this {@link IndustrySelector} */
	private static final RelativeDisplaySpecification LEFT_DISPLAY_SPECIFICATION = new RelativeDisplaySpecification(
			0.0f, 0.2f, 0.2f, 0.6f);
	
	/** Location and size of the middle display, relative to this {@link IndustrySelector} */
	private static final RelativeDisplaySpecification MIDDLE_DISPLAY_SPECIFICATION = new RelativeDisplaySpecification(
			0.35f, 0.0f, 0.3f, 1.0f);
	
	/** Location and size of the right display, relative to this {@link IndustrySelector} */
	private static final RelativeDisplaySpecification RIGHT_DISPLAY_SPECIFICATION = new RelativeDisplaySpecification(
			0.8f, 0.2f, 0.2f, 0.6f);
	
	/**
	 * Location and size of {@link #cycleLeftButton}, relative to this
	 * {@link IndustrySelector}
	 */
	private static final RelativeDisplaySpecification LEFT_CYCLE_SPECIFICATION = new RelativeDisplaySpecification(
			0.0f, 0.0f, 0.1f, 0.1f);
	
	/**
	 * Location and size of {@link #cycleRightButton}, relative to this
	 * {@link IndustrySelector}
	 */
	private static final RelativeDisplaySpecification RIGHT_CYCLE_SPECIFICATION = new RelativeDisplaySpecification(
			0.9f, 0.0f, 0.1f, 0.1f);
	
	private List<Industry> contents;
	private int currentSelectionIndex;
	private ContainerMaintenanceGroup<Actor> leftSelectionDisplay;
	private ContainerMaintenanceGroup<Actor> middleSelectionDisplay;
	private ContainerMaintenanceGroup<Actor> rightSelectionDisplay;
	
	private Image cycleLeftButton;
	private Image cycleRightButton;
	
	public IndustrySelector()
	{
		super();
		this.contents = new ArrayList<>();
		this.currentSelectionIndex = 0;
		this.leftSelectionDisplay = new ContainerMaintenanceGroup<>();
		this.middleSelectionDisplay = new ContainerMaintenanceGroup<>();
		this.rightSelectionDisplay = new ContainerMaintenanceGroup<>();
		
		FileHandle file = Gdx.files.internal("images/left-arrow-light.png");
		Texture cycleLeftButtonTexture = new Texture(file);
		file = Gdx.files.internal("images/right-arrow-light.png");
		Texture cycleRightButtonTexture = new Texture(file);
		
		this.cycleLeftButton = new Image(cycleLeftButtonTexture);
		this.cycleRightButton = new Image(cycleRightButtonTexture);
		addCycleListeners();
	}
	
	/**
	 * Creates a new {@link IndustrySelector}, initialized with the provided list of
	 * {@link Industry} objects.
	 * 
	 * @param industries
	 *            Contents of this list
	 */
	public IndustrySelector(Industry... industries)
	{
		this();
		for (Industry industry : industries)
		{
			this.add(industry);
		}
	}
	
	@Override
	public Actor debug()
	{
		leftSelectionDisplay.debug();
		middleSelectionDisplay.debug();
		rightSelectionDisplay.debug();
		return this;
	}
	
	private void addCycleListeners()
	{
		cycleLeftButton.addListener(new ClickListener(Input.Buttons.LEFT) {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				cycleLeft();
			}
		});
		
		cycleRightButton.addListener(new ClickListener(Input.Buttons.LEFT) {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				cycleRight();
			}
		});
	}
	
	/**
	 * Add to the contents of this selector.
	 * 
	 * @param industry
	 *            Will be added to {@link #contents}
	 */
	public void add(Industry industry)
	{
		contents.add(industry);
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
		
		Industry previousSelection = contents.get(previousIndex);
		Actor previousSelectionDisplay = createIndustryDisplay(previousSelection);
		
		Industry nextSelection = contents.get(nextIndex);
		Actor nextSelectionDisplay = createIndustryDisplay(nextSelection);
		
		Industry currentSelection = contents.get(currentIndex);
		Actor currentSelectionDisplay = createIndustryDisplay(currentSelection);
		
		this.leftSelectionDisplay.setActor(previousSelectionDisplay);
		this.middleSelectionDisplay.setActor(currentSelectionDisplay);
		this.rightSelectionDisplay.setActor(nextSelectionDisplay);
		
		resizeDisplays();
		repositionDisplays();
	}
	
	/**
	 * Creates an {@link Actor} to depict the given {@link Industry} object
	 * 
	 * @param industry
	 *            Target to display
	 * @return Display for the given target
	 */
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
		if (stage != null)
		{
			stage.addActor(leftSelectionDisplay);
			stage.addActor(rightSelectionDisplay);
			stage.addActor(middleSelectionDisplay);
			stage.addActor(cycleLeftButton);
			stage.addActor(cycleRightButton);
			
			leftSelectionDisplay.align(Align.top);
			rightSelectionDisplay.align(Align.top);
			middleSelectionDisplay.align(Align.top);
		}
	}
	
	@Override
	public void draw(Batch batch, float parentAlpha)
	{
		super.draw(batch, parentAlpha);
		
		if (getStage() == null)
		{
			leftSelectionDisplay.draw(batch, parentAlpha);
			middleSelectionDisplay.draw(batch, parentAlpha);
			rightSelectionDisplay.draw(batch, parentAlpha);
			cycleLeftButton.draw(batch, parentAlpha);
			cycleRightButton.draw(batch, parentAlpha);
		}
		
	}
	
	/**
	 * Change the current selection to the next selection, and update the displays
	 */
	public void cycleRight()
	{
		currentSelectionIndex++;
		validateCurrentSelectionIndex();
	}
	
	/**
	 * Change the current selection to the previous selection, and update the displays
	 */
	public void cycleLeft()
	{
		currentSelectionIndex--;
		validateCurrentSelectionIndex();
	}
	
	/**
	 * Ensure the {@link #currentSelectionIndex} is in valid bounds, and update the
	 * displays
	 */
	private void validateCurrentSelectionIndex()
	{
		currentSelectionIndex = convertToValidSelectionIndex(currentSelectionIndex);
		recalculateDisplayContents();
	}
	
	/**
	 * Convert the given index to a valid index such that 0 <= index < contents.size()
	 * 
	 * @param index
	 *            Arbitrary index of {@link #contents} to convert
	 * @return An index of {@link #contents} that is the wrapped equivalent of index, and
	 *         in the valid bounds
	 */
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
	
	/**
	 * Sets the position of {@link #leftSelectionDisplay}, {@link #rightSelectionDisplay},
	 * and {@link #middleSelectionDisplay} based on their
	 * {@link RelativeDisplaySpecification}
	 */
	private void repositionDisplays()
	{
		repositionChild(leftSelectionDisplay, LEFT_DISPLAY_SPECIFICATION);
		repositionChild(middleSelectionDisplay, MIDDLE_DISPLAY_SPECIFICATION);
		repositionChild(rightSelectionDisplay, RIGHT_DISPLAY_SPECIFICATION);
	}
	
	/**
	 * Set the position of an actor based on the given specification
	 * 
	 * @param child
	 *            Actor to position
	 * @param specification
	 *            Details for repositioning the actor relative to this selector
	 */
	private void repositionChild(Actor child, RelativeDisplaySpecification specification)
	{
		float xOffset = this.getWidth() * specification.xOffset;
		float yOffset = this.getHeight() * specification.yOffset;
		
		float x = this.getX() + xOffset;
		float y = this.getY() + yOffset;
		
		child.setPosition(x, y);
	}
	
	/**
	 * Set the size of an actor based on the given specification
	 * 
	 * @param child
	 *            Actor to position
	 * @param specification
	 *            Details for resizing the actor relative to this selector
	 */
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
		
		resizeButtons();
		repositionButtons();
	}
	
	/**
	 * Sets the position of {@link #cycleLeftButton} and {@link #cycleRightButton} based
	 * on their {@link RelativeDisplaySpecification}
	 */
	private void repositionButtons()
	{
		repositionChild(cycleLeftButton, LEFT_CYCLE_SPECIFICATION);
		repositionChild(cycleRightButton, RIGHT_CYCLE_SPECIFICATION);
	}
	
	/**
	 * Sets the size of {@link #cycleLeftButton} and {@link #cycleRightButton} based on
	 * their {@link RelativeDisplaySpecification}
	 */
	private void resizeButtons()
	{
		resizeChild(cycleLeftButton, LEFT_CYCLE_SPECIFICATION);
		resizeChild(cycleRightButton, RIGHT_CYCLE_SPECIFICATION);
	}
	
	/**
	 * Sets the size of {@link #leftSelectionDisplay}, {@link #rightSelectionDisplay}, and
	 * {@link #middleSelectionDisplay} based on their {@link RelativeDisplaySpecification}
	 */
	private void resizeDisplays()
	{
		resizeChild(leftSelectionDisplay, LEFT_DISPLAY_SPECIFICATION);
		resizeChild(middleSelectionDisplay, MIDDLE_DISPLAY_SPECIFICATION);
		resizeChild(rightSelectionDisplay, RIGHT_DISPLAY_SPECIFICATION);
	}
	
}
