package edu.asu.c3simulator.widgets;

import java.awt.Color;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
import com.badlogic.gdx.scenes.scene2d.ui.Window.WindowStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import edu.asu.c3simulator.screens.MainHub;

public class SellCompany extends Table
{
	
	private TextButton button;
	private TextButtonStyle textButtonStyle;
	private BitmapFont font;
	private Window window;
	
	
	public SellCompany(final Game game, final Skin skin)
	{
		super();
		final TextButton continueButton = new TextButton("Continue", skin);
		continueButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				System.out.println("Continue!");
				//TODO: Delete business
			}
		});
		
		final TextButton cancelButton = new TextButton("Cancel", skin);
		cancelButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				System.out.println("Cancel.");
				if(window != null)
				{
					window.remove();
				}
			}
		});
        button = new TextButton("Sell", skin);
        add(button);
		this.setPosition(700, 500);
		font = new BitmapFont(Gdx.files.internal("fonts/arial32_superSample.fnt"));
		button.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				window = new Window("Warning", skin);
				window.defaults().spaceBottom(10);
				window.row().fill().expandX();
				window.add("This will permanently sell your business\n and you will not be able to "
						+ "but it back.\n Are you sure you would like to continue? ").row();
				Table t = new Table();
				t.add(continueButton).height(window.getPadTop());
				t.add(cancelButton).height(window.getPadTop());
				window.add(t);
				window.setDebug(true);
				window.getButtonTable().right().bottom();
				window.setPosition(200f, 200f);
				window.setSize(550f, 200f);
				window.setColor(1f,1f, 1f, 1f);
				window.setResizable(false);
				getStage().addActor(window);
			}
		});
		
}
}
