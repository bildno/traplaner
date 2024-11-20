package com.project.traplaner.main;

import com.project.traplaner.main.dto.TopThreeFavoriteTravelDto;
import com.project.traplaner.main.repository.TopThreeFavoriteTravelRepository;
import com.project.traplaner.travelplan.mapper.TravelMapper;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    private final TopThreeFavoriteTravelRepository topThreeFavoriteTravelRepository;

    @GetMapping("/top3-favorite")
    public ResponseEntity<?> listTop3FavoriteTravel() {
        log.info("listTop3FavoriteTravel");
        mainService.getTop3FavoriteTravels();
        return null;
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
