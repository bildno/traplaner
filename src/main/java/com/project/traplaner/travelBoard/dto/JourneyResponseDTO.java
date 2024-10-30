package com.project.traplaner.travelBoard.dto;

import com.project.traplaner.entity.Journey;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class JourneyResponseDTO {
    private String journeyName;
    private String placeName;
    private LocalDateTime journeyStartDate;
    private String locationPin;

    public JourneyResponseDTO(Journey journey) {
        this.journeyName = journey.getJourneyName();
        this.placeName = journey.getAccommodationName();
        this.journeyStartDate = journey.getStartTime();
        this.locationPin = journey.getGoogleMapLocationPinInformation();
    }
}
