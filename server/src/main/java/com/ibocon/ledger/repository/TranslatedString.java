package com.ibocon.ledger.repository;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE, force = true)
@Entity
@Table(uniqueConstraints = {
    @UniqueConstraint(
        name = "ENGLISH_KOREAN_UNIQUE",
        columnNames = {"ENGLISH", "KOREAN"})})
public class TranslatedString {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    final private String english;

    @Column(nullable = false)
    final private String korean;
}