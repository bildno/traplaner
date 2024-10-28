package com.project.traplaner.travelplan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TravelPlanRequestDTO {

    private TravelInfo travel;

    @Setter @Getter @ToString
    public static class TravelInfo {
        @JsonProperty("title")
        private String title;
        @JsonProperty("startDate")
        private String startDate;
        @JsonProperty("endDate")
        private String endDate;
    }

    List<JourneyInfo> journeys;

    @Setter @Getter @ToString
    @JsonIgnoreProperties("reservation")
    public static class JourneyInfo {
        @JsonProperty("id")
        private int journeyId;

        @JsonProperty("day")
        private int day;

        @JsonProperty("date")
        private String date;

        @JsonProperty("location")
        private String location;

        @JsonProperty("title")
        private String journeyName;
        @JsonProperty("address")
        private String accommodationRoadAddressName;
        @JsonProperty("startTime")
        private String startTime;
        @JsonProperty("endTime")
        private String endTime;
        @JsonProperty("locationId")
        private String googleMapLocationPinInformation;

        private MultipartFile reservationConfirmImagePath;
        @JsonProperty("budget")
        private int budget;
    }
}
