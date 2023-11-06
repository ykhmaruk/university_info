package com.example.university_info.service.impl;

import com.example.university_info.entity.Degree;
import com.example.university_info.repository.DegreeRepository;
import com.example.university_info.service.DegreeService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DegreeServiceImpl implements DegreeService {
    private final DegreeRepository degreeRepository;

    @Override
    public Degree add(Degree degree) {
        return degreeRepository.save(degree);
    }

    @Override
    public Degree getById(Long id) {
        return degreeRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't find degree by id: " + id));
    }

    @Override
    public List<Degree> getAll() {
        return degreeRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        degreeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Degree update(Degree degree) {
        Degree degreeFromDb = degreeRepository.findById(degree.getId()).orElseThrow(() ->
                new NoSuchElementException("Can't find degree by id: " + degree.getId()));
        /* чи краще замінити це на виклик
        Degree degreeFromDb = getById(degree.getId());
         */
        degreeFromDb.setId(degree.getId());
        degreeFromDb.setName(degree.getName());
        return degreeFromDb;
    }
}
