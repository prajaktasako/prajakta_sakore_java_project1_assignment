package com.ems.Controller;


import com.ems.Entity.Employee;
import com.ems.Payload.Employeedto;
import com.ems.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/V1/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

//    http://localhost:8080/api/V1/employee/create
    @PostMapping("/create")
    public ResponseEntity<?> saveemployee(@RequestBody Employeedto employeedto){
        Employeedto save = employeeService.saveEmployee(employeedto);
        return new ResponseEntity<>("your Employee data is saved ", HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?>deleteemployeefromid(@PathVariable long id){
        employeeService.deleteEmployee(id);
        return new ResponseEntity<>("employee id is deleted !!", HttpStatus.OK);
    }

//    http://localhost:8080/api/V1/employee/fetch
    @GetMapping("/fetch")
    public ResponseEntity<List<?>>fetchall(){
        List<Employeedto> fetchall = employeeService.fetchall();
        return new ResponseEntity<>(fetchall,HttpStatus.OK);
    }
    @PutMapping("/update")
    public ResponseEntity<Employee>updateEmployee(@RequestBody Employee employee, long id){
        Employee employee1 = employeeService.updateEmployee(employee, id);
        return new ResponseEntity<>(employee1,HttpStatus.OK);

    }
}
