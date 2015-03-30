package edu.asu.c3simulator.testing.stubs;

import java.util.LinkedList;
import java.util.List;

import edu.asu.c3simulator.simulation.Company;
import edu.asu.c3simulator.simulation.Employee;
import edu.asu.c3simulator.simulation.Employee.Position;

/**
 * Stub to return default "dummy" values for testing purposes.
 * 
 * @author Krogstad, Nick
 * @author Moore, Zachary
 *
 */
public class CompanyTestingStub implements Company
{
	@Override
	public List<Employee> getEmployees()
	{
		Employee employee1 = new Employee("Jason Richards", Position.MANAGER, 0.4f, 0.67f);
		Employee employee2 = new Employee("Janet Wilmore", Position.PRODUCT_DESIGNER,
				0.8f, 0.99f);
		
		List<Employee> employees = new LinkedList<>();
		employees.add(employee1);
		employees.add(employee2);
		
		return employees;
	}
}
