package DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by den udvalgte on 21-08-2017.
 */
public class Connector
{
    private Connection conn;

    public Connection getConn()
    {
        return conn;
    }

    private Connector (){
        try {

            //Contructoren opretter forbindelse med vores database
            String myDriver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://77.66.47.183:3306/tandbud_project2";
            Class.forName(myDriver);

            conn = DriverManager.getConnection(url, "tandbud_project2", "hamzahistheking12");

            //System.out.println("TEST!");


        }
        catch (SQLException e)
        {
            System.out.println("FEJL med SQL!");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("FEJL!");
        }

    }


    /** SINGLETON VARIABLER OG METODER*/
    private static Connector connector;


    public static Connector getInstance(){
        if (connector == null){
            connector = new Connector();

        }
        return connector;
    }
}
