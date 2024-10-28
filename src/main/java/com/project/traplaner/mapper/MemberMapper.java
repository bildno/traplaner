package com.project.traplaner.mapper;

import com.project.traplaner.entity.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {

    boolean save(Member member);

    boolean duplicateTest(@Param("type") String type, @Param("keyword") String keyword);

    Member findOne(String email);
}
