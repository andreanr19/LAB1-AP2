package model;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.GregorianCalendar;

public class Date implements Serializable{

	
	private GregorianCalendar gc;
	private LocalTime time;
	private int currentYear;
	private int currentMonth;
	private int currentDay;
	
	private int currentMinute;
	private int currentHour;
	private int currentSecond;
	
	private static final long serialVersionUID = 1L;
	public Date() {
		gc=new GregorianCalendar();
		currentYear = gc.get(GregorianCalendar.YEAR);
		currentMonth= gc.get(GregorianCalendar.MONTH);
		currentDay= gc.get(GregorianCalendar.DAY_OF_MONTH);
		
		time=LocalTime.now();
		
		currentMinute = time.getMinute();
		currentHour = time.getHour();
		currentSecond = time.getSecond();
	}


	public GregorianCalendar getGc() {
		return gc;
	}


	public void setGc(GregorianCalendar gc) {
		this.gc = gc;
	}


	public LocalTime getTime() {
		return time;
	}


	public void setTime(LocalTime time) {
		this.time = time;
	}


	public int getCurrentYear() {
		currentYear= gc.get(GregorianCalendar.YEAR);
		return currentYear;
	}


	public int getYearTurn() {
		return currentYear;
	}
	
	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}


	public int getCurrentMonth() {
		
		currentMonth=gc.get(GregorianCalendar.MONTH);
		return currentMonth;
	}

	public int getMonthTurn() {
		return currentMonth;
	}

	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}


	public int getCurrentDay() {
		
		currentDay= gc.get(GregorianCalendar.DAY_OF_MONTH);

		return currentDay;
	}
	public int getDayTurn() {
		return currentDay;
	}


	public void setCurrentDay(int currentDay) {
		this.currentDay = currentDay;
	}


	public int getCurrentMinute() {
		time=LocalTime.now();
		currentMinute = time.getMinute();
		return currentMinute;
	}
	public int getMinuteTurn() {
		return currentMinute;
	}


	public void setCurrentMinute(int currentMinute) {
		this.currentMinute = currentMinute;
	}


	public int getCurrentHour() {
		time= LocalTime.now();
		currentHour= time.getHour();
		return currentHour;
	}

	public int getHourTurn() {
		return currentHour;
	}

	public void setCurrentHour(int currentHour) {
		this.currentHour = currentHour;
	}


	public int getCurrentSecond() {
		LocalTime.now();
		currentSecond= time.getSecond();
		return currentSecond;
	}
	public int getSecondTurn() {
		return currentSecond;
	}


	public void setCurrentSecond(int currentSecond) {
		this.currentSecond = currentSecond;
	}
	
	
}
