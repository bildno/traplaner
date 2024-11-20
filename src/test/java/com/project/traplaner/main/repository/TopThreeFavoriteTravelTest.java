package com.project.traplaner.main.repository;

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
class TopThreeFavoriteTravelTest {

    @Autowired
    TopThreeFavoriteTravelRepository topThreeFavoriteTravelRepository;

    @Test
    @DisplayName("Top 3 좋아요 Travel 조회")
    void testFindTopThreeFavoriteTravel() {
        // given
        PageRequest pageRequest = PageRequest.of(0, 3);

        // when
        List<TopThreeFavoriteTravelDto> topThreeFavoriteTravel
                = topThreeFavoriteTravelRepository.findTopThreeFavoriteTravel(pageRequest);

        // then
        topThreeFavoriteTravel.forEach(System.out::println);
        assertNotNull(topThreeFavoriteTravel);
    }
}