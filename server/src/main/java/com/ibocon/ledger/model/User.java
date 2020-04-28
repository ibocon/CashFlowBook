package com.ibocon.ledger.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
@Table(name = "USERS")
public class User {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Long id;

    @NotNull
    @Column(name = "\"name\"")
    private String name;

}