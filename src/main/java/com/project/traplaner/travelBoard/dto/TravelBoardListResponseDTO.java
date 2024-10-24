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
    private String shortTitle;
    private String writer;
    private String writeDate;

    public TravelBoardListResponseDTO(TravelBoardDetailResponseDTO board) {
        this.shortTitle = makeShortTitle(board.getTitle());
        this.writer = board.getWriter();
        this.writeDate = board.getWriteDate();
    }

    public static String makePrettierDateString(LocalDateTime writeDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일");
        return dtf.format(writeDate);
    }

    private String makeShortTitle(String title) {
        return sliceString(title, 10);
    }

    private String sliceString(String target, int wishLength) {
        return (target.length() > wishLength)
                ? target.substring(0, wishLength) + "..."
                : target;
    }
}
