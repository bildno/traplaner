package com.project.traplaner.member.service;

import com.project.traplaner.mapper.MemberMapper;
import com.project.traplaner.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;


    public boolean join(SignUpRequestDto dto){

        return memberMapper.save(dto.toEntity());
    }

    public boolean overlapping(String email){

        return memberMapper.overlapping(email);
    }
    public boolean duplicateTest(String type, String keyword){
       return memberMapper.duplicateTest(type,keyword);
    }


}
