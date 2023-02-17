package com.example.fandomTest.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "boardID")
@Builder
@Entity
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

    private String memNickName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idolID")
    private Idol idol;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memberID")
    private Member member;

    @JsonIgnoreProperties({"Board"})
    @OneToMany(mappedBy = "boardId",fetch = FetchType.EAGER,cascade =CascadeType.REMOVE)
    private List<Comment> comments;
}
