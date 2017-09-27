package DataAccessObject;

import DataWrappers.DataWrapper;
import Models.Play;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                play.setMovieName(rs.getString("movie_name"));
                play.setTime(rs.getTime("start_date").toString());
                plays.add(play);
            }
        } catch (SQLException e) {
e.printStackTrace();        }


        return plays;
    }
}
