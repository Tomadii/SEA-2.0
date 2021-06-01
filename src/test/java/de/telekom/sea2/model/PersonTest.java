package de.telekom.sea2.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.*;

public class PersonTest {
	
	private Person cut;
	
	@BeforeEach
	void setup() {
		cut = new Person();
	}

	@Test
	void personWith3Strings_test() {
		// Arrange
		cut = new Person("Herr", "Heinz", "Wurst");
		// Act
		var salutation = cut.getSalutation();
		var firstname = cut.getFirstname();
		var lastname = cut.getLastname();
		// Assert
		assertEquals("Herr", salutation.toString());
		assertEquals("Heinz", firstname);
		assertEquals("Wurst", lastname);
	}
	
	@Test
	void personWith4Strings_test() {
		// Arrange
		cut = new Person(5L, "Herr", "Heinz", "Wurst");
		// Act
		var id = cut.getId();
		var salutation = cut.getSalutation();
		var firstname = cut.getFirstname();
		var lastname = cut.getLastname();
		// Assert
		assertEquals(5L, id);
		assertEquals("Herr", salutation.toString());
		assertEquals("Heinz", firstname);
		assertEquals("Wurst", lastname);
	}
	
	@Test
	void setFirstname_test() {
		// Arrange
		cut.setFirstname("Sarah");

		// Act
		var result = cut.getFirstname();

		// Assert
		assertEquals("Sarah", result);
	}
	
	@Test
	void setLastname_test() {
		// Arrange
		cut.setLastname("Müller");

		// Act
		var result = cut.getLastname();

		// Assert
		assertEquals("Müller", result);
	}
	
	@Test
	void setSalutationFromString_test() {
		// Arrange
		cut.setSalutation("Herr");

		// Act
		var result = cut.getSalutation();

		// Assert
		assertEquals("Herr", result.toString());
	}
	
	@Test
	void setSalutationFromShort_test() {
		// Arrange
		cut.setSalutation( (short) 2 );

		// Act
		var result = cut.getSalutation();

		// Assert
		assertEquals("Herr", result.toString());
	}
	
	@Test
	void setId_test() {
		// Arrange
		cut.setId(3);

		// Act
		var result = cut.getId();

		// Assert
		assertEquals(3, result);
	}
	
	@AfterEach
	void teardown() {
		cut = null;
	}
}
