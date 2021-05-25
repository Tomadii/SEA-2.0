package de.telekom.sea2.ui;

import java.io.Closeable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;

public class Menu implements Closeable {

	private Scanner scanner;
	PersonsRepository perRepo; 
	MainMenu mainMenu;
	UpdatePerson updatePerson;
	
	public Menu(PersonsRepository perRepo) {
		openScanner();
		this.perRepo = perRepo;
	}
	
	private void openScanner() {
		this.scanner = new java.util.Scanner(System.in);
	}
	
	public void keepAsking() { 
		mainMenu = new MainMenu(perRepo);
		String result;
		
		do {
			mainMenu.showMenu();
			result = inputMenu();
			mainMenu.checkMenu(result);
		} while( !result.equals("exit"));
	}
		
	String inputMenu() { 
		return scanner.nextLine();
	}
	
	void inputPerson() {
		Person person = new Person();
		System.out.println("Anrede eingeben: ");
		try {
			person.setSalutation(inputMenu());
		} catch (IllegalArgumentException ex) {
			System.out.println("************************************************");
			System.out.println("* Flasche Eingabe nur Frau/Mann/Divers erlaubt *");
			System.out.println("************************************************");
			return;
		}
		System.out.println("Vorname eingeben: ");
		person.setFirstname(inputMenu());
		System.out.println("Nachname eingeben: ");
		person.setLastname(inputMenu());
		try {
			if (perRepo.create(person)) {
				System.out.println("Teilnehmer wurde erfolgreich angelegt.");
			}
		} catch (SQLException e) {
			System.out.println("*************************************");
			System.out.println("* Person kann nicht angelegt werden *");
			System.out.println("*************************************");
			e.printStackTrace();
		} 
	}
	
	Person getPerson() {
		Person person = new Person();
		System.out.println("Personen ID eingeben:");
		try {
			person = getPerson(Long.parseLong(inputMenu()));
		} catch (SQLException e) {
			System.out.println("Es gibt keine Person mit der ID");
		}
		return person;
	}
	
	Person getPerson(long id) throws SQLException {
		Person person = new Person();
		person = perRepo.get(id);
		System.out.println(String.format("%d %s %s %s", person.getId(), person.getSalutation(), person.getFirstname(), person.getLastname()));
		return person;
	}
	
	void updatePerson() {
		updatePerson = new UpdatePerson(perRepo);
		try {
			updatePerson.update();
		} catch (SQLException e) {
			System.out.println("*************************************");
			System.out.println("* Person kann nicht geändert werden *");
			System.out.println("*************************************");
			e.printStackTrace();
		}
	}
	
	void deletePerson() {
		System.out.println("Personen ID eingeben:");
		try {
			perRepo.deleteId(Long.parseLong(inputMenu()));
		} catch (NumberFormatException e) {
			System.out.println("******************************");
			System.out.println("* Keine gülige ID eingegeben *");
			System.out.println("******************************");
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
	}
	
	void listAll() {
		ArrayList<Person> persons;
		try {
			persons = perRepo.getAll();
			for (int i=0; i<persons.size(); i++) {
				System.out.print(persons.get(i).getId() + ": ");
				System.out.print(persons.get(i).getSalutation() + " ");
				System.out.print(persons.get(i).getFirstname() + " ");
				System.out.println(persons.get(i).getLastname());
			}
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
		
	}
	
	void deleteAll() {
		try {
			System.out.println("Liste der Personen ist gelöscht = " + perRepo.deleteAll());
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
	}
	
	@Override
	public void close() {
		scanner.close();
		
	}
	
}
