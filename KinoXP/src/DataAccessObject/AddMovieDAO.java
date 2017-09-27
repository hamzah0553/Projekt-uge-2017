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
public class AddMovieDAO extends DataWrapper {

    private Connection conn;

    public AddMovieDAO () {
        this.conn = super.connection;

        List<Movie> movies = new ArrayList<>();
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

            Movie shrek = new Movie();

            shrek.setName("Shrek");
            shrek.setAge(7);
            shrek.setLength("90 minutes");
            movies.add(shrek);

            Movie terminator2 = new Movie();

            terminator2.setName("Terminator 2");
            terminator2.setLength("120 minutes");
            terminator2.setAge(15);
            movies.add(terminator2);

            Movie gravity = new Movie();

            gravity.setName("Gravity");
            gravity.setLength("90 minutes");
            gravity.setAge(14);
            movies.add(gravity);

        Movie logan = new Movie();

        logan.setName("Logan");
        logan.setLength("90 minutes");
        logan.setAge(14);
        movies.add(logan);

        Movie avengers = new Movie();

        avengers.setName("Avengers");
        avengers.setLength("143 minutes");
        avengers.setAge(13);
        movies.add(avengers);





        }








    }


