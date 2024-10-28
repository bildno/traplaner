package com.project.traplaner.member.dto;

import com.project.traplaner.main.dto.TopThreeFavoriteTravelDto;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter @ToString @Builder
public class LoginUserResponseDTO {
    private int id;
    private String nickName;
    private String email;
    private String loginMethod;
    private String profile;

    // 10/28, by jhjeong
    private List<TopThreeFavoriteTravelDto> topThreeFavoriteTravelDtoList;
}