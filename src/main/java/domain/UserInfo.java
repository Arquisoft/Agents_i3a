package domain;

import java.io.Serializable;

/**
 * Created by Nicolás on 15/02/2017. Class that serves as a response for the
 * service, providing a subset of the User class' fields
 * 
 * Adapted by Víctor on 02/02/2018
 */
public class UserInfo implements Serializable {

	private String name;
	private String location;
	private String id;
	private String email;
	private String kind;
	private int kindCode;

	UserInfo() {

	}

	public UserInfo(String name, String location, String email, String id, String kind, int kindCode) {
		this.name = name;
		this.location = location;
		this.email = email;
		this.id = id;
		this.kind = kind;
		this.kindCode = kindCode;
	}

	public String getName() {
		return name;
	}


	public String getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String firstName) {
		this.name = firstName;
	}


	public void setId(String userId) {
		this.id = userId;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getLocation() {
	    return location;
	}

	public void setLocation(String location) {
	    this.location = location;
	}

	public String getKind() {
	    return kind;
	}

	public void setKind(String kind) {
	    this.kind = kind;
	}

	public int getKindCode() {
	    return kindCode;
	}

	public void setKindCode(int kindCode) {
	    this.kindCode = kindCode;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("UserInfo{");
		sb.append("name='").append(name).append('\'');
		sb.append(", location='").append(location).append('\'');
		sb.append(", email='").append(email).append('\'');
		sb.append(", id='").append(id).append('\'');
		sb.append(", kind='").append(kind).append('\'');
		sb.append(", kindCode=").append(kindCode);
		sb.append('}');
		return sb.toString();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		UserInfo userInfo = (UserInfo) o;

		return id.equals(userInfo.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
}
