package de.telekom.sea2;

import java.sql.*;
import java.util.ArrayList;

import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;

public class SeminarApp {
	
	public static SeminarApp theInstance;
	private Connection connection;
	private PersonsRepository personsRepository;
	private Person person;
	
	private SeminarApp() {
	}
	
	public static SeminarApp getRootApp() {
		if (theInstance == null) {
			theInstance = new SeminarApp();
		}
		return theInstance;
	}
	
	public void run(String[] args) throws ClassNotFoundException, SQLException {
		
		dbloader();
//		menu();
		testdb();
		
	}
	
	private void dbloader() throws ClassNotFoundException, SQLException {
		
		System.out.println("*** Datenbank verbinden ***");
		
		final String DRIVER = "org.mariadb.jdbc.Driver";
		Class.forName(DRIVER);
		this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seadb","seauser","seapass");
		personsRepository = new PersonsRepository(connection);
		
		System.out.println("*** Datenbank verbunden ***");
	}
		
	private void testdb() throws ClassNotFoundException, SQLException {
		
		System.out.println("*** Start Test DB ***");
		
		// deleteAll
		System.out.println("--- deleteAll() ---");
				
		System.out.println("alle Personen gelöscht = " + personsRepository.deleteAll());
				
		// create(Person)
		System.out.println("--- create(Person) ---");
		
		person = new Person(5L, "m", "Bianca", "Horchem");
		person.setId(5L);
		System.out.println("Person " + person + " angelegt = " + personsRepository.create(person));
		person = new Person("M", "Thomas", "Horchem");
		Person delPerson = person;
		person.setId(6L);
		System.out.println("Person " + person + " angelegt = " + personsRepository.create(person));
		
		// get(long)
		System.out.println("--- get(long) ---");
		
		person = personsRepository.get(5L);
		System.out.println(person.getId() + " " + person.getSalutation() + " " + person.getFirstname() + " " + person.getLastname());
		person = personsRepository.get(6L);
		System.out.println(person.getId() + " " + person.getSalutation() + " " + person.getFirstname() + " " + person.getLastname());
		
		// update
		System.out.println("--- update(Person) ---");
		
		person = new Person(5L, "F", "Bianca", "Horchem");
		System.out.println("Person " + person + " korrigiert = " + personsRepository.update(person));
		
		
		// getAll()
		System.out.println("--- getAll ---");
		
		ArrayList<Person> persons = personsRepository.getAll();
		for (int i = 0; i < persons.size(); i++) {
			System.out.print("ID = " + persons.get(i).getId());
			System.out.print(", Anrede = " + persons.get(i).getSalutation());
			System.out.print(", Vorname = " + persons.get(i).getFirstname());
			System.out.println(", Nachname = " + persons.get(i).getLastname());
		}		
		
		// deleteId(long)
		System.out.println("--- deleteId(long) ---");
		
		long del = 5L;
//		System.out.println("Person " + del + " gelöscht = " + personsRepository.deleteId(del));
		
		// deletePerson(Person)
		System.out.println("--- deletePerson(Person) ---");
		
//		System.out.println("Person " + delPerson + " gelöscht = " + personsRepository.deletePerson(delPerson));
		
				
		System.out.println("*** Test DB Ende ***");
		
	}
}
