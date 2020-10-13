package com.blackmarlins.adventurexp.controller;

import com.blackmarlins.adventurexp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    // Get All Employees
    @GetMapping("/list")
    public String findAll(Model model){
        model.addAttribute("activities", employeeService.findAllEmployees());
        model.addAttribute("isAdmin", LoginController.isAdmin());
        return "employee/employee";
    }
}
