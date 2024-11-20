package com.project.traplaner.main.repository;

import com.project.traplaner.main.dto.TopThreeFavoriteTravelDto;
import com.project.traplaner.travelplan.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopThreeFavoriteTravel
        extends JpaRepository<Travel, Integer> {

    @Query(value = """
            SELECT new TopThreeFavoriteTravelDto(
                t.id,
                t.member,
                tb.memberNickName,
                t.title,
                t.travelImg,
                COUNT(f.id) as likeCount,
                tb.content
            )
            FROM Travel t
            JOIN t.travelBoard tb
            LEFT JOIN tb.favorites f
            GROUP BY t.id, t.memberId, tb.memberNickName, t.title, t.travelImg, tb.content
            ORDER BY COUNT(f.id) DESC
            """
    )
    List<TopThreeFavoriteTravelDto> findTopThreeFavoriteTravel();
}
