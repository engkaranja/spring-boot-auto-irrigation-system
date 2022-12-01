package com.irrigation.automatedsystem.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irrigation.automatedsystem.models.Plot;
import com.irrigation.automatedsystem.models.PlotDetails;
import com.irrigation.automatedsystem.services.PlotService;
import com.irrigation.automatedsystem.utils.DataNotFoundException;

@RestController
@RequestMapping("/plot")
public class PlotsController {
	
	@Autowired
	PlotService plotService;
	
	
	@PostMapping("/add")
	public Plot addPlot(@RequestBody Plot plot) {
	   return plotService.savePlot(plot);
		
	}
	
	@GetMapping("/all")
	public List<Plot> getAllPlots(){
		return plotService.getAllPlots();
		
	}
	
	@PutMapping("/update/{id}")
	public Plot updatePlot(@RequestBody Plot plot, @PathVariable int id) {
		
		return plotService.updatePlot(plot, id);
	}
	
	@PutMapping("/configure/{id}")
	public Plot configurePlot(@RequestBody PlotDetails plotDetails, @PathVariable int id) throws DataNotFoundException {
		
		return plotService.configurePlot(plotDetails, id);
	}
	

}
