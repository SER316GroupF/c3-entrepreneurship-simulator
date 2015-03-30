package edu.asu.c3simulator.screens;

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
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import edu.asu.c3simulator.simulation.Company;
import edu.asu.c3simulator.simulation.Employee;
import edu.asu.c3simulator.simulation.EmployeeFactory;
import edu.asu.c3simulator.testing.stubs.CompanyTestingStub;

/**
 * Contains a list of employees, an Employee Model (which displays a single employee's
 * information, such as name, salary, etc), and controls to hire new employees, adjust
 * details (such as pay) of current employees, and other management tasks.
 * <p>
 * A list of active employees hired by the currently selected business is displayed, and
 * allows the user to select one employee at a time. If an employee is selected, their
 * information is displayed in the Employee Model.
 * <p>
 * Note: the Employee Model will display the information of a SINGLE employee at a time.
 * Multiple employees cannot be selected simultaneously.
 * <p>
 * The user has the option to set their base pay as well as fire them. The user may also
 * select New Employee and choose from a list of randomly generated employees to hire.
 * 
 * @author Krogstad, Nick
 * @author Moore, Zachary
 * 
 */
public class EmploymentScreen implements Screen
{
	/**
	 * To be added to an employee label in the list of employees. When the label is
	 * clicked, the selected employee will be set as the selected employee, and their
	 * information will be displayed in the employee model.
	 */
	private ManagePanel manage;
	private class EmployeeListener extends ClickListener
	{
		private Employee employee;
		private Label label;
		
		
		public EmployeeListener(Employee employee, Label label)
		{
			this.employee = employee;
			this.label = label;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			selectedEmployee = this.employee;
			selectedEmployeeLabel = this.label;
			employeeName.setText("Name: " + employee.getName());
			employeePosition.setText("Position: " + employee.getPosition());
			employeePayField.setText("" + employee.getActualHourlyWage());
			employeePreferredHourlyWage.setText("Income Preference: $"
					+ employee.getPreferredHourlyWage() + " / hr");
			employeeMorale.setText("Morale: " + employee.getMorale() * 100 + " %");
			netEarnings.setText("Net Earnings: " + employee.getNetEarnings());
			averageAnnualBonus.setText("Averale Annual Bonus: "
					+ employee.getAverageAnnualBonus() + "%");
			averageAnnualRaise.setText("Average Annual Raise: "
					+ employee.getAverageAnnualRaise() + "%");
			netBonuses.setText("Net Bonuses: " + employee.getNetBonuses());
			employeePayTable.setVisible(true);
		}
	}
	
