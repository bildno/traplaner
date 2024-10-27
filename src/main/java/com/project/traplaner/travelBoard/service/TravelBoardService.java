package com.project.traplaner.travelBoard.service;

import com.project.traplaner.mapper.TravelBoardMapper;
import com.project.traplaner.travelBoard.dto.PageDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardListResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Getter
public class TravelBoardService {

    private final TravelBoardMapper travelBoardMapper;

    // mapper로 전달받은 entity list를 dto list로 변환해서 controller에게 리턴
    public Map<String, Object> getList(PageDTO page) {

        List<TravelBoardListResponseDTO> travelBoardListResponseDTOS = travelBoardMapper.findAll(page);
        PageMaker pageMaker = new PageMaker(page, travelBoardMapper.getTotalCount(page));

        List<TravelBoardListResponseDTO> dtoList = new ArrayList<>(travelBoardListResponseDTOS);

        Map<String, Object> result = new HashMap<>();
        result.put("tbList", dtoList);
        result.put("pm", pageMaker);
        return result;
    }

    public TravelBoardDetailResponseDTO getOne(int id) {
        return travelBoardMapper.findOne(id);
    }

}
