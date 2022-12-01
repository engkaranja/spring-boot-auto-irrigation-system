package com.irrigation.automatedsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.irrigation.automatedsystem.models.Plot;
import com.irrigation.automatedsystem.repositories.PlotRepository;
import com.irrigation.automatedsystem.services.PlotService;

import io.vavr.collection.Stream;

@SpringBootTest
class AutomatedSystemApplicationTests {
	
	@Autowired
	private PlotService plotService;
	
	@MockBean
	private PlotRepository plotRepository;

	
	@Test
	public void getPlotsTest() {
		//Integer id, Double size, String crop, String location
		when(plotRepository.findAll()).thenReturn(
				Stream.of(new Plot(1, 2000.0, "Maize", "Nairobi"), new Plot(2, 3000.0, "Beans", "Cairo")).collect(Collectors.toList()));
		assertEquals(2, plotService.getAllPlots().size());
	}
	
	@Test
	public void savePlotTest() {
		Plot plot = new Plot(134, 4000.0, "Wheat", "Niger");
		when(plotRepository.save(plot)).thenReturn(plot);
		assertEquals(plot, plotService.savePlot(plot));
	}
	
	
	
	

}
