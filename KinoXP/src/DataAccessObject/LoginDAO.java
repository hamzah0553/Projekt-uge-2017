package DataAccessObject;

import java.sql.*;

/**
 * Created by den udvalgte on 25-09-2017.
 */
public class LoginDAO
{
    Statement st;
    Connection conn;

    public LoginDAO(){


        Connector connector = Connector.getInstance();

        conn = connector.getConn();


    }

    public boolean validate (String username, String password){

        boolean login = false;

        String query = "SELECT * FROM tandbud_project2.employee WHERE employee_UserName = ? AND employee_Password = ?";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            System.out.println(preparedStatement);

            preparedStatement.setString(1,username);
            preparedStatement.setString(2, password);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.first())
            {
                System.out.println(rs.getString("employee_UserName"));
                login= true;
                rs.close();
                preparedStatement.close();
                conn.close();

            }
            else
            {
                login=  false;
            }



        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("FEJL I SQL h");
        }


        return login;

    }

}
