package Models;

import DataAccessObject.Connector;

import java.sql.*;

public class Seat
{

    Statement st;
    Connection conn;

    private int row;
    private int column;
    private int hallID;

    private boolean hasBeenChosen = false;

    /**
     *
     * @param row
     * @param column
     * @param hallID
     */
    public Seat(int row, int column, int hallID)
    {

        Connector connector = Connector.getInstance();

        conn = connector.getConnection();

        this.row = row;
        this.column = column;
        this.hallID = hallID;

    }

    /**
     *
     * @return
     */
    public int getRow()
    {

        return row;

    }

    /**
     *
     * @return
     */
    public int getColumn()
    {

        return column;

    }

    public boolean checkIfClicked()
    {

        return hasBeenChosen;

    }

    /**
     * Determines if a seat has been clicked on (this is being used for, doing reservations on specific seat).
     * @return boolean
     */
    public void updateHasBeenClicked()
    {

        hasBeenChosen = !hasBeenChosen;

    }

    /**
     * Determines if the seat exists in the hall (known as visible).
     * @return boolean
     */
    public boolean isAvailable()
    {

        String query = "SELECT * FROM tandbud_project2.seats_not_visible where hall_id = ? and seat_row = ? and seat_column = ?";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, hallID);
            preparedStatement.setInt(2, row);
            preparedStatement.setInt(3, column);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.first())
            {

                return false;

            }

            return true;

        } catch (SQLException e)
        {
            System.out.println("error");
        }


        return true;

    }


    /**
     *
     * Determines if the seat is reserved from db.
     *
     * @return boolean
     */
    public boolean isReserved()
    {

        String query = "SELECT o.*, t.* FROM seats_reserved reserved \n" +
                "\n" +
                "INNER JOIN `order` o ON(o.order_id = reserved.order_id)\n" +
                "\n" +
                "INNER JOIN movie_playtimes t ON(o.movie_playtime_id = t.playtime_id)\n" +
                " \n" +
                "WHERE t.hall_id = ? AND t.playtime_id = ? AND reserved.seat_row = ? AND seat_column = ?";

        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, hallID);
            preparedStatement.setInt(2, 1);

            //seat..
            preparedStatement.setInt(3,  row); //row
            preparedStatement.setInt(4, column); //column

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.first())
            {

                System.out.println("lol");
                return true;

            }

            return false;

        } catch (SQLException e)
        {
            System.out.println("error");
        }


        return false;

    }

}
