package org.uniovi.i3a.agents_service.controllers;

import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.uniovi.i3a.agents_service.services.AgentsService;
import org.uniovi.i3a.agents_service.types.Agent;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AuthController {

    @Autowired
    AgentsService service;

    @RequestMapping(value = "/auth", method = RequestMethod.POST, consumes = {
	    MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> auth(@RequestBody Map<String, Object> payload) {

	Agent agent =
		service.getAgent(
			payload.get("login").toString(),
			payload.get("password").toString(),
			Integer.parseInt(payload.get("kind").toString()));

	if (agent != null) {
	    log.info("Login succesful for : " + agent.toString());
	    
	    try {
		return new ResponseEntity<String>(new JSONObject(agent.toString()).toString(), HttpStatus.OK);
	    } catch (JSONException e) {
		log.error("Exception generated when trying to send response as JSONObject: " + e.getMessage());
		log.trace(e.getMessage());
	    }
	}

	// No valid user found for the data provided.
	log.warn("Unauthorized access attemp: " + new JSONObject(payload).toString());
	return new ResponseEntity<String>("{\"response\":\"unauthorized\"}", HttpStatus.UNAUTHORIZED);
    }
}
