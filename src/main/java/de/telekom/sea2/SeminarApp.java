package de.telekom.sea2;

import de.telekom.sea2.model.Person;

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
		
//		menu();
		test();
		
	}
	
	private void test() {
		
		System.out.println("*** Start Menu ***");
		
		Person thomas = new Person();
		thomas.setFirstname("Thomas");
		thomas.setLastname("Horchem");
		thomas.setSalutation("mann");
		
		Person bianca = new Person("F", "Bianca", "Horchem");
		
		System.out.println(thomas.getSalutation() + " " + thomas.getFirstname() + " " + thomas.getLastname());
		System.out.println(bianca.getSalutation() + " " + bianca.getFirstname() + " " + bianca.getLastname());
		
		
	}
}
