package domain_tests;

import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nicolás on 18/02/2017.
 */
public class UserAdapterTest {

    private User user1;
    private User user2;

    @Before
    public void setUp(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1996);
        user1 = new User("User1", "User1Apellido", "User1@hola.com", "user1Password",
                new Date(cal.getTime().getTime()), "C/ hola", "spanish", "112233");
        user2 = new User("User2", "User2Apellido", "User2@hola.com", "user2Password",
                new Date(), "C/ hola", "spanish", "112233");
    }

    @Test
    public void testAdapter(){
        UserInfoAdapter adapter = new UserInfoAdapter(user1);
        UserInfo info = adapter.userToInfo();
        assertEquals(info.getFirstName(), user1.getFirstName());
        assertEquals(info.getLastName(), user1.getLastName());
        assertEquals(info.getEmail(), user1.getEmail());
        assertEquals(info.getUserId(), user1.getUserId());
        assertEquals(info.getAge(), 21);
    }

    @Test
    public void testToString(){
        UserInfoAdapter adapter = new UserInfoAdapter(user2);
        UserInfo info = adapter.userToInfo();
        String toString = info.toString();
        String test = "UserInfo{firstName='User2', lastName='User2Apellido', " +
                "age=0, userId='112233', email='User2@hola.com'}";
        assertEquals(toString, test);
    }

}
