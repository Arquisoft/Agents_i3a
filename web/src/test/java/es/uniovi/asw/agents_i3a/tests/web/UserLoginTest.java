/*
 * This source file is part of the web open source project.
 *
 * Copyright (c) 2018 willy and the web project authors.
 * Licensed under GNU General Public License v3.0.
 *
 * See /LICENSE for license information.
 * 
 */
package es.uniovi.asw.agents_i3a.tests.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import es.uniovi.asw.agents_i3a.dbmanagement.UsersRepository;
import es.uniovi.asw.agents_i3a.domain.Agent;
import es.uniovi.asw.agents_i3a.web.Application;



/**
 * Instance of UserLoginTest.java
 * 
 * @author 
 * @version 
 */
@SpringBootTest(classes = {
		Application.class }) @RunWith(SpringJUnit4ClassRunner.class)
public class UserLoginTest {
	
	@Autowired private WebApplicationContext context;

	// MockMvc --> Para realizar peticiones y comprobar resultado, usado para
	// respuestas con informacion json.
	private MockMvc mockMvc;

	@Autowired private UsersRepository repo;

	private MockHttpSession session;

	private Agent maria;
	private String plainPassword;
	
	@Before public void setUp() throws Exception {

		this.mockMvc = MockMvcBuilders.webAppContextSetup( this.context ).build();

		session = new MockHttpSession();

		// Setting up maria
		plainPassword = "pass14753";
		maria = new Agent( "Maria", "10N30E", "asd", plainPassword, "158", 1 );
		repo.insert( maria );

	}

	@After public void tearDown() {
		repo.delete( maria );
	}
	
	@Test public void userInterfaceInsertInfoCorect() throws Exception {
		MockHttpServletRequestBuilder request = post( "/userForm" ).session( session )
				.param( "login", maria.getId() )
				.param( "password", plainPassword )
				.param( "kind", Integer.toString( maria.getKindCode() ) );
		mockMvc.perform( request ).andExpect( status().isOk() );
	}

}
