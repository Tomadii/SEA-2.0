package de.telekom.sea2;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

	public static void main(String[] args) throws SQLException, IOException {
    	SeminarApp app = SeminarApp.getRootApp();

    	try { 
    		app.run(args);
    	} catch (Exception ex) {
    		System.out.println(ex);
    	}
    	
    }

}
