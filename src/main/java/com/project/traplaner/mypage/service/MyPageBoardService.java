package com.project.traplaner.mypage.service;

import com.project.traplaner.entity.Journey;
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
import org.eclipse.tags.shaded.org.apache.bcel.generic.NEW;
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

    public Map<String, Object> favorite(int memberId,PageDTO page) {
        PageMaker pageMaker = new PageMaker(page, favoriteMapper.getTotal(page, memberId));
        List<FavoriteListResponseDTO> favorites = favoriteMapper.favorite_List(memberId, page);

        favorites.forEach(dto -> {
            dto.setFormatDate(FavoriteListResponseDTO.makeDateStringFomatter(dto.getWriteDate()));

        });

        Map<String, Object> result = new HashMap<>();
        result.put("favorites",favorites);
        result.put("pm",pageMaker);

        return result;
    }

    public Map<String, Object> travel(int travelId){
        List<Journey> journeys = myPageBoardMapper.journeySelect(travelId);
        Travel travels = myPageBoardMapper.travelSelect(travelId);

        Map<String, Object> result = new HashMap<>();

        result.put("journeys",journeys);
        result.put("travels", travels);

        return result;

    }


}

