package com.example.university_info.service;

import com.example.university_info.entity.Department;

public interface DepartmentService extends AbstractService<Department>{
    String findHeadOfDepartment(String departmentName);
}
