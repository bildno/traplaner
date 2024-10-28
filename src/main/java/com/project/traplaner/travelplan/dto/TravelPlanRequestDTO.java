package com.project.traplaner.travelplan.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.traplaner.entity.Journey;
import com.project.traplaner.entity.Travel;
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
        private static String startDate;
        @JsonProperty("endDate")
        private static String endDate;

        public Travel toEntity(int memberId) {
            return Travel.builder()
                    .memberId(memberId)
                    .title(title)
                    .startDate(LocalDateTime.parse(startDate))
                    .endDate(LocalDateTime.parse(endDate))
                    .build();
        }
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

        public Journey toEntity(int travelId, String filePath) {
            return Journey.builder()
                    .journeyName(journeyName)
                    .accommodationRoadAddressName(accommodationRoadAddressName)
                    .startDate(LocalDateTime.parse(startTime))
                    .endDate(LocalDateTime.parse(endTime))
                    .googleMapLocationPinInformation(googleMapLocationPinInformation)
                    .reservationConfirmImagePath(filePath)
                    .budget(budget)
                    .build();
        }
        public Journey toEntity(int travelId) {
            return Journey.builder()
                    .journeyName(journeyName)
                    .accommodationRoadAddressName(accommodationRoadAddressName)
                    .startDate(LocalDateTime.parse(startTime))
                    .endDate(LocalDateTime.parse(endTime))
                    .googleMapLocationPinInformation(googleMapLocationPinInformation)
                    .budget(budget)
                    .build();
        }
    }

}
