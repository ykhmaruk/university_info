package com.example.university_info.service.impl;

import com.example.university_info.entity.Lector;
import com.example.university_info.repository.LectorRepository;
import com.example.university_info.service.LectorService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LectorServiceImpl implements LectorService {
    private final LectorRepository lectorRepository;

    @Override
    public Lector add(Lector lector) {
        return lectorRepository.save(lector);
    }

    @Override
    public Lector getById(Long id) {
        return lectorRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't find lector by id: " + id));
    }

    @Override
    public List<Lector> getAll() {
        return lectorRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        lectorRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Lector update(Lector lector) {
        Lector lectorFromDb = lectorRepository.findById(lector.getId()).orElseThrow(() ->
                new NoSuchElementException("Can't find lector by id: " + lector.getId()));
        /* чи краще замінити це на виклик
        Lector lectorFromDb = getById(lector.getId());
         */
        lectorFromDb.setId(lector.getId());
        lectorFromDb.setName(lector.getName());
        lectorFromDb.setDegree(lector.getDegree());
        lectorFromDb.setDepartments(lector.getDepartments());
        return lectorFromDb;
    }
}
