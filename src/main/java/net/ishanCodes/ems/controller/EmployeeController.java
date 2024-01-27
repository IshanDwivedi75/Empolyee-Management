package net.ishanCodes.ems.controller;


import lombok.AllArgsConstructor;
import net.ishanCodes.ems.dto.EmployeeDto;
import net.ishanCodes.ems.entity.Employee;
import net.ishanCodes.ems.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    //Building Add employee REST Api
    @PostMapping
    public ResponseEntity<EmployeeDto> createEmployee(@RequestBody EmployeeDto employeeDto){
        EmployeeDto savedEmployee = employeeService.createEmployee(employeeDto);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    //Build get employee rest api
    @GetMapping("{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long employeeId){
        EmployeeDto employeeDto = employeeService.getEmployeeById(employeeId);
        return ResponseEntity.ok(employeeDto);
    }
    @GetMapping
    //Build get all employee rest api
    public ResponseEntity<List<EmployeeDto>> getAllEmployees(){
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        return ResponseEntity.ok(employees);
    }

    //build update employee rest api
    @PutMapping("{id}")
    public ResponseEntity<EmployeeDto> updateEmployee(@PathVariable("id") Long employeeId,
                                                     @RequestBody EmployeeDto updateEmployee){
        EmployeeDto employeeDto = employeeService.updateEmployee(employeeId, updateEmployee);
        return ResponseEntity.ok(employeeDto);
    }

    //build delete employee rest api
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok("Employee Deleted Successfully!");
    }
}
