package main;

import java.util.Random;

public class Lidar {
	private int[] lidarValue = new int[2];

	public int getLidarValue(int index) {
		System.out.println("Lidar sensor value = " + this.lidarValue[index]);
		return this.lidarValue[index];
	}
	
	public void setBothValues(int sensorValOne,int sensorValTwo) {
		this.lidarValue[0] = sensorValOne;
		this.lidarValue[1] = sensorValTwo;
	}

}
