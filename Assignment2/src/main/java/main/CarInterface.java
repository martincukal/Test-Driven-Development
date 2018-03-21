package main;
import errorStreetException.*;

/*
 * Interface created for our Car
 */
public interface CarInterface {
	/*
	 * Method Description:
	 * Method used to move the car forward 5 meters
	 * If the car is asked to move forward when the current
	 * position is 100m (at the end of the street)
	 * a new StreetLengthException is thrown
	 */
    void moveForward() throws StreetLengthException;
    //------------------------------------------------------------------------------------------------------------------//
    
    /*
	 * Method Description:
	 * Method used to detect cars on the left lane 
	 * it uses 3 ultrasound sensors and one lidar which are querried twice
	 * to generate results Values : (0 - means sensor detects an object,-1 - sensor failed ,1 no object detected)
	 * the method returns true if there is no object detected and false otherwise
	 * 
	 */
    boolean leftLaneDetect(USoundInterface r1,USoundInterface r2,USoundInterface r3,LidarInterface l1);
    //------------------------------------------------------------------------------------------------------------------//
    
    /*
	 * Method Description:
	 * Method used to change the lane of the car
	 * it uses 3 ultrasound sensors and one lidar sensor for parameters to pass on the leftLaneDetect algoritham
	 * first it checks if the car is at the end of the street and if it is it generates new StreetLengthException 
	 * if not it than checks if the car is in lane that is smaller than 3 and if the left lane returns a positive reading
	 * if the two statements are true than it moves the car forward and increments the lane
	 * if the car is on the 3rd lane or the car detects an object than it only moves the car forward
	 */
    void changeLane(USoundInterface r1,USoundInterface r2,USoundInterface r3,LidarInterface l1) throws StreetLengthException;
    //------------------------------------------------------------------------------------------------------------------//
    
    /*
	 * Method Description:
	 * Method used to return the position of the car
	 * it returns a position object which has the car lane and car positionX(distance) information
	 * it generates a new  StreetLengthException if the car starting lane is not 1 and positon is not 0 
	 * if they are bigger than 3 and 100 respectively
	 */
    Position whereIs() throws StreetLengthException;
    

}