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
package domain_tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Calendar;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.junit.Before;
import org.junit.Test;

import Foundation.CSVFile;
import Foundation.URL;
import domain.Agent;

/**
 * Created by Damian on 15/02/2017.
 * 
 * Adapter by Víctor on 02/02/2018
 */
public class AgentTest {

    private Agent nico;
    private Agent jorge;
    private Agent damian;

    /**
     * Also a resources/master.csv file is needed with the following rows: 1 ,
     * Person 2 , Entity 3 , Sensor
     * 
     * @throws IOException
     */
    @Before
    public void setUp() throws IOException {
	Calendar cal = Calendar.getInstance();
	cal.set(1996, Calendar.JUNE, 12);
	nico = new Agent("Nicolás", "nico@nicomail.com", "nico123");
	jorge = new Agent("Jorge", "45N30E", "jorge@jorgemail.com", "jorge123", "111111111A", 2);
	cal.set(1997, Calendar.AUGUST, 1);
	damian = new Agent("Damian", "45S30W", "damian@damianmail.com", "damian123", "222222222B", 3);
    }

    @Test
    public void firstNameTest() {
	assertEquals("Nicolás", nico.getName());
	assertEquals("Jorge", jorge.getName());
	assertEquals("Damian", damian.getName());

	nico.setName("Antonio");
	assertEquals("Antonio", nico.getName());

	jorge.setName("Pepe");
	assertEquals("Pepe", jorge.getName());

	damian.setName("Roberto");
	assertEquals("Roberto", damian.getName());
    }

    @Test
    public void locationTest() {

	nico.setLocation(jorge.getLocation());
	assertEquals("45N30E", nico.getLocation());

	jorge.setLocation(damian.getLocation());
	assertEquals("45S30W", jorge.getLocation());

	damian.setLocation("30N48E");
	assertEquals("30N48E", damian.getLocation());
    }

    @Test
    public void emailTest() {

	nico.setEmail(damian.getEmail());
	assertEquals("damian@damianmail.com", nico.getEmail());

	jorge.setEmail("pepe@pepemail.com");
	assertEquals("pepe@pepemail.com", jorge.getEmail());

	damian.setEmail(jorge.getEmail());
	assertEquals("pepe@pepemail.com", damian.getEmail());
    }

    @Test
    public void passwordTest() {

	StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();

	nico.setPassword("1234");

	assertTrue(encryptor.checkPassword("1234", nico.getPassword()));

	jorge.setPassword("abcd");
	assertTrue(encryptor.checkPassword("abcd", jorge.getPassword()));

	damian.setPassword("yay");
	assertTrue(encryptor.checkPassword("yay", damian.getPassword()));
    }

    @Test
    public void nifTest() {

	assertEquals(null, nico.getUserId());

	assertEquals("111111111A", jorge.getUserId());

	assertEquals("222222222B", damian.getUserId());
    }

    @Test
    public void kindTest() throws IOException {
	nico.setKindCode(1);
	assertEquals("Person", nico.getKind());
	assertEquals("Entity", jorge.getKind());
	assertEquals(2, jorge.getKindCode());

	assertEquals("Sensor", damian.getKind());
	assertEquals(3, damian.getKindCode());

	nico.setKindCode(1);
	assertEquals("Person", nico.getKind());
	assertEquals(1, nico.getKindCode());

	jorge.setKindCode(1);
	assertEquals("Person", jorge.getKind());
	assertEquals(1, jorge.getKindCode());

	CSVFile previous = CSVFile.of(new URL("src/main/resources/master.csv"), ",");
	CSVFile testFile = new CSVFile(new URL("src/main/resources/master.csv"));
	testFile.setSeparator(",");
	String[] person = { "Person" }, entity = { "Entity" }, sensor = { "Sensor" }, iphone = { "iPhone" };
	testFile.getContent().put("1", person);
	testFile.getContent().put("2", entity);
	testFile.getContent().put("3", sensor);
	testFile.getContent().put("4", iphone);
	testFile.save();

	jorge.setKindCode(4);
	assertEquals("iPhone", jorge.getKind());
	testFile.getContent().replace("1", entity);
	testFile.getContent().replace("2", person);
	testFile.save();
	assertEquals("Entity", nico.getKind());

	previous.save();

    }

    @Test
    public void toStringTest() {
	nico.setKindCode(1);
	assertEquals("{" + "name='" + nico.getName() + "'," + "location='" + nico.getLocation() + "'," + "email='"
		+ nico.getEmail() + "'," + "id='" + nico.getUserId() + "'," + "kind='" + nico.getKind() + "',"
		+ "kindCode='" + nico.getKindCode() + "'" + "}", nico.toString());
    }

    @Test
    public void equalsTest() {
	Agent copyOfNico = new Agent(nico.getName(), nico.getLocation(), nico.getEmail(), nico.getPassword(), "111",
		nico.getKindCode());
	Agent anotherCopyOfNico = new Agent(nico.getName(), nico.getLocation(), nico.getEmail(), nico.getPassword(),
		"111", nico.getKindCode());

	assertEquals(true, anotherCopyOfNico.equals(copyOfNico));
    }

    @Test
    public void kindCodeTest() {

	jorge.setKindCode(2);
	assertEquals(2, jorge.getKindCode());
	damian.setKindCode(3);
	assertEquals(3, damian.getKindCode());
    }

}
