package de.telekom.sea2;

public class SeminarApp {
	
	public static SeminarApp theInstance;
	
	private SeminarApp() {
	}
	
	public static SeminarApp getRootApp() {
		if (theInstance == null) {
			theInstance = new SeminarApp();
		}
		return theInstance;
	}
	
	public void run(String[] args) {
		
		menu();
		
	}
	
	private void menu() {
		
		System.out.println("*** Start Menu ***");
		
	}
}
