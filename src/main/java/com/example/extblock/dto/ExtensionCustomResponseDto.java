package com.example.extblock.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

// 이거 굳이 필요한가...
@Getter
@NoArgsConstructor
public class ExtensionCustomResponseDto {

    private String name;

    public ExtensionCustomResponseDto(String name) {
        this.name = name;
    }
}
