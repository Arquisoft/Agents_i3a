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

import java.io.IOException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import domain.AgentInfo;
import main.Application;

/**
 * Created by Víctor on 03/02/2018
 */
@SpringBootTest(classes = { Application.class })
@RunWith(SpringJUnit4ClassRunner.class)
public class AgentInfoTest {


	// User to use as reference for test
	private AgentInfo testedUser;

	@Before
	public void setUp() throws IOException {
		testedUser = new AgentInfo("Victor", "30N45E","ejemplo@correo.com","asd", "Person", 1);
	}

	@Test
	public void gettersTest()
	{
	    Assert.assertEquals("Victor", testedUser.getName());
	    Assert.assertEquals("30N45E", testedUser.getLocation());
	    Assert.assertEquals("ejemplo@correo.com", testedUser.getEmail());
	    Assert.assertEquals("asd", testedUser.getId());
	    Assert.assertEquals("Person", testedUser.getKind());
	    Assert.assertEquals(1, testedUser.getKindCode());
	}
	
	@Test
	public void settersTest()
	{
	    
	    testedUser.setName("Juan");
	    testedUser.setLocation("10S30E");
	    testedUser.setEmail("ejemplo2@correo.com");
	    testedUser.setId("das");
	    testedUser.setKind("Entity");
	    testedUser.setKindCode(2);
	    
	    Assert.assertEquals("Juan", testedUser.getName());
	    Assert.assertEquals("10S30E", testedUser.getLocation());
	    Assert.assertEquals("ejemplo2@correo.com", testedUser.getEmail());
	    Assert.assertEquals("das", testedUser.getId());
	    Assert.assertEquals("Entity", testedUser.getKind());
	    Assert.assertEquals(2, testedUser.getKindCode());
	}

	@Test
	public void toStringTest()
	{
	    
	}
	
	@Test
	public void  hashCodeTest()
	{
	    Assert.assertEquals("asd".hashCode(), testedUser.hashCode());
	}
	
	
	@Test
	public void equalsTest()
	{
	    AgentInfo other = new AgentInfo("Juan", "20N45E","ejemplo3@correo.com","rerere", "Person", 1);
	    
	    Assert.assertTrue( testedUser.equals( testedUser ) );
	    
	    Assert.assertFalse( testedUser.equals( other ) );
	    Assert.assertFalse( testedUser.equals( null ) );
	}

}
