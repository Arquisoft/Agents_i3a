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

import java.io.IOException;
import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import domain.Agent;
import domain.AgentInfo;
import domain.AgentInfoAdapter;

/**
 * Created by Nicolás on 18/02/2017.
 * 
 * Adapter by Víctor on 02/02/2018
 */
public class AgentAdapterTest {

	private Agent user1;
	private Agent user2;
	
	/**
	 * A resources/master.csv file is needed with first record -> 1; Person
	 * @throws IOException 
	 */
	
	@Before
	public void setUp() throws IOException {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 1996);
		user1 = new Agent("User1", "10N20E", "User1@hola.com", "user1Password", "112233", 1);
		user2 = new Agent("User2", "20S10W", "User2@hola.com", "user2Password", "112233", 1);
	}

	@Test
	public void testAdapter() {
		AgentInfoAdapter adapter = new AgentInfoAdapter(user1);
		AgentInfo info = adapter.userToInfo();
		assertEquals(info.getName(), user1.getName());
		assertEquals(info.getLocation(), user1.getLocation());
		assertEquals(info.getEmail(), user1.getEmail());
		assertEquals(info.getId(), user1.getUserId());
		assertEquals(info.getLocation(), user1.getLocation());
	}

	@Test
	public void testToString() {
		AgentInfoAdapter adapter = new AgentInfoAdapter(user2);
		AgentInfo info = adapter.userToInfo();
		String toString = info.toString();
		String test = "UserInfo{name='User2', location='20S10W', "
				+ "email='User2@hola.com', id='112233', kind='Person', kindCode=1}";
		assertEquals(test, toString);
	}

}
