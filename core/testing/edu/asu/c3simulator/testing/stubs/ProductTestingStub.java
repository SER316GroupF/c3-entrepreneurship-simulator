/**
 * 
 */
package edu.asu.c3simulator.testing.stubs;

import java.text.DecimalFormat;

import edu.asu.c3simulator.simulation.C3Simulation;
import edu.asu.c3simulator.simulation.Product;

/**
 * Stub to test the product interface
 * @author Justin
 *
 */
public class ProductTestingStub implements Product
{
	private String name, laborer;
	private float productionCost, materialCost, distributionCost, sellingPrice;
	private int efficiency;
	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#getProduct()
	 */
	//TODO: fill out more testing methods where appropriate
	@Override
	public Product getProduct()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#getProductName()
	 */
	@Override
	public String getProductName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#getProductionCost()
	 */
	@Override
	public float getProductionCost()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return Float.valueOf(df.format(11.00));
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#getMaterialCost()
	 */
	@Override
	public float getMaterialCost()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return Float.valueOf(df.format(8.50));
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#getDistributionCost()
	 */
	@Override
	public float getDistributionCost()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#getSellingPrice()
	 */
	@Override
	public float getSellingPrice()
	{
		DecimalFormat df = new DecimalFormat("#.##");
		return Float.valueOf(df.format(16.50));
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#getEfficiency()
	 */
	@Override
	public int getEfficiency()
	{
		return 32;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#getLabor()
	 */
	@Override
	public String getLabor()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#setProductName()
	 */
	@Override
	public String setProductName()
	{
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#setProductionCost()
	 */
	@Override
	public void setProductionCost()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#setMaterialCost()
	 */
	@Override
	public void setMaterialCost()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#setDistributionCost()
	 */
	@Override
	public void setDistributionCost()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#setSellingPrice()
	 */
	@Override
	public void setSellingPrice()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#setEfficiency()
	 */
	@Override
	public void setEfficiency()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#setLabor()
	 */
	@Override
	public void setLabor()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#retireProduct()
	 */
	@Override
	public void retireProduct()
	{
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see edu.asu.c3simulator.simulation.Product#getSimulation()
	 */
	@Override
	public C3Simulation getSimulation()
	{
		// TODO Auto-generated method stub
		return null;
	}

}
