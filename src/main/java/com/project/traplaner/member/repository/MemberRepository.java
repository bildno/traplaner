package com.project.traplaner.member.repository;

import com.project.traplaner.member.entity.Member;
import com.project.traplaner.travelplan.entity.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {


    Optional<Member> findByEmail(String email);

//    @Query("SELECT m FROM Member m JOIN FETCH m.travels")
//    List<Travel> findAllByEmail();
}

/*
select t.*
from tbl_travel t
JOIN tbl_member tm ON t.member_id = tm.id
where tm.email="test78@abc.net";
 */