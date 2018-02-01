package view;

import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import domain.UserLoginData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import services.ParticipantsService;

/**
 * Created by Nicolás on 17/02/2017.
 * Class that handles the login data response. Access the service layer and recovers the user
 */
public class UserResponseAction {
    private final ParticipantsService part;

    UserResponseAction(ParticipantsService part){
        this.part = part;
    }

    public ResponseEntity<UserInfo> execute(UserLoginData info){
        User user = part.getParticipant(info.getLogin(), info.getPassword());
        UserInfoAdapter data = new UserInfoAdapter(user);
        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else
            return new ResponseEntity<>(data.userToInfo(), HttpStatus.OK);
    }
}
