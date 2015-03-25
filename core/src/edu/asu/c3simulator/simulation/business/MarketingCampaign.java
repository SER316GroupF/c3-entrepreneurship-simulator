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
	 * <p>
	 * The publicity change will not reflect the effects of generated events, which may
	 * cause drops in public opinion.
	 * <p>
	 * In most cases, the publicity change returned by this month will be positive.
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
	 * @return True if this {@link MarketingCampaign} is complete and can be retired,
	 *         false otherwise
	 */
	boolean isFinished();
}
