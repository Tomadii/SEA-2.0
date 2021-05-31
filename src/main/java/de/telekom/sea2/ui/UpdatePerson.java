package de.telekom.sea2.ui;

import java.sql.SQLException;
import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;

public class UpdatePerson extends Menu {
	
	private Person person;
	private String result;
	
	public UpdatePerson(PersonsRepository perRepo) {
		super(perRepo);
	}
	
	public String openMenu() throws SQLException {
		boolean loop = true;
		person = getPerson();
		if (person.getId() != 0L) {
			
			do {
				ausgabePerson(person);
				showMenu();
				result = super.inputMenu();
				checkMenu(result);
				if (result.equals("e") || result.equals("z")) {
					loop = false;
				}
			} while( loop);
			if (result.equals("e")) {
				return result;
			}
		}
		return "0";
	}

	void showMenu() {
		System.out.println("┌───────────────────────────────────┐");
		System.out.println("│            Update Menu            │");
		System.out.println("├───┬───────────────────────────────┤");
		System.out.println("│ 1 │ Anrede ändern                 │");
		System.out.println("│ 2 │ Vornamen ändern               │");
		System.out.println("│ 3 │ Nachnamen ändern       	    │");
		System.out.println("├───┼───────────────────────────────┤");
		System.out.println("│ s │ Speichern und ins Hauptmenü   │");
		System.out.println("│ z │ zurück ins Hauptmenü          │");
		System.out.println("│ e │ zum Beenden                   │");
		System.out.println("└───┴───────────────────────────────┘");
	}
	
	void checkMenu(final String input) {
		switch(input) {
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
			case "s":
				result = saveUpdate();
				break;
			case "z":
			case "e":
				break;
			default:  System.out.println("Du hast was anderes gewählt!");
		}
	}
	
	private String saveUpdate() {
		try {
			perRepo.update(person);
			System.out.println("Teilnehmer wurde erfolgreich geändert.");
			ausgabePerson(person);
			return "z";
		} catch (SQLException e) {
			System.out.println("*******************************************");
			System.out.println("* Teilnehmer konnte nicht geändert werden *");
			System.out.println("*******************************************");
			System.out.println(e);
			return "";
		}
	}
	
}
