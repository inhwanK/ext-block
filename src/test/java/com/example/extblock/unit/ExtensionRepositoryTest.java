package com.example.extblock.unit;


import com.example.extblock.domain.Extension;
import com.example.extblock.domain.ExtensionRepository;
import com.example.extblock.domain.vo.ExtensionName;
import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DataJpaTest
public class ExtensionRepositoryTest {

    @Autowired
    ExtensionRepository extensionRepository;

    @Autowired
    TestEntityManager testEntityManager;

    List<Extension> extensions;

    @BeforeEach
    void setUp() {
        // given
        extensions = createExtensions();
        extensions.forEach(ext -> extensionRepository.save(ext));

        testEntityManager.flush();
        testEntityManager.clear();
    }

    private List<Extension> createExtensions() {
        Extension extension1 = new Extension("sh", true, true);
        Extension extension2 = new Extension("exe", false, true);
        Extension extension3 = new Extension("bat", true, false);
        Extension extension4 = new Extension("txt", true, false);
        Extension extension5 = new Extension("md", true, true);

        return List.of(
                extension1, extension2, extension3, extension4, extension5
        );
    }

    @DisplayName("커스텀 확장자만 조회 성공한다.")
    @Test
    public void find_NotPin_Extension_Success() {
        // when
        List<Extension> findResult = extensionRepository.findByPinFalse();

        // then
        assertThat(findResult).hasSize(2);
        assertThat(findResult)
                .extracting("extensionName")
                .containsExactly(
                        extensions.get(2).getExtensionName(),
                        extensions.get(3).getExtensionName()
                );
    }

    @DisplayName("고정 확장자만 조회 성공한다.")
    @Test
    public void find_Pin_Extension_Success() {
        // when
        List<Extension> findResult = extensionRepository.findByPinTrue();

        // then
        assertThat(findResult).hasSize(3);
        assertThat(findResult)
                .extracting("extensionName")
                .containsExactly(
                        extensions.get(0).getExtensionName(),
                        extensions.get(1).getExtensionName(),
                        extensions.get(4).getExtensionName()
                );
    }

    @DisplayName("확장자 이름으로 조회 성공한다.")
    @Test
    public void find_Extension_By_Name_Success() {
        // when
        Extension findResult = extensionRepository.findByExtensionName(new ExtensionName("txt"))
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 확장자입니다."));

        // then
        assertThat(findResult)
                .isEqualTo(extensions.get(3));
    }

    @DisplayName("확장자 이름으로 삭제 성공한다.")
    @Test
    public void delete_Extension_By_Name_Success() {
        // when
        extensionRepository.deleteByExtensionName(new ExtensionName("txt"));

        List<Extension> findResult = extensionRepository.findByPinFalse();

        assertThat(findResult).hasSize(1);
        // then
        assertThatThrownBy(
                () -> extensionRepository.findByExtensionName(new ExtensionName("txt"))
                        .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 확장자입니다."))
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("존재하지 않는 확장자입니다.");
    }
}
