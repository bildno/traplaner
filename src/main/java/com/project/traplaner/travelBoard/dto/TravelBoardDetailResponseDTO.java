package com.project.traplaner.travelBoard.dto;

import com.project.traplaner.entity.Journey;
import com.project.traplaner.entity.Member;
import com.project.traplaner.entity.Travel;
import com.project.traplaner.entity.TravelBoard;
import lombok.*;

import java.time.LocalDateTime;

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
    private String img;
    private String content;
    private int likeCount;
    private String journeyName;
    private String placeName;
    private LocalDateTime journeyStartTime;
    private String locationPin;
    private int day;

    public TravelBoardDetailResponseDTO(Member member, Travel travel, TravelBoard travelBoard, Journey journey, int likeCount, int day) {
        this.id = travel.getId();
        this.title = travel.getTitle();
        this.writer = member.getNickName();
        this.writeDate = TravelBoardListResponseDTO.makePrettierDateString(travelBoard.getWriteDate());
        this.img = travel.getTravelImg();
        this.content = travelBoard.getContent();
        this.likeCount = likeCount;
        this.journeyName = journey.getJourneyName();
        this.placeName = journey.getAccommodationName();
        this.journeyStartTime = journey.getStartTime();
        this.locationPin = journey.getGoogleMapLocationPinInformation();
        this.day = day;
    }

}





