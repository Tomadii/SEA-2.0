package de.telekom.sea2;

import java.sql.*;

import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;

public class SeminarApp {
	
	public static SeminarApp theInstance;
	private Connection connection;
	private ResultSet resultSet;
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
//		test();
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
	
	private void test() {
		
		System.out.println("*** Start Test ***");
		
		Person thomas = new Person();
		thomas.setFirstname("Thomas");
		thomas.setLastname("Horchem");
		thomas.setSalutation("mann");
		
		Person bianca = new Person("F", "Bianca", "Horchem");
		
		System.out.println(thomas.getId() + " " + thomas.getSalutation() + " " + thomas.getFirstname() + " " + thomas.getLastname());
		System.out.println(bianca.getId() + " " + bianca.getSalutation() + " " + bianca.getFirstname() + " " + bianca.getLastname());
		
		System.out.println("*** Test Ende ***");
	}
	
	private void testdb() throws ClassNotFoundException, SQLException {
		
		System.out.println("*** Start Test DB ***");
//		PersonsRepository pr = new PersonsRepository(connection);
		
		// create(Person)
		System.out.println("--- create(Person) ---");
		
//		person = new Person(5L, "m", "Bianca", "Horchem");
//		person.setId(5L);
//		System.out.println("Person " + person + " angelegt = " + personsRepository.create(person));
//		person = new Person("M", "Thomas", "Horchem");
//		Person delPerson = person;
//		person.setId(6L);
//		System.out.println("Person " + person + " angelegt = " + personsRepository.create(person));
		
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
//		PersonsRepository pr = new PersonsRepository(connection);
//		this.resultSet = pr.getAll();
//		System.out.println(this.resultSet);
//		
//		while(resultSet.next()) {
//			System.out.print("ID " + resultSet.getLong(1) + ", ");
//			System.out.print("Anrede " + resultSet.getShort(2) + ", ");
//			System.out.print("Vorname " + resultSet.getString(3) + ", ");
//			System.out.println("Nachname " + resultSet.getString(4));
//		}
		
		// deleteId(long)
		System.out.println("--- deleteId(long) ---");
		
		long del = 5L;
//		System.out.println("Person " + del + " gelöscht = " + personsRepository.deleteId(del));
		
		// deletePerson(Person)
		System.out.println("--- deletePerson(Person) ---");
		
//		System.out.println("Person " + delPerson + " gelöscht = " + personsRepository.deletePerson(delPerson));
		
		// deleteAll
		System.out.println("--- deleteAll() ---");
		
//		System.out.println("alle Personen gelöscht = " + personsRepository.deleteAll());
		
		System.out.println("*** Test DB Ende ***");
		
	}
}
