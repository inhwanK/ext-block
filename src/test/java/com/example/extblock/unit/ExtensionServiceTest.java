package com.example.extblock.unit;

import com.example.extblock.domain.Extension;
import com.example.extblock.domain.ExtensionRepository;
import com.example.extblock.exception.InvalidExtensionException;
import com.example.extblock.service.ExtensionService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.any;
import static org.mockito.BDDMockito.given;

@Slf4j
@ExtendWith(MockitoExtension.class)
public class ExtensionServiceTest {

    @InjectMocks
    ExtensionService extensionService;

    @Mock
    ExtensionRepository extensionRepository;

    @Test
    @DisplayName("커스텀 확장자를 추가한다.")
    public void create_Custom_Extension_Success() {
        // given
        Extension extension = Extension.builder()
                .id(1L)
                .extensionName("new")
                .checked(true)
                .pin(false)
                .build();

        given(extensionRepository.findByExtensionName(any()))
                .willReturn(Optional.ofNullable(null));
        given(extensionRepository.save(any(Extension.class)))
                .willReturn(extension);

        Long actualId = extensionService.createCustomExtension("new");
        log.info("id : {}", actualId);

        assertThat(actualId)
                .isEqualTo(extension.getId());
    }

    @Test
    @DisplayName("이미 존재하는 커스텀 확장자 추가를 시도하면 예외를 던진다.")
    public void create_Custom_Extension_Exception() {

        // given
        Extension extension = Extension.builder()
                .id(1L)
                .extensionName("new")
                .checked(true)
                .pin(false)
                .build();

        given(extensionRepository.findByExtensionName(any()))
                .willReturn(Optional.of(extension));

        // then
        assertThatThrownBy(() -> extensionService.createCustomExtension("new"))
                .isInstanceOf(InvalidExtensionException.class)
                .hasMessageContaining("이미 존재하는 확장자입니다.");
    }
}
