package de.telekom.sea2.ui;

import java.sql.SQLException;
import java.util.ArrayList;

import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;

public class MainMenu extends Menu {
	
	private String result;
	private UpdatePerson updatePerson;
	private SearchMenu searchMenu;
	private DeleteAll deleteAll;

	public MainMenu(PersonsRepository perRepo) {
		super(perRepo);
	}

	public void openMenu() throws SQLException {
		do {
			showMenu();
			result = super.inputMenu();
			checkMenu(result);
		} while( !result.equals("e"));
	}
	
	void showMenu() { 
		System.out.println("┌───────────────────────────────────┐");
		System.out.println("│             Hauptmenü             │");
		System.out.println("├────┬──────────────────────────────┤");
		System.out.println("│  1 │ Person anlegen               │");
		System.out.println("│  2 │ Person anzeigen              │");
		System.out.println("│  3 │ Person ändern                │");
		System.out.println("│  4 │ Person löschen               │");
		System.out.println("│  5 │ Personen suchen              │");
		System.out.println("│  6 │ Personenliste anzeigen       │");
		System.out.println("│  7 │ Personenliste löschen        │");
		System.out.println("│  8 │ Testpersonen generieren      │");
		System.out.println("│  9 │ Liste in Datei speichern     │");
		System.out.println("│ 10 │ Liste aus Datei importieren  │");
		System.out.println("├────┼──────────────────────────────┤");
		System.out.println("│  e │ zum Beenden                  │");
		System.out.println("└────┴──────────────────────────────┘");
	}
	
	void checkMenu(String input) throws SQLException {	
		switch(input) {
			case "1": 
				System.out.println("*** Person anlegen ***");
				inputPerson();
				break;
			case "2": 
				System.out.println("*** Person anzeigen ***");
				Person person = getPerson();
				if (person.getId() != 0) {
					ausgabePerson(person);
				}
				break;
			case "3": 
				System.out.println("*** Person ändern ***");
				updatePerson();
				break;
			case "4": 
				System.out.println("*** Person löschen ***");
				deletePerson();
				break;	
			case "5": 
				searchPersons();
				break;
			case "6": 
				System.out.println("*** Personenliste anzeigen ***");
				listAll(); 
				break;
			case "7": 
				System.out.println("*** Personenliste löschen ***");
				deleteAll();
				break;
			case "8":
				System.out.println("*** Testpersonen generieren ***");
				testData();
				break;
			case "9": 
				System.out.println("*** Liste in Datei speichern ***");
				ArrayList<Person> persons = listAll();
				listExport(persons);
				break;
			case "10":
				System.out.println("*** Liste aus Datei importieren ***");
				listImport();
				break;
			case "e":
				break;
			default:  System.out.println("Du hast was anderes gewählt!");
		}
	}
	
	private void updatePerson() throws SQLException {
		updatePerson = new UpdatePerson(perRepo);
		result = updatePerson.openMenu();
	}
	
	private void searchPersons() {
		searchMenu = new SearchMenu(perRepo);
		result = searchMenu.openMenu();			
	}
	
	private void deleteAll() {
		deleteAll = new DeleteAll(perRepo);
		result = deleteAll.openMenu();			
	}
	
	@Override
	public void close() {
		updatePerson.close();
		searchMenu.close();
		deleteAll.close();
	}
	
}
