package de.telekom.sea2.ui;

import java.sql.SQLException;

import de.telekom.sea2.persistence.PersonsRepository;

public class DeleteAll extends Menu {
	
	private String result;
	
	public DeleteAll(PersonsRepository perRepo) {
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
		System.out.println("│      Komplette Liste löschen      │");
		System.out.println("├───┬───────────────────────────────┤");
		System.out.println("│ j │ Liste löschen                 │");
		System.out.println("│ z │ zurück ins Hauptmenü          │");
		System.out.println("│ e │ zum Beenden                   │");
		System.out.println("└───┴───────────────────────────────┘");
	}
	
	void checkMenu(final String input) {	
		switch(input) {
			case "j":
			case "y":
				System.out.println("*** Liste löschen ***");
				result = deleteAll();
				break;
			case "z":
			case "e":
				break;
			default:  System.out.println("Du hast was anderes gewählt!");
		}
	}
	
	private String deleteAll() {
		try {
			System.out.println("Liste der Personen ist gelöscht = " + perRepo.deleteAll());
			return "z";
		} catch (SQLException e) {
			System.out.println("***********************************************");
			System.out.println("* Auf Datenbank kann nicht zugegriffen werden *");
			System.out.println("***********************************************");
			System.out.println(e);
			return "0";
		}
	}
	
}
