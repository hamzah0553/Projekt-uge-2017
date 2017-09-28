package DataAccessObject;

import DataWrappers.DataWrapper;
import Models.Hall;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by den udvalgte on 28-09-2017.
 */
public class HallDAO extends DataWrapper
{

    Connection conn;
    public HallDAO(){
        this.conn = super.connection;
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

            while (rs.next())
            {
                Hall hall = new Hall();
                hall.setHallID(rs.getInt("hall_id"));
                hall.setHallName(rs.getString("hall_name"));
                hall.setSeats_column( rs.getInt("hall_seats_row"));
                hall.setSeats_column(rs.getInt("hall_seats_column"));
                halls.add(hall);
            }

        } catch (SQLException e)
        {
            System.out.println("?");
            System.out.println(e.getMessage());
        }

        for (Hall h : halls) {
            System.out.println(h.getHallID());
        }
        return halls;
    }

}
