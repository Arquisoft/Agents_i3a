package domain_tests;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import domain.User;
import util.JasyptEncryptor;

/**
 * Created by Damian on 15/02/2017.
 * 
 * Adapter by Víctor on 02/02/2018
 */
public class UserTest {

	private User nico;
	private User jorge;
	private User damian;

	/**
	 * Also a resources/master.csv file is needed with the following rows:
	 * 1 , Person
	 * 2 , Entity
	 * 3 , Sensor
	 * @throws IOException 
	 */
	@Before
	public void setUp() throws IOException {
		Calendar cal = Calendar.getInstance();
		cal.set(1996, Calendar.JUNE, 12);
		nico = new User("Nicolás", "nico@nicomail.com", "nico123");
		jorge = new User("Jorge", "45N30E", "jorge@jorgemail.com", "jorge123", cal.getTime(), "C/ La calle", "España",
				"111111111A", 2);
		cal.set(1997, Calendar.AUGUST, 1);
		damian = new User("Damian", "45S30W", "damian@damianmail.com", "damian123", cal.getTime(), "C/ The street",
				"Inglaterra", "222222222B",3);
	}

	@Test
	public void firstNameTest() {
		Assert.assertEquals("Nicolás", nico.getName());
		Assert.assertEquals("Jorge", jorge.getName());
		Assert.assertEquals("Damian", damian.getName());

		nico.setName("Antonio");
		Assert.assertEquals("Antonio", nico.getName());

		jorge.setName("Pepe");
		Assert.assertEquals("Pepe", jorge.getName());

		damian.setName("Roberto");
		Assert.assertEquals("Roberto", damian.getName());
	}

	@Test
	public void locationTest() {

		nico.setLocation(jorge.getLocation());
		Assert.assertEquals("45N30E", nico.getLocation());

		jorge.setLocation(damian.getLocation());
		Assert.assertEquals("45S30W", jorge.getLocation());

		damian.setLocation("30N48E");
		Assert.assertEquals("30N48E", damian.getLocation());
	}

	@Test
	public void emailTest() {

		nico.setEmail(damian.getEmail());
		Assert.assertEquals("damian@damianmail.com", nico.getEmail());

		jorge.setEmail("pepe@pepemail.com");
		Assert.assertEquals("pepe@pepemail.com", jorge.getEmail());

		damian.setEmail(jorge.getEmail());
		Assert.assertEquals("pepe@pepemail.com", damian.getEmail());
	}

	@Test
	public void passwordTest() {

		JasyptEncryptor encryptor = new JasyptEncryptor();

		nico.setPassword("1234");

		Assert.assertTrue(encryptor.checkPassword("1234", nico.getPassword()));

		jorge.setPassword("abcd");
		Assert.assertTrue(encryptor.checkPassword("abcd", jorge.getPassword()));

		damian.setPassword("yay");
		Assert.assertTrue(encryptor.checkPassword("yay", damian.getPassword()));
	}

	@Test
	public void dateOfBirthTest() {
		DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
		Calendar cal = Calendar.getInstance();

		cal.set(1996, Calendar.JUNE, 12);
		Assert.assertEquals(format.format(cal.getTime()), format.format(jorge.getDateOfBirth()));

		cal.set(1996, Calendar.AUGUST, 12);
		nico.setDateOfBirth(cal.getTime());
		Assert.assertEquals(format.format(cal.getTime()), format.format(nico.getDateOfBirth()));

		cal.set(1900, Calendar.FEBRUARY, 1);
		damian.setDateOfBirth(cal.getTime());
		Assert.assertEquals(format.format(cal.getTime()), format.format(damian.getDateOfBirth()));
	}

	@Test
	public void addressTest() {

		nico.setAddress("C/ Su calle");
		Assert.assertEquals("C/ Su calle", nico.getAddress());

		jorge.setAddress(damian.getAddress());
		Assert.assertEquals("C/ The street", jorge.getAddress());

		damian.setAddress(nico.getAddress());
		Assert.assertEquals("C/ Su calle", damian.getAddress());
	}

	@Test
	public void nationalityTest() {

		Assert.assertEquals(null, nico.getNationality());
		nico.setNationality("Swazilandia");
		Assert.assertEquals("Swazilandia", nico.getNationality());

		jorge.setNationality(damian.getNationality());
		Assert.assertEquals("Inglaterra", jorge.getNationality());

		damian.setNationality(nico.getNationality());
		Assert.assertEquals("Swazilandia", damian.getNationality());
	}

	@Test
	public void nifTest() {

		Assert.assertEquals(null, nico.getUserId());

		Assert.assertEquals("111111111A", jorge.getUserId());

		Assert.assertEquals("222222222B", damian.getUserId());
	}
	
	@Test
	public void kindTest() throws IOException
	{
	    	Assert.assertEquals(null, nico.getKind());
		Assert.assertEquals("Entity", jorge.getKind());
		Assert.assertEquals(2, jorge.getKindCode());
		
		Assert.assertEquals("Sensor", damian.getKind());
		Assert.assertEquals(3, damian.getKindCode());
		
		nico.setKindCode(1);
		Assert.assertEquals("Person", nico.getKind());
		Assert.assertEquals(1, nico.getKindCode());
		
		jorge.setKindCode(1);
		Assert.assertEquals("Person", jorge.getKind());
		Assert.assertEquals(1, jorge.getKindCode());
		
	}
	
	@Test
	public void kindCodeTest()
	{

		Assert.assertEquals(2, jorge.getKindCode());

		Assert.assertEquals(3, damian.getKindCode());
	}

}
