package exceptions;

public class UserWithAnActiveTurnException extends Exception {

	public UserWithAnActiveTurnException(String name, String documentNumber) {
		super("The user " + name + " with the document number " + documentNumber
				+ " has an active turn that hasn't been attended yet. Therefore, this user cannot receive another turn 'till the turn is attended");
	}
}
