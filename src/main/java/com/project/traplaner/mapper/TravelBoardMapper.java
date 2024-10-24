package com.project.traplaner.mapper;

import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.travelBoard.dto.PageDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardListResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TravelBoardMapper {

    // 목록 조회
    List<TravelBoardListResponseDTO> findAll();

    // 상세 조회
    TravelBoardDetailResponseDTO findOne(int id);

    // 검색 게시물 개수 카운트
    int getTotalCount(PageDTO page);
}
