package com.example.university_info.service.impl;

import com.example.university_info.entity.Lector;
import com.example.university_info.repository.LectorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class LectorServiceImplTest {
    @InjectMocks
    private LectorServiceImpl lectorService;
    @Mock
    private LectorRepository lectorRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetNamesByTemplate_Ok() {
        String template = "Black";
        List<Lector> lectors = new ArrayList<>();
        Lector black = new Lector();
        black.setFullName("Black Dilan");
        lectors.add(black);
        Lector alice = new Lector();
        alice.setFullName("Alice Black");
        lectors.add(alice);
        Mockito.when(lectorRepository.findAllByFullNameContainingIgnoreCase(template)).thenReturn(lectors);

        List<String> result = lectorService.getNamesByTemplate(template);

        assertEquals(2, result.size());
        assertEquals(black.getFullName(), result.get(0));
        assertEquals(alice.getFullName(), result.get(1));
    }

    @Test
    void testGetNamesByTemplateNoMatches() {
        String template = "Smith";
        List<Lector> lectors = new ArrayList<>();
        Mockito.when(lectorRepository.findAllByFullNameContainingIgnoreCase(template)).thenReturn(lectors);

        List<String> result = lectorService.getNamesByTemplate(template);

        assertEquals(0, result.size());
    }
}