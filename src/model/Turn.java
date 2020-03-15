package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public class Turn implements Serializable{

	private String turno;
	private int firstNumber;
	private int secondNumber;
	private char letter;
	private boolean status;
	private LocalTime turnHour;
	private LocalDate turnDate;
	private boolean attended;

	
	// relacion
	private TurnType myType;
	// relacion
	private Date myTurnDate;
//	relacion
	private User user;
	private static final long serialVersionUID = 1L;

	public Turn(String turno, int firstNumber, int secondNumber, char letter, boolean status, LocalTime turnHour,
			LocalDate turnDate, boolean attended, TurnType myType) {
		this.turno = turno;
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.letter = letter;
		this.status = status;
		this.turnHour = turnHour;
		this.turnDate = turnDate;
		this.attended = false;
		this.myType = myType;
	}

	public Turn(String turno, int firstNumber, int secondNumber, char letter, boolean status, boolean attended,
			TurnType myType) {
		this.turno = turno;
		this.firstNumber = firstNumber;
		this.secondNumber = secondNumber;
		this.letter = letter;
		this.status = status;
		this.attended = false;
		this.myType = myType;

		myTurnDate = new Date();
		
	}

	public TurnType getMyType() {
		return myType;
	}

	public void setMyType(TurnType myType) {
		this.myType = myType;
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

	public boolean isAttended() {
		return attended;
	}

	public void setAttended(boolean attended) {
		this.attended = attended;
	}

	public String showMyDate() {
		String msj= "Date of the turn: " + myTurnDate.getDayTurn()+"/"+myTurnDate.getMonthTurn()+"/"+myTurnDate.getYearTurn()+"\n";
		msj+= "Hour of the creation of the turn: " + myTurnDate.getHourTurn()+":"+ myTurnDate.getMinuteTurn()+":"+myTurnDate.getSecondTurn();
		
		
		return msj;
	}
}
