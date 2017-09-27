package DataAccessObject;

import DataWrappers.DataWrapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Kenedid on 27-09-2017.
 */
public class DAOmovie extends DataWrapper {

    private Connection conn;

    public DAOmovie() {
        this.conn = super.connection;
    }

    public boolean Createmovie(String movie_name, String movie_length,int movie_age, int movie_run, int hall_ID) throws SQLException {

        boolean create;
        try {
            String sql = "INSERT INTO tandbud_project2.movie_description(movie_name,movie_length,movie_age,movie_run,hall_ID) " +
                    "VALUE (?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, movie_name);
            preparedStatement.setString(2, movie_length);    // FIXME: 27-09-2017
            preparedStatement.setInt(3, movie_age);
            preparedStatement.setInt(4, movie_run);
            preparedStatement.setInt(5, hall_ID);

            preparedStatement.execute();

            System.out.println("JA TAK BABY");

            create = true;
            conn.close();
            return create;
        } catch (SQLException e) {
            create = false;
            conn.close();
            System.out.println(e.getMessage());

            return create;
        }

    }

    public boolean Delete(int movie_id) throws SQLException {

        boolean delete;
        try {
            String sql = "UPDATE `tandbud_project2`.`movie_description` SET `movie_run`='0' WHERE `movie_id`=?;";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, movie_id);

            preparedStatement.execute();

            System.out.println("SLET VIRKER");
            delete = true;
            conn.close();                                                          // FIXME: 27-09-2017 
            return delete;
        } catch (SQLException e) {
            delete = false;
            conn.close();
            return delete;
        }

    }
}