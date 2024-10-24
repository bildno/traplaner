package com.project.traplaner.member.service;

import com.project.traplaner.mapper.MemberMapper;
import com.project.traplaner.member.dto.SignUpRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    public boolean join(SignUpRequestDto dto, String savePath){

        return memberMapper.save(dto.toEntity(encoder, savePath));
    }

    public boolean overlapping(String email){

        return memberMapper.overlapping(email);
    }

}
