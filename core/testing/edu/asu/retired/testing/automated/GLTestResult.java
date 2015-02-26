package edu.asu.retired.testing.automated;

import java.util.LinkedList;
import java.util.List;

public class GLTestResult
{
	private String testName;
	private List<GLAssertionResult> results;
	
	public GLTestResult(String testName)
	{
		super();
		this.testName = testName;
		this.results = new LinkedList<>();
	}
	
	public boolean hasFailed()
	{
		for (GLAssertionResult result : results)
		{
			if (result.hasFailed())
				return true;
		}
		
		return false;
	}
	
	public String getTestName()
	{
		return testName;
	}
	
	public List<GLAssertionResult> getResults()
	{
		return results;
	}
	
	public void reportAssertion(GLAssertionResult result)
	{
		this.results.add(result);
	}
	
}
