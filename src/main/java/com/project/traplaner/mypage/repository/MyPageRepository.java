package com.project.traplaner.mypage.repository;

import com.project.traplaner.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MyPageRepository extends JpaRepository<Member, Integer> {

}
