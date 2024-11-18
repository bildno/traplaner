package com.project.traplaner.mypage.repository;

import com.project.traplaner.entity.Travel;
import com.project.traplaner.entity.TravelBoard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;

public interface MyPageTravelRepository extends JpaRepository<Travel, Integer> {

    static void findByNickName(Pageable pageable) {
    }

    List<Travel> findByMemberId(int id);

}
