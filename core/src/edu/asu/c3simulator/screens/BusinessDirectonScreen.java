package edu.asu.c3simulator.screens;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ButtonGroup;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

import edu.asu.c3simulator.widgets.Location;

/**
 * Displays two options for general company goals and highlights selected option.
 * 
 * @author Alyahya, Mohammed
 * some of Zack's code was reused here
 */
public class BusinessDirectonScreen extends BusinessCreationGuideScreens 
{
	private Skin skin;
	private ButtonGroup optionCheckBoxesGroup;
	private CheckBox longTermOption, shortTermOption;
	
	public BusinessDirectonScreen(Stage stage)
	{
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		Table directions = new Table();
		
		Actor longTermDirection = createLongTermDirection();
		Actor shortTermDirection = createShortTermDirection();
		
		longTermOption = new CheckBox(" Long Term", skin);
		shortTermOption = new CheckBox(" Short Term", skin);
		
		Label longTermLabel = new Label("Long Term", skin);
		Label shortTermLabel = new Label("Short Term", skin);
		
		optionCheckBoxesGroup = new ButtonGroup(longTermOption, shortTermOption);
		optionCheckBoxesGroup.setMaxCheckCount(1);
		optionCheckBoxesGroup.setMinCheckCount(0);
		optionCheckBoxesGroup.setUncheckLast(true);
		
		directions.add(longTermLabel).height(100).top();
		directions.add(shortTermLabel).height(100).top().spaceLeft(75);
		directions.row();
		directions.add(longTermDirection).top();
		directions.add(shortTermDirection).top().spaceLeft(75);
		directions.row();
		directions.add(longTermOption).height(100);
		directions.add(shortTermOption).height(100).spaceLeft(75);
		
		directions.padLeft(100);
		directions.setTransform(true);
		
		addActor(directions, new Location(stage.getWidth()/2, stage.getHeight()/2));
	}
	
	Actor createLongTermDirection()
	{
		String communityOutreach [] = {"Community Outreach:","Building company's name recognition ",
										"within the community through outreach ","projects."};
		String revenue [] = {"Revenue:","Concrete, annual fiscal goals supporting ","revenue expectations."};
		String customerService [] = {"Customer Service:","Maintain a long-lasting bond and ","build a devoted following."};
		
		VerticalGroup longTermDirectionText = new VerticalGroup();
		longTermDirectionText.addActor(createParagraph(communityOutreach));
		longTermDirectionText.addActor(createParagraph(revenue));
		longTermDirectionText.addActor(createParagraph(customerService));
		
		longTermDirectionText.space(25);
		longTermDirectionText.left();
		return longTermDirectionText;
	}
	
	Actor createShortTermDirection()
	{
		String communityOutreach [] = {"Community Outreach:","Incentives for employees who volunteer",
										"with designated community projects."};
		String revenue [] = {"Revenue:","One month contracts for advising","consultants"};
		String customerService [] = {"Customer Service:","Host monthly drawings for free","products or discounts on future ","purchases."};
		
		VerticalGroup longTermDirectionText = new VerticalGroup();
		longTermDirectionText.addActor(createParagraph(communityOutreach));
		longTermDirectionText.addActor(createParagraph(revenue));
		longTermDirectionText.addActor(createParagraph(customerService));
		
		longTermDirectionText.space(25);
		longTermDirectionText.left();
		return longTermDirectionText;
	}
	
	Table createParagraph(String[] text)
	{
		// REFACTOR: Combine with #createDifficultyChoiceDescriptionHard
		Table requestedParagraph = new Table();
		
		List<Label> lines = new LinkedList<>();
		// REFACTOR: Load lines from file
		for(String stringLine : text)
		{
			lines.add(new Label(stringLine, skin));
		}
		
		for (Label line : lines)
		{
			line.setAlignment(Align.left);
			requestedParagraph.add(line).left();
			requestedParagraph.row();
		}
		return requestedParagraph;
	}
	
	public String getChosenOption()
	{
		CheckBox chosenOption = (CheckBox) optionCheckBoxesGroup.getChecked();
		if(chosenOption == longTermOption)
			return "long term";
		else if(chosenOption == shortTermOption)
			return "short term";
		else
			return "none";
	}
}
