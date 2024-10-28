package com.project.traplaner.mapper;

import com.project.traplaner.entity.Journey;
import com.project.traplaner.entity.Travel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TravelMapper {

    void saveTravel(Travel travel);

    void postJourney(Journey journey);

    void updateJourney(Journey journey);

    Integer getNextTravelId();
}
