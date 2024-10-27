package com.project.traplaner.travelBoard.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ArrangeDTO {
    private String type;

    public ArrangeDTO() {
        this.type = "";
    }
}
