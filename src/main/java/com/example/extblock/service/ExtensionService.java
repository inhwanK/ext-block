package com.example.extblock.service;

import com.example.extblock.domain.ExtensionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ExtensionService {

    private final ExtensionRepository extensionRepository;

}
