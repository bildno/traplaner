package com.project.traplaner.mypage.dto.response;

import com.project.traplaner.travelBoard.entity.TravelBoard;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TravelBoardCreateDto {

    private Long travelId;
    private MultipartFile travelImg;
    private List<MultipartFile> journeyImage;
    private String content;
    private List<Integer> journeyId;


}
