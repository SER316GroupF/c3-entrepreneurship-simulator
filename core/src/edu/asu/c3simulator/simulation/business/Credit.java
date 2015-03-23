package edu.asu.c3simulator.simulation.business;

/**
 * Model of the debt, capital, assets, payments owed, and credit limits of a company. This
 * is effectively a model of the company's finances.
 * 
 * @author Moore, Zachary
 *
 */
public class Credit
{
	private static final float LIMIT_INCREASE_FACTOR = 0.05f;
	private static final float DEFAULT_INTEREST_RATE = 0.05f;
	private static final float DEFAULT_CREDIT_LIMIT = 10000;
	private static final float DEFAULT_PENALTY = 10000;
	
	private float limit;
	private float currentlyOwed;
	private float minimumMonthlyPayment;
	private float monthlyPenalty;
	private float monthlyInterest;
	private float amountPayedInCurrentMonth;
	private float capital;
	private float assetValue;
	
	public Credit(float initialCapital)
	{
		this.limit = DEFAULT_CREDIT_LIMIT;
		this.currentlyOwed = 0;
		this.minimumMonthlyPayment = 0;
		this.monthlyPenalty = DEFAULT_PENALTY;
		this.monthlyInterest = DEFAULT_INTEREST_RATE;
		this.amountPayedInCurrentMonth = 0;
		this.capital = initialCapital;
		this.assetValue = 0;
	}
	
	/**
	 * Update capital, payments, penalties, and other balance related variables for the
	 * current month.
	 */
	public void processMonth()
	{
		boolean penaltyApplies = amountPayedInCurrentMonth < minimumMonthlyPayment;
		if (penaltyApplies)
		{
			capital -= monthlyPenalty;
		}
		else
		{
			float limitIncrease = amountPayedInCurrentMonth * LIMIT_INCREASE_FACTOR;
			limit += limitIncrease;
		}
		
		float interestAmount = monthlyInterest * currentlyOwed;
		currentlyOwed += interestAmount;
		amountPayedInCurrentMonth = 0;
	}
	
	public float getLimit()
	{
		return limit;
	}
	
	public float getCurrentlyOwed()
	{
		return currentlyOwed;
	}
	
	public float getMinimumMonthlyPayment()
	{
		return minimumMonthlyPayment;
	}
	
	public float getMonthlyPenalty()
	{
		return monthlyPenalty;
	}
	
	public float getMonthlyInterest()
	{
		return monthlyInterest;
	}
	
	public void makePayment(float amount)
	{
		amountPayedInCurrentMonth += amount;
		currentlyOwed -= amount;
	}
	
	/**
	 * @param amount
	 *            Amount to borrow
	 * @return true if the requested amount will not put the company over their
	 *         {@link #limit}, false otherwise.
	 */
	public boolean canBorrow(float amount)
	{
		float maxAmount = limit - currentlyOwed;
		
		return amount <= maxAmount;
	}
	
	/**
	 * @see #borrow(float, int)
	 */
	public boolean requestLoan(float amount, int duration)
	{
		return borrow(amount, duration);
	}
	
	/**
	 * Attempts to borrow money in the form of a loan. The interest of the loan will be
	 * {@value #DEFAULT_INTEREST_RATE} per month, and the minimum monthly payment will be
	 * equal to amount / duration. Note that interest payments may still remain after the
	 * duration has expired, and debt will not be cleared, regardless of duration.
	 * 
	 * If the loan is successful, as determined by {@link #canBorrow(float)}, the amount
	 * will be added to the company's current {@link #capital}, and the
	 * {@link #currentlyOwed}, {@link #minimumMonthlyPayment}, and other relevant
	 * variables will be updated accordingly.
	 * 
	 * @param amount
	 *            Requested dollar value of the loan
	 * @param duration
	 *            Duration of the loan, in months. Used to determine interest and minimum
	 *            monthly payments.
	 * @return
	 */
	public boolean borrow(float amount, int duration)
	{
		boolean canBorrow = canBorrow(amount);
		
		if (canBorrow)
		{
			currentlyOwed += amount;
			capital += amount;
			minimumMonthlyPayment += amount / duration;
		}
		
		return canBorrow;
	}
	
	public static float getLimitIncreaseFactor()
	{
		return LIMIT_INCREASE_FACTOR;
	}
	
	public float getAmountPayedInCurrentMonth()
	{
		return amountPayedInCurrentMonth;
	}
	
	public float getCapital()
	{
		return capital;
	}
	
	public float getAssetValue()
	{
		return assetValue;
	}
	
	public void setAssetValue(float assetValue)
	{
		this.assetValue = assetValue;
	}
	
	public void setCapital(float capital)
	{
		this.capital = capital;
	}
	
	/**
	 * Reduces {@link #capital} by the specified amount.
	 * 
	 * @param amount Withdrawl amount
	 */
	public void processPayment(float amount)
	{
		this.capital -= amount;
	}
	
	/**
	 * Adds the specified amount to {@link #capital}
	 * 
	 * @param amount Deposit amount
	 */
	public void processGrossProfit(float amount)
	{
		this.capital += amount;
	}
	
}
