package org.uniovi.i3a.agents_service.controllers;

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
import org.uniovi.i3a.agents_service.Service;
import org.uniovi.i3a.agents_service.services.AgentsService;
import org.uniovi.i3a.agents_service.types.Agent;

import TestKit.IntegrationTest;

@SpringBootTest(classes = { Service.class })
@RunWith(SpringJUnit4ClassRunner.class)
@Category(IntegrationTest.class)
public class AuthTest {

    @Autowired
    private WebApplicationContext context;
    private static final String QUERY_STRING = "{\"login\":\"%s\", \"password\":\"%s\", \"kind\":\"%s\"}";
    private MockMvc mockMvc;

    @Autowired
    private AgentsService service;
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
	service.save(maria);
    }

    @After
    public void tearDown() {
	service.delete(maria);
    }

    @Test
    public void successfulRequestTest() throws Exception {
	String payload = String.format(QUERY_STRING, maria.getId(), plainPassword, maria.getKindCode());
	
	// We send a POST request to that URI (from http:localhost...)
	MockHttpServletRequestBuilder request = post("/auth").session(session).contentType(MediaType.APPLICATION_JSON)
		.content(payload.getBytes());
	
	// AndDoPrint it is very usefull to see the http response and see if
	// something went wrong.
	mockMvc.perform(request).andDo(print())
		// The state of the response must be OK. (200);
		.andExpect(status().isOk())
		// We can do jsonpaths in order to check 1996
		.andExpect(jsonPath("$.name", is(maria.getName())))
		.andExpect(jsonPath("$.id", is(maria.getId())))
		.andExpect(jsonPath("$.location", is(maria.getLocation())))
		.andExpect(jsonPath("$.email", is(maria.getEmail())))
		.andExpect(jsonPath("$.kind", is(maria.getKind())))
		.andExpect(jsonPath("$.kindCode", is(maria.getKindCode())));
    }
    
    @Test
    public void unSuccessfulRequestTest() throws Exception {
	String payload = String.format(QUERY_STRING, "Nothing", "Not really", -1);
	MockHttpServletRequestBuilder request = post("/auth").session(session).contentType(MediaType.APPLICATION_JSON)
		.content(payload.getBytes());
	mockMvc.perform(request).andDo(print())// AndDoPrint it is very
					       // usefull to see the http
					       // response and see if
					       // something went wrong.
		.andExpect(status().isUnauthorized()); // The state of the
						   // response must be OK.
						   // (200);
    }

    @Test
    public void successfulRequestWithWrongPasswordTest() throws Exception {
	String payload = String.format(QUERY_STRING, maria.getId(), "Not maria's password", 1);
	MockHttpServletRequestBuilder request = post("/auth").session(session).contentType(MediaType.APPLICATION_JSON)
		.content(payload.getBytes());
	mockMvc.perform(request).andDo(print()).andExpect(status().isUnauthorized());
    }

}
