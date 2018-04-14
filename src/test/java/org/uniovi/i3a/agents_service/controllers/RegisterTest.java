package org.uniovi.i3a.agents_service.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
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
public class RegisterTest {

    // {"name":"colunga91","location":"10N99W", "email":"colunga91@gmail.com",
    // "password":"123456","username":"47170929X","kindCode":1}

    @Autowired
    private WebApplicationContext context;
    private static final String QUERY_STRING = "{\"name\":\"%s\", \"location\":\"%s\", \"email\":\"%s\", \"password\":\"%s\", \"username\":\"%s\", \"kindCode\":%s}";
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
    }

    @After
    public void tearDown() {
	maria = service.getAgent(maria.getId(), plainPassword, maria.getKindCode());
	service.delete(maria);
    }

    @Test
    public void successfulInsertTest() throws Exception {
	String payload = String.format(QUERY_STRING, maria.getName(), maria.getLocation(), maria.getEmail(),
		plainPassword, maria.getId(), maria.getKindCode());

	// We send a POST request to that URI (from http:localhost...)
	MockHttpServletRequestBuilder request = post("/register").session(session)
		.contentType(MediaType.APPLICATION_JSON).content(payload.getBytes());

	mockMvc.perform(request).andDo(print())
		// The state of the response must be CREATED. (201);
		.andExpect(status().isCreated());
    }
    
    @Test @Ignore
    public void unSuccessfulInsertTest() {
	// TODO
    }
}
