package com.project.traplaner.member.dto;

import com.project.traplaner.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@ToString
@Builder
public class SignUpRequestDto {
    private String email;
    private String password;
    private String nickName;

    private Member.LoginMethod loginMethod;

    public Member toEntity(PasswordEncoder encoder) {
        return Member.builder()
                .email(email)
                .password(encoder.encode(password))
                .nickName(nickName)
                .loginMethod(loginMethod)
                .build();
    }

}
