package com.irrigation.automatedsystem.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


@Entity
@DynamicInsert
@DynamicUpdate
public class Plot {
	
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	private Integer id;
	
	private Double size;
	
	private String crop;
	
	private String location;
	
	@OneToOne
	@JoinColumn(name = "details_id")
	private PlotDetails details;

	public Plot(Integer id, Double size, String crop, String location) {
		//super();
		this.id = id;
		this.size = size;
		this.crop = crop;
		this.location = location;
	}

	public Plot(Integer id, Double size, String crop, String location, PlotDetails details) {
		super();
		this.id = id;
		this.size = size;
		this.crop = crop;
		this.location = location;
		this.details = details;
	}


	public Plot() {
		//super();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Double getSize() {
		return size;
	}


	public void setSize(Double size) {
		this.size = size;
	}


	public String getCrop() {
		return crop;
	}


	public void setCrop(String crop) {
		this.crop = crop;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}

	public PlotDetails getDetails() {
		return details;
	}

	public void setDetails(PlotDetails details) {
		this.details = details;
	}
	
	
	
	
	

}
