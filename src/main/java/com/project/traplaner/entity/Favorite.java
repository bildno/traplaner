package com.project.traplaner.entity;

import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString

public class Favorite {
    private int id;
    private int userId;
    private int travelBoardId;
}