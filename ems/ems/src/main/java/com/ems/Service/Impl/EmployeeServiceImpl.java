package com.ems.Service.Impl;

import com.ems.Entity.Employee;
import com.ems.Exception.ResourceNotFoundException;
import com.ems.Payload.Employeedto;
import com.ems.Repository.EmployeeRepository;
import com.ems.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public Employeedto saveEmployee(Employeedto employeedto) {
        Employee employee = maptoEntity(employeedto);
        Employee save = employeeRepository.save(employee);
        Employeedto dto = maptodto(save);
        return dto;
    }

    @Override
    public void deleteEmployee(long id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employeedto> fetchall() {
        List<Employee> all = employeeRepository.findAll();
        List<Employeedto> collect = all.stream().map(p -> maptodto(p)).collect(Collectors.toList());
        return collect;

    }

    @Override
    public Employee updateEmployee(Employee employee, long id) {
        Optional<Employee> byId = employeeRepository.findById(id);
        if(byId.isPresent()){
            Employee emp = byId.get();
            emp.setId(employee.getId());
            emp.setName(employee.getName());
            emp.setDOB(employee.getDOB());
            emp.setDepartment(employee.getDepartment());
            Employee save = employeeRepository.save(emp);
            return save;

        }else{
            throw new ResourceNotFoundException("this id is not found in the database"+ id);
        }

    }
    Employee maptoEntity (Employeedto employeedto){
        Employee employee = new Employee();
        employee.setId(employeedto.getId());
        employee.setName(employeedto.getName());
        employee.setDOB(employeedto.getDOB());
        employee.setDepartment(employeedto.getDepartment());
        return employee;
    }
    Employeedto maptodto (Employee employee){
        Employeedto dto =  new Employeedto();
        dto.setName(employee.getName());
        dto.setDOB(employee.getDOB());
        dto.setDepartment(employee.getDepartment());
        return dto;

    }


}
