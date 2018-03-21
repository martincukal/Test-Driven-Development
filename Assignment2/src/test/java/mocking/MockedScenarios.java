package mocking;

import static org.junit.Assert.assertEquals;
import junit.runner.Version;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mockito;

import errorStreetException.StreetLengthException;
import main.Car;
import main.Lidar;
import main.Position;
import main.UltrasoundSensor;

public class MockedScenarios {

	// implementation of car interface
	private Car car;
	/**
	 * Initialize the mocked sensors
	 */
	// mocked ultrasound sensors
	private UltrasoundSensor r1 = mock(UltrasoundSensor.class);
	private UltrasoundSensor r2 = mock(UltrasoundSensor.class);
	private UltrasoundSensor r3 = mock(UltrasoundSensor.class);
	// mocked lidar sensor
	private Lidar l1 = mock(Lidar.class);

	@Before
	public void setup() {
		/**
		 * Initialize the object car
		 */
		car = new Car();

	}

	/**
	 * Scenario 1 - Starts at the beginning of the street, Moves along the
	 * street and after a few moves forward it is issued a command to change
	 * lange,It senses the left-hand-side lane and in both queries receives
	 * within-range values (indicating that no sensor is faulty), and both
	 * queries indicate that the left-hand-side lane is vacant, It moves to the
	 * left-hand-side lane, and Moves to the end of the street
	 */
	@Test
	public void emptyLeftLaneMoveToEndOfStreetScenario() throws StreetLengthException {

		// car is at the beggining of the street and in lane 1 that is set by
		// the default car constructorand moves forward
		// 3 times
		car.moveForward();
		car.moveForward();
		car.moveForward();

		// set up for the mocked sensors values, since this getSensorValue
		// method is
		// used in the leftLaneDetect to get the values of the sensors index 0
		// means first iteration index 1 means second iteration , and this
		// method is used in changeLane to see if the lane is occupued or empty
		// in this case it will be epmty since we are returning the value 1

		// first iteration
		when(r1.getSensorValue(0)).thenReturn(1);
		when(r2.getSensorValue(0)).thenReturn(1);
		when(r3.getSensorValue(0)).thenReturn(1);
		when(l1.getLidarValue(0)).thenReturn(1);
		// second iteration
		when(r1.getSensorValue(1)).thenReturn(1);
		when(r2.getSensorValue(1)).thenReturn(1);
		when(r3.getSensorValue(1)).thenReturn(1);
		when(l1.getLidarValue(1)).thenReturn(1);
		// change lane request should result in switching the car into the left
		// lane
		car.changeLane(r1, r2, r3, l1);
		// car is in the second lane and distance 15 using a while loop we call
		// move forward until the car gets to the end of the road
		while (car.whereIs().getPosX() < 100) {
			car.moveForward();
		}
		// after the scenario has ended the car should be on lane 2 and distance
		// 100

		// checking the expected and actual lane
		assertEquals("The car is not on  lane 2", 2, car.getCarLane());
		assertEquals("The car is not on position 100", 100, car.getPositionX());

		/**
		 * To verify that the mocking worked we use the verify method from the
		 * class Mockito
		 *
		 */
		// first iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(0);
		verify(r2).getSensorValue(0);
		verify(r3).getSensorValue(0);
		verify(l1).getLidarValue(0);
		// second iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(1);
		verify(r2).getSensorValue(1);
		verify(r3).getSensorValue(1);
		verify(l1).getLidarValue(1);
	}

