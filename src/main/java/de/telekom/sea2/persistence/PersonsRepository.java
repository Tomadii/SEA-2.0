package de.telekom.sea2.persistence;

import java.sql.*;
import java.util.ArrayList;

import de.telekom.sea2.model.Person;

public class PersonsRepository {
	
	final static String DRIVER = "org.mariadb.jdbc.Driver";
	final static String URL = "jdbc:mysql://localhost:3306/seadb?user=seauser&password=seapass";
	private Connection connection;
	
	public PersonsRepository() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER);
		this.connection = DriverManager.getConnection(URL);
	}
	
	public Person create(Person person) throws SQLException {
		String query = "insert into personen (ID, ANREDE, VORNAME, NACHNAME) VALUES ( ?, ?, ?, ? )";
		long id;
		if (person.getId() == 0) { 
			id = maxId() +1;
		} else {
			if (countId(person.getId()) == 0) {
				id = person.getId();
			} else {
				return null;
			}
		}
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			preparedStatement.setShort(2, person.getSalutation().toShort());
			preparedStatement.setString(3, person.getFirstname());
			preparedStatement.setString(4, person.getLastname());
			preparedStatement.execute();
		return get(id);
	}
	
	public boolean update(Person person) throws SQLException {
		String query = "update personen set anrede=?, vorname=?, nachname=? where id=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setShort(1, person.getSalutation().toShort());
			preparedStatement.setString(2, person.getFirstname());
			preparedStatement.setString(3, person.getLastname());
			preparedStatement.setLong(4, person.getId());
			preparedStatement.execute();
		return true;
	}

	public Person get(long id) throws SQLException {
		String query = "select * from personen where id=? ";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
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
	
	public ArrayList<Person> searchDB(String column, String search) throws SQLException {
		String query = "SELECT * FROM personen WHERE " + column + "='" + search + "'";
//		String query = "SELECT * FROM personen WHERE " + column + " ='?'";
		
//		PreparedStatement preparedStatement = connection.prepareStatement(query);
//		preparedStatement.setString(1, column);
//		preparedStatement.setString(1, search);
		
		Statement statement = connection.createStatement();
		
		ResultSet resultSet = statement.executeQuery(query);
		
//		ResultSet resultSet = preparedStatement.executeQuery(query);
		
		ArrayList<Person> selectPersons = new ArrayList<Person>();
		Person person;
		
		while (resultSet.next()) {
			person = new Person();
			person.setId(resultSet.getLong(1));
			person.setSalutation(resultSet.getShort(2));
			person.setFirstname(resultSet.getString(3));
			person.setLastname(resultSet.getString(4));
			selectPersons.add(person);
		}
		return selectPersons;
	}
	
	public ArrayList<Person> getAll() throws SQLException {
		String query = "select * from personen";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		
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
		String query = "delete from personen where id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
		return true;
	}
	
	public boolean deletePerson(Person person) throws SQLException {
		return deleteId(person.getId());
	}
	
	public boolean deleteAll() throws SQLException {
		String query = "delete from personen";
		Statement statement = connection.createStatement();
		statement.execute(query);
		return true;
	}
	
	private long maxId() throws SQLException {
		String query = "select max(id) from personen";
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(query);
		resultSet.next();
		return resultSet.getLong(1);
	}
	
	private long countId(long id) throws SQLException {
		String query = "SELECT COUNT(id) FROM personen WHERE id = ?";
		PreparedStatement preparedStatement = connection.prepareStatement(query);
			preparedStatement.setLong(1, id);
		ResultSet resultSet = preparedStatement.executeQuery();
		resultSet.next();
		return resultSet.getLong(1);
	}
}
