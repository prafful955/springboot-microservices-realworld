package com.example.employeeservice.service;

import com.example.employeeservice.client.DepartmentClient;
import com.example.employeeservice.dto.DepartmentDto;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.event.EmployeeCreatedEvent;
import com.example.employeeservice.kafka.EmployeeEventProducer;
import com.example.employeeservice.repository.EmployeeRepository;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class EmployeeService {

    private final EmployeeRepository repository;
    private final DepartmentClient departmentClient;
    private final EmployeeEventProducer producer;

    public EmployeeService(EmployeeRepository repository,DepartmentClient departmentClient, EmployeeEventProducer producer)
    {
        this.repository = repository;
        this.departmentClient = departmentClient;
        this.producer = producer;
    }


   /*
    public Employee getById(Long id)
    {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
    }*/
   public Employee saveEmployee(Employee employee) {
       Employee saved = repository.save(employee);

       EmployeeCreatedEvent event =
               new EmployeeCreatedEvent(saved.getId(), saved.getName(),saved.getEmail());

       producer.sendEmployeeCreatedEvent(event);

       return saved;
   }

    public List<Employee> getAll()
    {
        return repository.findAll();
    }

    @CircuitBreaker(name = "departmentService", fallbackMethod = "departmentFallback")
    @Retry(name = "departmentService")
    @TimeLimiter(name = "departmentService")
    @Bulkhead(name = "departmentService", type = Bulkhead.Type.THREADPOOL)
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
    public CompletableFuture<Map<String, Object>> fallback(Long id, Throwable ex)
    {
        Employee employee = repository.findById(id).orElseThrow(() -> new RuntimeException("Employee not found"));
        Map<String, Object> response = new HashMap<>();
        response.put("employee", employee);
        response.put("department", "Department Service Unavailable (Fallback)");
        return CompletableFuture.completedFuture(response);
    }

    public CompletableFuture<Map<String, Object>> departmentFallback(Long id, Throwable ex)
    {

        log.warn("Fallback triggered for employeeId={}, reason={}",  id, ex.getClass().getSimpleName());

        Employee employee = repository.findById(id) .orElseThrow(() -> new RuntimeException("Employee not found"));

        DepartmentDto fallbackDepartment = new DepartmentDto(1L, "Department Service Unavailable");

        Map<String, Object> fallbackResponse = new HashMap<>();
        fallbackResponse.put("employee", employee);
        fallbackResponse.put("department", fallbackDepartment);
        fallbackResponse.put("message", "Fallback applied");
        return CompletableFuture.completedFuture(fallbackResponse);
    }



}
