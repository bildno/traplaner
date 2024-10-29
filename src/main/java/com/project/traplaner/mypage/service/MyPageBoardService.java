package com.project.traplaner.mypage.service;

import com.project.traplaner.entity.Travel;
import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.mapper.FavoriteMapper;
import com.project.traplaner.mapper.MemberMapper;
import com.project.traplaner.mapper.MyPageBoardMapper;
import com.project.traplaner.mypage.dto.response.FavoriteListResponseDTO;
import com.project.traplaner.mypage.dto.response.TravelBoardResponseDTO;
import com.project.traplaner.mypage.dto.response.TravelListResponseDTO;
import com.project.traplaner.travelBoard.dto.PageDTO;
import com.project.traplaner.travelBoard.service.PageMaker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MyPageBoardService {

    private final MyPageBoardMapper myPageBoardMapper;
    private final FavoriteMapper favoriteMapper;
    private final MemberMapper memberMapper;

    public Map<String, Object> findAll(int memberId, PageDTO page) {
        PageMaker pageMaker = new PageMaker(page, myPageBoardMapper.getTotalCount(page, memberId));
        List<Travel> travels = myPageBoardMapper.findAll(memberId, page);

        List<TravelListResponseDTO> dtoList = travels.stream()
                .map(travel -> new TravelListResponseDTO(travel))
                .collect(Collectors.toList());



        Map<String, Object> result = new HashMap<>();
        result.put("travels",dtoList);
        result.put("pm",pageMaker);

        return result;
    }


    public Map<String, Object> findBoardAll(String nickName, PageDTO page) {
        PageMaker pageMaker = new PageMaker(page, myPageBoardMapper.getBoardTotal(page, nickName));
        List<TravelBoardResponseDTO> boardAll = myPageBoardMapper.findBoardAll(nickName, page);

        Map<String, Object> result = new HashMap<>();
        result.put("boardAll",boardAll);
        result.put("pm",pageMaker);

        return result;
    }


    public List<TravelListResponseDTO> getList(int memberId) {

        List<Travel> travels = myPageBoardMapper.selectTravelById(memberId);
        List<TravelListResponseDTO> dtoList = travels.stream()
                .map(travel -> new TravelListResponseDTO(travel))
                .collect(Collectors.toList());

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

