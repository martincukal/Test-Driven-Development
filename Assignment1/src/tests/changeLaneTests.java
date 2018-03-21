package tests;

import static org.junit.Assert.*;

import org.junit.*;

import errorStreetException.StreetLengthException;
import main.*;

public class changeLaneTests {
	// implementation of car interface
	private Car car;
	// ultrasound sensors
	private UltrasoundSensor r1;
	private UltrasoundSensor r2;
	private UltrasoundSensor r3;
	// lidar sensor
	private Lidar l1;

	@Before
	public void setup() {
		car = new Car();
		r1 = new UltrasoundSensor();
		r2 = new UltrasoundSensor();
		r3 = new UltrasoundSensor();
		l1 = new Lidar();
		// set sensors to inRange values for both querries
		r1.setBothValues(1, 1);
		r2.setBothValues(1, 1);
		r3.setBothValues(1, 1);
		l1.setBothValues(1, 1);

	}

	// TEST1 The starting lane should be 1 so when change lane is called the
	// lane should be 2
	@Test
	public void testInitialChangeLane() throws StreetLengthException {
		car.changeLane(r1, r2, r3, l1);
		int lane = car.whereIs().getCarLane();
		assertEquals("The lane where the car is expected to be is 2", 2, lane);
	}

	// TEST2 When the current lane is 3 so when change lane is called an
	// the car should not change the lane
	@Test
	public void testOutOfBoundChangeLane() throws StreetLengthException {
		car.setCarLane(3);
		car.changeLane(r1, r2, r3, l1);
		int lane = car.whereIs().getCarLane();
		assertEquals("The lane where the car is expected to be is 2", 3, lane);
	}

	// TEST3 after change lane is invoked first the car moves forward for 5
	// meters than changes the lane
	@Test
	public void testDistanceAfterChangeLane() throws StreetLengthException {
		car.setPositionX(0);
		car.changeLane(r1, r2, r3, l1);
		int positionX = car.whereIs().getPosX();
		assertEquals("The lane where the car is expected to be is 2", 5, positionX);
	}

	// TEST4 When the current position is 100 and change lane is called when
	// there is no car in the left lane an
	// exception should be generated because there is no street to move
	@Test(expected = StreetLengthException.class)
	public void testOutOfStreetChangeLane() throws StreetLengthException {
		car.setPositionX(100);
		car.changeLane(r1, r2, r3, l1);
	}

	// TEST5 When change lane is called and there is a car in the left lane the
	// car should not change the lane
	@Test
	public void testChangeLangeWithCarInLaneLaneCheck() throws StreetLengthException {
		int initialLane = car.whereIs().getCarLane();
		r1.setBothValues(0, 0);
		r2.setBothValues(0, 0);
		r3.setBothValues(0, 0);
		l1.setBothValues(0, 0);
		car.changeLane(r1, r2, r3, l1);
		int currentLane = car.whereIs().getCarLane();
		assertEquals("The lane where the car is expected to be is 1", initialLane, currentLane);
	}

	// TEST6 When change lane is called and there is a car in the left lane the
	// car should not change the lane but it should move forward
	@Test
	public void testChangeLangeWithCarInLanePositionCheck() throws StreetLengthException {
		car.setPositionX(0);
		r1.setBothValues(0, 0);
		r2.setBothValues(0, 0);
		r3.setBothValues(0, 0);
		l1.setBothValues(0, 0);
		car.changeLane(r1, r2, r3, l1);
		int currentPos = car.whereIs().getPosX();
		assertEquals("The lane where the car is expected to be is 5", 5, currentPos);
	}

	// TEST7 When change lane is called and there is a car in the left lane the
	// car should not change the lane but it should move forward if there is
	// road left which in this case there isnt
	@Test(expected = StreetLengthException.class)
	public void testChangeLangeWithCarInLaneStreetExCheck() throws StreetLengthException {
		car.setPositionX(100);
		r1.setBothValues(0, 0);
		r2.setBothValues(0, 0);
		r3.setBothValues(0, 0);
		l1.setBothValues(0, 0);
		car.changeLane(r1, r2, r3, l1);
	}

}
