package com.example.university_info.console;

import com.example.university_info.entity.Degree;
import com.example.university_info.entity.Department;
import com.example.university_info.entity.Lector;
import com.example.university_info.repository.DepartmentRepository;
import com.example.university_info.repository.LectorRepository;
import jakarta.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class InsertData {
    private final DepartmentRepository departmentRepository;
    private final LectorRepository lectorRepository;

    @PostConstruct
    public void run() {
        Department Economic = new Department();
        Economic.setName("Economic");
        departmentRepository.save(Economic);

        Department Chemistry = new Department();
        Chemistry.setName("Chemistry");
        departmentRepository.save(Chemistry);

        Lector Albert = new Lector();
        Albert.setFullName("Albert Art");
        Albert.setDegree(Degree.ASSISTANT);
        Albert.setSalary(BigDecimal.valueOf(15000));
        Albert.setDepartments(List.of(Economic, Chemistry));
        lectorRepository.save(Albert);

        Lector John = new Lector();
        John.setFullName("John Galt");
        John.setSalary(BigDecimal.valueOf(20000));
        John.setDegree(Degree.ASSOCIATE_PROFESSOR);
        John.setDepartments(List.of(Economic, Chemistry));
        lectorRepository.save(John);

        Economic.setHeadOfDepartment(John);
        Economic.setLectors(List.of(John));
        departmentRepository.save(Economic);

        Chemistry.setHeadOfDepartment(Albert);
        Chemistry.setLectors(List.of(John, Albert));
        departmentRepository.save(Chemistry);
    }
}
