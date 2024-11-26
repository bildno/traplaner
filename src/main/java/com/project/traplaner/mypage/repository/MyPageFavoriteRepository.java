package com.project.traplaner.mypage.repository;

import com.project.traplaner.mypage.dto.FavoriteRes;
import com.project.traplaner.travelBoard.entity.Favorite;
import com.project.traplaner.travelplan.entity.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MyPageFavoriteRepository extends JpaRepository<Favorite, Integer> {


    @Query(value = "SELECT " +
            " tb.memberNickName AS memberNickName ," +
            " tb.writeDate AS writeDate," +
            " t.title AS title" +
            " FROM Favorite f JOIN TravelBoard tb on f.travelBoard.id = tb.id" +
            " join Travel  t on tb.travel.id = t.id WHERE  f.member.id = ?1" )
    Page<FavoriteRes> findByMemberId(Integer id, Pageable pageable);
}
