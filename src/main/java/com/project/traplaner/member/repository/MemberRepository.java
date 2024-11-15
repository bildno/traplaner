package com.project.traplaner.member.repository;

import com.project.traplaner.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {


}
