package domain;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

/**
 * Created by Nicolás on 15/02/2017.
 * Class in charge of translating a User object into the response format
 * Note: this class only creates a model class that contains a subset of the fields in the User class
 */
public class UserInfoAdapter {

    private User user;

    public UserInfoAdapter(User user) {
        this.user = user;
    }

    public UserInfo userToInfo(){
        LocalDate current = new Date().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate then = user.getDateOfBirth().toInstant()
                .atZone(ZoneId.systemDefault()).toLocalDate();
        Period per = Period.between(then, current);
        return new UserInfo(user.getFirstName(), user.getLastName(),
                per.getYears(), user.getUserId(), user.getEmail());
    }
}
