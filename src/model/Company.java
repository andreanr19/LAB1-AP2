package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

import exceptions.IncompleteInformationException;
import exceptions.TurnsTypeNonExistent;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotFoundException;
import exceptions.UserNotRegisteredYet;

public class Company implements Serializable {

	private ArrayList<Turn> turns;
	private ArrayList<User> registeredUsers;

	private ArrayList<TurnType> turnsTypes;

	private Date myDate;

	private String archives;
	private static final long serialVersionUID = 1L;

	public Company(String archives) {

		this.archives = archives;
		turns = new ArrayList<Turn>();
		registeredUsers = new ArrayList<User>();
		turnsTypes = new ArrayList<TurnType>();
		cont = 0;
		myDate = new Date();
		deserializableUser();
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

	public ArrayList<TurnType> getTurnsTypes() {
		return turnsTypes;
	}

	public void setTurnsTypes(ArrayList<TurnType> turnsTypes) {
		this.turnsTypes = turnsTypes;
	}

	/**
	 * 
	 * @return
	 */
	public String showActualDateAndHour() {
		String msj = "Actual system's date: " + myDate.getCurrentDay() + "/" + myDate.getCurrentMonth() + "/"
				+ myDate.getCurrentYear() + "\n";

		msj += "Actual system's time: " + myDate.getCurrentHour() + ":" + myDate.getCurrentMinute() + ":"
				+ myDate.getCurrentSecond();
		return msj;
	}

	public String showACTUALHOUR() {
		String msj = "Actual system's time: " + myDate.getCurrentHour() + ":" + myDate.getCurrentMinute() + ":"
				+ myDate.getCurrentSecond();
		return msj;
	}

	/**
	 * 
	 * @param nt
	 */
	public void toAddATypeToATurn(TurnType nt) {

		if (turnsTypes.size() == 0) {
			turnsTypes.add(nt);

		}
		for (int i = 0; i < turnsTypes.size(); i++) {
			if (nt.getName().equalsIgnoreCase(turnsTypes.get(i).getName())) {

			} else {
				turnsTypes.add(nt);

			}
		}
	}

	/**
	 * 
	 * @return
	 */
	public String showTypesTurns() {
		String msj = "";
		if (turnsTypes.size() == 0) {
			msj = "There are not types of turns yet, you should add one";
		} else {
			for (int i = 0; i < turnsTypes.size(); i++) {
				System.out
						.println("These are the turns type existents: \n" + i + 1 + ". " + turnsTypes.get(i).getName());
			}
		}
		return msj;

	}

	public void assignIdToTheTurnType() {
		int id = 1;
		for (int i = 0; i < turnsTypes.size(); i++) {
			turnsTypes.get(i).setId(id);
			id++;
		}
	}

	/**
	 * This method allows to add a turn to the arraylist of turns of the company
	 * 
	 * @param turno    this parameter is the complete name of the turn
	 * @param letter   is the letter of the turn
	 * @param number   is the first number of the turn
	 * @param snumber  is the second number of the turn
	 * @param status   this parameter allows to know if the turn has been attended
	 *                 or not
	 * @param turnHour is the hour when the turn was created
	 * @param turnDate is the date when the turn was created
	 * @param attended allows to know if the person's turn was really attended
	 */
	public void addTurn(String turno, char letter, int number, int snumber, boolean status, LocalTime turnHour,
			LocalDate turnDate, boolean attended, TurnType myType) {
		Turn newturn = new Turn(turno, number, snumber, letter, status, turnHour, turnDate, attended, myType);
		turns.add(newturn);
	}

	private int cont;

	/**
	 * This parameter allows to register a new User to the company
	 * 
	 * @param id             is the identifier of the user
	 * @param name           is the name of the user
	 * @param lastName       is the lastname of the user
	 * @param phone          is the phone of the user
	 * @param address        is the address of the user
	 * @param documentType   is the document type of the user
	 * @param documentNumber is the document number of the user
	 * @throws UserAlreadyExistsException     is an exception that is thrown in case
	 *                                        that the user already exists
	 * @throws IncompleteInformationException is an exception that is thrown in case
	 *                                        that the information of the user is
	 *                                        incomplete
	 */
	public void addRegisteredUser(int id, String name, String lastName, String phone, String address,
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

	public void addRegisteredUser2(int id, String name, String lastName, String phone, String address,
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
	public void toGiveATurnToAnUser(String nameUser, String documentNumber, TurnType myType)
			throws UserNotRegisteredYet {
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
							(char) theChar, true, turnHour, turnDate, false, myType);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
							newTurn.isAttended(), newTurn.getMyType());

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
							(char) theChar, true, turnHour, turnDate, false, myType);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
							newTurn.isAttended(), newTurn.getMyType());

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
							(char) theChar, true, turnHour, turnDate, false, myType);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
							newTurn.isAttended(), newTurn.getMyType());

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
							(char) theChar, true, turnHour, turnDate, false, myType);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate(),
							newTurn.isAttended(), newTurn.getMyType());

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
	 * 
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
	 * 
	 * @param DocumentNumber is the document number of the user
	 * @return a object of type User that has as document number the passed by
	 *         parameter
	 * @throws UserNotFoundException an exception is thrown in case that the user is
	 *                               not found
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
	 * 
	 * @param documentNumber is the document number of the user
	 * @return a String with the information of the user
	 * @throws UserNotFoundException an exception is thrown in case the user is not
	 *                               found
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

					msg += "The date and the time the user got the turn was: "
							+ registeredUsers.get(i).getTurnsUser().get(0).showMyDate();
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
	 * 
	 * @param documentNumber
	 * @return
	 * @throws UserNotFoundException
	 */
	public String toSearchAnUser2(String documentNumber) throws UserNotFoundException {
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
					int amountOfTurns = registeredUsers.get(i).getTurnsUser().size();
					while (amountOfTurns != 0) {
						msg += "\n" + "The turn # " + amountOfTurns + " assigned for "
								+ registeredUsers.get(i).getName() + (" ") + registeredUsers.get(i).getLastName()
								+ " was: " + registeredUsers.get(i).getTurnsUser().get(amountOfTurns - 1).getTurno();

						msg += "\n The type of the turn was: "
								+ registeredUsers.get(i).getTurnsUser().get(amountOfTurns - 1).getMyType().getName()
								+ " with the duration of being attended of: " + registeredUsers.get(i).getTurnsUser()
										.get(amountOfTurns - 1).getMyType().getDuration();
						msg += "\n The date and the time the user got the turn was: "
								+ registeredUsers.get(i).getTurnsUser().get(amountOfTurns - 1).showMyDate();
						amountOfTurns--;
					}
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
	 * 
	 * @param wasThere it indicates if the user was there when he/she was called or
	 *                 not
	 */
	public void toAttendAnUser(boolean wasThere) {
		boolean verify = false;

		// el true del status significa que no han pasado por �l en el llamado
		for (int i = 0; i < turns.size() && !verify; i++) {
			if (turns.get(i).getStatus() == true && turns.get(i).isAttended() == false) {
				if (wasThere == true) {
					turns.get(i).setStatus(false);
					// User theUser=getTheDue�oDelTurno(turns.get(i).getTurno());
					// theUser.getTheTurn(turns.get(i).getTurno()).setStatus(false);
					// theUser.getTheTurn(turns.get(i).getTurno()).setAttended(true);
					turns.get(i).getUser().getTurnsUser().get(i).setStatus(false);
					turns.get(i).getUser().getTurnsUser().get(i).setAttended(true);
					turns.get(i).setAttended(true);
					verify = true;
					System.out.println("The turn " + turns.get(i).getTurno() + " of the user "
							+ turns.get(i).getUser().getName() + " has been attended.");
				} else {
//					User theUser=getTheDue�oDelTurno(turns.get(i).getTurno());
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

	// devolver el primero con status en true
	public User toreturnUserStatusFalse() {
		User theUser = null;
		boolean found = false;
		for (int i = 0; i < turns.size() && !found; i++) {
			if (turns.get(i).getStatus() == true) {
				theUser = turns.get(i).getUser();
				found = true;
			}
		}
		return theUser;
	}

	/**
	 * this method allows to give a turn to an user that doesn't have one or was
	 * attended before.
	 * 
	 * @param nameUser       is the name of the user
	 * @param documentNumber is the document number of the user
	 * @throws UserNotRegisteredYet an exception is thrown in case the user is not
	 *                              registered yet.
	 */
	public void toGiveATurnToAnUser2(String nameUser, String documentNumber, TurnType myType)
			throws UserNotRegisteredYet, TurnsTypeNonExistent {
		boolean throwException = true;

		// ORDENA A LOS USUARIOS
//		orderUsersByBubbleSort();
		// VERIFICA PRIMERO SI HAY TIPOS DE TURNOS PARA QUE CUANDO CREE LOS TURNOS, LOS
		// CREE CON LOS YA ESPECIFICADOS
		if (turnsTypes.size() != 0) {
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
								(char) theChar, true, false, myType);
						turns.add(newTurn);
						User theUser = toSearchUser(nameUser);
						newTurn.setUser(theUser);
						theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
								newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(), newTurn.getMyType());

						cont++;
						System.out.println(
								"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());
						toAddATypeToATurn(myType);
						throwException = false;
						// cuando llega a diez veinte treinta cuarenta.. pero no se cambia el char aun,
						// solo el primer numero aumenta
						// y el segundo se reinizializa en cero

					} else if (registeredUsers.get(i).hasActiveTurn() == false) {

						if ((cont) % 10 == 0 && cont != 100 && cont != 200 && cont != 300 && cont != 400 && cont != 500
								&& cont != 600 && cont != 700 && cont != 800 && cont != 900 && cont != 1000
								&& cont != 1100 && cont != 1200 && cont != 1300 && cont != 1400 && cont != 1500
								&& cont != 1600 && cont != 1700 && cont != 1800 && cont != 1900 && cont != 2000
								&& cont != 2100 && cont != 2200 && cont != 2300 && cont != 2400 && cont != 2500
								&& cont != 2600 && cont != 0 && cont != 1 && cont != 2 && cont != 3 && cont != 4
								&& cont != 5 && cont != 6 && cont != 7 && cont != 8 && cont != 9) {
							Turn temp = turns.get(turns.size() - 1);
							int theChar = temp.getLetter();
							int theNumber1 = temp.getFirstNumber() + 1;
							int theNumber2 = 0;
							LocalTime turnHour = LocalTime.now();
							LocalDate turnDate = LocalDate.now();
							Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1,
									theNumber2, (char) theChar, true, false, myType);
							turns.add(newTurn);
							User theUser = toSearchUser(nameUser);
							newTurn.setUser(theUser);
							theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
									newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(),
									newTurn.getMyType());

							cont++;
							toAddATypeToATurn(myType);

							throwException = false;

							System.out.println("The turn " + newTurn.getTurno() + " was assigned to the user "
									+ theUser.getName());

						} else if (cont == 100 || cont == 200 || cont == 300 || cont == 400 || cont == 500
								|| cont == 600 || cont == 700 || cont == 800 || cont == 900 || cont == 1000
								|| cont == 1100 || cont == 1200 || cont == 1300 || cont == 1400 || cont == 1500
								|| cont == 1600 || cont == 1700 || cont == 1800 || cont == 1900 || cont == 2000
								|| cont == 2100 || cont == 2200 || cont == 2300 || cont == 2400 || cont == 2500
								|| cont == 2600) {
							Turn temp = turns.get(turns.size() - 1);
							int theChar = temp.getLetter() + 1;
							int theNumber1 = 0;
							int theNumber2 = 0;
							LocalTime turnHour = LocalTime.now();
							LocalDate turnDate = LocalDate.now();
							Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1,
									theNumber2, (char) theChar, true, false, myType);
							turns.add(newTurn);
							User theUser = toSearchUser(nameUser);
							newTurn.setUser(theUser);
							theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
									newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(),
									newTurn.getMyType());

							throwException = false;

							cont++;
							toAddATypeToATurn(myType);

							System.out.println("The turn " + newTurn.getTurno() + " was assigned to the user "
									+ theUser.getName());
						} else {
							Turn temp = turns.get(turns.size() - 1);
							int theChar = temp.getLetter();
							int theNumber1 = temp.getFirstNumber();
							int theNumber2 = temp.getSecondNumber() + 1;
							LocalTime turnHour = LocalTime.now();
							LocalDate turnDate = LocalDate.now();
							Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1,
									theNumber2, (char) theChar, true, turnHour, turnDate, false, myType);
							turns.add(newTurn);
							User theUser = toSearchUser(nameUser);
							newTurn.setUser(theUser);
							theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
									newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(),
									newTurn.getMyType());

							cont++;
							toAddATypeToATurn(myType);

							throwException = false;

							System.out.println("The turn " + newTurn.getTurno() + " was assigned to the user "
									+ theUser.getName());

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
		} else {
			System.out.println("There are no turn's types yet. You should first create a new one");
		}
	}

	public void toGiveATurnToAnUser4(String nameUser, String documentNumber, TurnType myType)
			throws UserNotRegisteredYet, TurnsTypeNonExistent {
		boolean throwException = true;

		// ORDENA A LOS USUARIOS
//		orderUsersByBubbleSort();
		// VERIFICA PRIMERO SI HAY TIPOS DE TURNOS PARA QUE CUANDO CREE LOS TURNOS, LOS
		// CREE CON LOS YA ESPECIFICADOS
		if (turnsTypes.size() != 0) {
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
								(char) theChar, true, false, myType);
						turns.add(newTurn);
						User theUser = toSearchUser(nameUser);
						User theU = serachByBinariMethodDocumentNumber(documentNumber);
						newTurn.setUser(theU);
						theU.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
								newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(), newTurn.getMyType());

						cont++;
						System.out.println(
								"The turn " + newTurn.getTurno() + " was assigned to the user " + theU.getName());
						toAddATypeToATurn(myType);
						throwException = false;
						// cuando llega a diez veinte treinta cuarenta.. pero no se cambia el char aun,
						// solo el primer numero aumenta
						// y el segundo se reinizializa en cero

					} else if (registeredUsers.get(i).hasActiveTurn() == false) {

						if ((cont) % 10 == 0 && cont != 100 && cont != 200 && cont != 300 && cont != 400 && cont != 500
								&& cont != 600 && cont != 700 && cont != 800 && cont != 900 && cont != 1000
								&& cont != 1100 && cont != 1200 && cont != 1300 && cont != 1400 && cont != 1500
								&& cont != 1600 && cont != 1700 && cont != 1800 && cont != 1900 && cont != 2000
								&& cont != 2100 && cont != 2200 && cont != 2300 && cont != 2400 && cont != 2500
								&& cont != 2600 && cont != 0 && cont != 1 && cont != 2 && cont != 3 && cont != 4
								&& cont != 5 && cont != 6 && cont != 7 && cont != 8 && cont != 9) {
							Turn temp = turns.get(turns.size() - 1);
							int theChar = temp.getLetter();
							int theNumber1 = temp.getFirstNumber() + 1;
							int theNumber2 = 0;
							LocalTime turnHour = LocalTime.now();
							LocalDate turnDate = LocalDate.now();
							Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1,
									theNumber2, (char) theChar, true, false, myType);
							turns.add(newTurn);
							User theUser = toSearchUser(nameUser);
							User theU = serachByBinariMethodDocumentNumber(documentNumber);
							newTurn.setUser(theU);
							theU.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
									newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(),
									newTurn.getMyType());

							cont++;
							toAddATypeToATurn(myType);

							throwException = false;

							System.out.println(
									"The turn " + newTurn.getTurno() + " was assigned to the user " + theU.getName());

						} else if (cont == 100 || cont == 200 || cont == 300 || cont == 400 || cont == 500
								|| cont == 600 || cont == 700 || cont == 800 || cont == 900 || cont == 1000
								|| cont == 1100 || cont == 1200 || cont == 1300 || cont == 1400 || cont == 1500
								|| cont == 1600 || cont == 1700 || cont == 1800 || cont == 1900 || cont == 2000
								|| cont == 2100 || cont == 2200 || cont == 2300 || cont == 2400 || cont == 2500
								|| cont == 2600) {
							Turn temp = turns.get(turns.size() - 1);
							int theChar = temp.getLetter() + 1;
							int theNumber1 = 0;
							int theNumber2 = 0;
							LocalTime turnHour = LocalTime.now();
							LocalDate turnDate = LocalDate.now();
							Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1,
									theNumber2, (char) theChar, true, false, myType);
							turns.add(newTurn);
							User theUser = toSearchUser(nameUser);
							User theU = serachByBinariMethodDocumentNumber(documentNumber);
							newTurn.setUser(theU);
							theU.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
									newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(),
									newTurn.getMyType());

							throwException = false;

							cont++;
							toAddATypeToATurn(myType);

							System.out.println(
									"The turn " + newTurn.getTurno() + " was assigned to the user " + theU.getName());
						} else {
							Turn temp = turns.get(turns.size() - 1);
							int theChar = temp.getLetter();
							int theNumber1 = temp.getFirstNumber();
							int theNumber2 = temp.getSecondNumber() + 1;
							LocalTime turnHour = LocalTime.now();
							LocalDate turnDate = LocalDate.now();
							Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1,
									theNumber2, (char) theChar, true, turnHour, turnDate, false, myType);
							turns.add(newTurn);
							User theUser = toSearchUser(nameUser);
							User theU = serachByBinariMethodDocumentNumber(documentNumber);
							newTurn.setUser(theU);
							theU.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
									newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(),
									newTurn.getMyType());

							cont++;
							toAddATypeToATurn(myType);

							throwException = false;

							System.out.println(
									"The turn " + newTurn.getTurno() + " was assigned to the user " + theU.getName());

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
		} else {
			System.out.println("There are no turn's types yet. You should first create a new one");
		}
	}

	public void toGiveATurnToAnUser3(String nameUser, String documentNumber, TurnType myType)
			throws UserNotRegisteredYet, TurnsTypeNonExistent {
		boolean throwException = true;

//		//ORDENA A LOS USUARIOS
//		orderUsersByBubbleSort();
		// VERIFICA PRIMERO SI HAY TIPOS DE TURNOS PARA QUE CUANDO CREE LOS TURNOS, LOS
		// CREE CON LOS YA ESPECIFICADOS
		if (turnsTypes.size() != 0) {

			User asked = serachByBinariMethodDocumentNumber(documentNumber);

			if (asked != null) {
//				if (registeredUsers.get(i).getName().equals(nameUser)
//						&& registeredUsers.get(i).getDocumentNumber().equals(documentNumber)) {
				if (turns.size() == 0) {
					// si no hay turnos todavia, se crean desde cero
					int theChar = 65;
					int theNumber1 = 0;
					int theNumber2 = 0;
					LocalTime turnHour = LocalTime.now();
					LocalDate turnDate = LocalDate.now();
					Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
							(char) theChar, true, false, myType);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(), newTurn.getMyType());

					cont++;
					System.out.println(
							"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());
					toAddATypeToATurn(myType);
					throwException = false;
					// cuando llega a diez veinte treinta cuarenta.. pero no se cambia el char aun,
					// solo el primer numero aumenta
					// y el segundo se reinizializa en cero

				} else if (asked.hasActiveTurn() == false) {

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
								(char) theChar, true, false, myType);
						turns.add(newTurn);
						User theUser = toSearchUser(nameUser);
						newTurn.setUser(theUser);
						theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
								newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(), newTurn.getMyType());

						cont++;
						toAddATypeToATurn(myType);

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
								(char) theChar, true, false, myType);
						turns.add(newTurn);
						User theUser = toSearchUser(nameUser);
						newTurn.setUser(theUser);
						theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
								newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(), newTurn.getMyType());

						throwException = false;

						cont++;
						toAddATypeToATurn(myType);

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
								(char) theChar, true, turnHour, turnDate, false, myType);
						turns.add(newTurn);
						User theUser = toSearchUser(nameUser);
						newTurn.setUser(theUser);
						theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
								newTurn.getLetter(), newTurn.getStatus(), newTurn.isAttended(), newTurn.getMyType());

						cont++;
						toAddATypeToATurn(myType);

						throwException = false;

						System.out.println(
								"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());

					}

				} else {
					System.out.println("The user " + asked.getName()
							+ " has an active turn that hasn't been attended yet. Therefore, this user cannot receive another turn 'till the turn is attended");
					throwException = false;

				}
