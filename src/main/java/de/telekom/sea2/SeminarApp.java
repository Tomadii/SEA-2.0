package de.telekom.sea2;

import java.sql.*;

import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;

public class SeminarApp {
	
	public static SeminarApp theInstance;
	private Connection connection;
	private ResultSet resultSet;
	
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
		test();
		testdb();
		
	}
	
	private void dbloader() throws ClassNotFoundException, SQLException {
		
		System.out.println("*** Datenbank verbinden ***");
		
		final String DRIVER = "org.mariadb.jdbc.Driver";
		Class.forName(DRIVER);
		this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/seadb","seauser","seapass");
		
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
		
		PersonsRepository pr = new PersonsRepository(connection);
		this.resultSet = pr.getAll();
		System.out.println(this.resultSet);
		
		while(resultSet.next()) {
			System.out.print("ID " + resultSet.getLong(1) + ", ");
			System.out.print("Anrede " + resultSet.getShort(2) + ", ");
			System.out.print("Vorname " + resultSet.getString(3) + ", ");
			System.out.println("Nachname " + resultSet.getString(4));
		}
		
		System.out.println("*** Test DB Ende ***");
		
	}
}
