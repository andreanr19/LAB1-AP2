package exceptions;

public class UserNotFoundException extends Exception{

	public UserNotFoundException(String documentNumber) {
		super("The user with the id " + documentNumber + " wasn't found in the system");
	}
}