//				}

			}

			if (throwException == true) {
				throw new UserNotRegisteredYet(nameUser);
			}
		} else {
			System.out.println("There are no turn's types yet. You should first create a new one");
		}
	}

	/**
	 * 
	 */

	public boolean toVerifyIfThereAreTurnsTypes() {
		boolean thereAre = false;

		if (turnsTypes.size() != 0) {
			thereAre = true;
		} else {
			thereAre = false;
		}
		return thereAre;
	}

	/**
	 * METODO BURBUJA USUARIOS
	 * 
	 */

	public void orderUsersByBubbleSort() {
		for (int i = 0; i < registeredUsers.size(); i++) {
			for (int j = 0; j < registeredUsers.size() - 1; j++) {
				if (registeredUsers.get(j).compareTo(registeredUsers.get(j + 1)) > 0) {
					User aux = registeredUsers.get(j);
					registeredUsers.set(j, registeredUsers.get(j + 1));
					registeredUsers.set(j + 1, aux);
				}
			}
		}
	}

	/**
	 * METODO PARA MOSTAR USUARIOS REGISTRADOS
	 * 
	 */
	public String showRegisteredUsers() {
		String msj = "";
		for (int i = 0; i < registeredUsers.size(); i++) {
			msj += i + 1 + "." + registeredUsers.get(i).getName() + "\n";
		}
		return msj;
	}

	/**
	 * BUSQUEDA BINARIA POR NOMBRE
	 */

	public User searchByBinariMethodName(String name) {
		orderUsersByBubbleSort();
		User theUser = null;

		int start = 0;
		int thefinal = registeredUsers.size() - 1;
		int middle = 0;
		boolean found = false;
		while (start <= thefinal && !found) {
			middle = (thefinal + start) / 2;
			if (registeredUsers.get(middle).getName().compareTo(name) > 0) {
				thefinal = middle - 1;
			} else if (registeredUsers.get(middle).getName().compareTo(name) < 0) {
				start = middle + 1;
			} else if (registeredUsers.get(middle).getName().compareTo(name) == 0) {
				found = true;
				theUser = registeredUsers.get(middle);
			}
		}
		return theUser;
	}

	/**
	 * BUSQUEDA BINARIA POR ID
	 */
	public User serachByBinariMethodDocumentNumber(String documentNumber) {
		orderUsersByBubbleSort();
		User theUser = null;
		int start = 0;
		int thefinal = registeredUsers.size() - 1;
		int middle = 0;
		boolean found = false;
		while (start <= thefinal && !found) {
			middle = (thefinal + start) / 2;
			if (registeredUsers.get(middle).getDocumentNumber().compareTo(documentNumber) > 0) {
				thefinal = middle - 1;
			} else if (registeredUsers.get(middle).getDocumentNumber().compareTo(documentNumber) < 0) {
				start = middle + 1;
			} else if (registeredUsers.get(middle).getDocumentNumber().compareTo(documentNumber) == 0) {
				found = true;
				theUser = registeredUsers.get(middle);
			}
		}
		return theUser;
	}

	/**
	 * ACTUALIZAR LA HORA DEL SISTEMA MANUALMENTE
	 *
	 */
	public void actualiceSystemsHour(String increment) {
		if (verifyIfUserHourIsBigger(increment) == true) {
			int posicion;
			posicion = increment.indexOf(":");
			int hourUser = Integer.parseInt(increment.substring(0, posicion));
			increment = increment.substring(posicion + 1);
			posicion = increment.indexOf(":");
			int minuteUser = Integer.parseInt(increment.substring(0, posicion));
			increment = increment.substring(posicion + 1);
			int secondsUser = Integer.parseInt(increment);

			int hour = myDate.getCurrentHour();
			int minute = myDate.getCurrentMinute();
			int second = myDate.getCurrentSecond();

			int hourDifference = hourUser - hour;
			int minuteDifference = minuteUser - minute;
			int secondsDifference = secondsUser - second;

			int hourToGive = hour + hourDifference;
			int minuteToGive = minute + minuteDifference;
			int secondsToGive = second + secondsDifference;

			if (secondsToGive > 60) {
				minuteToGive = minuteToGive + 1;
				secondsToGive = secondsDifference;
			}

			System.out.println("Actual's system's time: " + hourToGive + ":" + minuteToGive + ":" + secondsToGive);
			System.out.println("ACTUAL'S SYSTEM'S TIME: " + (myDate.getCurrentHour() + hourDifference) + ":"
					+ (myDate.getCurrentMinute() + minuteDifference) + ":"
					+ (myDate.getCurrentSecond() + secondsDifference));

		} else {
			System.out.println("You should enter a time bigger than the actual");
		}
	}

	/**
	 * VERIFICAR SI INGRESA UNA HORA MAYOR A LA ACTUAL
	 */
	public boolean verifyIfUserHourIsBigger(String time) {
		boolean isBigger = false;
		int posicion;
		posicion = time.indexOf(":");
		int hourUser = Integer.parseInt(time.substring(0, posicion));
		time = time.substring(posicion + 1);
		posicion = time.indexOf(":");
		int minuteUser = Integer.parseInt(time.substring(0, posicion));
		time = time.substring(posicion + 1);
		int secondsUser = Integer.parseInt(time);

		int hour = myDate.getCurrentHour();
		int minute = myDate.getCurrentMinute();
		int second = myDate.getCurrentSecond();
		if (hourUser > hour) {
			isBigger = true;
		} else if (hourUser == hour) {
			if (minuteUser > minute) {
				isBigger = true;
			} else if (minute == minuteUser) {
				if (secondsUser > second) {
					isBigger = true;

				} else if (second == secondsUser) {
					isBigger = true;
				}
			}
		}
		return isBigger;
	}

	/**
	 * EL USUARIO MANEJA LA HORA MANUAL
	 */
	public boolean manuallyHour() {
		boolean yes = true;
		return yes;
	}

	/**
	 * MOSTRAR HORA ACTUAL CON INCREMENTO
	 */
