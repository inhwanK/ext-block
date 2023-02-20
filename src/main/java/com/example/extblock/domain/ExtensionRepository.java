package com.example.extblock.domain;

import com.example.extblock.domain.vo.ExtensionName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {

    List<Extension> findByPinFalse();

    List<Extension> findByPinTrue();

    Optional<Extension> findByExtensionName(ExtensionName extensionName);

    void deleteByExtensionName(ExtensionName extensionName);
}
