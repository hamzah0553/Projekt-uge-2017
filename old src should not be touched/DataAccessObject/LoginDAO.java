package DataAccessObject;

import DataWrappers.DataWrapper;

import java.sql.*;

/**
 * Subclass to wrap data from login
 */
public class LoginDAO extends DataWrapper
{
    private Connection conn;

    public LoginDAO(){
        this.conn = super.connection;
    }

    public boolean validate (String username, String password) throws SQLException{

        boolean login;

        String query = "SELECT * FROM tandbud_project2.employee WHERE employee_username = ? AND employee_password = ?";

        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,username);
        preparedStatement.setString(2, password);
        System.out.println(preparedStatement);  //TODO: test

        ResultSet rs = getResultSet(preparedStatement);

        if (rs.first())
        {
            System.out.println(rs.getString("employee_username"));
            login = true;
            rs.close();
            preparedStatement.close();
            conn.close();
        }
        else
        {
            login =  false;
        }

        return login;
    }

}
