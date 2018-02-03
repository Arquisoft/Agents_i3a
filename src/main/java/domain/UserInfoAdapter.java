package domain;

/**
 * Created by Nicolás on 15/02/2017. Class in charge of translating a User
 * object into the response format Note: this class only creates a model class
 * that contains a subset of the fields in the User class
 * 
 * Adapted by Víctor on 02/02/2018
 * 
 */
public class UserInfoAdapter {

	private User user;

	public UserInfoAdapter(User user) {
		this.user = user;
	}

	public UserInfo userToInfo() {
		return new UserInfo(user.getName(), user.getLocation(), user.getEmail(), user.getUserId(), user.getKind() , user.getKindCode());
	}
}
