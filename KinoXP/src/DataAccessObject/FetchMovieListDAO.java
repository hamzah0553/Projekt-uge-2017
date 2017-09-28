package DataAccessObject;

import DataWrappers.DataWrapper;
import Models.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hamza on 26-09-2017.
 */
public class FetchMovieListDAO extends DataWrapper {

    private Connection conn;

    public FetchMovieListDAO() {
        this.conn = super.connection;
    }

    public ArrayList<Movie> getMovies(){

        ArrayList<Movie> movies = new ArrayList<>();
        String query= "SELECT * FROM tandbud_project2.movie_description";

         try {
             PreparedStatement preparedStatement = conn.prepareStatement(query);

             ResultSet rs = getResultSet(preparedStatement);

             while (rs.next()){
                 Movie movie = new Movie();
                 movie.setId(rs.getInt("movie_id"));
                 movie.setName(rs.getString("movie_name"));
                 movie.setLength(rs.getString("movie_length"));
                 movie.setAge(rs.getInt("movie_age"));
                 movie.setRun(rs.getInt("movie_run"));

                 movies.add(movie);
             }

         }catch (SQLException e){

         }

        for (Movie m :
                movies) {
            System.out.println(m.getName());
        }

        return movies;

        }

    }


