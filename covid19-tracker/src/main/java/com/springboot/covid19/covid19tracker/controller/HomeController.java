package com.springboot.covid19.covid19tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.springboot.covid19.covid19tracker.models.LocationStats;
import com.springboot.covid19.covid19tracker.services.CovidServiceData;

@Controller
public class HomeController {

	@Autowired
	CovidServiceData covidServiceData;
	
	@GetMapping("/")
	public String home(Model model) {
		List<LocationStats> allStats = covidServiceData.getAllStats();
		int totalCases = allStats.stream().mapToInt(stat -> stat.getLatestTotalCases()).sum();
		int totalNewCases = allStats.stream().mapToInt(stat -> stat.getDiffCasesPrevDay()).sum();
		model.addAttribute("appName", "Covid19 Tracker");
		model.addAttribute("locationStats",allStats);
		model.addAttribute("totalNumberOfCases",totalCases);
		model.addAttribute("totalNumberOfNewCases",totalNewCases);
		
		return "home1";
	}
}
