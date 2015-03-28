package tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestColorDecider.class, TestHistogram.class, TestPieChart.class,
		TestCredit.class, TestCompany.class })
public class AllTests
{
	
}
