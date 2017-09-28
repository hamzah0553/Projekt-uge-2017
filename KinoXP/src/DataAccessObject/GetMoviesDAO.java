package DataAccessObject;

import DataWrappers.DataWrapper;
import Models.TableInformation;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GetMoviesDAO extends DataWrapper {

    private Connection conn;
    private String isMovieViewable;

    public GetMoviesDAO(){
        this.conn = super.connection;
    }

    //returns an observable list that contains the database moviename, and ID(useful for when the employee wants to add new movies)
    public ObservableList<TableInformation> getMovieData(ObservableList<TableInformation> dataList) {

        try {

            String mySQL = ("SELECT movie_id, movie_name, movie_run FROM tandbud_project2.movie_description");
            ResultSet rs = conn.createStatement().executeQuery(mySQL);

            while (rs.next()){


                if (rs.getString("movie_run").equalsIgnoreCase("0")){
                    isMovieViewable = "No";
                }
                else
                    isMovieViewable = "Yes";

                dataList.add(new TableInformation(rs.getString("movie_id"),rs.getString("movie_name"), isMovieViewable));
            }
            rs.close();

        } catch (SQLException e){
            System.out.println(e + "SQL Broke");
        }

return dataList;
    }

    //gets latest ID in the database
    public String getID(){
        String id = "";

        try{
            String sql = ("SELECT movie_id FROM tandbud_project2.movie_description ORDER BY movie_id DESC LIMIT 1");

            ResultSet rs = conn.createStatement().executeQuery(sql);

            while (rs.next()) {
                id = rs.getString("movie_id");
            }



        }catch (SQLException e) {

            System.out.println("BROKE");
        }
        return id;
    }
}
