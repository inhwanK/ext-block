package com.example.extblock.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExtensionRepository extends JpaRepository<Extension, Long> {

    List<Extension> findByPinFalse();
    List<Extension> findByPinTrue();
}
