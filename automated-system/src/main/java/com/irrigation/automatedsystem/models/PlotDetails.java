package com.irrigation.automatedsystem.models;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@DynamicInsert
@DynamicUpdate
public class PlotDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int details_id;
	
	private Double amount_of_water;
	
	private LocalTime irrigation_start_time;
	
	private LocalTime irrigation_end_time;
	
	private Boolean sensor_notified;

	public PlotDetails(int details_id, Double amount_of_water, LocalTime irrigation_start_time, LocalTime irrigation_end_time, Boolean sensor_notified) {
		
		this.details_id = details_id;
		this.amount_of_water = amount_of_water;
		this.irrigation_start_time = irrigation_start_time;
		this.irrigation_end_time = irrigation_end_time;
		this.sensor_notified = sensor_notified;
	}

	

	public PlotDetails() {

	}
	

	public int getId() {
		return details_id;
	}

	public void setId(int details_id) {
		this.details_id = details_id;
	}

	public Double getAmount_of_water() {
		return amount_of_water;
	}

	public void setAmount_of_water(Double amount_of_water) {
		this.amount_of_water = amount_of_water;
	}

	public LocalTime getIrrigation_start_time() {
		return irrigation_start_time;
	}

	public void setIrrigation_start_time(LocalTime irrigation_start_time) {
		this.irrigation_start_time = irrigation_start_time;
	}

	public LocalTime getIrrigation_end_time() {
		return irrigation_end_time;
	}

	public void setIrrigation_end_time(LocalTime irrigation_end_time) {
		this.irrigation_end_time = irrigation_end_time;
	}

	public Boolean getSensor_notified() {
		return sensor_notified;
	}

	public void setSensor_notified(Boolean sensor_notified) {
		this.sensor_notified = sensor_notified;
	}
	

}
