package com.project.traplaner.travelplan.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.traplaner.travelplan.dto.TravelPlanRequestDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
public class travelController {
    @GetMapping("/travelplan")
    public String travelPlan() {
        return "travelplan/travelplan";
    }


    @PostMapping("/travelplan")
    public String travelSave(@RequestParam("data") String data,
                             @RequestParam Map<String, MultipartFile> reservationFiles) throws JsonProcessingException {

        log.info("reservationFile: {}", reservationFiles);

        ObjectMapper objectMapper = new ObjectMapper();
        TravelPlanRequestDTO requestDTO = objectMapper.readValue(data, TravelPlanRequestDTO.class);

        for (Map.Entry<String, MultipartFile> entry : reservationFiles.entrySet()) {
            // 이미지 파일만 따로 Map으로 받았어요.
            // 키: reservation_인덱스 : 파일(멀티파트)
            String key = entry.getKey();
            MultipartFile file = entry.getValue();

            // reservation_0, reservation_1, reservation_2.....
            int journeyId = Integer.parseInt(key.split("_")[1]); // 0, 1, 2 ...
            requestDTO.getJourneys().get(journeyId).setReservationConfirmImagePath(file);
        }

        log.info("최종 dto 형태 보자: {}", requestDTO);

        //사용자 확인

       //json 파싱

       //서비스 로직으로 json 전달?

        // 마이 페이지로 리턴
        return "travelplan/travelplan";
    }
}
