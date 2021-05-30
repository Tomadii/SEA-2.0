package de.telekom.sea2.ui;

import java.sql.SQLException;
import java.util.ArrayList;

import de.telekom.sea2.persistence.PersonsRepository;
import de.telekom.sea2.model.Person;

public class SearchMenu extends Menu {
	
	private ArrayList<Person> searchPerson;
	String result;

	public SearchMenu(PersonsRepository perRepo) {
		super(perRepo);
	}
	
	public String openMenu() {
		boolean loop = true;
		
		do {
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
		return "0";
	}
	
	void showMenu() {
		System.out.println("┌───────────────────────────────────┐");
		System.out.println("│            Search Menu            │");
		System.out.println("├───┬───────────────────────────────┤");
		System.out.println("│ 1 │ nach Anrede suchen            │");
		System.out.println("│ 2 │ nach Vornamen suchen          │");
		System.out.println("│ 3 │ nach Nachnamen suchen         │");
		if (searchPerson != null) {
			System.out.println("│ 4 │ Suchliste anzeigen            │");
			System.out.println("│ 5 │ Suchliste in Datei speichern  │");
		}
		System.out.println("├───┼───────────────────────────────┤");
		System.out.println("│ z │ zurück ins Hauptmenü          │");
		System.out.println("│ e │ zum Beenden                   │");
		System.out.println("└───┴───────────────────────────────┘");
	}
	
	void checkMenu(final String input) {	
		switch(input) {
			case "1": 
				System.out.println("*** nach Anrede suchen ***");
				openAnrede();
				result = "0";
				break;
			case "2": 
				System.out.println("*** nach Vornamen suchen ***");
				String search = search();
				search("vorname", search);
				break;
			case "3": 
				System.out.println("*** nach Nachnamen suchen ***");
				search = search();
				search("nachname", search);
				break;	
			case "4": 
				System.out.println("*** Suchliste anzeigen ***");
				if (searchPerson != null) {
					getList();
				} else {
					System.out.println("Du hast noch keine suche druchgeführt");
				}
				break;
			case "5": 
				System.out.println("*** Suchliste in Datei speichern ***");
				if (searchPerson != null) {
					saveList();
				} else {
					System.out.println("Du hast noch keine suche druchgeführt");
				}
				break;
			case "z":
			case "e":
				break;
			default:  System.out.println("Du hast was anderes gewählt!");
		}
	}
	
	private String openAnrede() {
		boolean loop = true;
		
		do {
			showAnrede();
			result = super.inputMenu();
			checkAnrede(result);
			if (result.equals("e") || result.equals("z")) {
				loop = false;
			}
		} while( loop);
		if (result.equals("e")) {
			return result;
		}
		return "0";
	}
	
	private void showAnrede() {
		System.out.println("┌──────────────────────────────────────┐");
		System.out.println("│        Search Salutation Menu        │");
		System.out.println("├───┬──────────────────────────────────┤");
		System.out.println("│ 1 │ nach Frau suchen                 │");
		System.out.println("│ 2 │ nach Herr suchen                 │");
		System.out.println("│ 3 │ nach Divers suchen               │");
		System.out.println("│ 4 │ nach Personen ohne Anrede suchen │");
		System.out.println("├───┼──────────────────────────────────┤");
		System.out.println("│ z │ zurück ins Hauptmenü             │");
		System.out.println("│ e │ zum Beenden                      │");
		System.out.println("└───┴──────────────────────────────────┘");
	}
	
	void checkAnrede(final String input) {	
		switch(input) {
			case "1": 
				System.out.println("*** nach Frau suchen ***");
				search("anrede", "1");
				result = "z";
				break;
			case "2": 
				System.out.println("*** nach Herr suchen ***");
				search("anrede", "2");
				result = "z";
				break;
			case "3": 
				System.out.println("*** nach Divers suchen ***");
				search("anrede", "3");
				result = "z";
				break;	
			case "4": 
				System.out.println("*** nach Personen ohne Anrede suchen ***");
				search("anrede", "4");
				result = "z";
				break;
			case "z":
			case "e":
				break;
			default:  System.out.println("Du hast was anderes gewählt!");
		}
	}
	
	private String search() {
		String search;
		System.out.println("Bitte Suchbegriff eingeben");
		search = inputMenu();
		return search;
	}
	
	private void search(String column, String search) {
		try {
			searchPerson = perRepo.searchDB(column, search);
		} catch (SQLException e) {
			System.out.println("**********************************************");
			System.out.println("* Auf Datenbank kann nich zugegriffen werden *");
			System.out.println("**********************************************");
			System.out.println(e);
		}
		getList();
	}
	
	private void getList() {
		for (int i=0; i<searchPerson.size(); i++) {
			System.out.println(String.format("%d %s %s %s", searchPerson.get(i).getId(), searchPerson.get(i).getSalutation(), searchPerson.get(i).getFirstname(), searchPerson.get(i).getLastname()));
		}
	}
	
	private void saveList() {
		super.listExport(searchPerson);
	}
	
}
