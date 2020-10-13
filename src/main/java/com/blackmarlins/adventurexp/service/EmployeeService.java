package com.blackmarlins.adventurexp.service;

import com.blackmarlins.adventurexp.model.Activity;
import com.blackmarlins.adventurexp.model.Employee;
import com.blackmarlins.adventurexp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> findAllEmployees() {
        return employeeRepository.findAll();
    }
}
