package net.ishanCodes.ems.mapper;

import net.ishanCodes.ems.dto.EmployeeDto;
import net.ishanCodes.ems.entity.Employee;

//mapper class for employee entity to employee dto
public class EmployeeMapper {
    public static EmployeeDto mapToEmployeeDto (Employee Employee){
        return new EmployeeDto(
                Employee.getId(),
                Employee.getFirstName(),
                Employee.getLastName(),
                Employee.getEmail()

        );
    }

    //mapper class for employee dto to employee entity
    public static Employee mapToEmployee(EmployeeDto employeeDto){
        return new Employee(
            employeeDto.getId(),
            employeeDto.getFirstName(),
            employeeDto.getLastName(),
            employeeDto.getEmail()
        );
    }
}
