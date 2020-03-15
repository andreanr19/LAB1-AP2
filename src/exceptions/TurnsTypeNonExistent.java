package exceptions;

public class TurnsTypeNonExistent extends Exception{

	public TurnsTypeNonExistent(String name) {
		super("The type of turn called " + name + " doesn't exist in the system");
	}
}
