package DataAccessObject;

import DataWrappers.DataWrapper;
import Models.Movie;
import Models.Play;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by den udvalgte on 26-09-2017.
 */
public class PlaylistDAO extends DataWrapper {
    private Connection conn;

    public PlaylistDAO() {
        this.conn = super.connection;
    }

    public ArrayList getPlaylist() {

        ArrayList<Play> plays = new ArrayList<>();

        String query = "SELECT * FROM tandbud_project2.movie_playtimes t inner join movie_description d on t.movie_id =d.movie_id ;";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet rs = getResultSet(preparedStatement);

            while (rs.next()) {
                Play play = new Play();
                String date = rs.getDate("start_date").toString();
                play.setDate(date.substring(date.length()-2,date.length())+"/"+date.substring(date.length()-5,date.length()-3));
                play.setTime(rs.getTime("start_date").toString());
                play.setMovieName(rs.getString("movie_name"));

                Movie movie = new Movie();
                play.setMovie(movie);
                play.getMovie().setName(rs.getString("movie_name"));
                play.getMovie().setAge(rs.getInt("movie_age"));
                play.getMovie().setId(rs.getInt("movie_id"));
                play.getMovie().setLength(rs.getString("movie_length"));

                plays.add(play);
            }
        } catch (SQLException e) {
            e.printStackTrace();        }


        return plays;
    }

    public void createPlay(Play play){


            try {
                Statement st = conn.createStatement();

                String query = "INSERT INTO `tandbud_project2`.`movie_playtimes` (`movie_id`, `hall_id`, `start_date`)" +
                        " VALUES (?,?,?);";


                PreparedStatement preparedStmt = conn.prepareStatement(query);
                preparedStmt.setInt(1, play.getMovie().getId());
                preparedStmt.setInt(2,0);
                preparedStmt.setString(3, play.getDate() + " " + play.getTime()+":00");


                preparedStmt.execute();
                st.close();


            }catch (Exception e){
                e.printStackTrace();

                System.out.println("Fejl: i anden halvdel");
            }

        }


    }


