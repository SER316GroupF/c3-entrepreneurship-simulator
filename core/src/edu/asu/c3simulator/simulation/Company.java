package edu.asu.c3simulator.simulation;

import java.util.List;

/**
 * Data representation of companies, which hold employees, have capital, 
 * and affect the global market.
 * 
 * @author nickkrogstad
 */
public interface Company
{
	List<Employee> getEmployees();
}
