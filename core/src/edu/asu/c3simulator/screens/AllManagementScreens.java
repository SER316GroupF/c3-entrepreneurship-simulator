package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import edu.asu.c3simulator.simulation.SimulationScreen;

/**
 * Enumerated Singleton to hold all sub-screens of the Management Screen. Each enumerated
 * value represents one sub-screen.
 * 
 * Note: {@link #initialize(Game)} must be called prior to {@link #getInstance()}.
 * {@link #initialize(Game)} only needs to be called once throughout the lifecycle of the
 * program, but additional calls are harmless.
 * 
 * @author Moore, Zachary
 * 
 */
public enum AllManagementScreens
{
	MAIN_HUB,
	PRE_MARKET,
	CURRENT_PRODUCTS,
	RETIRED_PRODUCTS,
	CURRENT_PRODUCTS_GROWTH,
	PRODUCT_HISTORY,
	GROWTH_DEMAND,
	EMPLOYMENT,
	MARKETING,
	BUSINESS_DIRECTION,
	FUNDING,
	INDUSTRY,
	TASKS,
	COMPANY_PANEL,
	COMPLETED_TASKS,
	TASK_MANAGEMENT;
	
	/**
	 * True if {@link #initialize(Game)} has been called. This value must be true before
	 * calling {@link #getInstance()} or an {@link IllegalStateException} will be thrown
	 */
	private static boolean initialized = false;
	
	private Screen instance;
	
	public static void initialize(Game game)
	{
		if (!initialized)
		{
			initialized = true;
			MAIN_HUB.instance = new MainHub(game);
			PRE_MARKET.instance = new PreMarketProducts(game);
			CURRENT_PRODUCTS.instance = new CurrentProducts(game);
			RETIRED_PRODUCTS.instance = new RetiredProducts(game);
			PRODUCT_HISTORY.instance = new ProductHistory(game);
			CURRENT_PRODUCTS_GROWTH.instance = null;
			GROWTH_DEMAND.instance = null;
			EMPLOYMENT.instance = new EmploymentScreen(game);
			MARKETING.instance = new MarketingScreen(game);
			BUSINESS_DIRECTION.instance = new BusinessDirectonScreen(game);
			FUNDING.instance = new FundingScreen(game);
			INDUSTRY.instance = new IndustryScreen(game);
			TASKS.instance = new TasksScreen(game);
			COMPANY_PANEL.instance = new CompanyPanel(game);
			COMPLETED_TASKS.instance = new CompletedTasks(game);
			TASK_MANAGEMENT.instance = new TaskManagementScreen(game);
			((MainHub) MAIN_HUB.instance).initialize();
			((SimulationScreen) PRE_MARKET.instance).createNavigationPanel();
			((SimulationScreen) RETIRED_PRODUCTS.instance).createNavigationPanel();
			((SimulationScreen) CURRENT_PRODUCTS.instance).createNavigationPanel();
			((SimulationScreen) PRODUCT_HISTORY.instance).createNavigationPanel();
			((SimulationScreen) EMPLOYMENT.instance).createNavigationPanel();
			((SimulationScreen) MARKETING.instance).createNavigationPanel();
			((SimulationScreen) BUSINESS_DIRECTION.instance).createNavigationPanel();
			((SimulationScreen) FUNDING.instance).createNavigationPanel();
			((SimulationScreen) INDUSTRY.instance).createNavigationPanel();
			((SimulationScreen) TASKS.instance).createNavigationPanel();
			((SimulationScreen) COMPANY_PANEL.instance).createNavigationPanel();
			((SimulationScreen) COMPLETED_TASKS.instance).createNavigationPanel();
			((SimulationScreen) TASK_MANAGEMENT.instance).createNavigationPanel();
			
			// TODO: initialize more as they are created
		}
	}
	
	/**
	 * @return The instance of this screen.
	 * @throws IllegalStateException
	 *             if {@link AllManagementScreens} has not been initialized
	 * @see #initialize(Game)
	 */
	public Screen getInstance()
	{
		if (!initialized)
		{
			throw new IllegalStateException(
					"Must call initialize(Game) before accessing screens");
		}
		return this.instance;
	}
}
