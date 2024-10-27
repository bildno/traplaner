package com.project.traplaner.mapper;

import com.project.traplaner.travelBoard.dto.ArrangeDTO;
import com.project.traplaner.travelBoard.dto.PageDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardDetailResponseDTO;
import com.project.traplaner.travelBoard.dto.TravelBoardListResponseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelBoardMapper {

    // 목록 조회
    List<TravelBoardListResponseDTO> findAll(@Param("page") PageDTO page, @Param("arrange") ArrangeDTO arrange);

     //상세 조회
    TravelBoardDetailResponseDTO findOne(int id);

    // 검색 게시물 개수 카운트
    int getTotalCount(@Param("page") PageDTO page);
}
