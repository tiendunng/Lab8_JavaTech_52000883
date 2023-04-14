package com.example.ex2.service;

import com.example.ex2.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();
    Employee addNewEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    Employee getByID(int id);
    boolean removeAll(List<Integer> employeeList);
    Page<Employee> findAll(Pageable page);
}
