package errorStreetException;

/*Class created to generate the street length exception when the car is out of range */
public class StreetLengthException extends Exception {

	public StreetLengthException(String message) {
		super(message);
	};

	public StreetLengthException() throws StreetLengthException {
		throw new StreetLengthException("No road left for the car to move");
	}
}
