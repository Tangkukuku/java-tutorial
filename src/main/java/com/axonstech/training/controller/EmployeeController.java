package com.axonstech.training.controller;

import com.axonstech.training.dto.EmployeeDto;
import com.axonstech.training.entity.Employee;
import com.axonstech.training.service.EmployeeService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public Page<EmployeeDto> getAllEmp(@RequestParam(name = "onlyActive", required = false) Boolean onlyActives,
                                    @RequestParam(required = false, defaultValue = "1") int page,
                                    @RequestParam(required = false, defaultValue = "10") int size) {
        return employeeService.getEmployees(onlyActives, page, size);
    }

    @GetMapping("/{id}")
    public EmployeeDto getEmp(@PathVariable Long id){
        return employeeService.getEmployee(id);
    }

    @PostMapping
    public Employee saveEmp(@RequestBody Employee employee) throws Exception {
        return employeeService.save(employee);
    }


    @PutMapping
    public Employee updateEmp(@RequestBody Employee employee){
        return employeeService.update(employee);
    }


    @DeleteMapping("/{id}")
    public void deleteEmp(@PathVariable Long id) {
        employeeService.delete(id);
    }

    @PostMapping("/{id}/company/{companyCode}")
    public Employee addToCompany(@PathVariable Long id ,@PathVariable String companyCode) throws Exception {
        return employeeService.addToCompany(id, companyCode);
    }
}