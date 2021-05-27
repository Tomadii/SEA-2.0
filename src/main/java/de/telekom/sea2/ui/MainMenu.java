package de.telekom.sea2.ui;

import de.telekom.sea2.persistence.PersonsRepository;

public class MainMenu extends Menu {

	public MainMenu(PersonsRepository perRepo) {
		super(perRepo);
	}

	void showMenu() { 				// privat - Zeigt die Menue-Auswahl an
		System.out.println("*** Menü ***");
		System.out.println("1. Person anlegen");
		System.out.println("2. Person anzeigen");
		System.out.println("3. Person ändern");
		System.out.println("4. Person löschen");
//		System.out.println("5. Personen suchen");
//		System.out.println("6. Suchliste anzeigen");
		System.out.println("7. Personenliste anzeigen");
		System.out.println("8. Personenliste löschen");
		System.out.println("9. Testpersonen generieren");
		System.out.println("10. Liste in Datei speichern");
//		System.out.println("11. Liste aus Datei importieren");
		System.out.println("exit um Eingabe zu verlassen");
		System.out.println("");
		System.out.println("Bitte wählen Sie aus!");
	}
	
	void checkMenu(final String input) {	
		switch(input) {
			case "1": 
				System.out.println("Du hast 1 gewählt!");
				inputPerson();
				break;
			case "2": 
				System.out.println("Du hast 2 gewählt!");
				getPerson();
				break;
			case "3": 
				System.out.println("Du hast 3 gewählt!");
				updatePerson();
				break;
			case "4": 
				System.out.println("Du hast 4 gewählt!");
				deletePerson();
				break;	
			case "5": 
				System.out.println("Du hast 5 gewählt!");
//				removePerson();
				break;
			case "6": 
				System.out.println("Du hast 6 gewählt!");
//				listSave();
				break;
			case "7": 
				System.out.println("Du hast 7 gewählt!");
				listAll(); 
				break;
			case "8": 
				System.out.println("Du hast 8 gewählt!");
				deleteAll();
				break;
			case "9":
				System.out.println("Du hast 9 gewählt!");
				testData();
				break;
			case "10": 
				System.out.println("Du hast 10 gewählt!");
				listAllExport();
				break;
			case "exit": System.out.println("Du hast exit gewählt");  // Fehler mit "4" beseitigt gegen "Q"
				break;
			default:  System.out.println("Du hast was anderes gewählt!");
		}
	}
	
}
