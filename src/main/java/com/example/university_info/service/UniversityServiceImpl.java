package com.example.university_info.service;

import com.example.university_info.repository.DepartmentRepository;
import com.example.university_info.repository.LectorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UniversityServiceImpl {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;


    public String headOfDepartment(String departmentName) {
        return "return";
    }

    public String departmentStatistics(String departmentName) {
        return "return";
    }

    public String averageSalary(String departmentName) {
        return "return";
    }

    public String employeeCount(String departmentName) {
        return "return";
    }

    public String globalSearch(String template) {
        return "return";
    }
}
