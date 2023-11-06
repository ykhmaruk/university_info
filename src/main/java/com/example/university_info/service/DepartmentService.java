package com.example.university_info.service;

import com.example.university_info.entity.Department;
import com.example.university_info.entity.Lector;
import java.util.List;

public interface DepartmentService {
    Department findByName(String departmentName);
    String findHeadOfDepartment(String departmentName);
    String getStatisticOfDepartment(String departmentName);
    double getAveregeSalaryOfDepartment(String departmentName);
    Long getNumberOfLectorsInDepartment(String departmentName);
    List<String> getNamesByTemplate(String template);
    Department setHeadOfDepartment(Department department, Lector lector);
}
