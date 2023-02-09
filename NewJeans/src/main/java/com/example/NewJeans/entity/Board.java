package com.example.NewJeans.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDateTime;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @ EqualsAndHashCode
@Builder @Entity
//@Table(name = "Board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardID;

    @Column(nullable = false)
    private String boardContent;

    private String boardFile;

    @CreationTimestamp
    private LocalDateTime boardDate;
    
    private int boardCnt;

    private int boardLike;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idolID")
    private Idol idol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberID")
    private  Member member;
}