	@SuppressWarnings("unused")
	private class NewEmployeeButtonListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			newEmployeeOptionsPanel.clear();
			// TODO: Do not keep number of employees generated magic
			for (int employeesGenerated = 0; employeesGenerated < 3; employeesGenerated++)
			{
				Employee employee = EmployeeFactory.getRandomEmployee();
				Actor employeeLabel = addEmployee(employee, newEmployeeOptionsPanel,
						false);
				employeeLabel.addListener(new HireEmployeeListener(employee));
			}
		}
	}
	
	/**
	 * 
	 * 
	 * @author Krogstad, Nick
	 * @author Moore, Zachary
	 *
	 */
	private class HireEmployeeListener extends ClickListener
	{
		private Employee employee;
		private VerticalGroup employeePane;
		
		public HireEmployeeListener(Employee employee)
		{
			this.employee = employee;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			newEmployeeOptionsPanel.clear();
			addEmployee(employee, employeePane);
		}
	}
	
	/**
	 * Listener to be added to a {@link TextField} that contains an employee wage.
	 * Whenever the value of the field is changed, the new value will be cast to an
	 * {@link Integer} and the result will be set as the new wage for the currently
	 * selected employee. The selected employee's label will be updated to reflect this
	 * change.
	 * <p>
	 * If the cast fails, or the proposed wage is otherwise invalid, the field will revert
	 * to the employee's current wage, and no change in wage will be registered with the
	 * employee.
	 * 
	 * @author Moore, Zachary
	 *
	 */
	private class WageFieldValidator implements TextFieldListener
	{
		@Override
		public void keyTyped(TextField textField, char c)
		{
			String proposal = textField.getText().trim();
			int proposalValue = selectedEmployee.getActualHourlyWage();
			
			if (proposal.length() <= 0)
			{
				proposalValue = 0;
			}
			
			try
			{
				proposalValue = Integer.parseInt(proposal);
				selectedEmployee.setActualHourlyWage(proposalValue);
				selectedEmployeeLabel.setText(selectedEmployee.toString());
			}
			catch (NumberFormatException exception)
			{
				// DO NOTHING
			}
			
			String text = Integer.toString(proposalValue);
			employeePayField.setText(text);
			employeePayField.setCursorPosition(text.length());
		}
	}
	
	@SuppressWarnings("unused")
	private class RemoveEmployeeListener extends ClickListener
	{
		// TODO
	}
	
	private static final int DESIGN_WIDTH = 1280;
	private static final int DESIGN_HEIGHT = 720;
	private static final int DESIGN_SCREEN_CENTER_X = DESIGN_WIDTH / 2;
	private static final int DESIGN_SCREEN_CENTER_Y = DESIGN_HEIGHT / 2;
	private static final int ROSTER_OFFSET_X = 500;
	private Actor employeePay;
	private Label employeeName;
	private Label employeePosition;
	private Label employeePreferredHourlyWage;
	private Label employeeMorale;
	private Label netBonuses;
	private Label averageAnnualBonus;
	private Label averageAnnualRaise;
	private Label netEarnings;
	private TextField employeePayField;
	private Table employeePayTable;
	private VerticalGroup newEmployeeOptionsPanel;
	
	@SuppressWarnings("unused")
	private Game game;
	private Skin skin;
	private Stage stage;
	private Employee selectedEmployee;
	private Label selectedEmployeeLabel;
	
	/**
	 * Used in order to transition between the Main Hub, Business Management, and
	 * Employment Screen.
	 * 
	 * @param game
	 */
	public EmploymentScreen(Game game)
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
		
		// TODO: Corner Advisor
		employeePane.setPosition(DESIGN_WIDTH / 4 + ROSTER_OFFSET_X, DESIGN_HEIGHT / 6);
		employeePane.setSize(350, 400);
		roster.setPosition(ROSTER_OFFSET_X, DESIGN_HEIGHT / 2);
		
		stage.addActor(roster);
		stage.addActor(employeePane);
		
	}
	
	public void initialize()
	{
		manage = new ManagePanel(game, stage);
	}
	
	/**
	 * @return The company for which information is currently being displayed.
	 */
	public Company getCompanyContext()
	{
		// TODO: return based on actual context, not a placeholder
		return new CompanyTestingStub();
	}
	
	/**
	 * @return The Employee Pane, a list of everyone employed to the business.
	 */
	private Actor createEmployeePane()
	{
		String newEmp = "New Employee";
		VerticalGroup employeeTable = new VerticalGroup();
		ScrollPane employeeScrollPane = new ScrollPane(employeeTable, skin);
		
		for (Employee employee : getCompanyContext().getEmployees())
		{
			addEmployee(employee, employeeTable);
		}
		
		newEmployee(newEmp, employeeTable);
		
		return employeeScrollPane;
	}
	
	/**
	 * Adds an employee to the provided vertical group
	 * <p>
	 * Used as an intermediate function to construct the Employee Panel
	 */
	private Label addEmployee(Employee employee, VerticalGroup group,
			boolean addEmployeeListener)
	{
		Label label = new Label(employee.toString(), skin);
		group.addActor(label);
		label.setAlignment(Align.center);
		
		if (addEmployeeListener)
		{
			label.addListener(new EmployeeListener(employee, label));
		}
		
		return label;
		
	}
	
	/**
	 * Adds an Employee object to the Vertical Group and sets its visibility to true
	 * 
	 * @param employee
	 * @param group
	 * 
	 */
	private Label addEmployee(Employee employee, VerticalGroup group)
	{
		return addEmployee(employee, group, true);
	}
	
	/**
	 * Placeholder for the "New Employee" button that will display a panel of randomly
	 * generated employees available for hire when clicked
	 */
	private void newEmployee(String newEmp, VerticalGroup group)
	{
		Label label = new Label(newEmp, skin);
		group.addActor(label);
		label.setAlignment(Align.center);
	}
	
	/**
	 * Creates the Employee Pay Table that allows the user to input a desired hourly wage
	 * for the selected employee
	 */
	private Actor createEmployeePay()
	{
		employeePayTable = new Table(skin);
		Label payLabel = new Label("Pay: $", skin);
		Label suffix = new Label(" / hr", skin);
		employeePayField = new TextField("", skin);
		
		employeePayField.setTextFieldListener(new WageFieldValidator());
		
		employeePayTable.add(payLabel);
		employeePayTable.add(employeePayField);
		employeePayTable.add(suffix);
		employeePayTable.setVisible(false);
		return employeePayTable;
	}
	
	/**
	 * Creates the Employee Model, which displays all of the components of an employee
	 * including: Name, Position, Pay, Pay Preference, Morale, Net Salary, Average Annual
	 * Bonus, Average Annual Raise, and Net Bonuses
	 */
	private Actor createEmployeeModel()
	{
		employeeName = new Label("", skin);
		employeePosition = new Label("", skin);
		employeePay = createEmployeePay();
		employeePreferredHourlyWage = new Label("", skin);
		employeeMorale = new Label("", skin);
		netBonuses = new Label("", skin);
		averageAnnualBonus = new Label("", skin);
		averageAnnualRaise = new Label("", skin);
		netEarnings = new Label("", skin);
		
		employeeName.setAlignment(Align.left);
		employeePreferredHourlyWage.setAlignment(Align.left);
		employeeMorale.setAlignment(Align.left);
		netBonuses.setAlignment(Align.left);
		averageAnnualBonus.setAlignment(Align.left);
		averageAnnualRaise.setAlignment(Align.left);
		netEarnings.setAlignment(Align.left);
		
		VerticalGroup employeeModel = new VerticalGroup();
		employeeModel.addActor(employeeName);
		employeeModel.addActor(employeePosition);
		employeeModel.addActor(employeePay);
		employeeModel.addActor(employeePreferredHourlyWage);
		employeeModel.addActor(employeeMorale);
		employeeModel.addActor(netBonuses);
		employeeModel.addActor(averageAnnualBonus);
		employeeModel.addActor(averageAnnualRaise);
		employeeModel.addActor(netEarnings);
		
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
