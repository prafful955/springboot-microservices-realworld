package com.example.employeeservice;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import com.example.employeeservice.service.EmployeeService;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableAsync
public class EmployeeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeeServiceApplication.class, args);
    }

   /* @Bean
    CommandLineRunner run(EmployeeService service) {
        return args -> {
            Employee emp = new Employee(null, "Debug User", "debug@gmail.com");
            service.saveEmployee(emp);
        };
    }
    @Bean
    CommandLineRunner run(EmployeeRepository repository) {
        return args -> {
            Employee emp = new Employee(null, "Praful Jain", "praful@gmail.com");
            repository.save(emp);
        }*/
}
