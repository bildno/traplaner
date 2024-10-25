package com.project.traplaner.travelBoard.service;

import com.project.traplaner.mapper.TravelBoardMapper;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardListResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class TravelBoardService {

    private final TravelBoardMapper travelBoardMapper;

    // mapper로 전달받은 entity list를 dto list로 변환해서 controller에게 리턴

    public List<TravelBoardListResponseDTO> findAll() {
        List<TravelBoardListResponseDTO> travelBoardListResponseDTOS = travelBoardMapper.findAll();
        return travelBoardListResponseDTOS;
    }

    public TravelBoardDetailResponseDTO findOne(int id) {
        return travelBoardMapper.findOne(id);
    }

}
