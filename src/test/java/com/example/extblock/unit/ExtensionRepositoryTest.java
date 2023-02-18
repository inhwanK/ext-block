package com.example.extblock.unit;


import com.example.extblock.domain.Extension;
import com.example.extblock.domain.ExtensionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class ExtensionRepositoryTest {

    @Autowired
    private ExtensionRepository extensionRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    List<Extension> extensions;

    @BeforeEach
    void setUp() {
        // given
        extensions = createExtensions();
        extensions.forEach(ext -> extensionRepository.save(ext));
        System.out.println(extensions.toString());

        testEntityManager.flush();
        testEntityManager.clear();
    }

    private List<Extension> createExtensions() {
        Extension extension1 = new Extension("sh", true, true);
        Extension extension2 = new Extension( "exe", false, true);
        Extension extension3 = new Extension( "bat", true, false);
        Extension extension4 = new Extension( "txt", true, false);
        Extension extension5 = new Extension( "md", true, true);

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
                .extracting("name")
                .containsExactly(
                        extensions.get(2).getName(),
                        extensions.get(3).getName()
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
                .extracting("name")
                .containsExactly(
                        extensions.get(0).getName(),
                        extensions.get(1).getName(),
                        extensions.get(4).getName()
                );
    }
}
