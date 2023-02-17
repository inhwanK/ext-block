package com.example.extblock.domain;

import javax.persistence.*;

@Entity
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Boolean pin;
}
