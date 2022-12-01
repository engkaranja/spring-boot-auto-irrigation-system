package com.irrigation.automatedsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.irrigation.automatedsystem.models.Plot;

public interface PlotRepository extends JpaRepository<Plot, Integer> {

}
