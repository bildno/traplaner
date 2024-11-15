package com.project.traplaner.travelplan.repository;

import com.project.traplaner.entity.Journey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JourneyRepository extends JpaRepository<Journey, Long> {

}
