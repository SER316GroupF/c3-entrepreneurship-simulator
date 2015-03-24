package edu.asu.c3simulator.simulation.business;

/**
 * Placeholder
 */
public interface MarketingCampaign
{
	/**
	 * @return Monthly cost of this {@link MarketingCampaign}
	 */
	float getMonthlyCost();
	
	/**
	 * Increments the month counter, registers any applicable events, rolls for random
	 * events (and registers them accordingly), and returns the publicity change for this
	 * month.
	 * 
	 * @return The publicity change caused directly by this {@link MarketingCampaign} for
	 *         the current month.
	 */
	int processMonth();
	
	/**
	 * @return The target of this marketing campaign, or null if the target is the company
	 */
	Product getTarget();
	
	/**
	 * @return True if this {@link MarketingCampaign} is complete and can be retired
	 */
	boolean isFinished();
}
