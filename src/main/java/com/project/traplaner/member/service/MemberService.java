package com.project.traplaner.member.service;

import com.project.traplaner.entity.Member;
import com.project.traplaner.mapper.MemberMapper;
import com.project.traplaner.member.dto.LoginRequestDto;
import com.project.traplaner.member.dto.LoginUserResponseDTO;
import com.project.traplaner.member.dto.SignUpRequestDto;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static com.project.traplaner.member.service.LoginResult.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    public boolean join(SignUpRequestDto dto, String savePath){

        return memberMapper.save(dto.toEntity(encoder, savePath));
    }

    public boolean duplicateTest(String type, String keyword){
       return memberMapper.duplicateTest(type,keyword);
    }

    //로그인 검증
    public LoginResult authenticate(LoginRequestDto dto, HttpSession session, HttpServletResponse response) {
        Member foundMember = memberMapper.findOne(dto.getEmail());
        if(foundMember == null){
            return NO_ACC;
        }
        if (!encoder.matches(dto.getPassword(), foundMember.getPassword())) {
            return NO_PW;
        }
        return SUCCESS;
    }

    public void maintainLoginState(HttpSession session, String email) {
        Member foundMember = memberMapper.findOne(email);
        // DB 데이터를 사용할 것만 정제
        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
                .id(foundMember.getId())
                .nickName(foundMember.getNickName())
                .email(foundMember.getEmail())
                .loginMethod(foundMember.getLoginMethod().toString())
                .profile(foundMember.getProfileImg())
                .build();

        // 세션에 로그인 한 회원 정보를 저장
        session.setAttribute("login", dto);
        // 세션 수명 설정
        session.setMaxInactiveInterval(60 * 60); // 1시간

    }

    public void kakaoLogout(LoginUserResponseDTO dto, HttpSession session) {

        String requestUri = "https://kapi.kakao.com/v1/user/logout";

        String accessToken = (String) session.getAttribute("access_token");

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + accessToken);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("target_id_type", "email");
        params.add("target_id", dto.getEmail());

        ResponseEntity<Map> responseEntity = new RestTemplate().exchange(
                requestUri,
                HttpMethod.POST,
                new HttpEntity<>(params, headers),
                Map.class);

    }

    public void naverLogout(LoginUserResponseDTO dto, HttpSession session) {

    }
}
