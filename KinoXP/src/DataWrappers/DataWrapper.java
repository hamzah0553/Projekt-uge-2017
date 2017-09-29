package DataWrappers;

import DataAccessObject.Connector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Transfers data to/from SQL statments
 */
public class DataWrapper
{
    protected Connection connection;

    protected DataWrapper()
    {
        Connector connector = Connector.getInstance();
        this.connection = connector.getConnection();
    }

    /** Method for "read" using prep. statement */
    public ResultSet getResultSet(PreparedStatement preparedStatement)
    {
        try
        {
            return preparedStatement.executeQuery();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    /** Method for "create/update/delete" using prep. statement */
    public void update(PreparedStatement preparedStatement)
    {
        try
        {
            preparedStatement.executeUpdate();
            preparedStatement.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
