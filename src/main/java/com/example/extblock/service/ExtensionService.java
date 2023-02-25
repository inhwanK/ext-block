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
    public Long createCustomExtension(String extensionName) {
        if (isPresentName(extensionName)) {
            throw new InvalidExtensionException("이미 존재하는 확장자입니다.");
        }

        Extension extension = Extension.builder()
                .extensionName(extensionName)
                .checked(true)
                .pin(false)
                .build();

        log.info("{}", extension);
        return extensionRepository.save(extension).getId();
    }

    private Boolean isPresentName(String extensionName) {
        return extensionRepository.findByExtensionName(new ExtensionName(refineExtensionName(extensionName)))
                .isPresent();
    }

    private String refineExtensionName(String extensionName) {
        extensionName = extensionName.replace(" ", "");
        extensionName = extensionName.toLowerCase();
        return extensionName;
    }

    @Transactional
    public void removeCustomExtension(String extensionName) {
        if (isPinExtension(extensionName)) {
            // 고정 확장자는 삭제할 수 없다는 예외
            throw new InvalidExtensionException("고정 확장자는 삭제할 수 없습니다.");
        }
        extensionRepository.deleteByExtensionName(new ExtensionName(extensionName));
    }

    private Boolean isPinExtension(String extensionName) {
        return findExtension(refineExtensionName(extensionName))
                .getPin();
    }

    @Transactional
    public void checkPinExtension(String extensionName) {
        Extension extension = findExtension(extensionName);
        extension.check();
    }

    @Transactional
    public void unCheckPinExtension(String extensionName) {
        Extension extension = findExtension(extensionName);
        extension.unCheck();
    }

    private Extension findExtension(String extensionName) {
        return extensionRepository.findByExtensionName(new ExtensionName(extensionName))
                .orElseThrow(() -> new InvalidExtensionException("존재하지 않는 확장자입니다."));
    }
}
