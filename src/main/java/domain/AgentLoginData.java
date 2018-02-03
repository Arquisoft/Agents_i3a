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

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "data")
public class AgentLoginData {

    private String login;
    private String password;
    private int kind;

    public AgentLoginData() {

    }

    public AgentLoginData(String login, String password, int kind) {
	this.login = login;
	this.password = password;
	this.kind = kind;
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

    public int getKind() {
	return this.kind;
    }

    public void setKind(int kindOfAgent) {
	this.kind = kindOfAgent;
    }
}
