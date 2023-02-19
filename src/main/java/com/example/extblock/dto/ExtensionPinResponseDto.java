package com.example.extblock.dto;


import com.example.extblock.domain.Extension;

public class ExtensionPinResponseDto {
    private String name;
    private Boolean checked;

    public ExtensionPinResponseDto(Extension extension) {
        this.name = extension.getName();
        this.checked = extension.getChecked();
    }
}
