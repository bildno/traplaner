package com.project.traplaner.mypage.repository;

import com.project.traplaner.travelplan.entity.Travel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.lang.constant.Constable;
import java.util.List;

public interface MyPageTravelRepository extends JpaRepository<Travel, Integer> {

    List<Travel> findByMemberId(int id);

    Page<Travel> findByMemberId(int id, Pageable pageable);


}
