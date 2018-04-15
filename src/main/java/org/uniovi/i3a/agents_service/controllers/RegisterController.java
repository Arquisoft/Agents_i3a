package org.uniovi.i3a.agents_service.controllers;

import java.util.Map;

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
public class RegisterController {

    @Autowired
    AgentsService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST, consumes = {
	    MediaType.APPLICATION_JSON_VALUE }, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> register(@RequestBody Map<String, Object> payload) {

	if (!isPayloadCorrect(payload)) {
	    // Not valid data.
	    log.warn("Not valid register attemp: " + new JSONObject(payload).toString());
	    return new ResponseEntity<String>("{\"response\":\"agent not registered\"}", HttpStatus.BAD_REQUEST);
	}

	Agent agent = new Agent(payload.get("name").toString(), payload.get("location").toString(),
		payload.get("email").toString(), payload.get("password").toString(), payload.get("username").toString(),
		(int) payload.get("kindCode"));

	service.save(agent);

	log.info("Agent registered: " + new JSONObject(payload).toString());
	return new ResponseEntity<String>("{\"response\":\"agent registered\"}", HttpStatus.CREATED);
    }

    /**
     * Checks if the payload is correct.
     * @param payload
     * @return
     */
    private boolean isPayloadCorrect(Map<String, Object> payload) {
	// TODO Auto-generated method stub
	return true;
    }
}
