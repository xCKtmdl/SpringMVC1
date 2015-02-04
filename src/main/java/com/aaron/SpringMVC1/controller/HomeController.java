package com.aaron.SpringMVC1.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aaron.SpringMVC1.domain.Spittle;
import com.aaron.SpringMVC1.service.*;

@Controller
public class HomeController {

	private SpitterService spitterService;

	@Inject
	public HomeController(SpitterService spitterService) {
		this.spitterService = spitterService;
	}

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String showHomePage(Map<String, Object> model) {
		
		List<Spittle> spittleList = spitterService.getRecentSpittles(spittlesPerPage);
		if (spittleList != null) {
		model.put("spittles", spittleList);
		}
		else {
			model.put("spittles", null);
		}
		return "home";
	}

	public static final int DEFAULT_SPITTLES_PER_PAGE = 25;

	private int spittlesPerPage = DEFAULT_SPITTLES_PER_PAGE;

	public void setSpittlesPerPage(int spittlesPerPage) {
		this.spittlesPerPage = spittlesPerPage;
	}

	public int getSpittlesPerPage() {
		return spittlesPerPage;
	}

}