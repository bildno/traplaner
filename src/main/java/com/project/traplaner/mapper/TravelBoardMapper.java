package com.project.traplaner.mapper;

import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.travelBoard.dto.PageDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TravelBoardMapper {
    // 목록 조회
    List<TravelBoardDetailResponseDTO> findAll(PageDTO page);

    // 상세 조회
    TravelBoard findOne(int id);

    // 좋아요수 처리

    // 검색 게시물 개수 카운트
    int getTotalCount(PageDTO page);
}
