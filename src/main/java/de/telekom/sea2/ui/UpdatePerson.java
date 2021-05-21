package de.telekom.sea2.ui;

import java.sql.SQLException;
import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;

public class UpdatePerson extends Menu {
	
	public UpdatePerson(PersonsRepository perRepo) {
		super(perRepo);
	}

	Person update() throws SQLException {
		Person person = new Person();
		person = getPerson();
		String input;
		do {
		System.out.println("Was möchten Sie ändern:");
		System.out.println("1. Anrede");
		System.out.println("2. Vornamen");
		System.out.println("3. Nachnamen");
		System.out.println("S. zum Speichen");
		System.out.println("A. zum abbrechen");
		input = inputMenu();
		switch (input) {
			case "1": 
				System.out.println("Andere Anrede eingeben");
				person.setSalutation(inputMenu());
				break;
			case "2": 
				System.out.println("Neuen Vornamen eingeben");
				person.setFirstname(inputMenu());
				break;
			case "3": 
				System.out.println("Neuen Nachnamen eingeben");
				person.setLastname(inputMenu());
				break;
			case "S":
				try {
					if (perRepo.update(person)) {
						System.out.println(person.getId() + " " + person.getSalutation() + " " + person.getFirstname() + " " + person.getLastname());
						System.out.println("Teilnehmer wurde erfolgreich geändert.");
					} else {
						System.out.println("Teilnehmer wurde nicht geändert!");
					}
				} catch (SQLException e) {
					System.out.println("**********************************************");
					System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
					System.out.println("**********************************************");
					System.out.println(e);
				}
				input = "A";
			case "A":
				break;
			default:  System.out.println("Du hast was anderes gewählt!");
		}
		} while (!input.equals("A"));
		return person;
	}
}
