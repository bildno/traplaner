package com.project.traplaner.main.controller;

import com.project.traplaner.common.dto.CommonResDto;
import com.project.traplaner.main.dto.TopThreeFavoriteTravelDto;
import com.project.traplaner.main.repository.TopThreeFavoriteTravelRepository;
import com.project.traplaner.main.service.MainService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
   Old Code, 주석처리, 11.21, by jhjeong

@Controller
@RequiredArgsConstructor
@Slf4j

 */
@RestController
@RequestMapping("/main")
@RequiredArgsConstructor
@Slf4j
public class MainController {

//    private final TopThreeFavoriteTravelRepository topThreeFavoriteTravelRepository;
    private final MainService mainService;

    @GetMapping("/top3-favorite")
    public ResponseEntity<?> listTop3FavoriteTravel() {
        log.info("listTop3FavoriteTravel");
        List<TopThreeFavoriteTravelDto> top3FavoriteTravels
                = mainService.getTop3FavoriteTravels();

        CommonResDto resDto
                = new CommonResDto(HttpStatus.OK, "Top3 Favorite Travels", top3FavoriteTravels);

        return new ResponseEntity<>(resDto, HttpStatus.OK);
    }

//
//   Old Code, 주석처리, 11.21, by jhjeong
//
//    private final TravelMapper travelMapper;
//
//    @GetMapping("/")
//    public String mainPage(HttpSession session, Model model) {
//
//        log.info("main page 요청 들어옴!");
//
//        if (session.getAttribute("login") != null) {
//            log.info("로그인 중인 사용자");
//
//            // Top 3 Favorite, 10/28, by jhjeong
//            List<TopThreeFavoriteTravelDto> topThreeFavoriteTravelDtoList = travelMapper.findTopThree();
//            System.out.println("--------------------------------------------------");
//            System.out.println(topThreeFavoriteTravelDtoList.toString());
//            System.out.println("--------------------------------------------------");
//            model.addAttribute("topThree", topThreeFavoriteTravelDtoList);
//        }
//
//        return "index";
//    }

}
