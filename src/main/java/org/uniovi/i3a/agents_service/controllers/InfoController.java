package org.uniovi.i3a.agents_service.controllers;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class InfoController {
    
    @RequestMapping(value = "/info")
    public ResponseEntity<String> info() {
	
	Map<String, String> payload = new HashMap<String, String>();
	payload.put("service-name", "agents_service");
	payload.put("service-description", "");

	log.info("Information about the service produced: " + new  JSONObject(payload).toString());
	return new ResponseEntity<String>(new  JSONObject(payload).toString(), HttpStatus.OK);
    }

}
