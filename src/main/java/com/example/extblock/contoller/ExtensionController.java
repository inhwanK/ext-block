package com.example.extblock.contoller;

import com.example.extblock.domain.Extension;
import com.example.extblock.domain.ExtensionRepository;
import com.example.extblock.dto.ExtensionCustomResponseDto;
import com.example.extblock.dto.ExtensionPinResponseDto;
import com.example.extblock.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ExtensionController {

    private final ExtensionService extensionService;
    private final ExtensionRepository extensionRepository;

    // 고정 확장자 조회하기 - pin 된 것만
    @GetMapping("/extensions/pin")
    public List<ExtensionPinResponseDto> getPinExtensions() {
        List<Extension> extensions = extensionRepository.findByPinTrue();
        return pinResponseDtos(extensions);
    }

    // 고정 확장자 check
    @PatchMapping("/extensions/pin/valid")
    public void checkPinExtension(@RequestParam String extensionName) {
        return;
    }

    // 고정 확장자 uncheck
    @PatchMapping("/extensions/pin/invalid")
    public void unCheckPinExtension(@RequestParam String extensionName) {
        return;
    }


    // 커스텀 확장자 조회하기
    @GetMapping("/extensions/custom")
    public List<ExtensionCustomResponseDto> getCustomExtensions() {
        List<Extension> extensions = extensionRepository.findByPinFalse();
        return customResponseDto(extensions);
    }

    // 필요하면 어셈블러로 분리하기
    private List<ExtensionPinResponseDto> pinResponseDtos(List<Extension> extensions) {
        List<ExtensionPinResponseDto> dtos = new ArrayList<>();
        extensions.forEach(extension ->
                dtos.add(new ExtensionPinResponseDto(extension))
        );
        return dtos;
    }

    // 필요하면 어셈블러로 분리하기
    private List<ExtensionCustomResponseDto> customResponseDto(List<Extension> extensions) {
        List<ExtensionCustomResponseDto> dtos = new ArrayList<>();
        extensions.forEach(extension ->
                dtos.add(new ExtensionCustomResponseDto(extension.getExtensionName()))
        );
        return dtos;
    }

    // 커스텀 확장자 생성하기 - pin 되면 안됨
    @PostMapping("/extensions/custom")
    public void createCustomExtension(
            @RequestParam(name = "extensionName")
            @NotBlank String extensionName
    ) {
        extensionService.createCustomExtension(extensionName);
    }

    // 커스텀 확장자 삭제하기 - pin 된 건 삭제하면 안됨
    @DeleteMapping("/extensions/custom")
    public void deleteCustomExtension(
            @RequestParam
            @NotBlank String extensionName
    ) {
        return;
    }
}
