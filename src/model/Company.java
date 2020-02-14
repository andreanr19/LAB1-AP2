package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import exceptions.IncompleteInformationException;
import exceptions.UserAlreadyExistsException;
import exceptions.UserNotRegisteredYet;

public class Company {

	private ArrayList<Turn> turns;
	private ArrayList<User> registeredUsers;

	public Company() {

		turns = new ArrayList<Turn>();
		registeredUsers = new ArrayList<User>();
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

	
	// add turn
	public void addTurn(String turno, char letter, int number, int snumber, boolean status, LocalTime turnHour,
			LocalDate turnDate) {
		Turn newturn = new Turn(turno, number, snumber, letter, status, turnHour, turnDate);
		turns.add(newturn);
	}
	
	
	//andrea es ñeraa

	// add user
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
			boolean found=false;
			for (int i = 0; i < registeredUsers.size() &&!found; i++) {
				if (registeredUsers.get(i).getDocumentNumber().equals(documentNumber)
						&& registeredUsers.get(i).getDocumentType().equals(documentType)) {
					throw new UserAlreadyExistsException(name, documentNumber);
				} else {
					registeredUsers.add(newUser);
					found= true;

					System.out
							.println("The user " + name + lastName + " was succesfully registered in the data system");
				}
			}
		}
	}

	// to give a turn to an user
	public void toGiveATurnToAnUser(String nameUser) throws UserNotRegisteredYet {
		for (int i = 0; i < registeredUsers.size(); i++) {
			if (registeredUsers.get(i).getName().equals(nameUser)) {
				int cont = 0;
				if (turns.size() == 0) {
					// si no hay turnos todavia, se crean desde cero
					int theChar = 65;
					int theNumber1 = 0;
					int theNumber2 = 0;
					LocalTime turnHour = LocalTime.now();
					LocalDate turnDate = LocalDate.now();
					Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
							(char) theChar, true, turnHour, turnDate);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate());

					cont++;
					System.out.println(
							"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());

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
							(char) theChar, true, turnHour, turnDate);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate());

					cont++;
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
							(char) theChar, true, turnHour, turnDate);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate());

					cont++;
					System.out.println(
							"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());
				} else {
					Turn temp = turns.get(turns.size() - 1);
					int theChar = temp.getLetter();
					int theNumber1 = temp.getFirstNumber();
					int theNumber2 = temp.getFirstNumber() + 1;
					LocalTime turnHour = LocalTime.now();
					LocalDate turnDate = LocalDate.now();
					Turn newTurn = new Turn("" + (char) theChar + theNumber1 + theNumber2, theNumber1, theNumber2,
							(char) theChar, true, turnHour, turnDate);
					turns.add(newTurn);
					User theUser = toSearchUser(nameUser);
					newTurn.setUser(theUser);
					theUser.addTurn(newTurn.getTurno(), newTurn.getFirstNumber(), newTurn.getSecondNumber(),
							newTurn.getLetter(), newTurn.getStatus(), newTurn.getTurnHour(), newTurn.getTurnDate());

					cont++;
					System.out.println(
							"The turn " + newTurn.getTurno() + " was assigned to the user " + theUser.getName());

				}
			} else {
//				throw new UserNotRegisteredYet(nameUser);
			}
		}
	}

	// buscar usuario
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
}
