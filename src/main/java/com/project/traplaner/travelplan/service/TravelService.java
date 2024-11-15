package com.project.traplaner.travelplan.service;
import com.project.traplaner.entity.Member;
import com.project.traplaner.entity.Travel;
import com.project.traplaner.main.dto.MainTravelDto;
import com.project.traplaner.mapper.MemberMapper;
import com.project.traplaner.mapper.TravelMapper;
import com.project.traplaner.member.repository.MemberRepository;
import com.project.traplaner.util.FileUtils;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.traplaner.travelplan.dto.TravelPlanRequestDTO.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class TravelService {

    private final MemberMapper memberMapper;
    private final TravelMapper travelMapper;
    private final MemberRepository memberRepository;
    @Value("${file.upload.root-path}")
    private String rootPath;

    public void saveTravel(TravelInfo travelInfo, long memberId) {
//        travelMapper.saveTravel(travel.toEntity(memberId));
        //jpa로 변환
        Member member = memberRepository.findById(memberId).orElseThrow(
                () -> new EntityNotFoundException("member not found")
        );
        Travel travel = Travel.builder()
                .
                        .build();
        travelMapper.saveTravel();
    }

    public void saveJourneys(List<JourneyInfo> journeys) {
//        int travelId = travelMapper.getNextTravelId();
//        log.info("travelId: {}", travelId);
//        for (JourneyInfo journey : journeys) {
//            if(journey.getReservationConfirmImagePath()!=null) {
//                String savePath = FileUtils.uploadFile(
//                        journey.getReservationConfirmImagePath(), rootPath);
//                travelMapper.postJourney(journey.toEntity(travelId,savePath));
//            }
//            else{
//                travelMapper.postJourney(journey.toEntity(travelId));
//            }
//        }

    }

    public void refreshLoginUserTravel(String email, HttpSession session) {
        List<MainTravelDto> mainTravelDtoList = travelMapper.findByEmail(email);
        session.setAttribute("mainTravelDtoList", mainTravelDtoList);
    }
}
