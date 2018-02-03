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

/**
 * Created by Nicolás on 15/02/2017. Class in charge of translating a User
 * object into the response format Note: this class only creates a model class
 * that contains a subset of the fields in the User class
 * 
 * Adapted by Víctor on 02/02/2018
 * 
 */

/**
 * Class in charge of translating a User object into the response format Note:
 * this class only creates a model class that contains a subset of the fields in
 * the User class.
 * 
 * Adapted by Víctor on 02/02/2018
 *
 * @author Nicolás
 * @since 15/02/2017
 */
public class AgentInfoAdapter {

    private Agent user;

    public AgentInfoAdapter(Agent user) {
	this.user = user;
    }

    public AgentInfo userToInfo() {
	return new AgentInfo(user.getName(), user.getLocation(), user.getEmail(), user.getUserId(), user.getKindCode());
    }
}
