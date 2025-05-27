package com.genshinimpact.crud.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "characters")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String element;

    @Column(nullable = false)
    private String weaponType;

    @Column(nullable = false)
    private String rarity;

    @Column(nullable = false)
    private String region;
}