package com.project.traplaner.member.dto;

import com.project.traplaner.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
public class SignUpRequestDto {

    private String email;
    private String password;
    private String nickName;

    private Member.LoginMethod loginMethod;

    public Member toEntity() {
        return Member.builder()
                .email(email)
                .password(password)
                .nickName(nickName)
                .loginMethod(loginMethod)
                .build();
    }

}
