package de.telekom.sea2.model;

import de.telekom.sea2.looup.Salutation;

public class Person {

	private String firstname;
	private String lastname;
	private Salutation salutation;
	private long id;

	public Person() {
		
	}
	
	public Person(final String salutation, final String firstname, final String lastname) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.salutation = Salutation.fromString(salutation);
	}
	
	public Person(final long id, final String salutation, final String firstname, final String lastname) {
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.salutation = Salutation.fromString(salutation);		
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(final String firstname) {
		this.firstname = firstname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(final String lastname) {
		this.lastname = lastname;
	}
	
	public Salutation getSalutation() {
		return salutation;
	}

	public void setSalutation(final String string) {
		this.salutation = Salutation.fromString(string);
	}
	
	public void setSalutation(final Short s) {
		this.salutation = Salutation.fromShort(s);
	}

	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	
}
