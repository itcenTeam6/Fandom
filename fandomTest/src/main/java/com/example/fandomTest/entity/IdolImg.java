package com.example.fandomTest.entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class IdolImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imgId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idolID")
    private Idol idolId;

    @Column(nullable = false, length = 2000)
    private String imgPath;

    @Column(nullable = false)
    private String msType;

    @Column(nullable = false)
    private String idolName;

    @CreationTimestamp
    private LocalDateTime imgDate;
}
