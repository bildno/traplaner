package com.project.traplaner.mapper;

import com.project.traplaner.entity.Travel;
import com.project.traplaner.mypage.dto.response.TravelBoardResponseDTO;
import com.project.traplaner.travelBoard.dto.PageDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MyPageBoardMapper {

    List<Travel> selectTravelById(Integer id);

    void deleteTravelById(Integer id);

    void deleteTravelByMemberOrder(Integer id);

    List<TravelBoardResponseDTO> selectTravelBoardByNickName(String nickName);

    void updateShare(Integer id);

    List<Travel> findAll(@Param("memberId") int memberId, @Param("page") PageDTO page);

    int getTotalCount(PageDTO page, int memberId);

    int getBoardTotal(PageDTO page, String nickName);

    List<TravelBoardResponseDTO> findBoardAll(@Param("nickName") String nickName, @Param("page") PageDTO page);
}
