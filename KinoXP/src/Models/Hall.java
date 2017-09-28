package Models;

import DataAccessObject.Connector;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Hall> getHalls (){
        ArrayList<Hall> halls = new ArrayList<>();
        Connector connector = Connector.getInstance();
        conn = connector.getConnection();
        String query = "SELECT * FROM tandbud_project2.hall";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.first())
            {
                Hall hall = new Hall();
                hallID = rs.getInt("hall_id");
                hallName = rs.getString("hall_name");
                seats_row = rs.getInt("hall_seats_row");
                seats_column = rs.getInt("hall_seats_column");
                halls.add(hall);
            }

        } catch (SQLException e)
        {
            System.out.println("?");
            System.out.println(e.getMessage());
        }
        return halls;
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


}
