package main;

import java.util.Random;

public class UltrasoundSensor {
	private int[] sensorValue = new int[2];
	private String name = null;

	public void setName(String name) {
		this.name = name;
	}
	
	public int getSensorValue(int index) {
		System.out.println("UltraSound sensor " + this.name + " value = " + this.sensorValue[index]);
		return this.sensorValue[index];
	}

	public void setBothValues(int sensorValOne,int sensorValTwo) {
		this.sensorValue[0] = sensorValOne;
		this.sensorValue[1] = sensorValTwo;
	}

}
