package com.project.traplaner.travelBoard.dto;

import com.project.traplaner.entity.Favorite;
import com.project.traplaner.entity.Travel;
import com.project.traplaner.entity.TravelBoard;
import lombok.*;

import java.nio.channels.Pipe;

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

    public TravelBoardDetailResponseDTO(TravelBoard travelBoard, Travel travel) {
        this.id = travel.getId();
        this.title = travel.getTitle();
        this.writeDate = TravelBoardListResponseDTO.makePrettierDateString(travelBoard.getWriteDate());
    }
}




