package com.project.traplaner.travelplan.repository;

import com.project.traplaner.travelplan.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelRepository extends JpaRepository<Travel, Long> {

}
