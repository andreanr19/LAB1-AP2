package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import exceptions.IncompleteInformationException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import exceptions.UserNotRegisteredYet;

public class Company {

	private ArrayList<Turn> turns;
	private ArrayList<User> registeredUsers;

	public Company() {

		turns = new ArrayList<Turn>();
		registeredUsers = new ArrayList<User>();
		cont = 0;
	}

	public ArrayList<Turn> getTurns() {
		return turns;
	}

	public void setTurns(ArrayList<Turn> turns) {
		this.turns = turns;
	}

	public ArrayList<User> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(ArrayList<User> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

/**
 * This method allows to add a turn to the arraylist of turns of the company	
 * @param turno this parameter is the complete name of the turn
 * @param letter is the letter of the turn 
 * @param number is the first number of the turn
 * @param snumber is the second number of the turn
 * @param status this parameter allows to know if the turn has been attended or not
 * @param turnHour is the hour when the turn was created
 * @param turnDate is the date when the turn was created
 * @param attended allows to know if the person's turn was really attended
 */
	public void addTurn(String turno, char letter, int number, int snumber, boolean status, LocalTime turnHour,
			LocalDate turnDate, boolean attended) {
		Turn newturn = new Turn(turno, number, snumber, letter, status, turnHour, turnDate, attended);
		turns.add(newturn);
	}

	private int cont;

/**
 * This parameter allows to register a new User to the company
 * @param id is the identifier of the user
 * @param name is the name of the user
 * @param lastName is the lastname of the user
 * @param phone is the phone of the user
 * @param address is the address of the user
 * @param documentType is the document type of the user
 * @param documentNumber is the document number of the user
 * @throws UserAlreadyExistsException is an exception that is thrown in case that the user already exists
 * @throws IncompleteInformationException is an exception that is thrown in case that the information of the user is incomplete
 */
	public void addRegisteredUser(int id, String name, String lastName, int phone, String address,
			DocumentType documentType, String documentNumber)
			throws UserAlreadyExistsException, IncompleteInformationException {
		if (documentType.equals(null) || documentNumber.equals("") || name.equals("") || lastName.equals("")) {
			throw new IncompleteInformationException();
		}
		User newUser = new User(id, name, lastName, phone, address, documentType, documentNumber);

		if (getRegisteredUsers().size() == 0) {
			registeredUsers.add(newUser);
			System.out
					.println("The user " + name + (" ") + lastName + " was succesfully registered in the data system");
		} else {
			boolean found = false;

			boolean throwException = false;
			for (int i = 0; i < registeredUsers.size() && !found; i++) {
				if (registeredUsers.get(i).getDocumentNumber().equals(documentNumber)
						&& registeredUsers.get(i).getDocumentType().equals(documentType)) {
					throwException = true;
					throw new UserAlreadyExistsException(name, documentNumber);
				}
			}

			if (throwException == false) {
				registeredUsers.add(newUser);
				found = true;

				System.out.println(
						"The user " + name + (" ") + lastName + " was succesfully registered in the data system");
			}
		}

	}

	public void addRegisteredUser2(int id, String name, String lastName, int phone, String address,
			DocumentType documentType, String documentNumber)
			throws UserAlreadyExistsException, IncompleteInformationException {
		if (documentType.equals(null) || documentNumber.equals("") || name.equals("") || lastName.equals("")) {
			throw new IncompleteInformationException();
		}
		User newUser = new User(id, name, lastName, phone, address, documentType, documentNumber);

		if (getRegisteredUsers().size() == 0) {
			registeredUsers.add(newUser);
			System.out
					.println("The user " + name + (" ") + lastName + " was succesfully registered in the data system");
		} else {
			boolean found = false;

			boolean throwException = false;
			for (int i = 0; i < registeredUsers.size() && !found; i++) {
				if (registeredUsers.get(i).getDocumentNumber().equals(documentNumber)
						&& registeredUsers.get(i).getDocumentType().equals(documentType)) {
					throw new UserAlreadyExistsException(name, documentNumber);
				} else {
					registeredUsers.add(newUser);
					found = true;

					System.out
							.println("The user " + name + lastName + " was succesfully registered in the data system");
				}
			}
		}
	}

	// to give a turn to an user
	public void toGiveATurnToAnUser(String nameUser, String documentNumber) throws UserNotRegisteredYet {
		boolean throwException = true;
		for (int i = 0; i < registeredUsers.size(); i++) {
			if (registeredUsers.get(i).getName().equals(nameUser)
					&& registeredUsers.get(i).getDocumentNumber().equals(documentNumber)) {

				if (turns.size() == 0) {
					// si no hay turnos todavia, se crean desde cero
					int theChar = 65;
					int theNumber1 = 0;
					int theNumber2 = 0;
					LocalTime turnHour = LocalTime.now();
					LocalDate turnDate = LocalDate.now();
					Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
							(char) theChar, true, turnHour, turnDate, false);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
							newTurn.isAttended());

					cont++;
					System.out.println(
							"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());
					throwException = false;
					// cuando llega a diez veinte treinta cuarenta.. pero no se cambia el char aun,
					// solo el primer numero aumenta
					// y el segundo se reinizializa en cero
				} else if ((cont) % 10 == 0 && cont != 100 && cont != 200 && cont != 300 && cont != 400 && cont != 500
						&& cont != 600 && cont != 700 && cont != 800 && cont != 900 && cont != 1000 && cont != 1100
						&& cont != 1200 && cont != 1300 && cont != 1400 && cont != 1500 && cont != 1600 && cont != 1700
						&& cont != 1800 && cont != 1900 && cont != 2000 && cont != 2100 && cont != 2200 && cont != 2300
						&& cont != 2400 && cont != 2500 && cont != 2600 && cont != 0 && cont != 1 && cont != 2
						&& cont != 3 && cont != 4 && cont != 5 && cont != 6 && cont != 7 && cont != 8 && cont != 9) {
					Turn temp = turns.get(turns.size() - 1);
					int theChar = temp.getLetter();
					int theNumber1 = temp.getFirstNumber() + 1;
					int theNumber2 = 0;
					LocalTime turnHour = LocalTime.now();
					LocalDate turnDate = LocalDate.now();
					Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
							(char) theChar, true, turnHour, turnDate, false);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
							newTurn.isAttended());

					cont++;
					throwException = false;

					System.out.println(
							"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());

				} else if (cont == 100 || cont == 200 || cont == 300 || cont == 400 || cont == 500 || cont == 600
						|| cont == 700 || cont == 800 || cont == 900 || cont == 1000 || cont == 1100 || cont == 1200
						|| cont == 1300 || cont == 1400 || cont == 1500 || cont == 1600 || cont == 1700 || cont == 1800
						|| cont == 1900 || cont == 2000 || cont == 2100 || cont == 2200 || cont == 2300 || cont == 2400
						|| cont == 2500 || cont == 2600) {
					Turn temp = turns.get(turns.size() - 1);
					int theChar = temp.getLetter() + 1;
					int theNumber1 = 0;
					int theNumber2 = 0;
					LocalTime turnHour = LocalTime.now();
					LocalDate turnDate = LocalDate.now();
					Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
							(char) theChar, true, turnHour, turnDate, false);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
							newTurn.isAttended());

					throwException = false;

					cont++;
					System.out.println(
							"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());
				} else {
					Turn temp = turns.get(turns.size() - 1);
					int theChar = temp.getLetter();
					int theNumber1 = temp.getFirstNumber();
					int theNumber2 = temp.getSecondNumber() + 1;
					LocalTime turnHour = LocalTime.now();
					LocalDate turnDate = LocalDate.now();
					Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
							(char) theChar, true, turnHour, turnDate, false);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
							newTurn.isAttended());

