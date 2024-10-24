package com.project.traplaner.travelBoard.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@EqualsAndHashCode
public class TravelBoardListResponseDTO {
    private final int id;
    private final int travel_id; // 5자 넘어가면 잘라내기
    private final String shortContent; // 30자 넘어가면 잘라내기
    private final String writeDate; // yyyy-MM-dd HH:mm
    //    private final int viewCount;
    private final String memberNickName;

    public TravelBoardListResponseDTO(TravelBoardDetailResponseDTO board) {
        this.id = board.getId();
        this.travel_id = board.getTravelId();
        this.shortContent = makeShortContent(board.getContent());
        this.writeDate = board.getWriteDate();
//        this.viewCount = board.getViewCount();
        this.memberNickName = board.getMemberNickName();
    }

    public static String makePrettierDateString(LocalDateTime regDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm");
        return dtf.format(regDate);
    }

    private String makeShortContent(String content) {
        return sliceString(content, 30);
    }


    //    private String makeShortTitle(String title) {
//        return sliceString(title, 5);
//    }
//
//    // target: 줄이고 싶은 문자열 원본
//    // wishLength: 자르고 싶은 글자 수
//    // target의 길이가 wishLength보다 길면 wishLength만큼 잘라서 뒤에 ...을 붙여서 리턴
    private String sliceString(String target, int wishLength) {
        return (target.length() > wishLength)
                ? target.substring(0, wishLength) + "..."
                : target;
    }
}
