package exceptions;

public class UserAlreadyExistsException extends Exception {
	
	public UserAlreadyExistsException(String name, String documentType) {
		super("The user with the name: "+ name + " and the document number " + documentType + " is already registered in the system's data");
		
	}

}
