package com.example.employeeservice.service;

import com.example.employeeservice.client.DepartmentClient;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;
    private final DepartmentClient departmentClient;
    public EmployeeService(EmployeeRepository repository,DepartmentClient departmentClient)
    {
        this.repository = repository;
        this.departmentClient = departmentClient;
    }

    public Employee saveEmployee(Employee employee)
    {
        return repository.save(employee);
    }
    public Employee getById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }

    public List<Employee> getAll()
    {
        return repository.findAll();
    }

    @CircuitBreaker(name = "departmentCircuit", fallbackMethod = "departmentFallback")
    @Retry(name = "departmentRetry")
    @TimeLimiter(name = "departmentTimeout")
    public CompletableFuture<Map<String, Object>> getEmployeeWithDepartment(Long id)
    {

        return CompletableFuture.supplyAsync(() -> {

            Employee employee = repository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Employee not found"));

            Map<String, Object> response = new HashMap<>();
            response.put("employee", employee);

            Map<String, Object> department =
                    departmentClient.getDepartmentById(1L);

            response.put("department", department);

            return response;
        });
    }

    // ✅ STEP 3 – Fallback method yahi likhna hai
    public CompletableFuture<Map<String, Object>> fallback(Long id, Throwable ex) {

        Employee employee = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        Map<String, Object> response = new HashMap<>();
        response.put("employee", employee);
        response.put("department", "Department Service Unavailable (Fallback)");

        return CompletableFuture.completedFuture(response);
    }

    public CompletableFuture<Map<String, Object>> departmentFallback( Long id, Exception ex)
    {
        Employee employee = getById(id);
        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("employee", employee);
        Map<String, Object> departmentFallback = new HashMap<>();
        departmentFallback.put("id", 1L);
        departmentFallback.put("name", "Department Service Unavailable");
        departmentFallback.put("status", "TEMPORARILY_DOWN");

        fallbackResponse.put("department", departmentFallback);
        fallbackResponse.put("message", "Fallback response applied");

        return CompletableFuture.completedFuture(fallbackResponse);
    }

}
