package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Window;
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
					+ Math.round(employee.getPreferredHourlyWage()) + " / hr");
			employeeMorale.setText("Morale: " + Math.round(employee.getMorale() * 100)
					+ "%");
			netEarnings.setText("Net Earnings: " + employee.getNetEarnings());
			averageAnnualBonus.setText("Averale Annual Bonus: "
					+ employee.getAverageAnnualBonus() + "%");
			averageAnnualRaise.setText("Average Annual Raise: "
					+ employee.getAverageAnnualRaise() + "%");
			netBonuses.setText("Net Bonuses: $" + employee.getNetBonuses());
			employeePayTable.setVisible(true);
			fireEmployee.setVisible(true);
			bonus.setVisible(true);
		}
	}
	
	/**
	 * Displays a list of randomly generated employees in a window when the "New Employee"
	 * button is selected.
	 */
	private class NewEmployeeButtonListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			newEmployeeOptionsWindow.clear();
			Table availableNewEmployees = new Table();
			
			// TODO: Do not keep number of employees generated magic
			for (int employeesGenerated = 0; employeesGenerated < 5; employeesGenerated++)
			{
				Employee employee = EmployeeFactory.getRandomEmployee();
				Actor employeeComponent = createNewEmployeeComponent(employee);
				availableNewEmployees.add(employeeComponent).expand().fill().space(20)
						.row();
			}
			
			ScrollPane newEmployeeOptionsScroll = new ScrollPane(availableNewEmployees,
					skin);
			TextButton cancel = new TextButton("Cancel", skin);
			cancel.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					newEmployeeOptionsWindow.remove();
				}
			});
			
			newEmployeeOptionsWindow.add(newEmployeeOptionsScroll).expand().fill().row();
			newEmployeeOptionsWindow.add(cancel).expandX().fillX();
			stage.addActor(newEmployeeOptionsWindow);
		}
	}
	
	/**
	 * Listener that displays a window when the "Employee Bonus" button is selected.
	 * Includes a text field where the user can input an amount of money to give as a
	 * bonus to the selected employee.
	 * 
	 * @author nickkrogstad
	 * 
	 */
	private class EmployeeBonusListener extends ClickListener
	{
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			Actor employeeBonus = createEmployeeBonusWindow(selectedEmployee);
			TextButton cancel = new TextButton("Cancel", skin);
			cancel.addListener(new ClickListener() {
				@Override
				public void clicked(InputEvent event, float x, float y)
				{
					employeeBonusWindow.remove();
					employeeBonusWindow.clear();
				}
			});
			
			employeeBonusWindow.add(employeeBonus).expand().fill().space(30).row();
			employeeBonusWindow.add(cancel).expandX().fillX();
			stage.addActor(employeeBonusWindow);
		}
		
	}
	
	/**
	 * Used when the user selects "New Employee" and a window is generated with a list of
	 * employees that are available for hire. Each employee has the hire options which,
	 * once selected, will add them to the list of hired employees on your active roster.
	 * 
	 * @author Krogstad, Nick
	 * @author Moore, Zachary
	 * 
	 */
	private class HireEmployeeListener extends ClickListener
	{
		private Employee employee;
		
		public HireEmployeeListener(Employee employee)
		{
			this.employee = employee;
		}
		
		@Override
		public void clicked(InputEvent event, float x, float y)
		{
			addEmployee(employee, employeeTable);
			((CompanyTestingStub) getCompanyContext()).addEmployee(employee);
			newEmployeeOptionsWindow.remove();
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
	private Window newEmployeeOptionsWindow;
	private Window employeeBonusWindow;
	private VerticalGroup employeeTable;
	private CompanyTestingStub companyTestingStub;
	private TextButton fireEmployee;
	private TextButton bonus;
	
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
	 *            Will be used to call {@link Game#setScreen(Screen)} when transitioning
	 */
	public EmploymentScreen(Game game)
	{
		this.game = game;
		
		Viewport stageViewport = new StretchViewport(DESIGN_WIDTH, DESIGN_HEIGHT);
		this.stage = new Stage(stageViewport);
		this.skin = new Skin(Gdx.files.internal("skins/default/uiskin.json"));
		
		companyTestingStub = new CompanyTestingStub();
		
		Table roster = new Table();
		
		Actor employeePane = createEmployeePane();
		Actor employeeModel = createEmployeeModel();
		
		roster.add(employeeModel).top().spaceRight(75);
		roster.add(employeePane).top().spaceLeft(75);
		
		/**
		 * Sets size and position of the window that pops up when "New Employee is
		 * selected. Window provides options for new employees available for hire.
		 */
		newEmployeeOptionsWindow = new Window("Employees for Hire", skin);
		newEmployeeOptionsWindow.setSize(0.70f * stage.getWidth(),
				0.80f * stage.getHeight());
		newEmployeeOptionsWindow.setPosition(
				stage.getWidth() / 2 - (0.70f * stage.getWidth()) / 2, stage.getHeight()
						/ 2 - (0.80f * stage.getHeight()) / 2);
		newEmployeeOptionsWindow.setMovable(false);
		newEmployeeOptionsWindow.setResizable(false);
		
		/**
		 * Sets size and position of the window that pops up when "Employee Bonus" is
		 * selected. Window provides the option to input a bonus to give to the selected
		 * employee.
		 */
		employeeBonusWindow = new Window("Employee Bonus", skin);
		employeeBonusWindow.setSize(0.30f * stage.getWidth(), 0.30f * stage.getHeight());
		employeeBonusWindow.setPosition(stage.getWidth() / 2 - (0.30f * stage.getWidth())
				/ 2, stage.getHeight() / 2 - (0.30f * stage.getHeight()) / 2);
		employeeBonusWindow.setMovable(true);
		employeeBonusWindow.setResizable(false);
		
		roster.setTransform(true);
		roster.setPosition(DESIGN_SCREEN_CENTER_X, DESIGN_SCREEN_CENTER_Y);
		
		employeePane.setPosition(DESIGN_WIDTH / 4 + ROSTER_OFFSET_X, DESIGN_HEIGHT / 6);
		employeePane.setSize(350, 400);
		roster.setPosition(ROSTER_OFFSET_X, DESIGN_HEIGHT / 2);
		
		stage.addActor(roster);
		stage.addActor(employeePane);
		
	}
	
	/**
	 * @return The company for which information is currently being displayed.
	 */
	public Company getCompanyContext()
	{
		// TODO: return based on actual context, not a placeholder
		return companyTestingStub;
	}
	
	/**
	 * The New Employee Component is a window consisting of a list of employees and
	 * information including name, position, morale, and a text box allowing the user to
	 * set their pay if hired. It also displays a hire button next to each employee (see:
	 * Employee Hire Button).
	 * 
	 * @return Window containing a list of employees available for hire.
	 */
	private Actor createNewEmployeeComponent(Employee newRandomEmployee)
	{
		Table newEmployeeComponent = new Table();
		
		/**
		 * Left side of New Employee Component; includes elements that are not defined by
		 * the user's interactions.
		 */
		VerticalGroup newEmployeeInformation = new VerticalGroup();
		Label name = new Label("Name: " + newRandomEmployee.getName(), skin);
		Label position = new Label("Position: " + newRandomEmployee.getPosition(), skin);
		Label preferredHourlyWage = new Label("Preferred Hourly Wage: $"
				+ Math.round(newRandomEmployee.getPreferredHourlyWage()), skin);
		newEmployeeInformation.addActor(name);
		newEmployeeInformation.addActor(position);
		newEmployeeInformation.addActor(preferredHourlyWage);
		
		/**
		 * Right side of New Employee Component; includes elements that are affected by
		 * the user's interactions.
		 */
		VerticalGroup newEmployeeInputs = new VerticalGroup();
		Label morale = new Label("Morale: "
				+ Math.round(newRandomEmployee.getMorale() * 100) + "%", skin);
		Actor pay = createNewEmployeePayField(newRandomEmployee, morale);
		TextButton hire = new TextButton("Hire", skin);
		hire.addListener(new HireEmployeeListener(newRandomEmployee));
		newEmployeeInputs.addActor(pay);
		newEmployeeInputs.addActor(morale);
		newEmployeeInputs.addActor(hire);
		
		newEmployeeComponent.add(newEmployeeInformation).expand().fill();
		newEmployeeComponent.add(newEmployeeInputs).expand().fill();
		
		return newEmployeeComponent;
	}
	
	/**
	 * @param selectedEmployee
	 *            The currently selected employee that will be receiving the bonus
	 * @return Table consisting of a TextField and Label
	 */
	private Actor createEmployeeBonusWindow(Employee selectedEmployee)
	{
		Table employeeBonusTable = new Table();
		
		VerticalGroup employeeBonusInformation = new VerticalGroup();
		Label bonus = new Label("Choose an amount: ", skin);
		Actor pay = createBonusPayField(selectedEmployee, netBonuses);
		
		employeeBonusInformation.addActor(bonus);
		employeeBonusInformation.addActor(pay);
		bonus.setAlignment(Align.right);
		
		employeeBonusTable.add(employeeBonusInformation).expand().fill();
		
		return employeeBonusTable;
	}
	
	/**
	 * Ensures that all fields that are affected by the employee's actual wage will be
	 * updated when the actual wage is altered.
	 */
	private class EmployeePayFieldListener implements TextField.TextFieldListener
	{
		private Employee newRandomEmployee;
		private TextField employeePayField;
		private Label morale;
		
		public EmployeePayFieldListener(Employee newRandomEmployee,
				TextField employeePayField, Label morale)
		{
			this.newRandomEmployee = newRandomEmployee;
			this.employeePayField = employeePayField;
			this.morale = morale;
		}
		
		@Override
		public void keyTyped(TextField textField, char c)
		{
			String proposal = employeePayField.getText().trim();
			int proposalValue = newRandomEmployee.getActualHourlyWage();
			
			if (proposal.length() <= 0)
			{
				proposalValue = 0;
			}
			
			try
			{
				proposalValue = Integer.parseInt(proposal);
				newRandomEmployee.setActualHourlyWage(proposalValue);
				morale.setText("Morale: " + newRandomEmployee.getMorale() * 100 + " %");
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
	
	/**
	 * Ensures that all fields that are affected by the employee's net bonuses are updated
	 * when the net bonus is altered.
	 */
	private class NetBonusPayFieldListener implements TextField.TextFieldListener
	{
		private Employee selectedEmployee;
		private TextField bonusPayField;
		
		public NetBonusPayFieldListener(Employee selectedEmployee, TextField bonusPayField)
		{
			this.selectedEmployee = selectedEmployee;
			this.bonusPayField = bonusPayField;
		}
		
		@Override
		public void keyTyped(TextField textField, char c)
		{
			String proposal = bonusPayField.getText().trim();
			int proposalValue = selectedEmployee.getNetBonuses();
			
			if (proposal.length() <= 0)
			{
				proposalValue = 0;
			}
			
			try
			{
				proposalValue = Integer.parseInt(proposal);
				selectedEmployee.setNetBonuses(proposalValue);
			}
			catch (NumberFormatException exception)
			{
				// DO NOTHING
			}
			
			String text = Integer.toString(proposalValue);
			bonusPayField.setText(text);
			bonusPayField.setCursorPosition(text.length());
			
		}
	}
	
	/**
	 * @param newRandomEmployee
	 *            Contains randomly generated components including: name, position, wage
	 *            tolerance, and ambition
	 * @param morale
	 *            Component of an employee that is directly affected by the user's
	 *            decision for actual hourly wage.
	 * @return Pay field that allows the user to set the potential new employee's actual
	 *         pay prior to hiring.
	 */
	private Actor createNewEmployeePayField(Employee newRandomEmployee, Label morale)
	{
		Table employeePayTable = new Table(skin);
		Label payLabel = new Label("Pay: $", skin);
		Label suffix = new Label(" / hr", skin);
		TextField employeePayField = new TextField("", skin);
		
		employeePayField.setTextFieldListener(new EmployeePayFieldListener(
				newRandomEmployee, employeePayField, morale));
		
		employeePayTable.add(payLabel);
		employeePayTable.add(employeePayField);
		employeePayTable.add(suffix);
		return employeePayTable;
	}
	
	/**
	 * @param netBonuses
	 *            Component of an employee that is directly affected when entering an
	 *            amount into the pay field for bonuses
	 * @return The pay field that is displayed in the Employee Bonus Window.
	 */
	private Actor createBonusPayField(Employee employee, Label netBonuses)
	{
		Table employeePayTable = new Table(skin);
		Label payLabel = new Label("$", skin);
		TextField employeePayField = new TextField("", skin);
		
		employeePayField.setTextFieldListener(new NetBonusPayFieldListener(employee,
				employeePayField));
		
		employeePayTable.add(payLabel);
		employeePayTable.add(employeePayField);
		return employeePayTable;
	}
	
	/**
	 * @return The Employee Pane, a list of everyone employed to the business.
	 */
	private Actor createEmployeePane()
	{
		String newEmp = "New Employee";
		employeeTable = new VerticalGroup();
		VerticalGroup updatedNewEmployee = new VerticalGroup();
		
		ScrollPane employeeScrollPane = new ScrollPane(updatedNewEmployee, skin);
		
		for (Employee employee : getCompanyContext().getEmployees())
		{
			addEmployee(employee, employeeTable);
		}
		
		updatedNewEmployee.addActor(employeeTable);
		newEmployee(newEmp, updatedNewEmployee);
		
		return employeeScrollPane;
	}
	
	/**
	 * Removes the currently selected employee from the active list of hired employees in
	 * Employee Pane.
	 * 
	 * @param employee
	 *            The currently selected employee
	 */
	public void removeEmployee(Employee employee)
	{
		((CompanyTestingStub) getCompanyContext()).removeEmployee(employee);
		employeeTable.clear();
		
		for (Employee employee1 : getCompanyContext().getEmployees())
		{
			addEmployee(employee1, employeeTable);
		}
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
		label.addListener(new NewEmployeeButtonListener());
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
	 * 
	 * @return Employee Model with all of its components
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
		
		fireEmployee = new TextButton("Fire Employee", skin);
		fireEmployee.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y)
			{
				removeEmployee(selectedEmployee);
				clearEmployeeModel();
			}
		});
		
		bonus = new TextButton("Employee Bonus", skin);
		bonus.addListener(new EmployeeBonusListener());
		
		bonus.setVisible(false);
		fireEmployee.setVisible(false);
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
		employeeModel.addActor(fireEmployee);
		employeeModel.addActor(bonus);
		
		return employeeModel;
	}
	
	/**
	 * Clears the Employee Model (the information displayed when you select an employee)
	 * when you fire the employee that is currently selected.
	 */
	private void clearEmployeeModel()
	{
		employeePayTable.setVisible(false);
		fireEmployee.setVisible(false);
		bonus.setVisible(false);
		employeeName.setText("");
		employeePosition.setText("");
		employeePreferredHourlyWage.setText("");
		employeeMorale.setText("");
		netBonuses.setText("");
		averageAnnualBonus.setText("");
		averageAnnualRaise.setText("");
		netEarnings.setText("");
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
