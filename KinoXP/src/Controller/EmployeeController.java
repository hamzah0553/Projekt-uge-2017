package Controller;

import DataAccessObject.EmployeeDAO;
import Models.Employee;
import System.*;

public class EmployeeController extends Controller
{

    public EmployeeController(Model model)
    {
        super(model);
    }

    public void createLogin(String username, String password, Employee employee)
    {
        new EmployeeDAO().createEmployee(username, password, employee);
    }


}
