package com.praful.departmentservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @GetMapping("/{id}")
    public Map<String, Object> getDepartment(@PathVariable Long id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("name", "IT");
        return map;
    }
}

