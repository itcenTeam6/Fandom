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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
