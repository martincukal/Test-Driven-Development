package main;

public class Position {
	private int carLane = 1;
	private int positionX = 0;
	

	public int getCarLane() {
		return carLane;
	}

	public void setCarLane(int lane) {
		this.carLane = lane;
	}

	public int getPosX() {
		return positionX;
	}

	public void setPosX(int posX) {
		this.positionX = posX;
	}
	
	public void incrementPos(){
		this.positionX += 5;
	}
	public void incrementLane(){
		this.carLane++;
	}

	/* 
	 * Created a hashcode method used for the equals method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + carLane;
		result = prime * result + positionX;
		return result;
	}

	/* 
	 * Created a equals method that is used by junit assertEquals to compare position objects
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (carLane != other.carLane)
			return false;
		if (positionX != other.positionX)
			return false;
		return true;
	}
}
