public interface Command {
    String EXIT = "0";
    String ADD_COMPANY = "1";
    String ADD_EMPLOYEE = "2";
    String UPDATE_COMPANY_BY_ID = "3";
    String PRINT_ALL_COMPANIES = "4";
    String PRINT_COMPANIES_BY_COUNTRY = "5";
    String DELETE_COMPANY_BY_ID = "6";
    String PRINT_ALL_EMPLOYEES = "7";
    String PRINT_EMPLOYEES_BY_COMPANY_ID = "8";
    String DELETE_EMPLOYEE_BY_ID = "9";
}
