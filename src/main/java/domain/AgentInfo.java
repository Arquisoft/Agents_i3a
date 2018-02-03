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

import java.io.Serializable;

/**
 * Class that serves as a response for the service, providing a subset of the
 * User class' fields.
 * 
 * Adapted by Víctor on 02/02/2018
 * 
 * @author Nicolás.
 * @since 15/02/2017
 */
public class AgentInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private String location;
    private String id;
    private String email;
    private String kind;
    private int kindCode;

    public AgentInfo(String name, String location, String email, String id, String kind, int kindCode) {
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

	AgentInfo userInfo = (AgentInfo) o;

	return id.equals(userInfo.id);
    }

    @Override
    public int hashCode() {
	return id.hashCode();
    }
}
