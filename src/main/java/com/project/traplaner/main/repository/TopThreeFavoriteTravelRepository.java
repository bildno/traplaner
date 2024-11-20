package com.project.traplaner.main.repository;

import com.project.traplaner.main.dto.TopThreeFavoriteTravelDto;
import com.project.traplaner.travelplan.entity.Travel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TopThreeFavoriteTravelRepository
        extends JpaRepository<Travel, Integer> {


//    public class TopThreeFavoriteTravelDto {
//
//        private int id;               // 여행 ID
//        private int memberId;         // 회원 ID
//        private String memberNickName; // 회원 닉네임
//        private String title;          // 여행 제목
//        private String travelImg;      // 여행 이미지 URL
//        private Integer likeCount;     // 좋아요 수
//        private String content;        // 여행 게시판 내용
//
//    }
    @Query(value = """
            SELECT new com.project.traplaner.main.dto.TopThreeFavoriteTravelDto(
                t.id, 
                t.member,
                tb.memberNickName,
                t.title,
                t.travelImg,
                COUNT(f.id),
                tb.content
            )
            FROM Travel t
            JOIN t.travelBoard tb
            LEFT JOIN tb.favorites f
            GROUP BY t.id, t.member, tb.memberNickName, t.title, t.travelImg, tb.content
            ORDER BY COUNT(f.id) DESC
            """
    )

    List<TopThreeFavoriteTravelDto> findTopThreeFavoriteTravel(Pageable pageable);


}
