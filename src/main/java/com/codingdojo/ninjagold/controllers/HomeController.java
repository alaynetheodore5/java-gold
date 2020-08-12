package com.codingdojo.ninjagold.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	private final Random random = new Random();

	@RequestMapping("/")
	public String index(HttpSession session, Model model) {
		Integer totalGold = (Integer) session.getAttribute("total");
		ArrayList<String> log = (ArrayList<String>) session.getAttribute("log");
		if(totalGold == null) {
			session.setAttribute("total", 0);
			totalGold = 0;
		}
		if(log == null) {
			log = new ArrayList<String>();
		}
		model.addAttribute("total", totalGold);
		model.addAttribute("log", log);
		return "index.jsp";
	}
	
	@RequestMapping("/get_gold")
	public String getGold(@RequestParam(value="location") String location, HttpSession session) {
		System.out.println(location);
		Integer totalGold = (Integer) session.getAttribute("total");
		ArrayList<String> log = (ArrayList<String>) session.getAttribute("log");
		System.out.println(log);
		if(log == null) {
			log = new ArrayList<String>();
		}
		
		if(location.equals("farm")) {
			int gold = random.nextInt(11)+10;
			String msg = "You just visited the " + location + " and found " + gold + " gold";
			System.out.println(msg);
			log.add(msg);
			totalGold += gold;
		}
		
		session.setAttribute("total", totalGold);
		session.setAttribute("log", log);
		return "redirect:/";
	}
}
