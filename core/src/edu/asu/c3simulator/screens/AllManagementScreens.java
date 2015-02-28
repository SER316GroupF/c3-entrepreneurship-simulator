package edu.asu.c3simulator.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * Enumerated Singleton to hold all sub-screens of the Management Screen. Each
 * enumerated value represents one sub-screen.
 * 
 * Note: {@link #initialize(Game)} must be called prior to
 * {@link #getInstance()}. {@link #initialize(Game)} only needs to be called
 * once throughout the lifecycle of the program, but additional calls are
 * harmless.
 * 
 * @author Moore, Zachary
 * 
 */
public enum AllManagementScreens
{
	PRE_MARKET, CURRENT_PRODUCTS, RETIRED_PRODUCTS, CURRENT_PRODUCTS_GROWTH, GROWTH_DEMAND, EMPLOYMENT, MARKETING;

	/**
	 * True if {@link #initialize(Game)} has been called. This value must be
	 * true before calling {@link #getInstance()} or an
	 * {@link IllegalStateException} will be thrown
	 */
	private static boolean initialized = false;

	private Screen instance;

	public static void initialize(Game game)
	{
		if (!initialized)
		{
			initialized = true;
			PRE_MARKET.instance = new PreMarketProducts(game);
			CURRENT_PRODUCTS.instance = null;
			RETIRED_PRODUCTS.instance = new RetiredProducts(game);
			CURRENT_PRODUCTS_GROWTH.instance = null;
			GROWTH_DEMAND.instance = null;
			EMPLOYMENT.instance = null;
			MARKETING.instance = null;
			((PreMarketProducts) PRE_MARKET.instance).initialize();
			((RetiredProducts) RETIRED_PRODUCTS.instance).initialize();
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
		System.out.println("instance obtained");
		if (!initialized)
		{
			throw new IllegalStateException(
					"Must call initialize(Game) before accessing screens");
		}
		return this.instance;
	}
}
