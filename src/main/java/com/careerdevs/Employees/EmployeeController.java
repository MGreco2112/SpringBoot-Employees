package com.careerdevs.Employees;

import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
//    private final EmployeeRepository repository;
    private AtomicLong idCounter = new AtomicLong();
//
//    public EmployeeController(EmployeeRepository repository) {
//        this.repository = repository;
//    }
    Map<Long, Employee> employees = new HashMap<>();

    public EmployeeController() {
                employees.put(idCounter.incrementAndGet(), new Employee(idCounter.get(),"Matt", 26, "Developer"));
    }


    @GetMapping
    public List<Employee> all() {
        return new ArrayList<Employee>(employees.values());
    }

    @PostMapping
    public Employee newEmployee(@RequestBody Employee newEmployee) {
        Long id = idCounter.incrementAndGet();
        newEmployee.setId(id);

        employees.put(id, newEmployee);

        return newEmployee;

    }

    @GetMapping("/{id}")
    public Employee employee(@PathVariable Long id)  {
        return employees.get(id);
    }

    //Update Post: sometimes use PUT, modern systems use POST

    @PutMapping("/{id}")
    public Employee updatedEmployee(@PathVariable Long id, @RequestBody Employee updateData) {
        Employee emp = employees.get(id);

        if (emp == null) {
            return emp;
        }

        if (updateData.getName() != null) {
            emp.setName(updateData.getName());
        }
        if (updateData.getRole() != null) {
            emp.setRole(updateData.getRole());
        }
        if (updateData.getAge() != null) {
            emp.setAge(updateData.getAge());
        }

        return emp;

    }

    @DeleteMapping("/{id}")
    public void deleteEmployee(@PathVariable Long id) {
        employees.remove(id);
    }

}
