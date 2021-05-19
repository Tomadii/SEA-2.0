package de.telekom.sea2.persistence;

import java.sql.*;
import de.telekom.sea2.model.Person;

public class PersonsRepository {
	
	private Connection connection;
	
	public PersonsRepository(Connection connection) {
		this.connection = connection;
	}
	
	public boolean create(Person person) {
		return true;
	}
	
	public boolean update(Person person) {
		return true;
	}

	public void get(long ID) {
		
	}
	
	public ResultSet getAll() throws SQLException {
		Statement statement = this.connection.createStatement();
		ResultSet resultSet = statement.executeQuery( "select * from personen");
		return resultSet;
	}
	
	public boolean deleteId(long id) {
		return true;
	}
	
	public boolean deletePerson(Person person) {
		return true;
	}
	
	public boolean deleteAll() {
		return true;
	}
	
}
