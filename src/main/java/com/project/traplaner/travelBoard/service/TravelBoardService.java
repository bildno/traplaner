package com.project.traplaner.travelBoard.service;

import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.mapper.TravelBoardMapper;
import com.project.traplaner.travelBoard.dto.PageDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardListResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelBoardService {

    private final TravelBoardMapper travelBoardMapper;

    // mapper로 전달받은 entity list를 dto list로 변환해서 controller에게 리턴

    public List<TravelBoardListResponseDTO> findAll() {
        List<TravelBoardListResponseDTO> travelBoardListResponseDTOS = travelBoardMapper.findAll();
        return travelBoardListResponseDTOS;
    }

//    public TravelBoardDetailResponseDTO findOne() {
//        TravelBoardDetailResponseDTO travelBoardDetailResponseDTO = travelBoardMapper.findOne();
//        return travelBoardDetailResponseDTO;
//    }

}
