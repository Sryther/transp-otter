package com.ynov.transpotter.controllers;

import java.util.logging.Logger;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ynov.transpotter.models.Trip;

@Controller
@RequestMapping("/trips")
public class TripController {

	private final static Logger logger = Logger.getLogger(TripController.class.getName());

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getTrip(Model model) {
		logger.info("getTrip");
		model.addAttribute("form", new Trip());
		model.addAttribute("statusOK", false);
		model.addAttribute("statusMessage", "");
		return "trip";
	}
}
