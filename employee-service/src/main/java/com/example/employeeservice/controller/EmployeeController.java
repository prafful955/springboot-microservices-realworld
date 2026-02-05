package com.example.employeeservice.controller;

import com.example.employeeservice.client.DepartmentClient;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;
    private final DepartmentClient departmentClient;

    // ✅ SINGLE CONSTRUCTOR
    public EmployeeController(EmployeeService service,
                              DepartmentClient departmentClient) {
        this.service = service;
        this.departmentClient = departmentClient;
    }

    // ✅ FEIGN + EMPLOYEE COMBINED RESPONSE
    @GetMapping("/{id}")
    public Map<String, Object> getEmployeeWithDepartment(@PathVariable Long id) {

        Employee employee = service.getById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("employee", employee);

        Map<String, Object> department =
                departmentClient.getDepartmentById(1L);

        response.put("department", department);
        return response;
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return service.saveEmployee(employee);
    }

    @GetMapping
    public List<Employee> getAll() {
        return service.getAll();
    }

    @GetMapping("/test")
    public String test() {
        return "Controller is working";
    }
}
