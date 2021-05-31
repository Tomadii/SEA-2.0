package de.telekom.sea2.ui;

import java.io.Closeable;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import de.telekom.sea2.io.*;
import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;

public class Menu implements Closeable {

	private Scanner scanner;
	private TestData testData;
	private ListReader listReader;
	PersonsRepository perRepo; 
	MainMenu mainMenu;
	
	public Menu(PersonsRepository perRepo) {
		openScanner();
		this.perRepo = perRepo;
	}
	
	private void openScanner() {
		this.scanner = new java.util.Scanner(System.in);
	}
	
	public void keepAsking() { 
		try {
			mainMenu = new MainMenu(perRepo);
			mainMenu.openMenu();
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
	}
	
	String inputMenu() { 
		return scanner.nextLine();
	}
	
	public void ausgabePerson(long id) throws SQLException {
		Person person = getPerson(id);
		System.out.println(id);
		if (id != 0) {
			System.out.println(String.format("%d %s %s %s", person.getId(), person.getSalutation(), person.getFirstname(), person.getLastname()));
		}
	}
	
	public void ausgabePerson(Person person) throws SQLException {
		ausgabePerson(person.getId());
	}
	
	void inputPerson() {
		Person person = new Person();
		System.out.println("Anrede eingeben: ");
		try {
			person.setSalutation(inputMenu());
		} catch (IllegalArgumentException e) {
			System.out.println("************************************************");
			System.out.println("* Flasche Eingabe nur Frau/Mann/Divers erlaubt *");
			System.out.println("************************************************");
			System.out.println(e);
			return;
		}
		System.out.println("Vorname eingeben: ");
		person.setFirstname(inputMenu());
		System.out.println("Nachname eingeben: ");
		person.setLastname(inputMenu());
		try {
			person = perRepo.create(person); 
			if (person != null) {
				System.out.println("Teilnehmer wurde erfolgreich angelegt.");
				ausgabePerson(person.getId());
			}
		} catch (SQLException e) {
			System.out.println("*************************************");
			System.out.println("* Person kann nicht angelegt werden *");
			System.out.println("*************************************");
			System.out.println(e);
		} 
	}
	
	Person getPerson() {
		Person person = new Person();
		System.out.println("Personen ID eingeben:");
		try {
			person = getPerson(Long.parseLong(inputMenu()));
		} catch (SQLException e) {
			System.out.println("Es gibt keine Person mit der ID");
		} catch (NumberFormatException e) {
			System.out.println("Eingabe ist keine Zahl");
		}
		return person;
	}
	
	Person getPerson(long id) throws SQLException {
		Person person = new Person();
		person = perRepo.get(id);
		return person;
	}
	
	Person getPerson(Person person) throws SQLException {
		person = getPerson(person.getId());
		return person;
	}
	
	void deletePerson() {
		System.out.println("Personen ID eingeben:");
		try {
			long id = Long.parseLong(inputMenu());
			Person person = getPerson(id);
			if (person.getId() != 0) {
				ausgabePerson(person);
				perRepo.deleteId(id);
				System.out.println("Person erfolgreich gelöscht");
			}
		} catch (NumberFormatException | SQLException e) {
			System.out.println("******************************");
			System.out.println("* Keine gülige ID eingegeben *");
			System.out.println("******************************");
		}
	}
	
	ArrayList<Person> listAll() {
		ArrayList<Person> persons;
		try {
			persons = perRepo.getAll();
			for (int i=0; i<persons.size(); i++) {
				System.out.print(persons.get(i).getId() + ": ");
				System.out.print(persons.get(i).getSalutation() + " ");
				System.out.print(persons.get(i).getFirstname() + " ");
				System.out.println(persons.get(i).getLastname());
			}
			return persons;
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
		return null;
	}
	
	void testData() {
		testData = new TestData(perRepo);
		try {
			if (testData.starWars()) {
				System.out.println("Testdaten in DB geschieben");
			}
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
	}
	
	void listExport(ArrayList<Person> persons) {
		try ( ListWriter list = new ListWriter() ) {
			list.writeList(persons);
		} catch (IOException e) {
			System.out.println("*****************************************");
			System.out.println("* Datei könnte nicht gespeichert werden *");
			System.out.println("*****************************************");
			System.out.println(e);
		}
	}
	
	void listImport() {
		listReader = new ListReader();
		try {
			ArrayList<Person> persons = listReader.readList();
			for ( int i = 0; i < persons.size(); i++) {
				perRepo.create(persons.get(i));
			}
		} catch (IOException e) {
			System.out.println("************************************");
			System.out.println("* Datei kann nicht geöffnet werden *");
			System.out.println("************************************");
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println("*************************************");
			System.out.println("* Person kann nicht angelegt werden *");
			System.out.println("*************************************");
			System.out.println(e);
		}
	}
	
	@Override
	public void close() {
		scanner.close();
	}
	
}
