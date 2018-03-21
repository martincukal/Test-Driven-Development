package main;

import java.util.Random;

public class UltrasoundSensor implements USoundInterface {
	private int[] sensorValue = new int[2];
	
	/* (non-Javadoc)
	 * @see main.USoundInterface#getSensorValue(int)
	 */
	public int getSensorValue(int index) {
		return this.sensorValue[index];
	}

	/* (non-Javadoc)
	 * @see main.USoundInterface#setBothValues(int, int)
	 */
	public void setBothValues(int sensorValOne,int sensorValTwo) {
		this.sensorValue[0] = sensorValOne;
		this.sensorValue[1] = sensorValTwo;
	}
	/*
	public int generateRandomVal() {
		Random rand = new Random();
		// random values generated
		int randVal = rand.nextInt(51) - 10;
		System.out.println("UltraSound sensor " + this.name + " value = " + this.sensorValue);
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
