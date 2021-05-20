package de.telekom.sea2.persistence;

import java.sql.*;
import java.util.ArrayList;

import de.telekom.sea2.model.Person;

public class PersonsRepository {
	
	private Connection connection;
	private Statement statement;
	
	public PersonsRepository(Connection connection) throws SQLException {
		this.connection = connection;
		statement = this.connection.createStatement();
	}
	
	public boolean create(Person person) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("insert into personen (ID, ANREDE, VORNAME, NACHNAME) VALUES ( ?, ?, ?, ? )");
			preparedStatement.setLong(1, person.getId());
			preparedStatement.setShort(2, person.getSalutation().toShort());
			preparedStatement.setString(3, person.getFirstname());
			preparedStatement.setString(4, person.getLastname());
			preparedStatement.execute();
		return true;
	}
	
	public boolean update(Person person) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("update personen set anrede=?, vorname=?, nachname=? where id=? ");
			preparedStatement.setShort(1, person.getSalutation().toShort());
			preparedStatement.setString(2, person.getFirstname());
			preparedStatement.setString(3, person.getLastname());
			preparedStatement.setLong(4, person.getId());
			preparedStatement.execute();
		return true;
	}

	public Person get(long id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("select * from personen where id=? ");
			preparedStatement.setLong(1, id);
		
		ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();
			Person person = new Person();	
			person.setId(resultSet.getLong(1));
			person.setSalutation(resultSet.getShort(2));
			person.setFirstname(resultSet.getString(3));
			person.setLastname(resultSet.getString(4));
		
		return person;
	}
	
	public ArrayList<Person> getAll() throws SQLException {
		ResultSet resultSet = statement.executeQuery( "select * from personen");
		
		ArrayList<Person> persons = new ArrayList<Person>();
		Person person;
		
		while (resultSet.next()) {
			person = new Person();
			person.setId(resultSet.getLong(1));
			person.setSalutation(resultSet.getShort(2));
			person.setFirstname(resultSet.getString(3));
			person.setLastname(resultSet.getString(4));
			persons.add(person);
		}
		return persons;
	}
	
	public boolean deleteId(long id) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("delete from personen where id = ? ");
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
		return true;
	}
	
	public boolean deletePerson(Person person) throws SQLException {
		return deleteId(person.getId());
	}
	
	public boolean deleteAll() throws SQLException {
		statement.executeQuery( "delete from personen");
		return true;
	}
	
	public long maxId() throws SQLException {
		ResultSet resultSet = statement.executeQuery( "select max(id) from personen" );
		resultSet.next();
		return resultSet.getLong(1);
	}
	
}
