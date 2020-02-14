package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class User {

	private int id;
	private String name;
	private String lastName;
	private int phone;
	private String address;
	private DocumentType documentType;
	private String documentNumber;
	
	private ArrayList<Turn> turnsUser;

	public User(int id, String name, String lastName, int phone, String address, DocumentType documentType,
			String documentNumber) {

		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.phone = phone;
		this.address = address;
		this.documentType = documentType;
		this.documentNumber = documentNumber;
		turnsUser= new ArrayList<Turn>();
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

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
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
	
	public void addTurn(String turno, int firstNumber, int secondNumber, char letter, boolean status, LocalTime turnHour,
			LocalDate turnDate) {
		Turn newTurn= new Turn(turno, firstNumber, secondNumber, letter, status, turnHour, turnDate);
		turnsUser.add(newTurn);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", lastName=" + lastName + ", phone=" + phone + ", address="
				+ address + ", documentType=" + documentType + ", documentNumber=" + documentNumber + "]";
	}

}
