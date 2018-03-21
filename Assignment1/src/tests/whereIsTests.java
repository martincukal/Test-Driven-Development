package tests;

import static org.junit.Assert.*;

import org.junit.*;

import errorStreetException.StreetLengthException;
import main.*;

public class whereIsTests {
	Car car;

	@Before
	public void setup() {
		car = new Car();

	}

	// TEST1 The starting lane should be 1
	@Test
	public void testNewCarLane() throws StreetLengthException {
		Position pos = car.whereIs();
		int lane = pos.getCarLane();
		assertEquals("The initial lane where the car is expected to be is 1", 1, lane);
	}

	// TEST2 The starting pos should be 0
	@Test
	public void testNewCarPosition() throws StreetLengthException {
		Position pos = car.whereIs();
		int posX = pos.getPosX();
		assertEquals("The initial position where the car is expected to be is 0", 0, posX);
	}

	// TEST3 changing the lane
	@Test
	public void testChangeCarLane() throws StreetLengthException {
		car.setCarLane(2);
		Position pos = car.whereIs();
		int lane = pos.getCarLane();
		assertEquals("The  lane where the car is expected to be is 2", 2, lane);
	}

	// TEST4 changing the position
	@Test
	public void testChangeCarPosition() throws StreetLengthException {
		car.setPositionX(80);
		Position pos = car.whereIs();
		int posX = pos.getPosX();
		assertEquals("The  position where the car is expected to be is 80", 80, posX);
	}

	// TEST5 changing the position to an illegal 101
	@Test(expected = StreetLengthException.class)
	public void testChangeCarPositionIllegal() throws StreetLengthException {
		car.setPositionX(101);
		car.whereIs();
	}

	// TEST6 changing the car lane to an illegal 4
	@Test(expected = StreetLengthException.class)
	public void testChangeCarLaneIllegal() throws StreetLengthException {
		car.setCarLane(4);
		car.whereIs();
	}

	// TEST7 changing the car lane to an illegal -1
	@Test(expected = StreetLengthException.class)
	public void testChangeCarLaneIllegalNeg() throws StreetLengthException {
		car.setCarLane(-1);
		car.whereIs();
	}

	// TEST8 changing the car lane to an illegal 0
	@Test(expected = StreetLengthException.class)
	public void testChangeCarLaneIllegal0() throws StreetLengthException {
		car.setCarLane(0);
		car.whereIs();
	}

	// TEST9 changing the car position to an illegal -1
	@Test(expected = StreetLengthException.class)
	public void testChangeCarPosIllegalNeg() throws StreetLengthException {
		car.setPositionX(-1);
		car.whereIs();
	}

}
