package model;

import java.io.Serializable;

public class TurnType implements Serializable{

	private String name;
	private float duration;
	private int id;
	private static final long serialVersionUID = 1L;
	public TurnType(String name, float duration) {
		this.name = name;
		this.duration = duration;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	
	
}
