package com.example.extblock.contoller;

import com.example.extblock.domain.Extension;
import com.example.extblock.domain.ExtensionRepository;
import com.example.extblock.dto.ExtensionCustomResponseDto;
import com.example.extblock.dto.ExtensionPinResponseDto;
import com.example.extblock.service.ExtensionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class ExtensionController {

    private final ExtensionService extensionService;
    private final ExtensionRepository extensionRepository;

    // 고정 확장자 조회하기 - pin 된 것만
    @GetMapping("/extension/pin")
    public List<ExtensionPinResponseDto> getPinExtensions() {
        List<Extension> extensions = extensionRepository.findByPinTrue();
        return pinResponseDtos(extensions);
    }

    // 고정 확장자 생성하기 - pin 체크


    // 고정 확장자 check

    // 고정 확장자 uncheck

    // 커스텀 확장자 조회하기
    @GetMapping("/extension/custom")
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
                dtos.add(new ExtensionCustomResponseDto(extension.getName()))
        );
        return dtos;
    }
    // 커스텀 확장자 생성하기 - pin 되면 안됨
    @PostMapping("/extension")
    public void createCustomExtension(@RequestBody String name) {
        return;
    }
    // 커스텀 확장자 삭제하기 - pin 된 건 삭제하면 안됨

}
