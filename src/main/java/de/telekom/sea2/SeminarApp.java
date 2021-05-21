package de.telekom.sea2;

import java.sql.*;
import java.util.ArrayList;

import de.telekom.sea2.model.IdCounter;
import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;
import de.telekom.sea2.ui.Menu;
	
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
		menu();
//		testdb();
		dbclose();
		
	}
	
	private void dbloader() {
		
		System.out.println("*** Datenbank verbinden ***");
		
		final String DRIVER = "org.mariadb.jdbc.Driver";
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("*************************************");
			System.out.println("* class MariaDB ist nicht vorhanden *");
			System.out.println("*************************************");
			System.out.println(e);
			return;
		}
		try {
			this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seadb","seauser","seapass");
			personsRepository = new PersonsRepository(connection);
			IdCounter.setIdCounter(personsRepository.maxId());
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
	}
	
	private void menu() {
		
		try (Menu menu = new Menu(personsRepository)) {
			
			menu.keepAsking();
			
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
		
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
	
	private void dbclose() {
		
		System.out.println("*** Datenbank trennen ***");
		
		try {
			connection.close();
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
		
	}
	
}
