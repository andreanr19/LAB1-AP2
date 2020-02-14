package model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Turn {

	private String turno;
	private int firstNumber;
	private int secondNumber;
	private char letter;
	private boolean status;
	private LocalTime turnHour;
	private LocalDate turnDate;

//	relacion
	private User user;

	public Turn(String turno, int firstNumber, int secondNumber, char letter, boolean status, LocalTime turnHour,
			LocalDate turnDate) {
		this.turno = turno;
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.letter = letter;
		this.status = status;
		this.turnHour = turnHour;
		this.turnDate = turnDate;

	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getTurno() {
		return turno;
	}

	public void setTurno(String turno) {
		this.turno = turno;
	}

	public int getFirstNumber() {
		return firstNumber;
	}

	public void setFirstNumber(int firstNumber) {
		this.firstNumber = firstNumber;
	}

	public char getLetter() {
		return letter;
	}

	public void setLetter(char letter) {
		this.letter = letter;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public int getSecondNumber() {
		return secondNumber;
	}

	public void setSecondNumber(int secondNumber) {
		this.secondNumber = secondNumber;
	}

	public LocalTime getTurnHour() {
		return turnHour;
	}

	public void setTurnHour(LocalTime turnHour) {
		this.turnHour = turnHour;
	}

	public LocalDate getTurnDate() {
		return turnDate;
	}

	public void setTurnDate(LocalDate turnDate) {
		this.turnDate = turnDate;
	}
	

}
