package Models;

import DataAccessObject.Connector;

import java.sql.*;

/**
 * Created by blerimcazimi on 29/09/2017.
 */
public class PlayTime
{

    Statement st;
    Connection conn;

    private int playtime_id;

    public PlayTime(int playtime_id)
    {

        Connector connector = Connector.getInstance();
        conn = connector.getConnection();

        this.playtime_id = playtime_id;

    }

    public String getPlayStartDate()
    {
        String startDate = "";

        String query = "select * from movie_playtimes p \n" +
                "\n" +
                "INNER JOIN movie_description des ON(des.movie_id = p.movie_id)\n" +
                "\n" +
                "WHERE p.playtime_id = ?";

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, playtime_id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                startDate =  rs.getString("start_date");
            }


            return startDate;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }


        return "";


    }

    public String getMovieNameByPlayTime()
    {
        String movieName = "";


        String query = "select * from movie_playtimes p \n" +
                "\n" +
                "INNER JOIN movie_description des ON(des.movie_id = p.movie_id)\n" +
                "\n" +
                "WHERE p.playtime_id = ?";

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, playtime_id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                movieName =  rs.getString("movie_name");
            }


            return movieName;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }


        return "";


    }

    /**
     *
     * @return
     */
    public int getHallIDByPlayTime()
    {

        int results = 0;

        String query = "select * from movie_playtimes p \n" +
                "\n" +
                "INNER JOIN movie_description des ON(des.movie_id = p.movie_id)\n" +
                "\n" +
                "WHERE p.playtime_id = ?";

        try {

            PreparedStatement preparedStatement = conn.prepareStatement(query);
            preparedStatement.setInt(1, playtime_id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {

                results = rs.getInt("hall_id");
            }


            return results;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("error");
        }


        return 0;

    }


}
