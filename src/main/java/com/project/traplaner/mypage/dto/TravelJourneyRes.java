package com.project.traplaner.mypage.dto;

import java.time.LocalDateTime;

public interface TravelJourneyRes {

     LocalDateTime getStartDate();
     LocalDateTime getEndDate();
     String getTitle();
     String getTravelImg();
     String getJourneyImg();
     String getJourneyName();


}