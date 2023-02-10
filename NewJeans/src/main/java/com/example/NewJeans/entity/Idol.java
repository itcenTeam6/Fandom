package com.example.NewJeans.entity;


import lombok.*;

import javax.persistence.*;

@Getter@Setter@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Builder
@Entity
public class Idol {
    
    @Id
    @Column(name="idolID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idolID;

    @Column(nullable = false)
    private String idolName;

    @Column(nullable = false)
    private String idolMainImg;
    
}
