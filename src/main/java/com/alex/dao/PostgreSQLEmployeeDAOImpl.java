package com.alex.dao;

import com.alex.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;


@Repository
public class PostgreSQLEmployeeDAOImpl implements EmployeeDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PostgreSQLEmployeeDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private static class EmployeeRowMapper implements RowMapper<Employee> {

        @Override
        public Employee mapRow(ResultSet resultSet, int i) throws SQLException {
            Employee employee = new Employee();
            employee.setId(resultSet.getInt("id"));
            employee.setName(resultSet.getString("name"));
            employee.setDepartment(resultSet.getString("department"));
            return employee;
        }
    }

    @Override
    public Collection<Employee> getAllEmployees() {
        final String sql = "SELECT id, name, department FROM employees";
        List<Employee> employees = jdbcTemplate.query(sql, new EmployeeRowMapper());
        return employees;
    }

    @Override
    public Employee getEmployeeById(int id) {

        final String sql = "SELECT id, name, department FROM employees where id = ?";
        Employee employee = jdbcTemplate.queryForObject(sql, new EmployeeRowMapper(), id);
        return employee;
    }

    @Override
    public int removeEmployeeById(int id) {
        final String sql = "DELETE FROM employees WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        final String sql = "UPDATE employees SET name = ?, department = ? WHERE id = ?";
        final int id = employee.getId();
        final String name = employee.getName();
        final String department = employee.getDepartment();
        jdbcTemplate.update(sql, name, department, id);
    }

    @Override
    public int insertEmployeeToDb(Employee employee) {
        final String sql = "INSERT INTO employees (name, department) VALUES (?, ?)";
        final String name = employee.getName();
        final String department = employee.getDepartment();
        return jdbcTemplate.update(sql, name, department);
    }
}
