package edu.miu.cs.cs544.labs.lab01.domain;

public class Fortune {
	private int id;
	private String text;
	
	public Fortune(int id, String text) {
		super();
		this.id = id;
		this.text = text;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}
