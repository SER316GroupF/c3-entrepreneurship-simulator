package edu.asu.c3simulator.simulation;

import java.util.List;

/**
 * @author nickkrogstad
 * Data representation of companies, which hold employees, have capital, 
 * and affect the global market.
 */
public interface Company
{
	List<Employee> getEmployees();
}
