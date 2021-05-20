package de.telekom.sea2.persistence;

import java.sql.*;
import de.telekom.sea2.model.Person;

public class PersonsRepository {
	
	private Connection connection;
	private Statement statement;
	
	public PersonsRepository(Connection connection) throws SQLException {
		this.connection = connection;
		statement = this.connection.createStatement();
	}
	
	public boolean create(Person person) throws SQLException {
		short salutation = person.getSalutation().toShort();
		statement.executeQuery("insert into personen (ID, ANREDE, VORNAME, NACHNAME) VALUES (" + person.getId() + "," + salutation + ",'" + person.getFirstname() + "','" + person.getLastname() + "')");
		return true;
	}
	
	public boolean update(Person person) throws SQLException {
		long personId = person.getId();
		deleteId(personId);
		create(person);
		return true;
	}

	public Person get(long ID) throws SQLException {
		ResultSet resultSet = statement.executeQuery( "select * from personen where id = '" + ID + "'");
		
		Person person = new Person();
		resultSet.next();
		person.setId(resultSet.getLong(1));
		person.setSalutation(resultSet.getShort(2));
		person.setFirstname(resultSet.getString(3));
		person.setLastname(resultSet.getString(4));
		
		return person;
	}
	
	public ResultSet getAll() throws SQLException {
		ResultSet resultSet = statement.executeQuery( "select * from personen");
		return resultSet;
	}
	
	public boolean deleteId(long id) throws SQLException {
		statement.executeQuery( "delete from personen where id=" + id);
		return true;
	}
	
	public boolean deletePerson(Person person) throws SQLException {
		deleteId(person.getId());
		return true;
	}
	
	public boolean deleteAll() throws SQLException {
		statement.executeQuery( "delete from personen");
		return true;
	}
	
}
