package de.telekom.sea2.io;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import de.telekom.sea2.model.Person;

public class ListReader implements Closeable {

	private String path = "/home/thomas/Liste.sea";
	private FileReader file;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	public ArrayList<Person> readList() throws IOException {
		ArrayList<Person> persons = new ArrayList<Person>();
		Person person = new Person();
		file = new FileReader( path );
		BufferedReader fileln = new BufferedReader(file);
		PersonReader pr = new PersonReader();
		
		do {
			person = pr.readPerson(fileln);
			if (person != null) {
				persons.add(person);
			}
		} while (person != null);
		return persons;
	}
	
	@Override
	public void close() throws IOException {
		file.close();
	}
}
