package com.project.traplaner.mapper;

import com.project.traplaner.entity.Travel;
import com.project.traplaner.mypage.dto.response.TravelBoardResponseDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MyPageBoardMapper {

    List<Travel> selectTravelById(Integer id);

    void deleteTravelById(Integer id);

    void deleteTravelByMemberOrder(Integer id, int memberId);

    List<TravelBoardResponseDTO> selectTravelBoardByNickName(String nickName);

    void updateShare(Integer id);
}
