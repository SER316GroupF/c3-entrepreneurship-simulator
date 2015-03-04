package edu.asu.c3simulator.simulation;


public interface Product
{
	boolean retired = false;
	Product getProduct();

	public String getProductName();
	public float getProductionCost();
	public float getMaterialCost();
	public float getDistributionCost();
	public float getSellingPrice();
	public int getEfficiency();
	public String getLabor();
	
	//setters for chainging product names/modifying cost attributes
	public String setProductName();
	public void setProductionCost();
	public void setMaterialCost();
	public void setDistributionCost();
	public void setSellingPrice();
	public void setEfficiency();
	//Change which laborer/s are currently producing the product.
	public void setLabor();
	//allows the product to be retired.
	/**
	 * 
	 */
	void retireProduct(); 

	C3Simulation getSimulation();
}
