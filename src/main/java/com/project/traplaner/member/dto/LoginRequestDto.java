package com.project.traplaner.member.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
@Builder
public class LoginRequestDto {

    private String email;
    private String password;

}
