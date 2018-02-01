package dbmanagement;

import domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoDatabase implements Database{
	
	@Autowired
	private UsersRepository users;

    @Override
	public void updateInfo(User user) {
		users.save(user);
	}

	@Override
	public User getParticipant(String email) {
		return users.findByEmail(email);
	}

}
