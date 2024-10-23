package com.project.traplaner.entity;

import lombok.*;

import java.time.LocalDateTime;

@Getter @ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Travel {
    private int id;
    private int memberId;
    private String title;
    private boolean isDomestic;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private boolean share;
}
