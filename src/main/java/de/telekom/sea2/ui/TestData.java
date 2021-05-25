package de.telekom.sea2.ui;

import java.sql.SQLException;

import de.telekom.sea2.model.Person;
import de.telekom.sea2.persistence.PersonsRepository;

public class TestData extends Menu {

	public TestData(PersonsRepository perRepo) {
		super(perRepo);
	}

	public boolean starWars() throws SQLException {
		Person person;
		person = new Person("Herr", "Luke", "Skywalker");
		perRepo.create(person);
		person = new Person("Herr", "Han", "Solo");
		perRepo.create(person);
		person = new Person("Frau", "Leia", "Organa");
		perRepo.create(person);
		person = new Person("Herr", "Obi-Wan", "Kenobi");
		perRepo.create(person);
		person = new Person("Divers", "Chewbacca", "");
		perRepo.create(person);
		person = new Person("Herr", "Anakin", "Skywalker");
		perRepo.create(person);
		person = new Person("Herr", "Qui-Gon", "Jinn");
		perRepo.create(person);
		person = new Person("Divers", "C3", "PO");
		perRepo.create(person);
		person = new Person("Divers", "R2", "D2");
		perRepo.create(person);
		person = new Person("Divers", "Yoda", "");
		perRepo.create(person);
		return true;
	}
	
}
