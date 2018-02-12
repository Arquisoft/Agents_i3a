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
package domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import categories.UnitTest;

import domain.AgentLoginData;

/**
 * Created by Nicolás on 18/02/2017.
 * 
 * Adapter by Víctor on 02/02/2018
 */
@Category(UnitTest.class)
public class AgentLoginDataTest {

    private AgentLoginData test;
    private AgentLoginData test2;

    @Before
    public void setUp() {
	test = new AgentLoginData();
	test.setLogin("hola1");
	test.setPassword("holaPassword");

	test2 = new AgentLoginData("Hola2", "Hola2Password", 1);
    }

    @Test
    public void getLogin() throws Exception {
	assertEquals("hola1", test.getLogin());
	assertEquals("Hola2", test2.getLogin());
    }

    @Test
    public void getPassword() throws Exception {
	assertEquals("holaPassword", test.getPassword());
	assertEquals("Hola2Password", test2.getPassword());
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