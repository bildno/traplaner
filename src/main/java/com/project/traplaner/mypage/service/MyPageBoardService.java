package com.project.traplaner.mypage.service;

import com.project.traplaner.entity.Travel;
import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.mapper.MyPageBoardMapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
public class MyPageBoardService {

    private final MyPageBoardMapper myPageBoardMapper;

    public List<Travel> getList(int memberId){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<Travel> travels = myPageBoardMapper.selectTravelById(memberId);
        travels.forEach(travel ->  {




        });



        return travels;
    }

}

