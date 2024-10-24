package com.project.traplaner.travelBoard.controller;

import com.project.traplaner.entity.TravelBoard;
import com.project.traplaner.mapper.TravelBoardMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class TravelBoardControllerTest {

    @Autowired
    TravelBoardMapper  travelBoardMapper;
    @Test
    @DisplayName("sadas")
    void asdas() {
        // given
        int id = 1;
        // when
        TravelBoard one = travelBoardMapper.findOne(id);
        // then
        String content = one.getContent();

        System.out.println(content);
    }

}