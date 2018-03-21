package tests;

import static org.junit.Assert.*;

import org.junit.*;

import errorStreetException.StreetLengthException;
import main.*;

public class moveForwardTests {
	Car car;

	@Before
	public void setup() {
		car = new Car();

	}

	// TEST1 Moving forward from the start of the street from 0 to 5 meters
	@Test
	public void testMoveForwardFrom0() throws StreetLengthException {
		car.moveForward();
		Position pos = car.whereIs();
		int positionX = pos.getPosX();
		assertEquals("The position  where the car is expected to be is 5", 5, positionX);
	}

	// TEST2 The car cannot be moved forward beyond the end of the street.
	@Test(expected = StreetLengthException.class)
	public void testMoveForwardFrom100() throws StreetLengthException {
		car.setPositionX(100);
		car.moveForward();
	}

	// TEST3 The car cannot be moved when it is out of the street
	@Test(expected = StreetLengthException.class)
	public void testMoveForwardFrom100Pos() throws StreetLengthException {
		car.setPositionX(-10);
		car.moveForward();
	}

	// TEST4 Moving forward from position 95
	@Test
	public void testMoveForwardFrom95() throws StreetLengthException {
		car.setPositionX(95);
		car.moveForward();
		Position pos = car.whereIs();
		int positionX = pos.getPosX();
		assertEquals("The position  where the car is expected to be is 100", 100, positionX);
	}
}
