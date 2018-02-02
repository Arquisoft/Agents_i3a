package database_tests;

import java.util.Calendar;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import dbmanagement.Database;
import dbmanagement.UsersRepository;
import domain.Kind;
import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import main.Application;
import util.JasyptEncryptor;

/**
 * Created by Nicolás on 15/02/2017.
 * 
 * Adapter by Víctor on 02/02/2018
 */
@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class DatabaseTest {

	@Autowired
	private UsersRepository repo;

	// User to use as reference for test
	private User testedUser;
	private User testedUser2;
	private Calendar user2cal;

	@Autowired
	private Database dat;

	/*
	 * Para este test se necesita el siguiente documento en la base de datos: {
	 * "_id" : ObjectId("5893a06ace8c8e1b79d8a9a9"), "_class" : "Model.User",
	 * "firstName" : "Maria", "location" : "10N30E", "password" :
	 * "9gvHm9TI57Z9ZW8/tTu9Nk10NDZayLIgKcFT8WdCVXPeY5gF57AFjS/l4nKNY1Jq",
	 * "dateOfBirth" : ISODate("1982-12-27T23:00:00.000Z"), "address" : "Hallo",
	 * "nationality" : "Core", "userId" : "321", "email" : "asd", "kind": "Person", kindCode:1 }
	 */
	@Before
	public void setUp() {
		testedUser = new User("Luis", "48.2S35E", "LGracia@gmail.com", "Luis123", new Date(), "Calle alfonso", "Spain",
				"147",Kind.PERSON);
		repo.insert(testedUser);

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1990);
		cal.set(Calendar.MONTH, 1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		user2cal = cal;
		testedUser2 = new User("Maria", "10N30E", "asd", "pass14753", cal.getTime(), "Hallo", "Core", "158",Kind.PERSON);
		repo.insert(testedUser2);
	}

	@After
	public void tearDown() {
		repo.delete(testedUser);
		repo.delete(testedUser2);
	}

	@Test
	public void testGetParticipant() {
		// It should be previously encoded if the DB is given so this may be changed.
		User user = dat.getParticipant("LGracia@gmail.com");
		user.setNationality("USA");
		Assert.assertEquals(user.getNationality(), "USA");
		Assert.assertNotEquals(testedUser.getNationality(), user.getNationality());
		User DBUser = dat.getParticipant("LGracia@gmail.com"); // just in case, same as before.
		Assert.assertNotEquals(user.getNationality(), DBUser.getNationality()); // Should be different from as we
																				// changed a transient one.
	}

	@Test
	public void testUpdateInfoWithPassword() {
		// It should be previously encoded if the DB is given so this may be changed.
		User user = dat.getParticipant("LGracia@gmail.com");
		user.setPassword("confidencial");
		JasyptEncryptor encryptor = new JasyptEncryptor();
		dat.updateInfo(user);
		User userAfter = dat.getParticipant("LGracia@gmail.com");
		Assert.assertTrue(encryptor.checkPassword("confidencial", userAfter.getPassword())); // They should be the same
																								// when we introduce the
																								// password.
		Assert.assertEquals(user, userAfter); // They should be the same user by the equals.

	}

	@Test
	public void testUpdateInfoAndAdaptation() {
		User user = dat.getParticipant("asd");

		Assert.assertEquals("Maria", user.getName());
		Assert.assertEquals("10N30E", user.getLocation());
		Assert.assertEquals(user2cal.getTime(), user.getDateOfBirth());
		Assert.assertEquals("Hallo", user.getAddress());
		Assert.assertEquals("Core", user.getNationality());
		Assert.assertEquals("158", user.getUserId());
		Assert.assertEquals("asd", user.getEmail());
		Assert.assertEquals(Kind.PERSON, user.getKind());
		Assert.assertEquals(Kind.PERSON.getValue(), user.getKindCode());

		UserInfoAdapter userAdapter = new UserInfoAdapter(user);

		UserInfo userInfo = userAdapter.userToInfo();

		Assert.assertEquals(user.getName(), userInfo.getName());
		Assert.assertEquals(user.getLocation(), userInfo.getLocation());
		Assert.assertEquals(user.getEmail(), userInfo.getEmail());
		Assert.assertEquals(user.getUserId(), userInfo.getId());
		Assert.assertEquals("PERSON", userInfo.getKind());
		Assert.assertEquals(1, userInfo.getKindCode());

		user.setName("Pepa");
		user.setLocation("45N35.5W");
		
		dat.updateInfo(user);
		User updatedUser = dat.getParticipant("asd");
		Assert.assertEquals("Pepa", updatedUser.getName());
		Assert.assertEquals("45N35.5W", updatedUser.getLocation());
		Assert.assertEquals(user2cal.getTime(), updatedUser.getDateOfBirth());
		Assert.assertEquals("Hallo", updatedUser.getAddress());
		Assert.assertEquals("Core", updatedUser.getNationality());
		Assert.assertEquals("158", updatedUser.getUserId());
		Assert.assertEquals("asd", updatedUser.getEmail());
		Assert.assertEquals("PERSON", userInfo.getKind());
		Assert.assertEquals(1, userInfo.getKindCode());

	}

}
