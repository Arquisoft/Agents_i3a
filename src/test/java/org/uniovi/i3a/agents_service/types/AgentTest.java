/*
 * This source file is part of the Agents_i3a open source project.
 *
 * Copyright (c) 2017 Agents_i3a project authors.
 * Licensed under MIT License.
 *
 * See /LICENSE for license information.
 * 
 * This class is based on the AlbUtil project.
 * 
 */
package org.uniovi.i3a.agents_service.types;

import static org.junit.Assert.*;

import java.io.IOException;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import Foundation.CSVFile;
import Foundation.URL;
import TestKit.UnitTest;

/**
 * 
 * Test for the domain instance of agent.
 * 
 * Adapted by Víctor on 02/02/2018.
 * Modified by Guillermo on 201802041300.
 *
 * @author Damian
 * @version 201802041300
 * @since 15/02/2017
 */
@Category(UnitTest.class)
public class AgentTest {

    private Agent nico, jorge, damian;

    /**
     * Also a resources/master.csv file is needed with the following rows: 1 ,
     * Person 2 , Entity 3 , Sensor
     * 
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
	nico = new Agent("Nicolás", "nico@nicomail.com", "nico123");
	jorge = new Agent("Jorge", "45N30E", "jorge@jorgemail.com", "jorge123", "111111111A", 2);
	damian = new Agent("Damian", "45S30W", "damian@damianmail.com", "damian123", "222222222B", 3);
    }

    @Test
    public void nameTest() {

	// Getting the previously set names.
	assertEquals("Nicolás", nico.getName());
	assertEquals("Jorge", jorge.getName());
	assertEquals("Damian", damian.getName());

	// Changing and testing the name of Nico to Antonio.
	nico.setName("Antonio");
	assertEquals("Antonio", nico.getName());

	// Same for Jorge.
	jorge.setName("Pepe");
	assertEquals("Pepe", jorge.getName());

	// Same for Roberto.
	damian.setName("Roberto");
	assertEquals("Roberto", damian.getName());

	// Test for the empty string.
	nico.setName("");
	assertEquals("", nico.getName());

	// Test for null values. Must return at least the empty string.
	nico.setName(null);
	assertEquals("", nico.getName());
    }

    @Test
    public void locationTest() {

	// Setting to nico the same location as jorge.
	nico.setLocation(jorge.getLocation());
	assertEquals(jorge.getLocation(), nico.getLocation());

	// Now jorge has the same location as damian.
	jorge.setLocation(damian.getLocation());
	assertEquals(damian.getLocation(), jorge.getLocation());

	// Hard coding a location.
	damian.setLocation("30N48E");
	assertEquals("30N48E", damian.getLocation());

	// The empty string.
	damian.setLocation("");
	assertEquals("", damian.getLocation());

	// Test for null values. Must return at least the empty string.
	damian.setLocation(null);
	assertEquals("", damian.getLocation());
    }

    @Test
    public void emailTest() {

	// Setting to nico the email of damian.
	nico.setEmail(damian.getEmail());
	assertEquals(damian.getEmail(), nico.getEmail());

	// Hard coding an email for jorge.
	jorge.setEmail("pepe@pepemail.com");
	assertEquals("pepe@pepemail.com", jorge.getEmail());

	// Setting to damian the email of jorge that was previously hard coded.
	damian.setEmail(jorge.getEmail());
	assertEquals("pepe@pepemail.com", damian.getEmail());

	// Empty string check.
	nico.setEmail("");
	assertEquals("", nico.getEmail());

	// Test for null values. Must return at least the empty string.
	damian.setEmail(null);
	assertEquals("", damian.getEmail());
    }

    @Test
    public void passwordTest() {

	// Creating the encrypt instance.
	StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

	// Setting the password of nico to 1234. And checking the encrypt process works.
	nico.setPassword("1234");
	assertTrue(encryptor.checkPassword("1234", nico.getPassword()));

	// Checking that the password is not being stored as plain text.
	assertNotEquals("1234", nico.getPassword());

	// Checking the empty string as a password.
	nico.setPassword("");
	assertTrue(encryptor.checkPassword("", nico.getPassword()));

	// Checking that the password is not being stored as plain text.
	assertNotEquals("", nico.getPassword());

	// Check for the null string as a password.
	nico.setPassword(null);
	assertTrue(encryptor.checkPassword("", nico.getPassword()));

	// Checking that the password is not being stored as plain text.
	assertNotEquals("", nico.getPassword());
    }

    @Test
    public void identifierTest() {

	// Nico has its id set to null, jorge to 111111111A and damian to 222222222B.
	// So testing that a null id will return at least the empty string.
	assertEquals("", nico.getId());

	// Checking that the correct id is returned.
	assertEquals("111111111A", jorge.getId());

	// Same for damian.
	assertEquals("222222222B", damian.getId());
    }

    @Test
    public void kindTest() throws IOException {
	// The actual CSVFile contains 1,Person;2,Entity;3,Sensor.
	// Setting nico to Person (1); And checking the resolution of the name work
	// properly.
	nico.setKindCode(1);
	assertEquals("Person", nico.getKind());

	// Jorge was previously set to (2) Entity. Checking that both, the code and the
	// resolution work.
	assertEquals("Entity", jorge.getKind());
	assertEquals(2, jorge.getKindCode());

	// Same for the Sensor damian.
	assertEquals("Sensor", damian.getKind());
	assertEquals(3, damian.getKindCode());

	// Changing the value of jorge to a Person and checking now the resolution of
	// the kind of agent still works.
	jorge.setKindCode(1);
	assertEquals("Person", jorge.getKind());
	assertEquals(1, jorge.getKindCode());

	// Creating a new CSV file with diffetent attributes that the old one. Checking
	// that if the file changes at execution times the resolution of names still
	// works.
	CSVFile originalFile = CSVFile.of(new URL("src/main/resources/master.csv"), ",", "id", "kind");
	CSVFile testFile = CSVFile.of(new URL("src/main/resources/master.csv"),",", "id", "kind");
	testFile.setSeparator(",");
	
	testFile.addRow("4", "iphone");
	testFile.save();

	// Now we set nico to 4, a value that was not in the previous master.csv file.
	jorge.setKindCode(4);
	assertEquals("iphone", jorge.getKind());
	
	// Checking the resolution of the agent kind still works.
	assertEquals("Person", nico.getKind());

	// We revert changes and save the original file.
	originalFile.save();
	
	// Checking if we assign a kindCode that is not in the file
	nico.setKindCode(-1);
	assertEquals(Agent.KIND_NOT_FOUND, nico.getKind());

    }

    @Test
    public void toStringTest() {
	
	// Ensuring the kind code for nico test.
	nico.setKindCode(1);
	
	// Checking the format of the toString.
	assertEquals("{" + "name:'" + nico.getName() + "'," + "location:'" + nico.getLocation() + "'," + "email:'"
		+ nico.getEmail() + "'," + "id:'" + nico.getId() + "'," + "kind:'" + nico.getKind() + "',"
		+ "kindCode:" + nico.getKindCode() + "}", nico.toString());
    }

    @SuppressWarnings("unlikely-arg-type")
    @Test
    public void equalsTest() {
	
	// Creating a copy of nico.
	Agent copyOfNico = new Agent(nico.getName(), nico.getLocation(), nico.getEmail(), nico.getPassword(), "111",
		nico.getKindCode());
	
	// Another copy of nico.
	Agent anotherCopyOfNico = new Agent(nico.getName(), nico.getLocation(), nico.getEmail(), nico.getPassword(),
		"111", nico.getKindCode());

	// Both copies must be equal.
	assertEquals(true, anotherCopyOfNico.equals(copyOfNico));
	
	// Creating a copy of nico that has a different id.
	Agent nonEqualCopyOfNico = new Agent(nico.getName(), nico.getLocation(), nico.getEmail(), nico.getPassword(),
		"112", nico.getKindCode());
	
	// Checking now the copies are not equal.
	assertNotEquals(true, copyOfNico.equals(nonEqualCopyOfNico));
	
	// Cheking with itself
	assertEquals(true, copyOfNico.equals(copyOfNico));
	
	// Check for null values.
	assertEquals(false, copyOfNico.equals(null));
	
	// Check for different classes.
	assertEquals(false, copyOfNico.equals("I'm a fake agent..."));
    }
    
    @Test
    public void hashCodeTest() {
	// Nico has null ad id so a perfect test, must be 0.
	assertEquals(nico.getId().hashCode(), nico.hashCode());
	
	// Testing someone with an id.
	assertEquals(jorge.getId().hashCode(), jorge.hashCode());
    }

    @Test
    public void kindCodeTest() {
	
	// Setting the kind code of jorge to be 2.
	jorge.setKindCode(2);
	assertEquals(2, jorge.getKindCode());
	
	// Setting the kind code of damian to be 3.
	damian.setKindCode(3);
	assertEquals(3, damian.getKindCode());
	
	// Checking negative integers.
	nico.setKindCode(-1);
	assertEquals(-1, nico.getKindCode());
    }

    @Test
    public void compareToTest() {
	// Creating to Agents where jorge has a bigger id than nico.
	nico = new Agent("nico", "10W10E", "nico@nico.com", "12345", "11111111A", 1);
	jorge = new Agent("jorge", "10W10E", "jorge@jorge.com", "12345", "11111111B", 1);
	
	// Checking all possibilities of the equals.
	assertEquals(-1, nico.compareTo(jorge));
	assertEquals(0, nico.compareTo(nico));
	assertEquals(1, jorge.compareTo(nico));
    }

}
