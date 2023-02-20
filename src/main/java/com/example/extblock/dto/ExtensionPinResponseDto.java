package com.example.extblock.dto;


import com.example.extblock.domain.Extension;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ExtensionPinResponseDto {
    private String name;
    private Boolean checked;

    public ExtensionPinResponseDto(Extension extension) {
        this.name = extension.getExtensionName();
        this.checked = extension.getChecked();
    }
}
