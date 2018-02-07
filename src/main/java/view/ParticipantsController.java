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
package view;

import javax.servlet.http.HttpSession;

import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import domain.Agent;
import domain.AgentLoginData;
import services.ParticipantsService;

/**
 * 
 * Instance of ParticipantsController
 *
 * @author Nicolás
 * @since 08/02/2017
 */
@Controller
public class ParticipantsController {

    private final ParticipantsService participantsService;

    ParticipantsController(ParticipantsService part) {
	this.participantsService = part;
    }

    // The first page shown will be login.html.
    @GetMapping(value = "/")
    public String getParticipantInfo(Model model) {
	model.addAttribute("userinfo", new AgentLoginData());
	return "login";
    }

    // This method process an POST html request once fulfilled the login.html form
    // (clicking in the "Enter" button).
    @RequestMapping(value = "/userForm", method = RequestMethod.POST)
    public String showInfo(Model model, @ModelAttribute AgentLoginData data) {
	Agent user = participantsService.getParticipant(data.getLogin(), data.getPassword(), data.getKind());
	if (user == null) {
	    throw new UserNotFoundException();
	} else {
	    model.addAttribute("name", user.getName());
	    model.addAttribute("location", user.getLocation());
	    model.addAttribute("email", user.getEmail());
	    model.addAttribute("kind", user.getKind());
	    model.addAttribute("id", user.getId());
	    model.addAttribute("kindCode", user.getKindCode());
	    model.addAttribute("user", user);
	    return "data";
	}
    }
}