					cont++;
					throwException = false;

					System.out.println(
							"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());

				}
			}
//				throw new UserNotRegisteredYet(nameUser);

		}
		if (throwException == true) {
			throw new UserNotRegisteredYet(nameUser);
		}
	}

/**
 * this method allows to search an user by his/her name
 * @param name is the name of the user
 * @return a object of an User that has as name the one passed by parameter
 */
	public User toSearchUser(String name) {
		boolean found = false;
		User theUser = null;
		for (int i = 0; i < registeredUsers.size() && !found; i++) {
			if (registeredUsers.get(i).getName().equals(name)) {
				theUser = registeredUsers.get(i);
				found = true;
			}
		}
		return theUser;
	}

/**
 * This method allows to find an user given his/her document number
 * @param DocumentNumber is the document number of the user
 * @return a object of type User that has as document number the passed by parameter
 * @throws UserNotFoundException an exception is thrown in case that the user is not found
 */
	public User toSearchUserById(String DocumentNumber) throws UserNotFoundException {
		boolean found = false;
		User theUser = null;
		for (int i = 0; i < registeredUsers.size() && !found; i++) {
			if (registeredUsers.get(i).getDocumentNumber().equals(DocumentNumber)) {
				theUser = registeredUsers.get(i);
				found = true;
			}
		}
		if (theUser == null) {
			throw new UserNotFoundException(DocumentNumber);
		}
		return theUser;
	}

