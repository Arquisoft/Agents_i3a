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

import domain.User;
import domain.UserInfo;
import domain.UserInfoAdapter;
import domain.UserLoginData;
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

	private final ParticipantsService part;

	ParticipantsController(ParticipantsService part) {
		this.part = part;
	}

	// The first page shown will be login.html.
	@GetMapping(value = "/")
	public String getParticipantInfo(Model model) {
		model.addAttribute("userinfo", new UserLoginData());
		return "login";
	}

	// This method process an POST html request once fulfilled the login.html form
	// (clicking in the "Enter" button).
	@RequestMapping(value = "/userForm", method = RequestMethod.POST)
	public String showInfo(Model model, @ModelAttribute UserLoginData data, HttpSession session) {
		User user = part.getParticipant(data.getLogin(), data.getPassword());
		if (user == null) {
			throw new UserNotFoundException();
		} else {
			UserInfoAdapter adapter = new UserInfoAdapter(user);
			UserInfo info = adapter.userToInfo();
			model.addAttribute("name", info.getName());
			model.addAttribute("location", info.getLocation());
			model.addAttribute("email", info.getEmail());
			model.addAttribute("kind", info.getKind());
			model.addAttribute("kindCode", info.getKindCode());
			model.addAttribute("user", user);
			session.setAttribute("user", user);
			return "data";
		}
	}

	@RequestMapping(value = "/passMenu", method = RequestMethod.GET)
	public String showMenu(Model model) {
		// Just in case there must be more processing.
		return "changePassword";
	}

	@RequestMapping(value = "/userChangePassword", method = RequestMethod.POST)
	public String changePassword(Model model, @RequestParam String password, @RequestParam String newPassword,
			@RequestParam String newPasswordConfirm, HttpSession session) {
		User loggedUser = (User) session.getAttribute("user");
		if (new StrongPasswordEncryptor().checkPassword(password, loggedUser.getPassword()) && newPassword.equals(newPasswordConfirm)) {
			part.updateInfo(loggedUser, newPassword);
			return "data";
		}
		return "changePassword";
	}

}
