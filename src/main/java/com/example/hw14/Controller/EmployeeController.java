package com.example.hw14.Controller;

import com.example.hw14.ApiRresponse.ApiResponse;
import com.example.hw14.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/employee")
public class EmployeeController {

    ArrayList<Employee> employees = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Employee> getEmployees() {
        return employees;

    }

    @PostMapping("/add")
    public ResponseEntity addEmployee(@RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (employee.getPosition().equalsIgnoreCase("supervisor") || employee.getPosition().equalsIgnoreCase("coordinator") ) {
            employees.add(employee);
            return ResponseEntity.status(200).body(new ApiResponse("added successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("position must be supervisor or coordinator only "));
    }

    @PutMapping("/update/{index}")
    public ResponseEntity updateEmployee(@PathVariable int index, @RequestBody @Valid Employee employee, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (employee.getPosition().equalsIgnoreCase("supervisor") || employee.getPosition().equalsIgnoreCase("coordinator") ) {
            employees.set(index, employee);
            return ResponseEntity.status(200).body(new ApiResponse("updated successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("position must be supervisor or coordinator only "));


    }

    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteEmployee(@PathVariable int index) {
        if (employees.size() <= index) {
            return ResponseEntity.status(400).body(new ApiResponse("not found"));
        }
        employees.remove(index);
        return ResponseEntity.status(200).body(new ApiResponse("deleted successfully"));
    }

    @PutMapping("/annualleave/{name}")
    public ResponseEntity annualLeave(@PathVariable String name) {

        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                if (employee.getAnnualLeave() == 0) {
                    return ResponseEntity.status(400).body(new ApiResponse("your annual leave 0"));
                } else if (employee.isOnLeave()) {
                    return ResponseEntity.status(400).body(new ApiResponse("your now on leave"));

                } else {
                    employee.setOnLeave(true);
                    employee.setAnnualLeave(0);
                    return ResponseEntity.status(200).body(new ApiResponse("happy vacation"));

                }

            }
        }

        return ResponseEntity.status(400).body(new ApiResponse("not found"));


    }
}
