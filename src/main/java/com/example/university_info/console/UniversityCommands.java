package com.example.university_info.console;

import com.example.university_info.service.DepartmentService;
import com.example.university_info.service.LectorService;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@RequiredArgsConstructor
public class UniversityCommands {
    private final DepartmentService departmentService;
    private final LectorService lectorService;

    @ShellMethod(key = "start")
    public String start() {
         return """
                 Hi! This is university-info app!
                 You can get answer on such commands:
                 1. Who is head of department {department_name};
                 2. Show statistics for {department_name} ;
                 3. Show the average salary for the department {department_name};
                 4. Show count of employee for {department_name};
                 5. Global search by {template};
                 Let`s try it.
                 """;
    }

    @ShellMethod(key = "Who is head of department")
    public String getHeadOfDepartment(String departmentName) {
        String headOfDepartment = departmentService.findHeadOfDepartment(departmentName);
        return "Head of %s department is %s".formatted(departmentName, headOfDepartment);
    }

    @ShellMethod(key = "Show statistics for")
    public String getDepartmentStatistics(String departmentName) {
        return departmentService.getStatisticOfDepartment(departmentName);
    }

    @ShellMethod(key = "Show the average salary for the department")
    public String getAverageSalary(String departmentName) {
        double averageSalary = departmentService.getAveregeSalaryOfDepartment(departmentName);
        return "The average salary of %s is %s".formatted(departmentName, averageSalary);
    }

    @ShellMethod(key = "Show count of employee for")
    public String getNumberOfLectors(String departmentName) {
        Long numberOfLectors = departmentService.getNumberOfLectorsInDepartment(departmentName);
        return "The number of lectors in the %s department is %s".formatted(departmentName, numberOfLectors);
    }

    @ShellMethod(key = "Global search by")
    public String getByTemplate(String template) {
        List<String> lectorNames = lectorService.getNamesByTemplate(template);
        List<String> departmentNames = departmentService.getNamesByTemplate(template);
        return Stream.of(lectorNames, departmentNames)
                .flatMap(List::stream)
                .collect(Collectors.joining(", "));
    }
}
