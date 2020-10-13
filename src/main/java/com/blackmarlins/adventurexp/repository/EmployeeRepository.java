package com.blackmarlins.adventurexp.repository;

import com.blackmarlins.adventurexp.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
