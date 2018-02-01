package domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class UserLoginData {

	private String login;
	private String password;

	public UserLoginData(){

	}

	public UserLoginData(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getLogin() {
		return login;
	}

    public String getPassword() {
		return password;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
