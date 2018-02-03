/*
 * This source file is part of the Agents_i3a open source project.
 *
 * Copyright (c) 2017 Agents_i3a project authors.
 * Licensed under MIT License.
 *
 * See /LICENSE for license information.
 * 
 * This class is based on the AlbUtil project.
 * 
 */
package domain;

import java.io.IOException;
import java.util.Date;

import org.bson.types.ObjectId;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import Foundation.CSVFile;
import Foundation.URL;

/**
 * Instance of the user.
 * 
 * @author Damian.
 * @since 06/02/2017
 */
@Document(collection = "users")
public class Agent {

    @Id
    private ObjectId id;

    private String name;
    private String email;
    private String password;
    private String userId;
    private String location;
    private int kindCode;

    Agent() {

    }

    public Agent(String name, String email, String password) {
	this.name = name;
	this.email = email;
	setPassword(password);
    }

    public Agent(String name, String location, String email, String password, String userId, int kindCode) throws IOException {
	this(name, email, password);
	this.location = location;
	this.userId = userId;
	this.kindCode = kindCode;
	setKindCode(kindCode);
    }

    @Override
    public String toString() {
	return "User{" + "name='" + name + '\'' + ", lastName='" + location + '\'' + ", email='" + email + '\''
		+ ", password='" + password + '\'' + ", id='" + userId + '\'' + ", kind='"
		+ getKind()
		+ '\'' + ", kindcode=" + kindCode + '}';
    }

    @Override
    public boolean equals(Object o) {
	if (this == o)
	    return true;
	if (o == null || getClass() != o.getClass())
	    return false;

	Agent user = (Agent) o;

	return userId.equals(user.userId);

    }

    @Override
    public int hashCode() {
	return userId.hashCode();
    }

    /**
     * @return the name of the user.
     */
    public String getName() {
	return name;
    }

    /**
     * @return the email of the user.
     */
    public String getEmail() {
	return email;
    }

    /**
     * @return the password of the user.
     */
    public String getPassword() {
	return password;
    }

    /**
     * @return get the user id.
     */
    public String getUserId() {
	return userId;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public void setPassword(String password) {
	this.password =  new StrongPasswordEncryptor().encryptPassword(password);
    }

    public String getLocation() {
	return location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    /**
     * Gets the kind of user as a plain text. that will depend from a master csv file.
     * 
     * @return the kind of user as a plain text. that will depend from a master csv file.
     */
    public String getKind() {
	return CSVFile.of(new URL("src/main/resources/master.csv"), ",").getContent().get(Integer.toString(kindCode))[0];
    }

    /**
     * Gives the kind of user as a code (int type).
     * 
     * @return the kind of user as a code (int type).
     */
    public int getKindCode() {
	return kindCode;
    }

    /**
     * Sets the kind of user from a code.
     * 
     * @param kindCode to set to the user.
     */
    public void setKindCode(int kindCode) {
	this.kindCode = kindCode;
    }

}
