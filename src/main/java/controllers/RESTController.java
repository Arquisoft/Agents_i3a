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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import domain.Agent;
import domain.AgentLoginData;
import services.ParticipantsService;

@RestController
public class RESTController {

    private final ParticipantsService part;

    @Autowired
    RESTController(ParticipantsService part) {
	this.part = part;
    }

    @RequestMapping(value = "/user", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE,
	    MediaType.APPLICATION_XML_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<Agent> userOkJSON(@RequestBody AgentLoginData info) {
	return new LoginResponseController(part).execute(info);
    }

}