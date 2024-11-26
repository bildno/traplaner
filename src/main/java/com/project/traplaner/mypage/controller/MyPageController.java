package com.project.traplaner.mypage.controller;

import com.project.traplaner.common.auth.TokenUserInfo;
import com.project.traplaner.common.util.FileUtils;
import com.project.traplaner.member.entity.Member;
import com.project.traplaner.member.repository.MemberRepository;
import com.project.traplaner.member.service.MemberService;
import com.project.traplaner.mypage.dto.FavoriteRes;
import com.project.traplaner.mypage.dto.ModifyMemberInfoDTO;
import com.project.traplaner.mypage.dto.TravelJourneyRes;
import com.project.traplaner.mypage.dto.response.TravelBoardCreateDto;
import com.project.traplaner.mypage.dto.response.TravelBoardResponseDTO;
import com.project.traplaner.mypage.dto.response.TravelListResponseDTO;
import com.project.traplaner.mypage.service.MyPageService;
import com.project.traplaner.travelBoard.entity.TravelBoard;
import com.project.traplaner.travelplan.entity.Travel;
import com.project.traplaner.travelplan.service.TravelService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MyPageController {

    private final MyPageService myPageService;
    private final MemberService memberService;
    private final TravelService travelService;
    private final MemberRepository memberRepository;



    // 마이페이지 메인 (달력 있는 곳)
    // 달력에 일정 띄워주는 작업 해야댐
    // 계정관리 페이지 작성 필요
    @GetMapping("/my-page")
    @ResponseBody
    public ResponseEntity<?> myPage() {

        List<TravelListResponseDTO> travelListResponseDTOS = myPageService.myPage();


        return new ResponseEntity<>(travelListResponseDTOS, HttpStatus.OK);
    }


    // 마이 페이지 내 게시물
    // db연동 아직 x
//    @GetMapping("/my-page/mytravelboard/{nickName}")
//    public String myBoard(@PathVariable String nickName, Model model,
//                          @ModelAttribute("s") PageDTO page) {
//
//        Map<String, Object> map = myPageBoardService.findBoardAll(nickName, page);
//
//        model.addAttribute("dtoList", map.get("boardAll"));
//        model.addAttribute("maker", map.get("pm"));
//
//        return "member/my-board";
//    }

    // 마이페이지 내 게시물
    @GetMapping("/my-page/mytravelboard")
    @ResponseBody
    public ResponseEntity<?> myBoard(Pageable pageable) {

        Page<TravelBoardResponseDTO> map = myPageService.findBoardAll(pageable);
        List<TravelBoardResponseDTO> content = map.getContent();

        return new ResponseEntity<>(content, HttpStatus.OK);
    }


    // 마이페이지 나의 여행
    @GetMapping("/my-page/my-travel")
    public ResponseEntity<?> myTravel(Pageable pageable) {
        Page<Travel> travels = myPageService.myTravel(pageable);
        List<Travel> content = travels.getContent();

        return new ResponseEntity<>(content, HttpStatus.OK);
    }

    // 공유여부 변경
    @PostMapping("/my-page/shareIs/{boardId}")
    @ResponseBody
    public ResponseEntity<?> shareIs(@PathVariable int boardId) {
        myPageService.updateShare(boardId);

        return ResponseEntity.ok().body("success");
    }

//     글 삭제
    @PostMapping("/my-page/delete/{boardId}")
    @ResponseBody
    public ResponseEntity<?> deleteBoard(@PathVariable int boardId,
                                         HttpSession session) {



        myPageService.deleteBoard(boardId, session);

        return ResponseEntity.ok().body("success");
    }



    // 좋아요 게시물
//    @GetMapping("/my-page/favorite/{memberId}")
//    public String favorite(@PathVariable int memberId,
//                           @ModelAttribute("s") PageDTO page,
//                           Model model) {
//        Map<String, Object> map = myPageService.favorite(memberId, page);
//
//        model.addAttribute("list", map.get("favorite"));
//        model.addAttribute("maker", map.get("pm"));
//
//        return "member/favorite";
//    }

    @GetMapping("/my-page/favorite")
    @ResponseBody
    public ResponseEntity<?> favorite(Pageable pageable) {
        Page<FavoriteRes> favorite = myPageService.favorite(pageable);
        List<FavoriteRes> content = favorite.getContent();

        return new ResponseEntity<>(content, HttpStatus.OK);
    }


    // 비밀번호 변경
    @GetMapping("/my-page/pwChange")
    public String pwChange() {


        return "member/my-pw-change";
    }

    @PostMapping("/my-page/changeConfirm")
    @ResponseBody
    public ResponseEntity<?> changeConfirm(@Validated @RequestBody ModifyMemberInfoDTO dto) {

        boolean b = memberService.updateInfo(dto);


        if (b) {
            return ResponseEntity.ok().body("success");
        } else {
            return ResponseEntity.ok().body("fail");
        }


    }

    // 닉네임 중복체크
    @PostMapping("/my-page/nickNameChk/{newNick}")
    @ResponseBody
    public ResponseEntity<?> nickNameChk(@PathVariable String newNick) {
        String type = "nickname";

        boolean b = memberService.duplicateTest(type, newNick);
        System.out.println(b);
        if (!b) {
            return ResponseEntity.ok().body("success");
        } else {
            return ResponseEntity.badRequest().body("fail");
        }

    }


    @GetMapping("my-page/board-info/{travelNo}")
    @ResponseBody
    public ResponseEntity<?> boardInfo(@PathVariable int travelNo) {
        log.info("여행번호 {}", travelNo);
        List<TravelJourneyRes> stringObjectMap = myPageService.boardInfo(travelNo);

        return new ResponseEntity<>(stringObjectMap, HttpStatus.OK);
    }


    @Value("${file.upload.root-path}")
    private String rootPath;

    // 게시글 작성
   @PostMapping("/my-page/insert-board")
   @ResponseBody
   public ResponseEntity<?> insertBoard(TravelBoardCreateDto dto){



       TokenUserInfo userinfo = (TokenUserInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       String email = userinfo.getEmail();
       Member member = memberRepository.findByEmail(email).orElseThrow(() -> new NullPointerException("없는 사람"));
       String nickName = member.getNickName();


       if (StringUtils.hasText(dto.getTravelImg().getOriginalFilename())) {

            String savePath = FileUtils.uploadFile(dto.getTravelImg(), rootPath);

            myPageService.updateTravelImg(dto.getTravelId(), savePath);

        }


       int byTravelId = myPageService.findByTravelId(dto.getTravelId());

       if(byTravelId == 0) {
           TravelBoard board = myPageService.createBoard(dto.getTravelId(), nickName, LocalDateTime.now(), dto.getContent());
       }

       if (!dto.getJourneyImage().isEmpty()) {
            for (int i = 0, j = dto.getJourneyId().size(); i < j; i++ ) {
                String save = FileUtils.uploadFile(dto.getJourneyImage().get(i), rootPath);
                if (save != null )myPageService.updateJourneyImg(Long.valueOf(dto.getJourneyId().get(i)), save);
            }
        }

     return new ResponseEntity<>("등록성공",HttpStatus.OK);

   }

}



