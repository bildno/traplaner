package com.project.traplaner.travelBoard.repository;

import com.project.traplaner.travelBoard.entity.TravelBoard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TravelBoardRepository extends JpaRepository<TravelBoard,Long> {

}