	/**
	 * Scenario 2 - Starts at the beginning of the street, Moves along the
	 * street and after a few moves forward it is issued a command to change
	 * lange,It senses the left-hand-side lane and in both queries receives
	 * within-range values (indicating that no sensor is faulty), but both
	 * queries indicate that the left-hand-side lane is occupied, It stays in
	 * its lane, and Moves to the end of the street
	 */
	@Test
	public void nonEmptyLeftLaneInBothIterationsMoveToEndOfStreetScenario() throws StreetLengthException {

		// car is at the beggining of the street and in lane 1 by the default
		// constructor of the car object and moves forward 3 times
		car.moveForward();
		car.moveForward();
		car.moveForward();

		// set up for the mocked sensors values, since this getSensorValue
		// method is
		// used in the leftLaneDetect to get the values of the sensors index 0
		// means first iteration index 1 means second iteration , and this
		// method is used in changeLane to see if the lane is occupued or empty
		// in this case sensors r2 and r3 will return the value 0
		// which will result in a occupied left lane in first and second
		// iteration

		// first iteration
		when(r1.getSensorValue(0)).thenReturn(1);
		when(r2.getSensorValue(0)).thenReturn(0);
		when(r3.getSensorValue(0)).thenReturn(1);
		when(l1.getLidarValue(0)).thenReturn(1);
		// second iteration
		when(r1.getSensorValue(1)).thenReturn(1);
		when(r2.getSensorValue(1)).thenReturn(1);
		when(r3.getSensorValue(1)).thenReturn(0);
		when(l1.getLidarValue(1)).thenReturn(1);

		// change lane request should result in switching the car into the left
		// lane
		car.changeLane(r1, r2, r3, l1);
		// car is in the second lane and distance 15 using a while loop we call
		// move forward until the car gets to the end of the road
		while (car.whereIs().getPosX() < 100) {
			car.moveForward();
		}
		// after the scenario has ended the car should be on lane 1 and distance
		// 100
		assertEquals("The car is not on lane 1", 1, car.getCarLane());
		assertEquals("The car is not on position 100", 100, car.getPositionX());

		/**
		 * To verify that the mocking worked we use the verify method from the
		 * class Mockito
		 *
		 */
		// first iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(0);
		verify(r2).getSensorValue(0);
		verify(r3).getSensorValue(0);
		verify(l1).getLidarValue(0);
		// second iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(1);
		verify(r2).getSensorValue(1);
		verify(r3).getSensorValue(1);
		verify(l1).getLidarValue(1);

	}

	/**
	 * Scenario 3 - Starts at the beginning of the street, Moves along the
	 * street and after a few moves forward it is issued a command to change
	 * lange,It senses the left-hand-side lane and in both queries receives
	 * within-range values and out of range values and both queries indicate
	 * that the left-hand-side lane is vacant, It moves to the left-hand-side
	 * lane, and moves 5m forward
	 */
	@Test
	public void HalfBrokenSensorsInEachIterationScenario() throws StreetLengthException {

		// car is at the beggining of the street and in lane 1 by the default
		// constructor of the car object

		// first iteration
		when(r1.getSensorValue(0)).thenReturn(1);
		when(r2.getSensorValue(0)).thenReturn(-1);
		when(r3.getSensorValue(0)).thenReturn(-1);
		when(l1.getLidarValue(0)).thenReturn(1);
		// second iteration
		when(r1.getSensorValue(1)).thenReturn(1);
		when(r2.getSensorValue(1)).thenReturn(1);
		when(r3.getSensorValue(1)).thenReturn(-1);
		when(l1.getLidarValue(1)).thenReturn(-1);

		// change lane request should result in switching the car into the left
		// lane
		car.changeLane(r1, r2, r3, l1);

		// after the scenario has ended the car should be on lane 2 and distance
		// 5
		assertEquals("The car is not on lane 2", 2, car.getCarLane());
		assertEquals("The car is not on position 5", 5, car.getPositionX());

		/**
		 * To verify that the mocking worked we use the verify method from the
		 * class Mockito
		 *
		 */
		// first iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(0);
		verify(r2).getSensorValue(0);
		verify(r3).getSensorValue(0);
		verify(l1).getLidarValue(0);
		// second iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(1);
		verify(r2).getSensorValue(1);
		verify(r3).getSensorValue(1);
		verify(l1).getLidarValue(1);

	}

