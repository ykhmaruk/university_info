package com.example.university_info.service.impl;

import com.example.university_info.entity.Degree;
import com.example.university_info.entity.Department;
import com.example.university_info.entity.Lector;
import com.example.university_info.repository.DepartmentRepository;
import com.example.university_info.service.DepartmentService;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Override
    public Department findByName(String departmentName) {
        return departmentRepository.findByName(departmentName).orElseThrow(() ->
                new NoSuchElementException("Can't find department by name: " + departmentName));
    }

    @Override
    public String findHeadOfDepartment(String departmentName) {
        Department department = findByName(departmentName);
        Lector head = department.getHeadOfDepartment();
        if (head != null) {
            return head.getFullName();
        }
        throw new NoSuchElementException("This department does not have a head");
    }

    @Override
    @Transactional
    public String getStatisticOfDepartment(String departmentName) {
        Department department = findByName(departmentName);

        long assistantsСount = department.getLectors().stream()
                .filter(lector -> lector.getDegree() == Degree.ASSISTANT)
                .count();
        long associateProfessorsCount = department.getLectors().stream()
                .filter(lector -> lector.getDegree() == Degree.ASSOCIATE_PROFESSOR)
                .count();
        long professorsCount = department.getLectors().stream()
                .filter(lector -> lector.getDegree() == Degree.PROFESSOR)
                .count();

        return """
                    assistants - %d.
                    associate professors - %d.
                    professors - %d.
                """.formatted(assistantsСount, associateProfessorsCount, professorsCount);
    }

    @Override
    @Transactional
    public double getAveregeSalaryOfDepartment(String departmentName) {
        Department department = findByName(departmentName);
        if (department.getLectors() == null) {
            return 0;
        }
        double totalSalary = department.getLectors().stream()
                .map(Lector::getSalary)
                .mapToDouble(BigDecimal::doubleValue)
                .sum();
        int amountOfLectors = department.getLectors().size();
        return (double)Math.round((totalSalary / amountOfLectors) * 100) / 100;
    }

    @Override
    @Transactional
    public Long getNumberOfLectorsInDepartment(String departmentName) {
        Department department = findByName(departmentName);
        return (long) (department.getLectors() == null ? 0 : department.getLectors().size());
    }

    @Override
    public List<String> getNamesByTemplate(String template) {
        return departmentRepository.findAllByNameContainingIgnoreCase(template)
                .stream()
                .map(Department::getName)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Department setHeadOfDepartment(Department department, Lector lector) {
        Department departmentFromDb = findByName(department.getName());
        departmentFromDb.setHeadOfDepartment(lector);
        return departmentFromDb;
    }
}
