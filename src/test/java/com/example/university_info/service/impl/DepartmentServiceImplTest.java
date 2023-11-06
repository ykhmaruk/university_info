package com.example.university_info.service.impl;

import com.example.university_info.entity.Degree;
import com.example.university_info.entity.Department;
import com.example.university_info.entity.Lector;
import com.example.university_info.repository.DepartmentRepository;
import java.math.BigDecimal;
import java.util.NoSuchElementException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class DepartmentServiceImplTest {
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    @Mock
    private DepartmentRepository departmentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByName_Ok() {
        String departmentName = "Sample Department";
        Department sampleDepartment = new Department();
        Mockito.when(departmentRepository.findByName(departmentName))
                .thenReturn(Optional.of(sampleDepartment));

        Department result = departmentService.findByName(departmentName);

        assertEquals(sampleDepartment, result);
    }

    @Test
    void testFindHeadOfDepartment_Ok() {
        String departmentName = "Sample Department";
        Department sampleDepartment = new Department();
        Lector headOfDepartment = new Lector();
        headOfDepartment.setFullName("Head Lector");
        sampleDepartment.setHeadOfDepartment(headOfDepartment);
        Mockito.when(departmentRepository.findByName(departmentName))
                .thenReturn(Optional.of(sampleDepartment));

        String expected = "Head Lector";
        String actual = departmentService.findHeadOfDepartment(departmentName);

        assertEquals(expected, actual);
    }

    @Test
    void testFindHeadOfDepartmentNoHead_NotOk() {
        String departmentName = "Sample Department";
        Department sampleDepartment = new Department();
        Mockito.when(departmentRepository.findByName(departmentName))
                .thenReturn(Optional.of(sampleDepartment));

        assertThrows(NoSuchElementException.class,
                () -> departmentService.findHeadOfDepartment(departmentName));
    }

    @Test
    void testGetStatisticOfDepartment_Ok() {
        String departmentName = "Sample Department";
        Department sampleDepartment = new Department();
        List<Lector> lectors = new ArrayList<>();
        Lector bob = new Lector();
        bob.setDegree(Degree.ASSISTANT);;
        lectors.add(bob);
        Lector alice = new Lector();
        alice.setDegree(Degree.ASSISTANT);
        lectors.add(alice);
        sampleDepartment.setLectors(lectors);
        Mockito.when(departmentRepository.findByName(departmentName))
                .thenReturn(Optional.of(sampleDepartment));

        String expected = """
                    assistants - 2.
                    associate professors - 0.
                    professors - 0.
                """;
        String actual = departmentService.getStatisticOfDepartment(departmentName);

        assertEquals(expected, actual);
    }

    @Test
    void testGetAveregeSalaryOfDepartment_Ok() {
        String departmentName = "Sample Department";
        Department sampleDepartment = new Department();
        List<Lector> lectors = new ArrayList<>();
        Lector bob = new Lector();
        bob.setSalary(BigDecimal.valueOf(1000));
        lectors.add(bob);
        Lector alice = new Lector();
        alice.setSalary(BigDecimal.valueOf(2000));
        lectors.add(alice);
        sampleDepartment.setLectors(lectors);
        Mockito.when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(sampleDepartment));

        double expected = 1500;
        double actual = departmentService.getAveregeSalaryOfDepartment(departmentName);

        assertEquals(expected, actual);
    }

    @Test
    void testGetAveregeSalaryOfDepartmentNoLectors_Ok() {
        String departmentName = "Sample Department";
        Department sampleDepartment = new Department();
        Mockito.when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(sampleDepartment));

        double expected = 0.0;
        double actual = departmentService.getAveregeSalaryOfDepartment(departmentName);

        assertEquals(expected, actual);
    }

    @Test
    void testGetNumberOfLectorsInDepartment_Ok() {
        String departmentName = "Sample Department";
        Department sampleDepartment = new Department();
        List<Lector> lectors = new ArrayList<>();
        lectors.add(new Lector());
        lectors.add(new Lector());
        sampleDepartment.setLectors(lectors);
        Mockito.when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(sampleDepartment));

        Long expected = 2L;
        Long actual = departmentService.getNumberOfLectorsInDepartment(departmentName);

        assertEquals(expected, actual);
    }

    @Test
    void testGetNumberOfLectorsInDepartmentNoLectors_Ok() {
        String departmentName = "Sample Department";
        Department sampleDepartment = new Department();
        Mockito.when(departmentRepository.findByName(departmentName)).thenReturn(Optional.of(sampleDepartment));

        Long expected = 0L;
        Long actual = departmentService.getNumberOfLectorsInDepartment(departmentName);

        assertEquals(expected, actual);
    }

    @Test
    void testGetNamesByTemplate_Ok() {
        String template = "Sample";
        List<Department> departments = new ArrayList<>();
        Department firstSample = new Department();
        firstSample.setName("Sample Department 1");
        departments.add(firstSample);
        Department secondSample = new Department();
        secondSample.setName("Sample Department 2");
        departments.add(secondSample);
        Mockito.when(departmentRepository.findAllByNameContainingIgnoreCase(template)).thenReturn(departments);

        List<String> result = departmentService.getNamesByTemplate(template);

        assertEquals(2, result.size());
        assertEquals(firstSample.getName(), result.get(0));
        assertEquals(secondSample.getName(), result.get(1));
    }
}