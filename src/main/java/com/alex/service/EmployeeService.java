package com.alex.service;

import com.alex.dao.EmployeeDAO;
import com.alex.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeService {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    public Collection<Employee> getAllStudents(){
        return this.employeeDAO.getAllEmployees();
    }

    public Employee getEmployeeById(int id){
        return this.employeeDAO.getEmployeeById(id);
    }
    public void updateEmployee(Employee employee){
        this.employeeDAO.updateEmployee(employee);
    }

    public int insertEmployee(Employee employee) {
        return this.employeeDAO.insertEmployeeToDb(employee);
    }
    public int deleteEmployeeById(int id){ return this.employeeDAO.removeEmployeeById(id);}
}
