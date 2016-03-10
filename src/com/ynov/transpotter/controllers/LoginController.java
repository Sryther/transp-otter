package com.ynov.transpotter.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
    private final static Logger logger = Logger.getLogger(LoginController.class.getName());
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLogin(Model model) {
	logger.info("getLogin");
	return "login";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String postLogin(Model model) {
	logger.info("getLogin");
	System.out.println("toto");
	return "login";
    }

}
