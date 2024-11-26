package com.project.traplaner.mypage.repository;

import com.project.traplaner.mypage.dto.TravelJourneyRes;
import com.project.traplaner.travelplan.entity.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MyPageTravelRepository extends JpaRepository<Travel, Integer> {

    List<Travel> findByMemberId(int id);

    Page<Travel> findByMemberId(int id, Pageable pageable);


    @Query(value = "SELECT t.startDate AS startDate," +
            "t.endDate AS endDate,"  +
            "t.title AS title," +
            "t.travelImg AS travelImg," +
            "j.journeyImg AS journeyImg," +
            "j.journeyName AS journeyName " +
            "FROM Travel t join Journey j on t.id = j.travel.id WHERE t.id = ?1")
    Optional<List<TravelJourneyRes>> findTravelById(int travelNo);
}
