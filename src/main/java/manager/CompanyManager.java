package manager;

import db.DBConnectionProvider;
import model.Company;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CompanyManager {
    private Connection connection = DBConnectionProvider.getInstance().getConnection();

    public Company getById(int id) {
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from company where id =" + id);
            if (resultSet.next()) {
                return getCompanyFromResultSet(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Company> getAll() {
        List<Company> companyList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from company");
            while (resultSet.next()) {
                companyList.add(getCompanyFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return companyList;
    }
    public void removeById(int companyId) {
        String sql = "DELETE FROM company WHERE id =" + companyId;
        try(Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Company> getByCountry(String country) {
        List<Company> companyList = new ArrayList<>();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM company WHERE cauntry = ?");
            ps.setString(1,country);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                companyList.add(getCompanyFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return companyList;
    }

    private Company getCompanyFromResultSet(ResultSet resultSet) throws SQLException {
        Company company = new Company();
        company.setId(resultSet.getInt("id"));
        company.setName(resultSet.getString("name"));
        company.setCountry(resultSet.getString("country"));
        return company;
    }

    public void save(Company company) {
        try {
            Statement statement = connection.createStatement();
            String sql = "INSERT INTO company(name,country) VALUES('%s','%s')";
            statement.executeUpdate(String.format(sql, company.getName(), company.getCountry()));
            System.out.println("Company inserted into DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Company company) {
        String sql = "UPDATE company SET name = '%s', country = '%s' WHERE id = %d";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(String.format(sql,company.getName(),company.getCountry(),company.getId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
