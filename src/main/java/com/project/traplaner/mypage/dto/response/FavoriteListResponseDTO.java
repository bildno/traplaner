package com.project.traplaner.mypage.dto.response;


import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FavoriteListResponseDTO {

    private int id;
    private int memberId;
    private int travelBoardId;
    private String member_nick_name;
    private LocalDateTime writeDate;

    private String formatDate;

    public static String makeDateStringFomatter(LocalDateTime startDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf.format(startDate);
    }

}
