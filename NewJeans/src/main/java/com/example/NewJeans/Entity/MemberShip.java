package com.example.NewJeans.Entity;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

//Beans
@Getter @Setter @ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="msId")
@Builder
//JPA
@Entity
//@Table(name="memberShip")
public class MemberShip {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="msId")
    private Long msId;

    @Column(name="msType",nullable = false)
    private String msType;

    @CreationTimestamp
    @Column(name="bCnt")
    private LocalDateTime bCnt;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="memId")
    private Member mem;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IdolID")
    private Idol Idol;
}