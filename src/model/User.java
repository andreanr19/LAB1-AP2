package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class User implements Comparable<User>, Serializable{

	private int id;
	private String name;
	private String lastName;
	private String phone;
	private String address;
	private DocumentType documentType;
	private String documentNumber;

	private ArrayList<Turn> turnsUser;
	private static final long serialVersionUID = 1L;

	public User(int id, String name, String lastName, String phone, String address, DocumentType documentType,
			String documentNumber) {

		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		turnsUser = new ArrayList<Turn>();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public String getDocumentNumber() {
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber) {
		this.documentNumber = documentNumber;
	}

	public ArrayList<Turn> getTurnsUser() {
		return turnsUser;
	}

	public void setTurnsUser(ArrayList<Turn> turnsUser) {
		this.turnsUser = turnsUser;
	}

	public void addTurn(String turno, int firstNumber, int secondNumber, char letter, boolean status,
			LocalTime turnHour, LocalDate turnDate, boolean attended, TurnType myType) {
		Turn newTurn = new Turn(turno, firstNumber, secondNumber, letter, status, turnHour, turnDate, attended, myType);
		turnsUser.add(newTurn);
	}

	public void addTurn(String turno, int firstNumber, int secondNumber, char letter, boolean status, boolean attended,
			TurnType myType) {
		Turn newTurn = new Turn(turno, firstNumber, secondNumber, letter, status, attended, myType);
		turnsUser.add(newTurn);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", phone=" + phone + ", address="
				+ address + ", documentType=" + documentType + ", documentNumber=" + documentNumber + "]";
	}

	public boolean hasActiveTurn() {
		boolean hasActive = false;
		for (int i = 0; i < turnsUser.size(); i++) {
			// getStatus==false significa que no lo han atendido, osea que tiene activo un
			// turno
			if (turnsUser.get(i).getStatus() == true) {
				hasActive = true;
			}
		}
		return hasActive;
	}

	public void toAttendAnUser(boolean wasThere) {
		boolean verify = false;

		// el true del status significa que no han pasado por �l en el llamado
		for (int i = 0; i < turnsUser.size() && !verify; i++) {
			if (turnsUser.get(i).getStatus() == true && turnsUser.get(i).isAttended() == false) {
				if (wasThere == true) {
					turnsUser.get(i).setStatus(false);
					turnsUser.get(i).setAttended(true);
					verify = true;
					System.out.println("The turn " + turnsUser.get(i).getTurno() + " of the user "
							+ turnsUser.get(i).getUser().getName() + " has been attended.");
				} else {
					turnsUser.get(i).setStatus(false);
					turnsUser.get(i).setAttended(false);

					verify = true;
					System.out.println("The turn " + turnsUser.get(i).getTurno() + " of the user "
							+ turnsUser.get(i).getUser().getName()
							+ " hasn't been attended because the user wasn't there");
				}
			}

		}

	}

	public boolean getTheDueño(String turn) {
		Turn theturn = null;
		boolean yes = false;
		for (int i = 0; i < turnsUser.size() && !yes; i++) {
			if (turnsUser.get(i).getTurno().equals(turn)) {
				theturn = turnsUser.get(i);
				yes = true;
			}
		}
		return yes;
	}

	public Turn getTheTurn(String nameTurn) {
		Turn theTurn = null;
		boolean yes = false;
		for (int i = 0; i < turnsUser.size() && !yes; i++) {
			if (turnsUser.get(i).getTurno().equals(nameTurn)) {
				theTurn = turnsUser.get(i);
				yes = true;
			}
		}
		return theTurn;

	}

	@Override
	public int compareTo(User u) {
		
		int comparator=0;
		if(this.getName().compareToIgnoreCase(u.getName())>0) {
			comparator=1;
		}else if(this.getName().compareToIgnoreCase(u.getName())<0) {
			comparator=-1;
		}else {
			comparator=0;
		}
		return comparator;
	}

}
