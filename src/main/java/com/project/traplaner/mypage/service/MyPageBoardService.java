package com.project.traplaner.mypage.service;

import com.project.traplaner.entity.Travel;
import com.project.traplaner.mapper.MyPageBoardMapper;
import com.project.traplaner.mypage.dto.response.TravelListResponseDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MyPageBoardService {

    private final MyPageBoardMapper myPageBoardMapper;

    public List<TravelListResponseDTO> getList(int memberId){

        List<Travel> travels = myPageBoardMapper.selectTravelById(memberId);
        List<TravelListResponseDTO> dtoList = travels.stream()
                .map(travel -> new TravelListResponseDTO(travel))
                .collect(Collectors.toList());

        return dtoList;
    }

}

