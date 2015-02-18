package edu.asu.c3simulator.testing.automated;

public class GLAssertionResult
{
	private String expectation;
	private Failure failure;
	
	public GLAssertionResult(String expectation)
	{
		this(expectation, null);
	}
	
	public GLAssertionResult(String expectation, Failure failure)
	{
		super();
		this.expectation = expectation;
		this.failure = failure;
	}
	
	public boolean hasFailed()
	{
		return failure != null;
	}
	
	public String getExpectation()
	{
		return expectation;
	}
}