//
//	public String showActualHourIncrement() {
//		String msj = "Actual system's date: " + myDate.getCurrentDay() + "/" + myDate.getCurrentMonth() + "/"
//				+ myDate.getCurrentYear() + "\n";
//	}

	public String actualiceSystemsHour2(String increment) {
		String msj = "";
		if (verifyIfUserHourIsBigger(increment) == true) {
			int posicion;
			posicion = increment.indexOf(":");
			int hourUser = Integer.parseInt(increment.substring(0, posicion));
			increment = increment.substring(posicion + 1);
			posicion = increment.indexOf(":");
			int minuteUser = Integer.parseInt(increment.substring(0, posicion));
			increment = increment.substring(posicion + 1);
			int secondsUser = Integer.parseInt(increment);

			int hour = myDate.getCurrentHour();
			int minute = myDate.getCurrentMinute();
			int second = myDate.getCurrentSecond();

			int hourDifference = hourUser - hour;
			int minuteDifference = minuteUser - minute;
			int secondsDifference = secondsUser - second;

			int hourToGive = hour + hourDifference;
			int minuteToGive = minute + minuteDifference;
			int secondsToGive = second + secondsDifference;

			if (secondsToGive > 60) {
				minuteToGive = minuteToGive + 1;
				secondsToGive = secondsDifference;
			}

//			System.out.println("Actual's system's time: " + hourToGive + ":" + minuteToGive + ":" + secondsToGive);
//			System.out.println("ACTUAL'S SYSTEM'S TIME: " + (myDate.getCurrentHour() + hourDifference) + ":"
//					+ (myDate.getCurrentMinute() + minuteDifference) + ":"+(myDate.getCurrentSecond()+secondsDifference));

			msj += "ACTUAL'S SYSTEM'S TIME: " + (myDate.getCurrentHour() + hourDifference) + ":"
					+ (myDate.getCurrentMinute() + minuteDifference) + ":"
					+ (myDate.getCurrentSecond() + secondsDifference);
		} else {
//			System.out.println("You should enter a time bigger than the actual");
		}
		return msj;
	}

	public Date getMyDate() {
		return myDate;
	}

	public void setMyDate(Date myDate) {
		this.myDate = myDate;
	}

	/**
	 * ATENDER TURNOS!!!
	 */
	public void toAttendTurns() {

		int contadorturnossinatender = 0;
		int beggining = 0;
		int contador = 0;
		double waitSeconds = 0.25;
		double horaActual = myDate.getCurrentHour();
		double minutoActual = myDate.getCurrentMinute();
		double segundoActual = myDate.getCurrentSecond();
		for (int i = 0; i < turns.size(); i++) {

			if (turns.get(i).getStatus() != true) {
				contadorturnossinatender++;
				contador++;
			}
		}
		int dondeempezar = turns.size() - contador;

//		int horaprimerturnosinatender= Integer.parseInt(turns.get(dondeempezar).getTurnHour());
//		while()

	}

	/**
	 * SERIALIZAR
	 * 
	 */
	public void serializableUser() {
		try {

			File fl = new File(archives);

			FileOutputStream fos = new FileOutputStream(fl);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(registeredUsers);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * DESERIALIZAR!!
	 */
	public void deserializableUser() {
		ArrayList<User> usersitos;
		try {
			File fl = new File(archives);

			FileInputStream fis = new FileInputStream(fl);
			ObjectInputStream ois = new ObjectInputStream(fis);

			usersitos = (ArrayList<User>) ois.readObject();

			setRegisteredUsers(usersitos);
			ois.close();

		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.getMessage();
		}
	}

	/**
	 * CARGAR USUARIOS FROM ARCHIVO DE TEXTO
	 * 
	 */

	public void loadUsers(int cantidadAGenerar) {
		File fl = new File("data/users2.csv");
		try {
			FileReader fr = new FileReader(fl);
			BufferedReader br = new BufferedReader(fr);
			int contador = 0;
			String line = br.readLine();
			int cantidad=0;
			while (line != null && cantidad<cantidadAGenerar) {
				String[] a = line.split(",");
				User u = new User(Integer.parseInt(a[0]), a[1], a[2], a[3], a[4], DocumentType.CITIZENSHIP_CARD,
						"" + contador);
				addRegisteredUser(u.getId(), u.getName(), u.getLastName(), u.getPhone(), u.getAddress(),
						u.getDocumentType(), u.getDocumentNumber());
				contador++;
				cantidad++;
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (UserAlreadyExistsException e) {
			e.getMessage();
		} catch (IncompleteInformationException e) {
			e.getMessage();
		}
	}

	public void loadUsers2() {
		File fl = new File("data/users2.csv");
		try {
			FileReader fr = new FileReader(fl);
			BufferedReader br = new BufferedReader(fr);
			int contador = 0;
			String line = br.readLine();
			int cantidad=0;
			while (line != null) {
				String[] a = line.split(",");
				User u = new User(Integer.parseInt(a[0]), a[1], a[2], a[3], a[4], DocumentType.CITIZENSHIP_CARD,
						"" + contador);
				addRegisteredUser(u.getId(), u.getName(), u.getLastName(), u.getPhone(), u.getAddress(),
						u.getDocumentType(), u.getDocumentNumber());
				contador++;
				cantidad++;
				line = br.readLine();
			}
			br.close();
			fr.close();
		} catch (FileNotFoundException e) {
			e.getMessage();
		} catch (IOException e) {
			e.getMessage();
		} catch (UserAlreadyExistsException e) {
			e.getMessage();
		} catch (IncompleteInformationException e) {
			e.getMessage();
		}
	}


	/**
	 * GENERAR UN REPORTE
	 */
	public void toGenerateReport(String filename) throws IOException, UserNotFoundException {

		PrintWriter r = new PrintWriter(new File("data/" + filename));

		r.print("REPORT OF THE INFORMATIOR OF THE REGISTERED USERS" + '\n');
		r.print("-------------------------------------------------" + '\n');
		r.print("-------------------------------------------------" + '\n');

		String msj3 = "";
		User u = null;
		for (int i = 0; i < registeredUsers.size(); i++) {
			u = registeredUsers.get(i);

			msj3 = toSearchAnUser(u.getDocumentNumber()) + "\n";
			r.print(msj3);

		}
		r.close();

	}

	public String toGenerateAReportConsole() throws UserNotFoundException {
		String msj = "";
		User u = null;
		for (int i = 0; i < registeredUsers.size(); i++) {
			u = registeredUsers.get(i);

			msj += toSearchAnUser(u.getDocumentNumber()) + "\n";

		}
		return msj;
	}
	
	public void sortByDocumentNumber() {
		DocumentNumberComparator u = new DocumentNumberComparator();

		Collections.sort(registeredUsers,u);
	}
	
	public void sortByPhone() {
		PhoneComparator pc= new PhoneComparator();
		Collections.sort(registeredUsers,pc);
	}
	public void sortByid() {
		idComparator idc= new idComparator();
		Collections.sort(registeredUsers,idc);
	}
	
	public void sortByIdComparator() {
		Comparator<User> cid= new Comparator<User>() {
			@Override
			public int compare(User u1, User u2) {
				int comp=0;
				if(u1.getId()<u2.getId()) {
					comp=-1;
				}else if(u1.getId()>u2.getId()) {
					comp=1;
				}else {
					comp=0;
				}return comp;
			}
		};
		Collections.sort(registeredUsers,cid);
	}
	public void orderBySelectionSort() {
		
		for(int i=0;i< registeredUsers.size();i++) {
			User aux= registeredUsers.get(i);
			int j=i-1;
			while(j>=0 && registeredUsers.get(j).compareTo(aux)>0) {
				registeredUsers.set(j+1, registeredUsers.get(j));
				j= j-1;
			}
			registeredUsers.set(j+1, aux);
		}
	}
	
	public void orderByInsertion() {
		for(int i=0; i< registeredUsers.size();i++) {
			User aux = registeredUsers.get(i);
			int j= i-1;
			while(j>0 && registeredUsers.get(j).compareTo(aux)>0) {
				registeredUsers.set(j+1, registeredUsers.get(j));
				j= j-1;
				
			}
			registeredUsers.set(j+1, aux);
		}
	}

	public String getArchives() {
		return archives;
	}

	public void setArchives(String archives) {
		this.archives = archives;
	}
	

}
