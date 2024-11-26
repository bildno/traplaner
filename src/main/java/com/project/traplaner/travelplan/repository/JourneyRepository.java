package com.project.traplaner.travelplan.repository;

import com.project.traplaner.travelplan.entity.Journey;
import com.project.traplaner.travelplan.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface JourneyRepository extends JpaRepository<Journey, Long> {


}
