package de.telekom.sea2.io;

import java.io.BufferedReader;
import java.io.IOException;

import de.telekom.sea2.model.Person;

public class PersonReader {
	
	public Person readPerson(BufferedReader fileln) throws IOException {
		Person person;
		String line = fileln.readLine();
		
		if ( line != null ) {
			if ( line.startsWith("ID") || line.startsWith("Anrede") ) {
				line = fileln.readLine();
			}
			String[] result = line.split(";");
			long id;
			String salutation;
			System.out.println(result[0]);
			if ( result[0].equals("") ) {
				id = 0;
			} else {
				id = Long.parseLong(result[0]);
			}
			if ( result[1].equals("") ) {
				salutation = "NN";
			} else {
				salutation = result[1];
			}
			if ( result.length < 4 ) {
				person = new Person(id, salutation, result[2], "" );
			} else {
				person = new Person(id, salutation, result[2], result[3] );
			}
			return person;
		}
		return null;
	}
		
}
