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

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import categories.IntegrationTest;
import dbmanagement.UsersRepository;
import domain.Agent;
import main.Application;

@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
public class ControllerTest {

    @Autowired
    private WebApplicationContext context;

    private static final String QUERY_STRING = "{\"login\":\"%s\", \"password\":\"%s\", \"kind\":\"%s\"}";

    // MockMvc --> Para realizar peticiones y comprobar resultado, usado para
    // respuestas con informacion json.
    private MockMvc mockMvc;

    @Autowired
    private UsersRepository repo;

    private MockHttpSession session;

    private Agent maria;
    private String plainPassword;

    @Before
    public void setUp() throws Exception {

	this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();

	session = new MockHttpSession();

	// Setting up maria
	plainPassword = "pass14753";
	maria = new Agent("Maria", "10N30E", "asd", plainPassword, "158", 1);
	repo.insert(maria);

    }

    @After
    public void tearDown() {
	repo.delete(maria);
    }

    @Test
    public void RESTJSONRequestTest() throws Exception {
	String payload = String.format(QUERY_STRING, maria.getId(), plainPassword, maria.getKindCode());
	// We send a POST request to that URI (from http:localhost...)
	MockHttpServletRequestBuilder request = post("/user").session(session).contentType(MediaType.APPLICATION_JSON)
		.content(payload.getBytes());
	// AndDoPrint it is very usefull to see the http response and see if
	// something went wrong.
	mockMvc.perform(request).andDo(print())
		// The state of the response must be OK. (200);
		.andExpect(status().isOk())
		// We can do jsonpaths in order to check 1996
		.andExpect(jsonPath("$.name", is(maria.getName()))).andExpect(jsonPath("$.id", is(maria.getId())))
		.andExpect(jsonPath("$.location", is(maria.getLocation())))
		.andExpect(jsonPath("$.email", is(maria.getEmail()))).andExpect(jsonPath("$.kind", is(maria.getKind())))
		.andExpect(jsonPath("$.kindCode", is(maria.getKindCode())));
    }

    @Test
    public void RESTXMLRequestTest() throws Exception {
	String payload = String.format("<data><login>%s</login><password>%s</password><kind>%s</kind></data>",
		maria.getId(), plainPassword, maria.getKindCode());
	// POST request with XML content
	MockHttpServletRequestBuilder request = post("/user").session(session)
		.contentType(MediaType.APPLICATION_XML_VALUE).content(payload.getBytes());
	// AndDoPrint it is very usefull to see the http response and see if
	// something went wrong.
	mockMvc.perform(request).andDo(print())
		// The state of the response must be OK. (200);
		.andExpect(status().isOk())
		// We can do jsonpaths in order to check
		.andExpect(jsonPath("$.name", is(maria.getName()))).andExpect(jsonPath("$.id", is(maria.getId())))
		.andExpect(jsonPath("$.location", is(maria.getLocation())))
		.andExpect(jsonPath("$.email", is(maria.getEmail()))).andExpect(jsonPath("$.kind", is(maria.getKind())))
		.andExpect(jsonPath("$.kindCode", is(maria.getKindCode())));
    }

    @Test
    public void webInterfaceLoginTest() throws Exception {
	MockHttpServletRequestBuilder request = post("/userForm").session(session).param("login", maria.getId())
		.param("password", plainPassword).param("kind", Integer.toString(maria.getKindCode()));
	mockMvc.perform(request).andExpect(status().isOk());
    }

    @Test
    public void notFoundTest() throws Exception {
	String payload = String.format(QUERY_STRING, "Nothing", "Not really", -1);
	MockHttpServletRequestBuilder request = post("/user").session(session).contentType(MediaType.APPLICATION_JSON)
		.content(payload.getBytes());
	mockMvc.perform(request).andDo(print())// AndDoPrint it is very
					       // usefull to see the http
					       // response and see if
					       // something went wrong.
		.andExpect(status().isNotFound()); // The state of the
						   // response must be OK.
						   // (200);
    }

    /**
     * Should return a 404 as before
     */
    @Test
    public void passwordTest() throws Exception {
	String payload = String.format(QUERY_STRING, maria.getId(), "Not maria's password", 1);
	MockHttpServletRequestBuilder request = post("/user").session(session).contentType(MediaType.APPLICATION_JSON)
		.content(payload.getBytes());
	mockMvc.perform(request).andDo(print()).andExpect(status().isNotFound());
    }
}
