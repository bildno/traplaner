package com.project.traplaner.mypage.service;

import com.project.traplaner.common.auth.TokenUserInfo;
import com.project.traplaner.member.entity.Member;
import com.project.traplaner.member.repository.MemberRepository;
import com.project.traplaner.mypage.dto.FavoriteRes;
import com.project.traplaner.mypage.dto.TravelBoardDto;
import com.project.traplaner.mypage.dto.TravelJourneyRes;
import com.project.traplaner.mypage.repository.MyPageFavoriteRepository;
import com.project.traplaner.mypage.repository.MyPageTravelBoardRepository;
import com.project.traplaner.travelBoard.entity.TravelBoard;
import com.project.traplaner.travelplan.entity.Journey;
import com.project.traplaner.travelplan.entity.Travel;
import com.project.traplaner.main.dto.MainTravelDto;
import com.project.traplaner.travelBoard.mapper.FavoriteMapper;
import com.project.traplaner.member.mapper.MemberMapper;
import com.project.traplaner.mypage.mapper.MyPageBoardMapper;
import com.project.traplaner.mypage.dto.response.TravelBoardResponseDTO;
import com.project.traplaner.mypage.dto.response.TravelListResponseDTO;
import com.project.traplaner.mypage.repository.MyPageRepository;
import com.project.traplaner.mypage.repository.MyPageTravelRepository;
import com.project.traplaner.travelplan.repository.JourneyRepository;
import com.project.traplaner.travelplan.repository.TravelRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class MyPageService {

    private final MyPageBoardMapper myPageBoardMapper;
    private final FavoriteMapper favoriteMapper;
    private final MemberMapper memberMapper;
    private final MyPageRepository MyPageRepository;
    private final MyPageTravelRepository MyPageTravelRepository;
    private final MemberRepository memberRepository;
    private final MyPageTravelBoardRepository MyPageTravelBoardRepository;
    private final MyPageTravelRepository myPageTravelRepository;
    private final TravelRepository travelRepository;
    private final JourneyRepository journeyRepository;
    private final MyPageFavoriteRepository myPageFavoriteRepository;



    // jpa
    public List<TravelListResponseDTO> myPage() {

        TokenUserInfo userinfo = (TokenUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userinfo.getEmail();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NullPointerException("없는 사람"));
        log.info("이메일 {}", email);
        log.info("아이디 {}", member.getId());

        List<Travel> byMemberId = MyPageTravelRepository.findByMemberId(member.getId());
        List<TravelListResponseDTO> dtoList = byMemberId.stream().map(travel -> new TravelListResponseDTO(travel)).collect(Collectors.toList());

        return dtoList;

    }

    public void updateShare(int id) {

        Travel travel = myPageTravelRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("업따"));
        travel.setShare(!travel.isShare());
        myPageTravelRepository.save(travel);
    }

    public void deleteBoard(int boardId, HttpSession session) {
//        myPageBoardMapper.deleteTravelByMemberOrder(boardId);
        MyPageTravelBoardRepository.deleteById(boardId);

        List<MainTravelDto> dtoList = (List<MainTravelDto>) session.getAttribute("mainTravelDtoList");



    }

    public Page<FavoriteRes> favorite(Pageable pageable) {
//        List<FavoriteListResponseDTO> favorites = favoriteMapper.favorite_List(memberId, page);
        TokenUserInfo userinfo = (TokenUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Member member = memberRepository.findByEmail(userinfo.getEmail()).orElseThrow(() -> new EntityNotFoundException("업따"));

        Page<FavoriteRes> byMemberId = myPageFavoriteRepository.findByMemberId(member.getId(), pageable);


//        favorites.forEach(dto -> {
//            dto.setFormatDate(FavoriteListResponseDTO.makeDateStringFomatter(dto.getWriteDate()));
//
//        });

        return byMemberId;
    }

    public Map<String, Object> travel(int travelId) {
        List<Journey> journeys = myPageBoardMapper.journeySelect(travelId);
        Travel travels = myPageBoardMapper.travelSelect(travelId);

        Map<String, Object> result = new HashMap<>();

        result.put("journeys", journeys);
        result.put("travels", travels);

        return result;

    }


    public void updateTravelImg(Long travelId, String savePath) {
        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new NullPointerException("업따"));
        travel.setTravelImg(savePath);
        travelRepository.save(travel);
    }


    public void updateJourneyImg(Long journeyId, String save) {
//        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new NullPointerException("없따"));
//        List<Journey> journeys = journeyRepository.findByTravel(travel).orElseThrow();
        Journey journey = journeyRepository.findById(journeyId).orElseThrow(() -> new EntityNotFoundException("업따"));
        journey.setJourneyImg(save);
        journeyRepository.save(journey);
    }

    public TravelBoard createBoard(Long travelId, String memberNickName, LocalDateTime writeDate, String content) {
        Travel travel = travelRepository.findById(travelId).orElseThrow();
        TravelBoardDto build = TravelBoardDto.builder()
                .travel(travel)
                .memberNickName(memberNickName)
                .writeDate(writeDate)
                .content(content)
                .build();

        TravelBoard travelBoard = build.toEntity();

        return MyPageTravelBoardRepository.save(travelBoard);
    }

    public Page<TravelBoardResponseDTO> findBoardAll(Pageable pageable) {

        TokenUserInfo userinfo = (TokenUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email = userinfo.getEmail();
        Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NullPointerException("없는사람"));
        String nickName = member.getNickName();


        return MyPageTravelBoardRepository.findByMemberNickName(nickName, pageable);
    }


    public Page<Travel> myTravel(Pageable pageable) {
        TokenUserInfo user = (TokenUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Member member = memberRepository.findByEmail(user.getEmail()).orElseThrow(() -> new NullPointerException("없는사람"));


        return myPageTravelRepository.findByMemberId(member.getId(), pageable);
    }

    public int findByTravelId(Long travelId) {
        Long travel = MyPageTravelBoardRepository.countById(travelId);

        return Math.toIntExact(travel);
    }

    public List<TravelJourneyRes> boardInfo(int travelNo) {
        List<TravelJourneyRes> travelJourneyResDtos
                = myPageTravelRepository.findTravelById(travelNo).orElseThrow(() -> new NullPointerException("업따"));



        return travelJourneyResDtos;

    }
}

