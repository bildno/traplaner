package com.project.traplaner.mapper;

import com.project.traplaner.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    boolean save(Member member);

    boolean overlapping(String email);

    boolean duplicateTest(String type, String keyword);
}
