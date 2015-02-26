package edu.asu.c3simulator.screens;

import java.util.List;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.widgets.CornerAdvisor;
import edu.asu.c3simulator.widgets.Employee;

public class EmployeeHistoryScreen implements Screen
{
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	private static final int DESIGN_SCREEN_CENTER_X = DESIGN_WIDTH / 2;
	private static final int DESIGN_SCREEN_CENTER_Y = DESIGN_HEIGHT / 2;
	private static final int ROSTER_OFFSET = 500;
	private Label employeeName;
	private Label employeePay;
	private Label employeePref;
	private Label employeeMorale;
	private List<Employee> employees;
	
	private Game game;
	private Skin skin;
	private Stage stage;
	
	private class EmployeeListener extends ClickListener
	{
		private Employee employee;
		private String newEmp;
		
		public EmployeeListener(Employee passedEmployee)
		{
			employee = passedEmployee;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			employeeName.setText("Name: " + employee.getEmployeeName());
			employeePay.setText("Pay: $" + employee.getEmployeePay() + " / hr");
			employeePref.setText("Income Preference: $" + employee.getEmployeePref() + " / hr");
			employeeMorale.setText("Morale: " + employee.getEmployeeMorale() + " / 10");
		}
	}
	
	public EmployeeHistoryScreen(Game game)
	{
		this.game = game;
		
		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH, DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		Table roster = new Table();
		
		Actor employeePane = createEmployeePane();
		Actor employeeModel = createEmployeeModel();
		
		roster.add(employeeModel).top().spaceRight(75);
		roster.add(employeePane).top().spaceLeft(75);
		
		roster.setTransform(true);
		roster.setPosition(DESIGN_SCREEN_CENTER_X, DESIGN_SCREEN_CENTER_Y);
		
		CornerAdvisor advisor = new CornerAdvisor("You can view an employee's history on this screen.");
		float padding = 0.01f * DESIGN_HEIGHT;
		float advisorLeft = DESIGN_WIDTH - advisor.getPrefWidth() - padding;
		float advisorBottom = DESIGN_HEIGHT - advisor.getPrefHeight() - padding;
		employeePane.setPosition(DESIGN_WIDTH / 4 + ROSTER_OFFSET, DESIGN_HEIGHT / 6);
		employeePane.setSize(350, 400);
		roster.setPosition(ROSTER_OFFSET, DESIGN_HEIGHT / 2);
		advisor.setPosition(advisorLeft, advisorBottom);
		advisor.setSize(100, 100);
		// HomeButton home = new HomeButton();
		// roster.debug();
		
		// stage.addActor(home);
		stage.addActor(advisor);
		stage.addActor(roster);
		stage.addActor(employeePane);
		
	}
	
	private Actor createEmployeePane()
	{
		Employee employee1 = new Employee("Jason Richards", 14, 11, 10);
		Employee employee2 = new Employee("Janet Wilmore", 8, 8, 7);
		VerticalGroup employeeTable = new VerticalGroup();
		
		ScrollPane employeeScrollPane = new ScrollPane(employeeTable, skin);
		
		addEmployee(employee1, employeeTable);
		addEmployee(employee2, employeeTable);
		
		return employeeScrollPane;
	}
	
	private void addEmployee(Employee employee, VerticalGroup group)
	{
		Label label = new Label(employee.toString(), skin);
		group.addActor(label);
		label.setAlignment(Align.center);
		label.addListener(new EmployeeListener(employee));
	}
	
	private Actor createEmployeeModel()
	{
		
		Table employeeModel = new Table(skin);
		
		employeeName = new Label("", skin);
		employeePay = new Label("", skin);
		employeePref = new Label("", skin);
		employeeMorale = new Label("", skin);
		
		employeeName.setAlignment(Align.left);
		employeePay.setAlignment(Align.left);
		employeePref.setAlignment(Align.left);
		employeeMorale.setAlignment(Align.left);
		
		employeeModel.add(employeeName);
		employeeModel.row();
		employeeModel.add(employeePay);
		employeeModel.row();
		employeeModel.add(employeePref);
		employeeModel.row();
		employeeModel.add(employeeMorale);
		
		return employeeModel;
	}
	
	@Override
	public void dispose()
	{
		stage.dispose();
		this.game = null;
	}
	
	@Override
	public void hide()
	{
		Gdx.input.setInputProcessor(null);
	}
	
	@Override
	public void pause()
	{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height)
	{
		stage.getViewport().update(width, height);
	}
	
	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("The method is not implemented yet.");
	}
	
	@Override
	public void show()
	{
		Gdx.input.setInputProcessor(stage);
	}
}
