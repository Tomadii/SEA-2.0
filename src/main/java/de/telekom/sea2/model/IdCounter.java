package de.telekom.sea2.model;

public class IdCounter {
	
	private long id; 
	private static long idCounter = 1;
	
	public IdCounter () {
		this.id = idCounter++;
	}

	public long getId() {
		return id;
	}
		
}
