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
            return create;
        } catch (SQLException e) {
            create = false;
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
    public boolean Update(int movie_id,int hall_id,String movie_name,String movie_length,int movie_age) throws SQLException {
        boolean update;
        try{
            String sql= "UPDATE tandbud_project2.movie_description SET movie_name= ?, hall_id = ?, movie_length = ?, movie_age = ? WHERE movie_id = ?";
           PreparedStatement preparedStatement = conn.prepareStatement(sql);
           preparedStatement.setString(1,movie_name);
          preparedStatement.setInt(2,hall_id);
          preparedStatement.setString(3,movie_length);
          preparedStatement.setInt(4,movie_age);
          preparedStatement.setInt(5,movie_id);                       // FIXME: 28-09-2017

           preparedStatement.execute();

            System.out.println("UPDATE VIRKER");
            update=true;
            return update;
        }catch (SQLException e){
            update=false;
            System.out.println(e.getMessage());

            return update;

        }
    }
}