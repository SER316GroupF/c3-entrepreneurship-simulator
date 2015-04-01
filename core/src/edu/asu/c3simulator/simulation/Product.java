package edu.asu.c3simulator.simulation;

public interface Product
{
	boolean retired = false;

	Product getProduct();

	String getProductName();

	float getProductionCost();

	float getMaterialCost();

	float getDistributionCost();

	float getSellingPrice();

	int getEfficiency();

	int getNumSold();

	float getTotalWorth();

	String getLabor();

	/** setters for changing product names/modifying cost attributes */
	String setProductName();

	void setProductionCost();

	void setMaterialCost();

	void setDistributionCost();

	void setSellingPrice();

	void setEfficiency();

	/** Change which laborer/s are currently producing the product. */
	public void setLabor();

	/** allows the product to be retired. */
	void retireProduct();

	C3Simulation getSimulation();
}
