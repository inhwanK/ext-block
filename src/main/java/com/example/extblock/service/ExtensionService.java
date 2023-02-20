package com.example.extblock.service;

import com.example.extblock.domain.Extension;
import com.example.extblock.domain.ExtensionRepository;
import com.example.extblock.domain.vo.ExtensionName;
import com.example.extblock.exception.InvalidExtensionException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class ExtensionService {

    private final ExtensionRepository extensionRepository;

    @Transactional
    public void createCustomExtension(String extensionName) {
        if (isPresentName(extensionName)) {
            // 이미 존재하는 확장자 예외, Invalid 말고 다른걸로.
            throw new InvalidExtensionException("이미 존재하는 확장자입니다.");
        }

        Extension extension = Extension.builder()
                .extensionName(extensionName)
                .checked(true)
                .pin(false)
                .build();

        log.info("{}", extension);
        extensionRepository.save(extension);
    }

    @Transactional
    public void removeCustomExtension(String extensionName) {
        if (!isCustomExtension(extensionName)) {
            // 고정 확장자는 삭제할 수 없다는 예외
            throw new InvalidExtensionException("고정 확장자는 삭제할 수 없습니다.");
        }
        extensionRepository.deleteByExtensionName(new ExtensionName(extensionName));
    }

    private Boolean isCustomExtension(String extensionName) {
        if (!isPresentName(extensionName)) {
            // 존재하지 않는 확장자 예외
            throw new InvalidExtensionException("존재하지 않는 확장자입니다.");
        }
        return extensionRepository.findByExtensionName(new ExtensionName(extensionName)).get().getPin();
    }

    private Boolean isPresentName(String extensionName) {
        return extensionRepository.findByExtensionName(new ExtensionName(extensionName))
                .isPresent();
    }
}
