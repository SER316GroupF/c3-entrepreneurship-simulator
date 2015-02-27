package edu.asu.retired.testing.automated;

public class Failure
{
	private Exception exception;
	
	public Failure(Exception exception)
	{
		super();
		this.exception = exception;
	}
	
	public Failure()
	{
		this(null);
	}
	
	public Exception getException()
	{
		return exception;
	}
}
