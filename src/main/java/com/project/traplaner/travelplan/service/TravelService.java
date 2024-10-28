package com.project.traplaner.travelplan.service;
import com.project.traplaner.mapper.TravelMapper;
import com.project.traplaner.util.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.traplaner.travelplan.dto.TravelPlanRequestDTO.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelService {


    private final TravelMapper travelMapper;
    @Value("${file.upload.root-path-reservation}")
    private String rootPathReservation;

    public void saveTravel(TravelInfo travel, int memberId) {
        travelMapper.saveTravel(travel.toEntity(memberId));
    }

    public void saveJourneys(List<JourneyInfo> journeys) {

        int travelId = travelMapper.getNextTravelId();
        for (JourneyInfo journey : journeys) {
            if(journey.getReservationConfirmImagePath()!=null) {
                String savePath = FileUtils.uploadFile(
                        journey.getReservationConfirmImagePath(), rootPathReservation);
                travelMapper.postJourney(journey.toEntity(travelId,savePath));
            }
            else{
                travelMapper.postJourney(journey.toEntity(travelId));
            }
        }
    }
}
