package com.project.traplaner.mypage.dto;

import com.project.traplaner.travelBoard.entity.TravelBoard;
import com.project.traplaner.travelplan.entity.Travel;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelBoardDto {
    
    private Travel travel;
    private String memberNickName;
    private LocalDateTime writeDate;
    private String content;


    public TravelBoard toEntity() {
        return TravelBoard.builder()
                .travel(travel)
                .memberNickName(memberNickName)
                .writeDate(writeDate).content(content).build();
    }

}
