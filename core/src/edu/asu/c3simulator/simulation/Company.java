package edu.asu.c3simulator.simulation;

import com.badlogic.gdx.scenes.scene2d.ui.List;

import edu.asu.c3simulator.util.Observer;

/**
 * @author Justin
 *
 */
public interface Company
{
	// getters
	Company getCompany();

	String getCompanyName();

	int getNetWorth();

	int getAnnualIncome();

	int getCapital();

	List getEmployees();

	// setters
	void setNetWorth();

	void setAnnualIncome();

	void setCapital();

	void setEmployees();

	// allows for company to be deleted
	void removeCompany(Company removedCompany);
}
