package edu.asu.c3simulator.simulation;

import java.util.Random;

import edu.asu.c3simulator.simulation.Employee.Position;

/**
 * Used to generate employees with random qualities including name, position, wage
 * tolerance, and ambition.
 * 
 * @author nickkrogstad
 * 
 */
public class EmployeeFactory
{
	/**
	 * @return A randomly selected name generated for an employee TODO: Load from file
	 */
	public static String getRandomName()
	{
		String[] firstName = { "Nicholas", "Colton", "Sawyer", "Frankie", "Jennifer",
				"Charles", "Kathy", "James", "Jacob", "Crystal", "Mike", "Cody", "Jorge",
				"Sam", "Lisa", "Margaret", "Marth", "Roy", "Victoria", "Susan", "Ted" };
		String[] lastName = { "Krogstad", "Tucker", "Hardenbech", "Connelley", "Li",
				"Baker", "Tyke", "Mattingly", "Feng", "Lee", "Smith", "Houston",
				"Franco", "White", "Andrade", "Manning", "Brady", "Boyle", "Terry" };
		
		int randomFirst = (int) (Math.random() * firstName.length);
		int randomLast = (int) (Math.random() * lastName.length);
		
		String randomEmployeeName = firstName[randomFirst] + " " + lastName[randomLast];
		
		return randomEmployeeName;
	}
	
	/** @return Randomly selected position based on the four available positions. */
	public static Position getRandomPosition()
	{
		Position[] positions = Position.values();
		int randomPositions = (int) (Math.random() * positions.length);
		Position position = positions[randomPositions];
		return position;
	}
	
	/** @return An employee with its randomly generated qualities. */
	public static Employee getRandomEmployee()
	{
		String name = getRandomName();
		Position position = getRandomPosition();
		
		Random random = new Random();
		float temporary = random.nextInt(1001);
		float wageTolerance = temporary / 1000;
		temporary = random.nextInt(1001);
		float ambition = temporary / 1000;
		
		return new Employee(name, position, wageTolerance, ambition);
	}
}
