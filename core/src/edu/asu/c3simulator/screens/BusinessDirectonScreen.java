package edu.asu.c3simulator.screens;

import java.util.LinkedList;
import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;

public class BusinessDirectonScreen extends BusinessCreationGuide 
{
	public BusinessDirectonScreen()
	{
		
		Table directions = new Table();
		
		Actor longTermDirection = createLongTermDirection();
		Actor shortTermDirection = createShortTermDirection();
		
		directions.add(longTermDirection).top();
		directions.add(shortTermDirection).top().spaceLeft(75);
		
		directions.setTransform(true);
		directions.setPosition(stage.getWidth()/2, stage.getHeight()/2);
		
		stage.addActor(directions);
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
			requestedParagraph.add(line);
			requestedParagraph.row();
		}
		return requestedParagraph;
	}
}
