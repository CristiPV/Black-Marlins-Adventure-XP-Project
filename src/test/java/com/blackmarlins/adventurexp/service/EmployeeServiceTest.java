package com.blackmarlins.adventurexp.service;

import com.blackmarlins.adventurexp.model.reservation.Reservation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmployeeServiceTest {

    @Autowired
    EmployeeService employeeService;

    @Test
    @DisplayName("Can retrieve all employees from the database")
    void findAllEmployeesTest()
    {
        assertThat(employeeService.findAllEmployees().size() > 0).isTrue();
    }
}
