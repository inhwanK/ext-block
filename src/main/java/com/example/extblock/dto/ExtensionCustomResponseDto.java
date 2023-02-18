package com.example.extblock.dto;


import com.example.extblock.domain.Extension;
import lombok.Getter;

// 이거 굳이 필요한가...
@Getter
public class ExtensionCustomResponseDto {

    private String name;

    public ExtensionCustomResponseDto(String name) {
        this.name = name;
    }
}
