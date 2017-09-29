package DataAccessObject;

import DataWrappers.DataWrapper;
import Models.Employee;

import java.sql.*;


public class EmployeeDAO extends DataWrapper
{

        private Connection conn;

        public EmployeeDAO() {
            this.conn = super.connection;
        }

        public void createEmployee(String username, String password, Employee employee){

            try {
                Statement st = conn.createStatement();

                String query = "INSERT INTO `tandbud_project2`.`employee` (`employee_username`, `employee_password`,`employee_role`, `employee_name`)" +
                        " VALUES (?,?,?,?);";


                PreparedStatement preparedStmt = conn.prepareStatement(query);

                preparedStmt.setString(1, username);
                preparedStmt.setString(2, password);
                preparedStmt.setString(3, employee.getRole());
                preparedStmt.setString(4, employee.getName());
                preparedStmt.execute();
                st.close();

            }catch (Exception e){
                e.printStackTrace();

                System.out.println("Fejl: i anden halvdel");
            }

        }
}
