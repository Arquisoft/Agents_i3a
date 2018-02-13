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

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Antonio Nicolas on 26/04/2017.
 */
/**
 * 
 * Instance of UserNotFoundException
 *
 * @author Antonio Nicolas
 * @since 26/04/2017
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such agent")
public class AgentNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;
}
