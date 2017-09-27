package Models;

import DataAccessObject.Connector;

import java.sql.*;

public class Hall
{

    Connection conn;
    Statement st;

    /**
     *
     */
    private int hallID;

    private int seats_row;

    private int seats_column;

    /**
     *
     * @param hallID
     */
    public Hall(int hallID)
    {

        this.hallID = hallID;

        Connector connector = Connector.getInstance();
        conn = connector.getConn();

        String query = "SELECT hall_name, hall_seats_row, hall_seats_column FROM tandbud_project2.hall where hall_id = ?";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, hallID);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.first())
            {

                seats_row = rs.getInt(2);
                seats_column = rs.getInt(3);

            }

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }


    }

    /**
     * Returns amount of rows the hall has.
     * @return int
     */
    public int getSeatsRow()
    {

        return seats_row;

    }

    /**
     * Returns amount of columns the hall has.
     * @return int
     */
    public int getSeatsColumn()
    {

        return seats_row;

    }

    /**
     * Returns the amount of seats the hall has.
     * @return int
     */
    public int amountOfSeats()
    {

        return seats_column * seats_row;

    }


}
