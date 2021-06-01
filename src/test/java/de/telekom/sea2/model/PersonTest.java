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
	void setFirstname_test() {
		// Arrange
		cut.setFirstname("Sarah");

		// Act
		var result = cut.getFirstname();

		// Assert
		assertEquals("Sarah", result);
	}
	
	@AfterEach
	void teardown() {
		cut = null;
	}
}
