package com.project.traplaner.travelBoard.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Getter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class TravelBoardListResponseDTO {
    private String id;
    private String shortTitle;
    private String writer;
    private String writeDate;

    public TravelBoardListResponseDTO(TravelBoardDetailResponseDTO board) {
        this.shortTitle = makeShortTitle(board.getTitle());
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
