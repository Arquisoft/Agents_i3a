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
package es.uniovi.asw.agents_i3a.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication @EnableMongoRepositories("es.uniovi.asw.agents_i3a.dbmanagement") @ComponentScan(basePackages = {
		"es.uniovi.asw.agents_i3a.dbmanagement",
		"es.uniovi.asw.agents_i3a.controller",
		"es.uniovi.asw.agents_i3a.domain",
		"es.uniovi.asw.agents_i3a.services",
		"es.uniovi.asw.agents_i3a.web" })
public class Application {

	public static void main( String[] args ) {
		SpringApplication.run( Application.class, args );
	}
}