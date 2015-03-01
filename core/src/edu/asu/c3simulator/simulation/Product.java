package edu.asu.c3simulator.simulation;

import edu.asu.c3simulator.util.Observer;

public interface Product
{
	boolean retired = false;
	Product getProduct();

	String getProductName();
	int getProductionCost();
	int getMaterialCost();
	int getDistributionCost();
	int getSellingPrice();
	String getLabor();
	
	//setters for chainging product names/modifying cost attributes
	String setProductName();
	void setProductionCost();
	void setMaterialCost();
	void setDistributionCost();
	void setSellingPrice();
	//Change which laborer/s are currently producing the product.
	void setLabor();
	//allows the product to be retired.
	/**
	 * 
	 */
	void retireProduct(); 

	C3Simulation getSimulation();
}
