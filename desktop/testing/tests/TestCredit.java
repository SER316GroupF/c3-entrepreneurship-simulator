package tests;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.asu.c3simulator.simulation.business.Credit;

public class TestCredit
{
	@Before
	public void setUp() throws Exception
	{
	}
	
	@After
	public void tearDown() throws Exception
	{
	}
	
	/**
	 * Ensure an account with no debt has a minimum monthly of 0, and does not take losses
	 */
	@Test
	public void testNoDebt()
	{
		Credit credit = new Credit(5000);
		credit.processMonth();
		
		assertEquals(0, credit.getMinimumMonthlyPayment(), 0.1f);
		assertFalse(credit.getCapital() < 5000);
	}
	
	@Test
	public void testLoan()
	{
		Credit credit = new Credit(5000);
		assertTrue(credit.borrow(credit.getCreditLimit(), 10));
		
		assertTrue(credit.getMinimumMonthlyPayment() > 0);
		
		float preProcessCapital = credit.getCapital();
		credit.processMonth();
		assertTrue(credit.getCapital() < preProcessCapital);
	}
	
	/**
	 * If a loan is taken, and paid in full before the month expires, no fees should ensue
	 */
	@Test
	public void testLoanInterestAvoidance()
	{
		Credit credit = new Credit(5000);
		float amountToBorrow = credit.getCreditLimit();
		assertTrue(credit.borrow(amountToBorrow, 10));
		credit.makePayment(amountToBorrow);
		credit.processMonth();
		assertEquals(5000, credit.getCapital(), 0.1f);
	}

	@Test
	public void testExceedingLoan()
	{
		Credit credit = new Credit(5000);
		float amountToBorrow = credit.getCreditLimit() + 1;
		assertFalse(credit.borrow(amountToBorrow, 10));
	}

	@Test
	public void testNegativeLoan()
	{
		Credit credit = new Credit(5000);
		float amountToBorrow = -credit.getCreditLimit();
		assertFalse(credit.borrow(amountToBorrow, 10));
	}
	
}
