package com.project.traplaner.main.repository;

import com.project.traplaner.main.dto.MainTravelDto;
import com.project.traplaner.main.dto.TopThreeFavoriteTravelDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class MainTravelRepositoryTest {

    @Autowired
    MainTravelRepository mainTravelRepository;

    @Test
    @DisplayName("Top 3 좋아요 Travel 조회")
    void testFindTopThreeFavoriteTravel() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3);

        // when
        List<TopThreeFavoriteTravelDto> topThreeFavoriteTravel
                = mainTravelRepository.findTopThreeFavoriteTravel(pageRequest);

        // then
        topThreeFavoriteTravel.forEach(System.out::println);
        assertNotNull(topThreeFavoriteTravel);
    }

    @Test
    @DisplayName("Email로 가입자의 모든 Travel 조회")
    void findTravelListByMemberEmail() {
        // given
        String email = "test78@abc.net";

        // when
        List<MainTravelDto> travelDtoList
                = mainTravelRepository.findAllTravelByMemberEmail(email);

        // then
        System.out.println("travelDtoList = " + travelDtoList);

        assertNotNull(travelDtoList);

    }

}