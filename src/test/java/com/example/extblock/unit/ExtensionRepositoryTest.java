package com.example.extblock.unit;


import com.example.extblock.domain.Extension;
import com.example.extblock.domain.ExtensionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ExtensionRepositoryTest {

    @Autowired
    ExtensionRepository extensionRepository;

    Extension extension;

    @BeforeEach
    void setUp() {
        extension = Extension.builder()
                .name("sh")
                .pin(true)
                .build();

        extensionRepository.save(extension);
    }

    @DisplayName("확장자 조회 성공한다.")
    @Test
    public void find_Extension_Success() {

        Extension actualExtension = extensionRepository.findById(1L).get();

        assertThat(actualExtension)
                .isEqualTo(extension);
    }
}
