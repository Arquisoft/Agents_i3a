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
package controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import domain.Agent;
import domain.AgentLoginData;
import services.ParticipantsService;

/**
 * Class that handles the login data response. Access the service layer and
 * recovers the user
 *
 * @author Nicolás
 * @since 17/02/2017
 */
public class LoginResponseController {
    private final ParticipantsService part;

    LoginResponseController(ParticipantsService part) {
	this.part = part;
    }

    public ResponseEntity<Agent> execute(AgentLoginData info) {
	
	Agent user = part.getParticipant(info.getLogin(), info.getPassword(), info.getKind());
	
	//AgentInfoAdapter data = new AgentInfoAdapter(user);
	if (user == null) {
	    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	} else {
	    //return new ResponseEntity<>(data.userToInfo(), HttpStatus.OK);
	    return new ResponseEntity<>(user, HttpStatus.OK);
	}
    }
}
