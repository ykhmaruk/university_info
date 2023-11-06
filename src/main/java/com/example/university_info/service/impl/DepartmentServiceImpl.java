package com.example.university_info.service.impl;

import com.example.university_info.entity.Department;
import com.example.university_info.repository.DepartmentRepository;
import com.example.university_info.service.DepartmentService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department add(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department getById(Long id) {
        return departmentRepository.findById(id).orElseThrow(() ->
                new NoSuchElementException("Can't find department by id: " + id));
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        departmentRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Department update(Department department) {
        Department departmentFromDb =
                departmentRepository.findById(department.getId()).orElseThrow(() ->
                        new NoSuchElementException(
                                "Can't find department by id: " + department.getId()));
        /* чи краще замінити це на виклик
        Department departmentFromDb = getById(department.getId());
         */
        departmentFromDb.setId(department.getId());
        departmentFromDb.setName(department.getName());
        departmentFromDb.setLectors(department.getLectors());
        return departmentFromDb;
    }

    @Override
    public String findHeadOfDepartment(String departmentName) {
        Department department = departmentRepository.findByName(departmentName).orElseThrow(() ->
                new NoSuchElementException("Can't find department by name: " + departmentName));
        return department.ge
    }
}
