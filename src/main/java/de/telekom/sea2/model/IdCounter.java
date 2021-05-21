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
	
	public void setId(long id) {
		this.id = id;
		if (idCounter < id) {
			idCounter = id;
		}
	}
	
	public static void setIdCounter(long id) {
		if (idCounter <= id) {
			idCounter = ++id;
		}
	}
}
