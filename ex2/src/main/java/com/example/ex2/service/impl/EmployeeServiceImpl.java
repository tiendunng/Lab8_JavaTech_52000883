package com.example.ex2.service.impl;

import com.example.ex2.model.Employee;
import com.example.ex2.repository.EmployeeRepository;
import com.example.ex2.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee addNewEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        Employee e = employeeRepository.findById(employee.getId()).orElse(null);

        if (e == null) {
            System.out.println("Không tìm thấy employee tương ứng!");
            return null;
        }
        e.setName(employee.getName());
        e.setEmail(employee.getEmail());
        e.setAddress(employee.getAddress());
        e.setPhone(employee.getPhone());
        employeeRepository.save(e);
        return e;
    }

    @Override
    public Employee getByID(int id) {
        return employeeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean removeAll(List<Integer> id) {
        employeeRepository.deleteAllById(id);
        return true;
    }

    @Override
    public Page<Employee> findAll(Pageable page) {
        return employeeRepository.findAll(page);
    }


}
