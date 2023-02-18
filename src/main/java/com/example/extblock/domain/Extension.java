package com.example.extblock.domain;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Extension {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // unique key 고려하기, vo embeded로 빼기
    @Column(nullable = false, length = 20)
    private String name;
    @Column
    @ColumnDefault("true")
    private Boolean checked;
    @Column
    @ColumnDefault("false")
    private Boolean pin;

    @Builder
    public Extension(
            String name,
            Boolean checked,
            Boolean pin
    ) {
        this.name = name;
        this.checked = checked;
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "Extension{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", checked=" + checked +
                ", pin=" + pin +
                '}';
    }
}
