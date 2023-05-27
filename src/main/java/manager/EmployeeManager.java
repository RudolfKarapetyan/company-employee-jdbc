package manager;

import db.DBConnectionProvider;
import model.Employee;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public Employee getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from employee where id =" + id);
            if (resultSet.next()) {
                return getEmployeeFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Employee> getAll() {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from employee");
            while (resultSet.next()) {
                employees.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    public void save(Employee employee) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO employee(name,surname,email,company_id) VALUES('%s','%s','%s',%d)";
            statement.executeUpdate(String.format(sql, employee.getName(), employee.getSurname(),
                    employee.getEmail(), employee.getCompanyId()));
            System.out.println("Employee inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Employee> getAllByCompanyId(int id) {
        List<Employee> employees = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee WHERE company_id =" + id);
            while (resultSet.next()) {
                employees.add(getEmployeeFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    private Employee getEmployeeFromResultSet(ResultSet resultSet) throws SQLException {
        Employee employee = new Employee();
        employee.setId(resultSet.getInt("id"));
        employee.setName(resultSet.getString("name"));
        employee.setSurname(resultSet.getString("surname"));
        employee.setEmail(resultSet.getString("email"));
        employee.setCompanyId(resultSet.getInt("company_id"));
        return employee;
    }

    public void removeById(int employeeId) {
        String sql = "DELETE FROM employee WHERE id =" + employeeId;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
