package com.alex.dao;


import com.alex.entity.Employee;
import java.util.Collection;

public interface EmployeeDAO {

    Collection<Employee> getAllEmployees();

    Employee getEmployeeById(int id);

    int removeEmployeeById(int id);

    void updateEmployee(Employee employee);

    int insertEmployeeToDb(Employee employee);
}
