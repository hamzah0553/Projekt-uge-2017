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

    public DAOmovie(){
        this.conn = super.connection;
    }

    public boolean Createmovie(String movie_name, String movie_length,int movie_age,int moive_run,int hall_ID) throws SQLException {

        boolean create;
        try {
            String sql = "INSERT INTO tandbud_project2.movie_description(movie_name,movie_length,movie_enddate,movie_run,hall_ID) VALUE (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, movie_name);
            preparedStatement.setString(2, movie_length);
            preparedStatement.setInt(3, movie_age);
            preparedStatement.setInt(4, moive_run);
            preparedStatement.setInt(5, hall_ID);

            create = true;
            conn.close();
            return create;
        } catch (SQLException e) {
            create = false;
            conn.close();
            return create;
        }

    }
    public boolean Delete(int movie_id) throws SQLException {

        boolean delete;
        try {
            String sql = "DELETE FROM tandbud_project2.movie_description WHERE Movie_id= ?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, movie_id);
            delete = true;
            conn.close();
            return delete;
        } catch (SQLException e) {
            delete = false;
            conn.close();
            return delete;


        }
    }
    public boolean update(String movie_name, String movie_length,int movie_age,int moive_run,int hall_ID ) throws SQLException{

        boolean update;
        try{
            String sql="UPDATE tandbud_project2.movie_description SET movie_name = ?,movie_length = ?,movie_age = ?,movie_run = ?,hall_ID=?";

            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,movie_name);
            preparedStatement.setString(2,movie_length);
            preparedStatement.setInt(3,movie_age);
            preparedStatement.setInt(4,moive_run);
            preparedStatement.setInt(5,hall_ID);
            update=true;
            conn.close();
            return update;
        }catch (SQLException e){
            update=false;
            conn.close();
            return update;
        }
    }

}
