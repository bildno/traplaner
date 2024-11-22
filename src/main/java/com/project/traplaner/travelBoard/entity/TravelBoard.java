package com.project.traplaner.travelBoard.entity;

import com.project.traplaner.travelplan.entity.Travel;
import jakarta.persistence.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/*
   1. 여행 목록 게시판 Entity 클래스
   2. 작성자: 정재훈
   3. 테이블 생성 SQL:
    CREATE TABLE `tbl_travel_board` (
    `id`        int        NOT NULL,
    `travel_id` int        NOT NULL,
    `member_nick_name`  varchar(30)   NOT NULL,
    `write_date`        datetime      NOT NULL,
    `content`           varchar(1000) NOT NULL
    );
 */

@Getter @Setter
@ToString @EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Slf4j
@Entity
@Table(name = "tbl_travel_board")
public class TravelBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "travel_id")
    Travel travel;

    @Column(name = "member_nick_name")
    private String memberNickName;

    @CreationTimestamp
    @Column(name = "write_date")
    private LocalDateTime writeDate;

    private String content;
}
