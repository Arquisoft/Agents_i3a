package domain_tests;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import domain.Kind;
import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;

/**
 * Created by Nicolás on 18/02/2017.
 * 
 * Adapter by Víctor on 02/02/2018
 */
public class UserAdapterTest {

	private User user1;
	private User user2;
	
	@Before
	public void setUp() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1996);
		user1 = new User("User1", "10N20E", "User1@hola.com", "user1Password", new Date(cal.getTime().getTime()),
				"C/ hola", "spanish", "112233", Kind.PERSON);
		user2 = new User("User2", "20S10W", "User2@hola.com", "user2Password", new Date(), "C/ hola", "spanish",
				"112233", Kind.PERSON);
	}

	@Test
	public void testAdapter() {
		UserInfoAdapter adapter = new UserInfoAdapter(user1);
		UserInfo info = adapter.userToInfo();
		assertEquals(info.getName(), user1.getName());
		assertEquals(info.getLocation(), user1.getLocation());
		assertEquals(info.getEmail(), user1.getEmail());
		assertEquals(info.getId(), user1.getUserId());
		assertEquals(info.getLocation(), user1.getLocation());
	}

	@Test
	public void testToString() {
		UserInfoAdapter adapter = new UserInfoAdapter(user2);
		UserInfo info = adapter.userToInfo();
		String toString = info.toString();
		String test = "UserInfo{name='User2', location='20S10W', "
				+ "email='User2@hola.com', id='112233', kind='PERSON', kindCode=1}";
		assertEquals(toString, test);
	}

}
