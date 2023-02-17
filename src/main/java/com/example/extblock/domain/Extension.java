package com.example.extblock.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    @Column
    @ColumnDefault("false")
    private Boolean pin;

    @Builder
    public Extension(
            String name,
            Boolean pin
    ) {
        this.name = name;
        this.pin = pin;
    }
}
