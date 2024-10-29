package com.project.traplaner.mypage.service;

import com.project.traplaner.entity.Favorite;
import com.project.traplaner.entity.Member;
import com.project.traplaner.entity.Travel;
import com.project.traplaner.mapper.FavoriteMapper;
import com.project.traplaner.mapper.MemberMapper;
import com.project.traplaner.mapper.MyPageBoardMapper;
import com.project.traplaner.mypage.dto.response.FavoriteListResponseDTO;
import com.project.traplaner.mypage.dto.response.TravelBoardResponseDTO;
import com.project.traplaner.mypage.dto.response.TravelListResponseDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MyPageBoardService {

    private final MyPageBoardMapper myPageBoardMapper;
    private final FavoriteMapper favoriteMapper;
    private final MemberMapper memberMapper;


    public List<TravelListResponseDTO> getList(int memberId) {

        List<Travel> travels = myPageBoardMapper.selectTravelById(memberId);
        List<TravelListResponseDTO> dtoList = travels.stream()
                .map(travel -> new TravelListResponseDTO(travel))
                .collect(Collectors.toList());

        return dtoList;
    }

    public List<TravelBoardResponseDTO> getTravelList(String nickName) {
        List<TravelBoardResponseDTO> dtoList = myPageBoardMapper.selectTravelBoardByNickName(nickName);

        dtoList.forEach(dto -> {
            String formatDate = TravelBoardResponseDTO.makeDateStringFomatter(dto.getWriteDate());
            dto.setFormatDate(formatDate);

        });

        return dtoList;
    }

    public void updateShare(int id) {
        myPageBoardMapper.updateShare(id);
    }

    public void deleteBoard(int boardId) {
        myPageBoardMapper.deleteTravelByMemberOrder(boardId);
    }

    public List<FavoriteListResponseDTO> favorite(int memberId) {

        List<FavoriteListResponseDTO> favorites = favoriteMapper.favorite_List(memberId);

        favorites.forEach(dto -> {
            dto.setFormatDate(FavoriteListResponseDTO.makeDateStringFomatter(dto.getWriteDate()));

        });


        return favorites;
    }

}

