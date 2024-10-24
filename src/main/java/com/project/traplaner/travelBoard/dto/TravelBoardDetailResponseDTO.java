package com.project.traplaner.travelBoard.dto;

import com.project.traplaner.entity.TravelBoard;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class TravelBoardDetailResponseDTO {
    private final int id;
    private final int travelId;
    private final String content;
    private final String writeDate;
    private final String memberNickName;
//    private final int viewCount;

    public TravelBoardDetailResponseDTO(TravelBoard board) {
        this.id = board.getId();
        this.travelId = board.getTravelId();
        this.content = board.getContent();
        this.writeDate = TravelBoardListResponseDTO.makePrettierDateString(board.getWriteDate());
        this.memberNickName = board.getMemberNickName();
//        this.viewCount = board.getViewCount();
    }
}
