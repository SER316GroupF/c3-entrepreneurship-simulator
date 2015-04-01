package edu.asu.c3simulator.testing.stubs;

import java.text.DecimalFormat;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Product;

/**
 * Stub to test the product interface
 * @author Reigel, Justin
 *
 */
public class ProductTestingStub implements Product
{
	private String name, laborer;
	private float productionCost, materialCost, distributionCost, sellingPrice;
	private int efficiency;
	//TODO: fill out more testing methods where appropriate
	@Override
	public Product getProduct()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getProductName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public float getProductionCost()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return Float.valueOf(df.format(11.00));
	}

	@Override
	public float getMaterialCost()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return Float.valueOf(df.format(8.50));
	}

	@Override
	public float getDistributionCost()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float getSellingPrice()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return Float.valueOf(df.format(16.50));
	}

	@Override
	public int getEfficiency()
	{
		return 32;
	}

	@Override
	public String getLabor()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setProductName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setProductionCost()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setMaterialCost()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setDistributionCost()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setSellingPrice()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setEfficiency()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void setLabor()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void retireProduct()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public C3Simulation getSimulation()
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getNumSold()
	{
		// TODO Get the actual number sold
		return 20;
	}

	@Override
	public float getTotalWorth()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return Float.valueOf(df.format(getNumSold() * getSellingPrice()));
	}

}
