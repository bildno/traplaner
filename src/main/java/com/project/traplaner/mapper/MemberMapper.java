package com.project.traplaner.mapper;

import com.project.traplaner.entity.Member;
import com.project.traplaner.mypage.dto.ModifyMemberInfoDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {

    boolean save(Member member);

    boolean duplicateTest(String type, String keyword);

    Member findOne(String email);

    boolean update(Member member);

}
