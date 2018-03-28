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

import org.bson.types.ObjectId;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import Foundation.CSVFile;
import Foundation.URL;

/**
 * Instance of the user.
 * 
 * @author Damian.
 * @since 06/02/2017
 */
@Document(collection = "agents")
@JsonPropertyOrder({ "name", "location", "email", "id", "kind", "kindCode" })
public class Agent implements Comparable<Agent> {

    @Id
    private ObjectId _id;

    private String name, email, password, id, location;
    private int kindCode;
    
    public final static String KIND_NOT_FOUND = "KIND NOT FOUND";

    Agent() {

    }

    public Agent(String name, String email, String password) {
	this.name = name;
	this.email = email;
	setPassword(password);
    }

    public Agent(String name, String location, String email, String password, String userId, int kindCode) {
	this(name, email, password);
	this.location = location;
	this.id = userId;
	this.kindCode = kindCode;
    }

    @Override
    public String toString() {
	final StringBuilder sb = new StringBuilder("{");
	sb.append("name='").append(name).append('\'');
	sb.append(",location='").append(location).append('\'');
	sb.append(",email='").append(email).append('\'');
	sb.append(",id='").append(id).append('\'');
	sb.append(",kind='").append(getKind()).append('\'');
	sb.append(",kindCode='").append(kindCode).append("'");
	sb.append('}');
	return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
	// Self check.
	if (this == o)
	    return true;

	// Null or different instance check.
	if (o == null || getClass() != o.getClass())
	    return false;

	// Casting the object to agent.
	Agent user = (Agent) o;
	return id.equals(user.id);
    }

    @Override
    public int compareTo(Agent anotherAgent) {
	return this.id.compareTo(anotherAgent.getId());
    }

    @Override
    public int hashCode() {
	return getId().hashCode();
    }

    /**
     * @return the name of the user.
     */
    public String getName() {
	return (name == null) ? name = "" : name;
    }

    /**
     * @return the email of the user.
     */
    public String getEmail() {
	return (email == null) ? email = "" : email;
    }

    /**
     * @return the password of the user.
     */
    @JsonIgnore
    @JsonProperty(value = "user_password")
    public String getPassword() {
	return password;
    }

    /**
     * @return get the user id.
     */
    public String getId() {
	return (id == null) ? id = "" : id;
    }

    public void setName(String name) {
	this.name = name;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public void setPassword(String password) {
	this.password = (password == null) ? new StrongPasswordEncryptor().encryptPassword(password = "")
		: new StrongPasswordEncryptor().encryptPassword(password);
    }

    public String getLocation() {
	return (location == null) ? location = "" : location;
    }

    public void setLocation(String location) {
	this.location = location;
    }

    /**
     * Gets the kind of user as a plain text. that will depend from a master csv
     * file.
     * 
     * @return the kind of user as a plain text. that will depend from a master csv
     *         file.
     */
    public String getKind() {
	try {
	    return CSVFile.of(new URL("src/main/resources/master.csv"), ",").getContent()
		    .get(Integer.toString(kindCode))[0];
	} catch (NullPointerException e) {
	    return "KIND NOT FOUND";
	}
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
     * @param kindCode
     *            to set to the user.
     */
    public void setKindCode(int kindCode) {
	this.kindCode = kindCode;
    }

}
