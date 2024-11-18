package com.project.traplaner.travelBoard.service;

import com.project.traplaner.travelBoard.mapper.TravelBoardMapper;
import com.project.traplaner.travelBoard.dto.JourneyResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class JourneyService {

    private final TravelBoardMapper travelBoardMapper;

    public List<JourneyResponseDTO> getJourney(int id) {
        return travelBoardMapper.journeys(id);
    }

}
