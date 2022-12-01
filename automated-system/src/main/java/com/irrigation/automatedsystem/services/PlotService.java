package com.irrigation.automatedsystem.services;

import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.irrigation.automatedsystem.models.Message;
import com.irrigation.automatedsystem.models.Plot;
import com.irrigation.automatedsystem.models.PlotDetails;
import com.irrigation.automatedsystem.repositories.PlotDetailsRepository;
import com.irrigation.automatedsystem.repositories.PlotRepository;
import com.irrigation.automatedsystem.utils.DataNotFoundException;

@Service
public class PlotService {

	@Autowired
	PlotRepository plotRepository;

	@Autowired
	PlotDetailsRepository plotDetailsRepository;
	
	

	@Value("${irrigate.sensor.base_url}")
	private String baseUrl;

	@Value("${server.port}")
	private String port;

	Logger logger = LoggerFactory.getLogger(PlotService.class);

	private final RestTemplate restTemplate;

	public PlotService(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	public Plot savePlot(Plot plot) {
		return plotRepository.save(plot);
	}

	public Plot updatePlot(Plot plot, int id) {
		Plot existingPlot = plotRepository.findById(id).get();
		existingPlot.setSize(plot.getSize());
		existingPlot.setCrop(plot.getCrop());
		existingPlot.setLocation(plot.getLocation());

		return plotRepository.save(existingPlot);

	}

	public Plot configurePlot(PlotDetails newDetails, int id) throws DataNotFoundException {
		Plot existingPlot = plotRepository.findById(id).get();
		// .orElseThrow(() -> new DataNotFoundException("Plot not found"));
		if (existingPlot.getDetails() == null) {
			PlotDetails saved = plotDetailsRepository.save(newDetails);

			existingPlot.setDetails(saved);
		} else {
			PlotDetails existingDetails = plotDetailsRepository.findById(existingPlot.getDetails().getId()).get();
			existingDetails.setAmount_of_water(newDetails.getAmount_of_water());
			existingDetails.setIrrigation_start_time(newDetails.getIrrigation_start_time());
			existingDetails.setIrrigation_end_time(newDetails.getIrrigation_end_time());

			existingPlot.setDetails(existingDetails);
		}

		return plotRepository.save(existingPlot);

	}

	public List<Plot> getAllPlots() {
		return plotRepository.findAll();
	}

	@Scheduled(cron = "0/60 * * * * *") // run job after every 60 seconds || 1 minute
	public void irrigateLandSensor() {
		LocalTime my_time = LocalTime.now();
		logger.info("Scheduler running at " + my_time);

		List<Plot> plots = plotRepository.findAll();

		for (Plot plot : plots) {
			if (plot.getDetails() != null) {
				PlotDetails details = plot.getDetails();
				LocalTime irrigate_time = details.getIrrigation_start_time();
				logger.info("Irrigate time " + irrigate_time);
				LocalTime current_time = LocalTime.now();
				logger.info("Scheduler running at " + current_time);
				System.out.println("Current local time " + current_time);
				if (irrigate_time == current_time) {
					// notify irrigate land sensor
					logger.info("About to notify sensor! ");
					Boolean notified = notifySensor(plot);
					if (notified) {
						// update sensor notified status
						Plot updatePlot = plotRepository.findById(plot.getId()).get();
						details.setSensor_notified(true);
						updatePlot.setDetails(details);
						plotRepository.save(updatePlot);
						
					}
				}

			}
		}

	}

	@io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker(name = "circuit_breaker", fallbackMethod = "alertService")
	public Boolean notifySensor(Plot plot) {
		/*PlotDetails details = plot.getDetails();
		// create headers
		HttpHeaders headers = new HttpHeaders();
		// set `content-type` header
		headers.setContentType(MediaType.APPLICATION_JSON);
		// set `accept` header
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		// create a map for post parameters
		Map<String, Object> map = new HashMap<>();
		map.put("plot_id", plot.getId());
		map.put("plot_size", plot.getSize());
		map.put("amount_of_water", details.getAmount_of_water());
		map.put("irrigation_end_time", details.getIrrigation_end_time());

		// build the request
		HttpEntity<Map<String, Object>> entity = new HttpEntity<>(map, headers); */

		String sensorUrl =  baseUrl + ":" + port + "/sensor/notify";
		logger.info("Sensor Url " + sensorUrl);
		// send POST request
		ResponseEntity<Plot> response = this.restTemplate.postForEntity(sensorUrl, plot, Plot.class);

		// check response status code
		if (response.getStatusCode() == HttpStatus.OK) {
			System.out.println("Sensor notified ok!");
			return true;
		} else {
			return false;
		}
	}
	
	public Boolean alertService() {
		//make a call to external alert service through sms
		Message message = new Message();
		message.setMessage("Hey! we were unable to reach the sensor. Please check it out.");
		String sensorUrl =  baseUrl + ":" + port + "/alerts/send";
		logger.info("Sensor Url " + sensorUrl);
		// send POST request
		restTemplate.postForEntity(sensorUrl, message, Message.class);
		
		return true;
	}

}
