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
package services;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dbmanagement.Database;
import domain.Agent;

/**
 * Instance of PartipantsServiceImpl
 *
 * @author Nicolás
 * @version
 */
@Service
public class PartipantsServiceImpl implements ParticipantsService {

    private final Database dat;

    @Autowired
    PartipantsServiceImpl(Database dat) {
	this.dat = dat;
    }

    @Override
    public Agent getParticipant(String identifier, String password, int kindOfAgent) {
	Agent user = dat.getParticipant(identifier);
	System.out.println(user);
	if (user != null && new StrongPasswordEncryptor().checkPassword(password, user.getPassword())
		&& user.getKindCode() == kindOfAgent)
	    return user;
	else
	    return null;
    }

    @Override
    public void updateInfo(Agent user, String newPassword) {
	// It is not necessary, done by the domain class itself.
	user.setPassword(newPassword);
	dat.updateInfo(user);
    }
}
