package com.project.traplaner.main.service;

import com.project.traplaner.main.dto.TopThreeFavoriteTravelDto;
import com.project.traplaner.main.repository.TopThreeFavoriteTravelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MainService {

    private final TopThreeFavoriteTravelRepository topThreeFavoriteTravelRepository;

    public List<TopThreeFavoriteTravelDto> getTop3FavoriteTravels() {

        int pageNumber = 0;
        int desiredFavoriteCount = 3;
        PageRequest pageRequest = PageRequest.of(pageNumber, desiredFavoriteCount);

        // when
        List<TopThreeFavoriteTravelDto> topThreeFavoriteTravel
                = topThreeFavoriteTravelRepository.findTopThreeFavoriteTravel(pageRequest);

        return topThreeFavoriteTravel;
    }
}
