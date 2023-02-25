package com.example.extblock.domain;

import com.example.extblock.domain.vo.ExtensionName;
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
    @Embedded
    private ExtensionName extensionName;
    @Column
    @ColumnDefault("false")
    private Boolean checked;
    @Column
    @ColumnDefault("false")
    private Boolean pin;

    public Extension(
            String extensionName,
            Boolean checked,
            Boolean pin
    ) {
        this.extensionName = new ExtensionName(extensionName);
        this.checked = checked;
        this.pin = pin;
    }

    @Builder
    public Extension(
            Long id,
            String extensionName,
            Boolean checked,
            Boolean pin
    ) {
        this.id = id;
        this.extensionName = new ExtensionName(extensionName);
        this.checked = checked;
        this.pin = pin;
    }

    public String getExtensionName() {
        return extensionName.getExtensionName();
    }

    public void check() {
        this.checked = true;
    }

    public void unCheck() {
        this.checked = false;
    }

    @Override
    public String toString() {
        return "Extension{" +
                "id=" + id +
                ", name='" + extensionName + '\'' +
                ", checked=" + checked +
                ", pin=" + pin +
                '}';
    }
}
