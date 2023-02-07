package com.example.NewJeans.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter @Setter @ToString
@AllArgsConstructor @NoArgsConstructor @EqualsAndHashCode
@Builder @Entity
//@Table(name = "Board")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long boardID;

    //@Column(nullable = false)   //게시판에 작성자 닉네임
    private String memNickName;

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

    @OneToMany(mappedBy = "boardId",fetch = FetchType.EAGER,cascade =CascadeType.REMOVE)
    private List<Comment> comments;

}
