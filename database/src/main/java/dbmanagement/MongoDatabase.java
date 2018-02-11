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
package dbmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Agent;

@Service
public class MongoDatabase implements Database {

    @Autowired
    private UsersRepository users;

    @Override
    public void updateInfo(Agent user) {
	users.save(user);
    }

    @Override
    public Agent getParticipant(String email) {
	return users.findById(email);
    }

}
