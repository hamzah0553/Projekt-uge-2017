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

    private String hallName;

    public Hall (){

    }

    /**
     *
     * @param hallID
     */
    public Hall(int hallID)
    {

        this.hallID = hallID;

        Connector connector = Connector.getInstance();
        conn = connector.getConnection();

        String query = "SELECT hall_name, hall_seats_row, hall_seats_column FROM tandbud_project2.hall where hall_id = ?";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, hallID);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.first())
            {
                hallName = rs.getString(1);
                seats_row = rs.getInt(2);
                seats_column = rs.getInt(3);

            }

        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }

    public int getHallID()
    {
        return hallID;
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

    public void setHallID(int hallID)
    {
        this.hallID = hallID;
    }

    public int getSeats_row()
    {
        return seats_row;
    }

    public void setSeats_row(int seats_row)
    {
        this.seats_row = seats_row;
    }

    public int getSeats_column()
    {
        return seats_column;
    }

    public void setSeats_column(int seats_column)
    {
        this.seats_column = seats_column;
    }

    public String getHallName()
    {
        return hallName;
    }

    public void setHallName(String hallName)
    {
        this.hallName = hallName;
    }
}
