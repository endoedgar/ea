package cs544.exercise23_2.bank.domain;

import java.io.Serializable;

public class Customer implements Serializable {
	private String name;

	public Customer(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

}
