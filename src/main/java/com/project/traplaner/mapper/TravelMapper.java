package com.project.traplaner.mapper;

import com.project.traplaner.entity.Travel;
import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.main.dto.MainTravelDto;
import com.project.traplaner.main.dto.TopThreeFavoriteTravelDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TravelMapper {

    void save(Travel travel);
/*
    Travel fineOne(int memberId);

    List<Travel> findAll(int memberId);
  */
    List<TopThreeFavoriteTravelDto> findTopThree();

    List<MainTravelDto> findByEmail(String email);

}
