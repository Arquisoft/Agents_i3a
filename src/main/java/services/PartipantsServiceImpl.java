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
import domain.User;

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
	public User getParticipant(String email, String password) {
		User user = dat.getParticipant(email);
		if (user != null && new StrongPasswordEncryptor().checkPassword(password, user.getPassword()))
			return user;
		else
			return null;
	}

	@Override
	public void updateInfo(User user, String newPassword) {
		// It is not necessary, done by the domain class itself.
		user.setPassword(newPassword);
		dat.updateInfo(user);
	}
}
