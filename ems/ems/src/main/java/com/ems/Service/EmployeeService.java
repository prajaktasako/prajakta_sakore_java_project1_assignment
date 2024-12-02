package com.ems.Service;

import com.ems.Entity.Employee;
import com.ems.Payload.Employeedto;

import java.util.List;

public interface EmployeeService {

    public Employeedto saveEmployee(Employeedto employeedto);
    public void deleteEmployee(long id);
    public List<Employeedto> fetchall();
    public Employee updateEmployee(Employee employee, long id);
}
