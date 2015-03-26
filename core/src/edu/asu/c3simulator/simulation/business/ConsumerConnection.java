package edu.asu.c3simulator.simulation.business;

/**
 * Models a primary income channel of a business. A {@link ConsumerConnection} is anything
 * that allows a business to conduct business with its customers, such as a storefront, an
 * online shop, a mail-in shopping system, etc.
 * 
 * @author Moore, Zachary
 *
 */
public interface ConsumerConnection
{
	/**
	 * @param product
	 *            Target
	 * @return Number of units of the given product that this {@link ConsumerConnection}
	 *         is able to sell each month
	 */
	int getMonthlySalesPotential(Product product);
}
