package com.project.traplaner.travelBoard.service;

import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.mapper.TravelBoardMapper;
import com.project.traplaner.travelBoard.dto.PageDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardListResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelBoardService {
    // mybatis가 우리가 만든 xml을 클래스로 변환해서 빈등록을 해 두기 때문에 주입이 가능하다.
    private final TravelBoardMapper mapper;

    // mapper로부터 전달받은 entity list를 dto list로 변환해서 컨트롤러에게 리턴
    public Map<String, Object> getList(PageDTO page) {
        // 전체 게시글을 가지고 오는것이 아닌, 특정 페이지 부분만 가져와야 함.
        List<TravelBoardDetailResponseDTO> boardList = mapper.findAll(page);
        log.info("boardList: {}", boardList);
        PageMaker pageMaker = new PageMaker(page, mapper.getTotalCount(page));

        List<TravelBoardListResponseDTO> dtoList = boardList.stream()
                .map(TravelBoardListResponseDTO::new)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("bList", dtoList);
        result.put("pm", pageMaker);
        return result;
    }

    public TravelBoardDetailResponseDTO getDetail(int id) {
        // 상세보기니까 조회수를 하나 올려주는 처리를 하자.
        // 상세 조회 전에 실행하자.
//        mapper.updateViewCount(id);
        TravelBoard board = mapper.findOne(id);
        return new TravelBoardDetailResponseDTO(board);
    }
}
