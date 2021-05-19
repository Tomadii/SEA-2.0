package de.telekom.sea2;

public class Main {

	public static void main(String[] args) {
    	SeminarApp app = SeminarApp.getRootApp();

    	try { 
    		app.run(args);
    	} catch (Exception ex) {
    		System.out.println(ex);
    	}
    	
    }

}
