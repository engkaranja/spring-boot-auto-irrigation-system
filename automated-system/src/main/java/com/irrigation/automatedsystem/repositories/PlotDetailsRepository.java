package com.irrigation.automatedsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irrigation.automatedsystem.models.PlotDetails;


public interface PlotDetailsRepository extends JpaRepository<PlotDetails, Integer> {

}
