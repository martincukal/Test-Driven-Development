package main;

import java.util.Random;

public class Lidar implements LidarInterface {
	private int[] lidarValue = new int[2];

	/* (non-Javadoc)
	 * @see main.LidarInterface#getLidarValue(int)
	 */
	public int getLidarValue(int index) {
		System.out.println("Lidar sensor value = " + this.lidarValue[index]);
		return this.lidarValue[index];
	}
	/*
	public void setFirstLidarValue(int lidarValue) {
		this.lidarValue[0] = lidarValue;
	}

	public void setSecLidarValue(int lidarValue) {
		this.lidarValue[1] = lidarValue;
	}*/

	/* (non-Javadoc)
	 * @see main.LidarInterface#setBothValues(int, int)
	 */
	public void setBothValues(int sensorValOne, int sensorValTwo) {
		this.lidarValue[0] = sensorValOne;
		this.lidarValue[1] = sensorValTwo;
	}
	/*
	public int generateRandomVal() {
		Random rand = new Random();
		int randVal = rand.nextInt(51);
		System.out.println("Lidar sensor value = " + this.lidarValue);
		// if the value of the sensor is smaller than 0 that means it is not
		// working properly
		if (randVal < 0) {
			return -1;
			// since the wide distance for a lane is 5 meters so if value is
			// less
			// than 5 meters that means that there is a car in the left lane
		} else if (randVal >= 0 && randVal < 5) {
			return 0;
			// bigger values than 5 there is no car in the left lane
		} else {
			return 1;
		}
	}
	*/

}
