package de.telekom.sea2.io;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import de.telekom.sea2.model.Person;

public class ListWriter {

	private String path = "/home/thomas/Liste.sea";
	FileWriter file;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public void writeList(ArrayList<Person> persons) throws IOException {
		file = new FileWriter( path );
		file.write("ID;Anrede;Vorname;Nachname\n");
//		if ( persons != null ) {
//			for ( int i = 0 ; i < persons.size() ; i++ ) {
//				file.write(persons.get(i).getId() + ";");
//				file.write(persons.get(i).getSalutation() + ";");
//				file.write(persons.get(i).getFirstname() + ";");
//				file.write(persons.get(i).getLastname() + "/n");
//			}
//		}
		System.out.println("Test");
	}
	
}
