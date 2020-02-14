package exceptions;

public class UserNotRegisteredYet extends Exception {

	public UserNotRegisteredYet(String name) {
		super("The user with the name: " + name + " hasn't been registered yet");
	}
}
