package domain_tests;

import domain.User;
import util.JasyptEncryptor;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Damian on 15/02/2017.
 */
public class UserTest {

    private User nico;
    private User jorge;
    private User damian;

    @Before
    public void setUp(){
        Calendar cal = Calendar.getInstance();
        cal.set(1996, Calendar.JUNE, 12);
        nico = new User("Nicolás", "Rivero", "nico@nicomail.com","nico123");
        jorge = new User("Jorge", "Zapatero", "jorge@jorgemail.com", "jorge123", cal.getTime(), "C/ La calle", "España", "111111111A");
        cal.set(1997, Calendar.AUGUST, 1);
        damian = new User("Damian", "Rubio", "damian@damianmail.com", "damian123", cal.getTime(), "C/ The street", "Inglaterra", "222222222B");
    }

    @Test
    public void firstNameTest(){
        Assert.assertEquals("Nicolás", nico.getFirstName());
        Assert.assertEquals("Jorge", jorge.getFirstName());
        Assert.assertEquals("Damian", damian.getFirstName());

        nico.setFirstName("Antonio");
        Assert.assertEquals("Antonio", nico.getFirstName());

        jorge.setFirstName("Pepe");
        Assert.assertEquals("Pepe", jorge.getFirstName());

        damian.setFirstName("Roberto");
        Assert.assertEquals("Roberto", damian.getFirstName());
    }

    @Test
    public void lastNameTest(){

        nico.setLastName(jorge.getLastName());
        Assert.assertEquals("Zapatero", nico.getLastName());

        jorge.setLastName(damian.getLastName());
        Assert.assertEquals("Rubio", jorge.getLastName());

        damian.setLastName("Fernández");
        Assert.assertEquals("Fernández", damian.getLastName());
    }

    @Test
    public void emailTest(){

        nico.setEmail(damian.getEmail());
        Assert.assertEquals("damian@damianmail.com", nico.getEmail());

        jorge.setEmail("pepe@pepemail.com");
        Assert.assertEquals("pepe@pepemail.com", jorge.getEmail());

        damian.setEmail(jorge.getEmail());
        Assert.assertEquals("pepe@pepemail.com", damian.getEmail());
    }

    @Test
    public void passwordTest(){

    	JasyptEncryptor encryptor = new JasyptEncryptor();
    	
        nico.setPassword("1234");
        
    
        Assert.assertTrue(encryptor.checkPassword( "1234",nico.getPassword()));

        jorge.setPassword("abcd");
        Assert.assertTrue(encryptor.checkPassword( "abcd",jorge.getPassword()));

        damian.setPassword("yay");
        Assert.assertTrue(encryptor.checkPassword( "yay",damian.getPassword()));
    }

    @Test
    public void dateOfBirthTest(){
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
    public void addressTest(){

        nico.setAddress("C/ Su calle");
        Assert.assertEquals("C/ Su calle", nico.getAddress());

        jorge.setAddress(damian.getAddress());
        Assert.assertEquals("C/ The street", jorge.getAddress());

        damian.setAddress(nico.getAddress());
        Assert.assertEquals("C/ Su calle", damian.getAddress());
    }

    @Test
    public void nationalityTest(){

        Assert.assertEquals(null, nico.getNationality());
        nico.setNationality("Swazilandia");
        Assert.assertEquals("Swazilandia", nico.getNationality());

        jorge.setNationality(damian.getNationality());
        Assert.assertEquals("Inglaterra", jorge.getNationality());

        damian.setNationality(nico.getNationality());
        Assert.assertEquals("Swazilandia", damian.getNationality());
    }

    @Test
    public void nifTest(){

        Assert.assertEquals(null, nico.getUserId());

        Assert.assertEquals("111111111A", jorge.getUserId());

        Assert.assertEquals("222222222B", damian.getUserId());
    }

}
