package DataAccessObject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Class connecting to database
 */
public class Connector
{
    private Connection conn = null;

    private Connection createConnection()
    {
        try {
            //Contructoren opretter forbindelse med vores database
            String myDriver = "com.mysql.jdbc.Driver";
            String url = "jdbc:mysql://77.66.47.183:3306/tandbud_project2";
            Class.forName(myDriver);

            conn = DriverManager.getConnection(url, "tandbud_project2", "hamzahistheking12");

            System.out.println("SUCCESS!");
        }
        catch (SQLException e)
        {
            System.out.println("FEJL med SQL!");
        }
        catch (ClassNotFoundException e)
        {
            System.out.println("FEJL!");
        }
        return conn;
    }

    public Connection getConnection(){return conn;}

    /** SINGLETON VARIABLER OG METODER*/
    private static Connector connector;

    private Connector (){
        this.conn = createConnection();
    }

    public static Connector getInstance(){
        if (connector == null){
            connector = new Connector();
        }
        return connector;
    }
}
