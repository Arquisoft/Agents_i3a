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
package domain_tests;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import domain.UserLoginData;

/**
 * Created by Nicolás on 18/02/2017.
 * 
 * Adapter by Víctor on 02/02/2018
 */
public class UserLoginDataTest {

	private UserLoginData test;

	@Before
	public void setUp() {
		test = new UserLoginData();
		test.setLogin("hola1");
		test.setPassword("holaPassword");
	}

	@Test
	public void getLogin() throws Exception {
		assertEquals("hola1", test.getLogin());
	}

	@Test
	public void getPassword() throws Exception {
		assertEquals("holaPassword", test.getPassword());
	}

	@Test
	public void setLogin() throws Exception {
		test.setLogin("HOLAAAAAAAA");
		assertEquals("HOLAAAAAAAA", test.getLogin());
	}

	@Test
	public void setPassword() throws Exception {
		test.setPassword("PASWOOOOOOOORD");
		assertEquals("PASWOOOOOOOORD", test.getPassword());
	}

}