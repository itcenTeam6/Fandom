package com.example.NewJeans.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "cmtID")
@Builder

@Entity
//@Table(name = "Comment")
public class Comment {

    @Id
    @GeneratedValue(generator = "system-uuid") // 중복없는 랜덤문자로 pk 생성전략
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private Long cmtID;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardID")
    private Board boardId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "memID")
    private Member memID;

    private String cmtContent;

    @CreationTimestamp
    private LocalDateTime cmtDate;


}
