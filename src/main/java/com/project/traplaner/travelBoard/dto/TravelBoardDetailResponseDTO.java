package com.project.traplaner.travelBoard.dto;

import com.project.traplaner.entity.Member;
import com.project.traplaner.entity.Travel;
import com.project.traplaner.entity.TravelBoard;
import lombok.*;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public class TravelBoardDetailResponseDTO {
    private int id;
    private String title;
    private String writer;
    private String writeDate;
    private String content;
    private int likeCount;

    public TravelBoardDetailResponseDTO(TravelBoard travelBoard, Travel travel, Member member, int likeCount) {
        this.id = travel.getId();
        this.title = travel.getTitle();
        this.writer = member.getNickName();
        this.writeDate = TravelBoardListResponseDTO.makePrettierDateString(travelBoard.getWriteDate());
        this.content = travelBoard.getContent();
        this.likeCount = likeCount;
    }

}





