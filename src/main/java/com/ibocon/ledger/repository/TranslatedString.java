package com.ibocon.ledger.repository;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(
        name = "KOREAN_UNIQUE",
        columnNames = {"KOREAN"}),
    })
public class TranslatedString {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    final private String korean;

    private String english;
}