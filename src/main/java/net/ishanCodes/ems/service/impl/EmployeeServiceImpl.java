package net.ishanCodes.ems.service.impl;

import lombok.AllArgsConstructor;
import net.ishanCodes.ems.dto.EmployeeDto;
import net.ishanCodes.ems.entity.Employee;
import net.ishanCodes.ems.exception.ResourceNotFoundException;
import net.ishanCodes.ems.mapper.EmployeeMapper;
import net.ishanCodes.ems.respository.EmployeeRepository;
import net.ishanCodes.ems.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;
  @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto){

      Employee employee = EmployeeMapper.mapToEmployee(employeeDto);
        Employee savedEmployee = employeeRepository.save(employee);
        return EmployeeMapper.mapToEmployeeDto(savedEmployee);
  }

  @Override
  public EmployeeDto getEmployeeById(Long employeeId) {

     Employee employee = employeeRepository.findById(employeeId)
            .orElseThrow(() ->
                    new ResourceNotFoundException("employee does not exist with the given id: " + employeeId));
    return EmployeeMapper.mapToEmployeeDto(employee);
  }

  @Override
  public List<EmployeeDto> getAllEmployees() {
    List<Employee> employees = employeeRepository.findAll();
    return employees.stream().map((employee) -> EmployeeMapper.mapToEmployeeDto(employee)).
            collect(Collectors.toList());
  }

  @Override
  public EmployeeDto updateEmployee(Long employeeId, EmployeeDto updateEmployee) {

   Employee employee = employeeRepository.findById(employeeId).orElseThrow(
            ()-> new ResourceNotFoundException("Employee Does not Exist with the given idL " + employeeId)
    );

   employee.setFirstName(updateEmployee.getFirstName());
   employee.setLastName(updateEmployee.getLastName());
   employee.setEmail(updateEmployee.getEmail());

   Employee updatedEmployeeObj = employeeRepository.save(employee);

    return EmployeeMapper.mapToEmployeeDto(updatedEmployeeObj);
  }

    @Override
    public void deleteEmployee(Long employeeId) {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(
                ()-> new ResourceNotFoundException("Employee Does not Exist with the given idL " + employeeId)
        );

        employeeRepository.deleteById(employeeId);
    }
}
