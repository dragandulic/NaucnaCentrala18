package naucnaCentrala.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import naucnaCentrala.service.NaturalScienceService;

@Controller
public class NaturalScienceController {

	@Autowired
	private NaturalScienceService naturalScienceService;
	
}
