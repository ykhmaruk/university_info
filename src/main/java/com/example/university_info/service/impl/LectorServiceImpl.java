package com.example.university_info.service.impl;

import com.example.university_info.entity.Lector;
import com.example.university_info.repository.LectorRepository;
import com.example.university_info.service.LectorService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectorServiceImpl implements LectorService {
    private final LectorRepository lectorRepository;

    @Override
    public List<String> getNamesByTemplate(String template) {
        return lectorRepository.findAllByFullNameContainingIgnoreCase(template)
                .stream()
                .map(Lector::getFullName)
                .collect(Collectors.toList());
    }
}
