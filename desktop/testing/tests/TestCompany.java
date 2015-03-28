package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import edu.asu.c3simulator.simulation.business.Company;
import edu.asu.c3simulator.simulation.business.Credit;
import edu.asu.c3simulator.simulation.business.Product;
import edu.asu.c3simulator.simulation.business.ProductionInformation;
import edu.asu.c3simulator.testing.stubs.ProductTestingStub;

public class TestCompany
{
	Product productA;
	Product productB;
	Product productC;
	
	@Before
	public void setUp() throws Exception
	{
		productA = new ProductTestingStub();
		productB = new ProductTestingStub();
		productC = new ProductTestingStub();
	}
	
	@After
	public void tearDown() throws Exception
	{
	}
	
	/**
	 * Ensure a company with no debt and no expenses will not loose money
	 */
	@Test
	public void testCapitalPersistance()
	{
		Company company = new Company("company1", 100);
		Credit credit = company.getCredit();
		
		assertEquals(100, credit.getCapital(), 0.1f);
		
		for (int month = 0; month < 3; month++)
		{
			company.processMonth();
		}
		
		assertEquals(100, credit.getCapital(), 0.1f);
	}
	
	/**
	 * Ensure products with no production do not produce expenses
	 */
	@Test
	public void testProductExpense()
	{
		Company company = new Company("company1", 100);
		ProductionInformation information = new ProductionInformation(productA, 0, 50);
		company.addProduct(information, 256);
		Credit credit = company.getCredit();
		
		assertEquals(100, credit.getCapital(), 0.1f);
		
		for (int month = 0; month < 3; month++)
		{
			company.processMonth();
		}
		
		assertEquals(100, credit.getCapital(), 0.1f);
	}
	
	/**
	 * Negative retail prices should result in an {@link IllegalArgumentException}
	 */
	@Test
	public void testNegativeRetail()
	{
		Company company = new Company("company1", 5000);
		ProductionInformation information = new ProductionInformation(productA, 10, 50);
		
		try
		{
			company.addProduct(information, -1);
			fail("Negative retail price should not be allowed");
		}
		catch (Exception e)
		{
			// EXPECTED
		}
	}
	
	@Test
	public void testAddProduct()
	{
		Company company = new Company("company1", 5000);
		ProductionInformation informationA = new ProductionInformation(productA, 0, 0);
		ProductionInformation informationB = new ProductionInformation(productB, 10, 50);
		ProductionInformation informationC = new ProductionInformation(productC,
				Integer.MAX_VALUE, 50);
		
		assertFalse(company.isSellerOf(productA));
		assertFalse(company.isSellerOf(productB));
		assertFalse(company.isSellerOf(productC));
		
		company.addProduct(informationA, 256);
		assertTrue(company.isSellerOf(productA));
		assertFalse(company.isSellerOf(productB));
		assertFalse(company.isSellerOf(productC));
		
		company.addProduct(informationB, 512);
		assertTrue(company.isSellerOf(productA));
		assertTrue(company.isSellerOf(productB));
		assertFalse(company.isSellerOf(productC));
		
		company.addProduct(informationC, 1028);
		assertTrue(company.isSellerOf(productA));
		assertTrue(company.isSellerOf(productB));
		assertTrue(company.isSellerOf(productC));
	}
	
	/**
	 * Attempt registering a sale of a product not sold by the company
	 */
	@Test
	public void testNonexistentSale()
	{
		Company company = new Company("company1", 5000);
		
		assertFalse(company.isSellerOf(productA));
		
		assertFalse(company.registerSale(productA, 0));
		assertFalse(company.registerSale(productA, 1));
	}
	
	@Test
	public void testInavlidSale()
	{
		Company company = new Company("company1", 5000);
		ProductionInformation information = new ProductionInformation(productA, 10, 50);
		company.addProduct(information, 50);
		
		assertTrue(company.isSellerOf(productA));
		int max = company.getSaleCapacity(productA);
		
		assertFalse(company.registerSale(productA, max + 1));
	}
	
	@Test
	public void testProcessMonthExpenses()
	{
		Company company = new Company("company1", 5000);
		Credit credit = company.getCredit();
		ProductionInformation information = new ProductionInformation(productA, 10, 50);
		float expectedExpense = 10 * 50;
		float expectedRemainingCapital = 5000 - expectedExpense;
		
		company.addProduct(information, 50);
		company.processMonth();
		
		assertEquals(expectedRemainingCapital, credit.getCapital(), 0.1f);
	}
	
	@Test
	public void testProcessMonthProduction()
	{
		Company company = new Company("company1", 5000);
		ProductionInformation information = new ProductionInformation(productA, 10, 50);
		
		company.addProduct(information, 50);
		company.processMonth();
		
		assertEquals(10, company.getInventoryOf(productA), 0.1f);
		
		company.processMonth();
		assertEquals(20, company.getInventoryOf(productA), 0.1f);
	}
	
}
