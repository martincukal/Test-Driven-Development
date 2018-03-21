package main;

import errorStreetException.*;

/*
 * Interface created for our Car
 */
public interface CarInterface {
	/*
	 * Method Description: Method used to move the car forward 5 meters If the
	 * car is asked to move forward when the current position is 100m (at the
	 * end of the street) a new StreetLengthException is thrown
	 * 
	 * Pre-condition: The car has to be in the range of the street meaning car
	 * lane 1-3 and position from 0 - 100 otherwise we cannot move it
	 * 
	 * Post-condition: After execution the car lane should stay the same lane and the
	 * position should be incremented by 5 meaning if starting pos is 0 after
	 * execution it should be 5
	 * 
	 * Test-cases:4
	 */

	void moveForward() throws StreetLengthException;

	// ------------------------------------------------------------------------------------------------------------------//

	/*
	 * Method Description: Method used to detect cars on the left lane it uses 3
	 * ultrasound sensors and one lidar which are querried twice to generate
	 * results Values : (0 - means sensor detects an object,-1 - sensor failed
	 * ,1 no object detected) the method returns true if there is no object
	 * detected and false otherwise
	 * 
	 * Pre-condition: Ultrasound sensors and Lidar values have to be set up for
	 * both iterations before executing this method.
	 * 
	 * Post-condition: After execution the method should return true if the lane
	 * is empty or false if there were object detected or more than 3 failed
	 * sensors in one querie
	 * 
	 * Test-cases:10
	 */

	boolean leftLaneDetect(UltrasoundSensor r1, UltrasoundSensor r2,
			UltrasoundSensor r3, Lidar l1);

	// ------------------------------------------------------------------------------------------------------------------//

	/*
	 * Method Description: Method used to change the lane of the car it uses 3
	 * ultrasound sensors and one lidar sensor for parameters to pass on the
	 * leftLaneDetect algoritham first it checks if the car is at the end of the
	 * street and if it is it generates new StreetLengthException if not it than
	 * checks if the car is in lane that is smaller than 3 and if the left lane
	 * returns a positive reading if the two statements are true than it moves
	 * the car forward and increments the lane if the car is on the 3rd lane or
	 * the car detects an object than it only moves the car forward
	 * 
	 * Pre-condition: Car has to have valid in bound street position and lane
	 * 
	 * Post-condition: If the cars starting pos was lane 1 and pos 0 after
	 * execution if the criteria for changing lane is fulfilled first the car
	 * should move forward 5m and than change the lane to 2
	 * 
	 * Test-cases:7
	 */

	void changeLane(UltrasoundSensor r1, UltrasoundSensor r2,
			UltrasoundSensor r3, Lidar l1) throws StreetLengthException;

	// ------------------------------------------------------------------------------------------------------------------//

	/*
	 * Method Description: Method used to return the position of the car it
	 * returns a position object which has the car lane and car
	 * positionX(distance) information it generates a new StreetLengthException
	 * if the car starting lane is not 1 and positon is not 0 if they are bigger
	 * than 3 and 100 respectively
	 * 
	 * Pre-condition:A car object needs to be initialized
	 * 
	 * Post-condition: After execution the method should return the Position
	 * object of the car which holds the car lane and posX (distance) and the
	 * car position should stay the same
	 * 
	 * Test-cases:9
	 */

	Position whereIs() throws StreetLengthException;

}