	/**
	 * Scenario 4 - Starts at position 50 on the street, and lane 2 it is issued
	 * a command to change lange,It senses the left-hand-side lane and in both
	 * queries receives out of range values (indicating that more than 2 sensor
	 * are faulty), and both queries indicate that It stays in its lane, and
	 * moves forward 5m
	 */
	@Test
	public void brokenSensorsMoveToEndOfStreetScenario() throws StreetLengthException {

		// car is set to be on lane 2 and distance 50m
		car.setCarLane(2);
		car.setPositionX(50);
		// setting up the sensor values for the change request 3 sensor in each
		// iteration
		// is faulty

		// first iteration
		when(r1.getSensorValue(0)).thenReturn(-1);
		when(r2.getSensorValue(0)).thenReturn(1);
		when(r3.getSensorValue(0)).thenReturn(-1);
		when(l1.getLidarValue(0)).thenReturn(-1);
		// second iteration
		when(r1.getSensorValue(1)).thenReturn(1);
		when(r2.getSensorValue(1)).thenReturn(-1);
		when(r3.getSensorValue(1)).thenReturn(-1);
		when(l1.getLidarValue(1)).thenReturn(-1);

		// change lane request should result in staying in the same lane since
		// sensors are broken
		car.changeLane(r1, r2, r3, l1);

		// after the scenario has ended the car should be on lane 3 and distance
		// 55

		assertEquals("The car is not on lane 2", 2, car.getCarLane());
		assertEquals("The car is not on position 55", 55, car.getPositionX());

		/**
		 * To verify that the mocking worked we use the verify method from the
		 * class Mockito
		 *
		 */
		// first iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(0);
		verify(r2).getSensorValue(0);
		verify(r3).getSensorValue(0);
		verify(l1).getLidarValue(0);
		// second iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(1);
		verify(r2).getSensorValue(1);
		verify(r3).getSensorValue(1);
		verify(l1).getLidarValue(1);

	}

	/*
	 * Scenario 5 car is on lane 3 and distance 50 and tries to change lane
	 */
	@Test
	public void carLane3ChangeLaneRequest() throws StreetLengthException {
		// car requests to change lane
		car.setCarLane(3);
		car.setPositionX(50);

		// first iteration
		when(r1.getSensorValue(0)).thenReturn(1);
		when(r2.getSensorValue(0)).thenReturn(1);
		when(r3.getSensorValue(0)).thenReturn(1);
		when(l1.getLidarValue(0)).thenReturn(1);
		// second iteration
		when(r1.getSensorValue(1)).thenReturn(1);
		when(r2.getSensorValue(1)).thenReturn(1);
		when(r3.getSensorValue(1)).thenReturn(1);
		when(l1.getLidarValue(1)).thenReturn(1);
		// change lane request should result in switching the car into the left
		// lane
		car.changeLane(r1, r2, r3, l1);

		// car is in 3rd lane it should only move forward meaning the position
		// should be 3rd lane 5 distance
		assertEquals("The car is not on lane 3", 3, car.getCarLane());
		assertEquals("The car is not on position 55", 55, car.getPositionX());

	}

	/*
	 * Scenario 6 car is at the end of the street and we want to changeLane
	 * generates an exception
	 */
	
	@Test(expected = StreetLengthException.class)
	public void carPosChangeLaneRequest() throws StreetLengthException {
		// car requests to change lane
		car.setPositionX(100);

		// first iteration
		when(r1.getSensorValue(0)).thenReturn(1);
		when(r2.getSensorValue(0)).thenReturn(1);
		when(r3.getSensorValue(0)).thenReturn(1);
		when(l1.getLidarValue(0)).thenReturn(1);
		// second iteration
		when(r1.getSensorValue(1)).thenReturn(1);
		when(r2.getSensorValue(1)).thenReturn(1);
		when(r3.getSensorValue(1)).thenReturn(1);
		when(l1.getLidarValue(1)).thenReturn(1);
		// change lane request should result in switching the car into the left
		// lane but in this case it should genarte an exception since is at the end of the street
		car.changeLane(r1, r2, r3, l1);
		
		/**
		 * To verify that the mocking worked we use the verify method from the
		 * class Mockito
		 *
		 */
		// first iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(0);
		verify(r2).getSensorValue(0);
		verify(r3).getSensorValue(0);
		verify(l1).getLidarValue(0);
		// second iteration sensor values for r1 r2 r3 and l1
		verify(r1).getSensorValue(1);
		verify(r2).getSensorValue(1);
		verify(r3).getSensorValue(1);
		verify(l1).getLidarValue(1);
		
	}

}
