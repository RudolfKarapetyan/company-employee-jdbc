import manager.CompanyManager;
import manager.EmployeeManager;
import model.Company;
import model.Employee;

import java.util.List;
import java.util.Scanner;

public class CompanyEmployeeMain implements Command {
    private static Scanner scanner = new Scanner(System.in);
    private static CompanyManager companyManager = new CompanyManager();
    private static EmployeeManager employeeManager = new EmployeeManager();

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommand();
            String command = scanner.nextLine();
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_COMPANY:
                    addCompany();
                    break;
                case ADD_EMPLOYEE:
                    addEmployee();
                    break;
                case UPDATE_COMPANY_BY_ID:
                    updateCompanyById();
                    break;
                case PRINT_ALL_COMPANIES:
                    printAllCompanies();
                    break;
                case PRINT_COMPANIES_BY_COUNTRY:
                    printCompaniesByCountry();
                    break;
                case DELETE_COMPANY_BY_ID:
                    deleteCompanyById();
                    break;
                case PRINT_ALL_EMPLOYEES:
                    printAllEmployees();
                    break;
                case PRINT_EMPLOYEES_BY_COMPANY_ID:
                    printEmployeesByCompanyId();
                    break;
                case DELETE_EMPLOYEE_BY_ID:
                    deleteEmployeeById();
                    break;
                default:
                    System.out.println("Wrong command! Please try again.");
                    break;
            }
        }
    }

    private static void updateCompanyById() {
        List<Company> all = companyManager.getAll();
        for (Company company : all) {
            System.out.println(company);
        }
        System.out.println("Please choose company id");
        int id = Integer.parseInt(scanner.nextLine());
        if (companyManager.getById(id) != null){
            System.out.println("Please input name,country");
            String data = scanner.nextLine();
            String[] dataArray = data.split(",");
            Company company = new Company();
            company.setId(id);
            company.setName(dataArray[0]);
            company.setCountry(dataArray[1]);
            companyManager.update(company);
            System.out.println("Company was updated");
        }else {
            System.out.println("Company does not exists");
        }
    }

    private static void deleteCompanyById() {
        try {
            List<Company> all = companyManager.getAll();
            for (Company company : all) {
                System.out.println(company);
            }
            System.out.println("Please choose company id");
            int id = Integer.parseInt(scanner.nextLine());
            companyManager.removeById(id);
            System.out.println("Company remove");
        } catch (NumberFormatException e) {
            System.out.println("Please input number id!");
        }
    }

    private static void deleteEmployeeById() {
        try {
            List<Employee> all = employeeManager.getAll();
            for (Employee employee : all) {
                System.out.println(employee);
            }
            System.out.println("Please choose employee id");
            int employeeId = Integer.parseInt(scanner.nextLine());
            employeeManager.removeById(employeeId);
            System.out.println("Employee remove");
        } catch (NumberFormatException e) {
            System.out.println("Please input number id!");
        }
    }

    private static void printEmployeesByCompanyId() {
        List<Company> all = companyManager.getAll();
        for (Company company : all) {
            System.out.println(company);
        }
        System.out.println("Please choose company id");
        int id = Integer.parseInt(scanner.nextLine());
        Company company = companyManager.getById(id);
        if (company != null) {
            List<Employee> allByCompanyId = employeeManager.getAllByCompanyId(id);
            for (Employee employee : allByCompanyId) {
                System.out.println(employee);
            }
        }
    }


    private static void printAllEmployees() {
        List<Employee> all = employeeManager.getAll();
        for (Employee employee : all) {
            System.out.println(employee);
        }
    }

    private static void printCompaniesByCountry() {
        System.out.println("Please input country");
        String country = scanner.nextLine();
        List<Company> companies = companyManager.getByCountry(country);
        for (Company company : companies) {
            System.out.println(company);
        }
    }

    private static void printAllCompanies() {
        List<Company> all = companyManager.getAll();
        for (Company company : all) {
            System.out.println(company);
        }
    }

    private static void addEmployee() {
        List<Company> all = companyManager.getAll();
        for (Company company : all) {
            System.out.println(company);
        }
        System.out.println("Please choose company id");
        int id = Integer.parseInt(scanner.nextLine());
        Company company = companyManager.getById(id);
        if (company != null) {
            System.out.println("Please input employee name,surname,email");
            String employeeStr = scanner.nextLine();
            String[] employeeData = employeeStr.split(",");
            Employee employee = new Employee();
            employee.setCompanyId(company.getId());
            employee.setName(employeeData[0]);
            employee.setSurname(employeeData[1]);
            employee.setEmail(employeeData[2]);
            employeeManager.save(employee);
        }
    }

    private static void addCompany() {
        try {
            System.out.println("Please input name,country");
            String data = scanner.nextLine();
            String[] dataArray = data.split(",");
            Company company = new Company();
            company.setName(dataArray[0]);
            company.setCountry(dataArray[1]);
            companyManager.save(company);
        } catch (NullPointerException e) {
            System.out.println("Little data");
        }
    }

    private static void printCommand() {
        System.out.println("Please input 0 for exit");
        System.out.println("Please input 1 for add company");
        System.out.println("Please input 2 for add employee");
        System.out.println("Please input 3 for update company by id");
        System.out.println("Please input 4 for print all companies");
        System.out.println("Please input 5 for print companies by Country");
        System.out.println("Please input 6 for delete company by ID");
        System.out.println("Please input 7 for print all employees");
        System.out.println("Please input 8 for print employees by companyId");
        System.out.println("Please input 9 for delete employee by id");
    }
}