/**
 * This methods allows to know information about the user
 * @param documentNumber is the document number of the user
 * @return a String with the information of the user
 * @throws UserNotFoundException an exception is thrown in case the user is not found
 */
	public String toSearchAnUser(String documentNumber) throws UserNotFoundException {
		String msg = "";
		boolean throwException = true;
		for (int i = 0; i < registeredUsers.size(); i++) {
			if (registeredUsers.get(i).getDocumentNumber().equals(documentNumber)) {
				msg += "The user has the following information: " + "\n" + "Document Type: " + "\n"
						+ registeredUsers.get(i).getDocumentType() + "\n" + "Name: " + registeredUsers.get(i).getName()
						+ "\n" + "LastName: " + registeredUsers.get(i).getLastName() + "\n" + "Phone: "
						+ registeredUsers.get(i).getPhone();
				throwException = false;
				if (registeredUsers.get(i).getTurnsUser().size() != 0) {
					msg += "\n" + "The turn assigned for " + registeredUsers.get(i)
							+ registeredUsers.get(i).getLastName() + " is: "
							+ registeredUsers.get(i).getTurnsUser().get(0).getTurno();
				} else {
					msg += "\n" + "The user " + registeredUsers.get(i).getName() + " "
							+ registeredUsers.get(i).getLastName() + " doesn't have a turn assigned yet";
				}
			}

		}
		if (throwException == true) {
			throw new UserNotFoundException(documentNumber);
		}
		return msg;
	}

	/**
	 * this method allows to attend the user 
	 * @param wasThere it indicates if the user was there when he/she was called or not
	 */
	public void toAttendAnUser(boolean wasThere) {
		boolean verify = false;

		// el true del status significa que no han pasado por él en el llamado
		for (int i = 0; i < turns.size() && !verify; i++) {
			if (turns.get(i).getStatus() == true && turns.get(i).isAttended() == false) {
				if (wasThere == true) {
					turns.get(i).setStatus(false);
					// User theUser=getTheDueñoDelTurno(turns.get(i).getTurno());
					// theUser.getTheTurn(turns.get(i).getTurno()).setStatus(false);
					// theUser.getTheTurn(turns.get(i).getTurno()).setAttended(true);
					turns.get(i).getUser().getTurnsUser().get(i).setStatus(false);
					turns.get(i).getUser().getTurnsUser().get(i).setAttended(true);
					turns.get(i).setAttended(true);
					verify = true;
					System.out.println("The turn " + turns.get(i).getTurno() + " of the user "
							+ turns.get(i).getUser().getName() + " has been attended.");
				} else {
//					User theUser=getTheDueñoDelTurno(turns.get(i).getTurno());
//					theUser.getTheTurn(turns.get(i).getTurno()).setStatus(false);
//					theUser.getTheTurn(turns.get(i).getTurno()).setAttended(false);

					turns.get(i).setStatus(false);
					turns.get(i).setAttended(false);
//					
					verify = true;
					System.out.println("The turn " + turns.get(i).getTurno() + " of the user "
							+ turns.get(i).getUser().getName() + " hasn't been attended because the user wasn't there");
				}
			}

		}

	}

	//devolver el primero con status en true
	public User toreturnUserStatusFalse() {
		User theUser= null;
		boolean found=false;
		for(int i=0; i<turns.size() && !found;i++) {
			if(turns.get(i).getStatus()==true) {
				theUser= turns.get(i).getUser();
				found=true;
			}
		}
		return theUser;
	}

	/**
	 * this method allows to give a turn to an user that doesn't have one or was attended before.
	 * @param nameUser is the name of the user
	 * @param documentNumber is the document number of the user
	 * @throws UserNotRegisteredYet an exception is thrown in case the user is not registered yet.
	 */
	public void toGiveATurnToAnUser2(String nameUser, String documentNumber) throws UserNotRegisteredYet {
		boolean throwException = true;
		for (int i = 0; i < registeredUsers.size(); i++) {
			if (registeredUsers.get(i).getName().equals(nameUser)
					&& registeredUsers.get(i).getDocumentNumber().equals(documentNumber)) {
				if (turns.size() == 0) {
					// si no hay turnos todavia, se crean desde cero
					int theChar = 65;
					int theNumber1 = 0;
					int theNumber2 = 0;
					LocalTime turnHour = LocalTime.now();
					LocalDate turnDate = LocalDate.now();
					Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
							(char) theChar, true, turnHour, turnDate, false);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
							newTurn.isAttended());

					cont++;
					System.out.println(
							"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());
					throwException = false;
					// cuando llega a diez veinte treinta cuarenta.. pero no se cambia el char aun,
					// solo el primer numero aumenta
					// y el segundo se reinizializa en cero

				} else if (registeredUsers.get(i).hasActiveTurn() == false) {

					if ((cont) % 10 == 0 && cont != 100 && cont != 200 && cont != 300 && cont != 400 && cont != 500
							&& cont != 600 && cont != 700 && cont != 800 && cont != 900 && cont != 1000 && cont != 1100
							&& cont != 1200 && cont != 1300 && cont != 1400 && cont != 1500 && cont != 1600
							&& cont != 1700 && cont != 1800 && cont != 1900 && cont != 2000 && cont != 2100
							&& cont != 2200 && cont != 2300 && cont != 2400 && cont != 2500 && cont != 2600 && cont != 0
							&& cont != 1 && cont != 2 && cont != 3 && cont != 4 && cont != 5 && cont != 6 && cont != 7
							&& cont != 8 && cont != 9) {
						Turn temp = turns.get(turns.size() - 1);
						int theChar = temp.getLetter();
						int theNumber1 = temp.getFirstNumber() + 1;
						int theNumber2 = 0;
						LocalTime turnHour = LocalTime.now();
						LocalDate turnDate = LocalDate.now();
						Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
								(char) theChar, true, turnHour, turnDate, false);
						turns.add(newTurn);
						User theUser = toSearchUser(nameUser);
						newTurn.setUser(theUser);
						theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
								newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
								newTurn.isAttended());

						cont++;
						throwException = false;

						System.out.println(
								"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());

					} else if (cont == 100 || cont == 200 || cont == 300 || cont == 400 || cont == 500 || cont == 600
							|| cont == 700 || cont == 800 || cont == 900 || cont == 1000 || cont == 1100 || cont == 1200
							|| cont == 1300 || cont == 1400 || cont == 1500 || cont == 1600 || cont == 1700
							|| cont == 1800 || cont == 1900 || cont == 2000 || cont == 2100 || cont == 2200
							|| cont == 2300 || cont == 2400 || cont == 2500 || cont == 2600) {
						Turn temp = turns.get(turns.size() - 1);
						int theChar = temp.getLetter() + 1;
						int theNumber1 = 0;
						int theNumber2 = 0;
						LocalTime turnHour = LocalTime.now();
						LocalDate turnDate = LocalDate.now();
						Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
								(char) theChar, true, turnHour, turnDate, false);
						turns.add(newTurn);
						User theUser = toSearchUser(nameUser);
						newTurn.setUser(theUser);
						theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
								newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
								newTurn.isAttended());

						throwException = false;

						cont++;
						System.out.println(
								"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());
					} else {
						Turn temp = turns.get(turns.size() - 1);
						int theChar = temp.getLetter();
						int theNumber1 = temp.getFirstNumber();
						int theNumber2 = temp.getSecondNumber() + 1;
						LocalTime turnHour = LocalTime.now();
						LocalDate turnDate = LocalDate.now();
						Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
								(char) theChar, true, turnHour, turnDate, false);
						turns.add(newTurn);
						User theUser = toSearchUser(nameUser);
						newTurn.setUser(theUser);
						theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
								newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
								newTurn.isAttended());

						cont++;
						throwException = false;

						System.out.println(
								"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());

					}

				} else {
					System.out.println("The user " + registeredUsers.get(i).getName()
							+ " has an active turn that hasn't been attended yet. Therefore, this user cannot receive another turn 'till the turn is attended");
					throwException = false;

				}
			}
		}
		if (throwException == true) {
			throw new UserNotRegisteredYet(nameUser);
		}
	}
}
