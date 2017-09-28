package DataAccessObject;

import DataWrappers.DataWrapper;
import Models.Movie;
import Models.Play;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by den udvalgte on 26-09-2017.
 */
public class PlaylistDAO extends DataWrapper {
    private Connection conn;

    public PlaylistDAO() {
        this.conn = super.connection;
    }

    public void removeOrder(String orderID)
    {
        String query = "UPDATE `order` SET deleted = ? WHERE order_id = ?";

        PreparedStatement preparedStatement;
        try
        {
            preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, orderID);

            update(preparedStatement);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public ArrayList<HashMap<String, String>> getTicketInfo(String phone)
    {
        HashMap<String, String> rows = new HashMap<>();
        ArrayList<HashMap<String, String>> ticketInfo = new ArrayList<>();

        String query =
                "SELECT\n" +
                        "\n" +
                        "customer.customer_id, customer_phonenumber, customer_email,\n" +
                        "\n" +
                        "order.order_id, order.order_price, order.order_date,\n" +
                        "\n" +
                        "description.movie_name, hall.hall_name, hall.hall_id, play.start_date, seat_column, seat_row\n" +
                        "\n" +
                        "FROM customers customer\n" +
                        "\n" +
                        "INNER JOIN `order` ON(customer.customer_id = order.customer_id)\n" +
                        "\n" +
                        "INNER JOIN movie_playtimes play ON(play.playtime_id = order.movie_playtime_id)\n" +
                        "\n" +
                        "INNER JOIN movie_description description ON(description.movie_id = play.movie_id)\n" +
                        "\n" +
                        "INNER JOIN hall hall ON(hall.hall_id = play.hall_id)\n" +
                        "\n" +
                        "INNER JOIN seats seats ON (order.order_id = seats.order_id)\n" +
                        "\n" +
                        "WHERE customer_phonenumber LIKE '%"+ phone +"%'";

        System.out.println(query);

        try {
            PreparedStatement preparedStatement = conn.prepareStatement(query);
            ResultSet rs = getResultSet(preparedStatement);

            while (rs.next()) {
                rows.put("customer_id", rs.getString("customer_id"));
                rows.put("customer_phonenumber", rs.getString("customer_phonenumber"));
                rows.put("customer_email", rs.getString("customer_email"));
                rows.put("order_id", rs.getString("order_id"));
                rows.put("order_price", rs.getString("order_price"));
                rows.put("start_date", rs.getString("start_date"));
                //rows.put("order_date", rs.getString("order_date"));
                rows.put("movie_name", rs.getString("movie_name"));
                rows.put("hall_name", rs.getString("hall_name"));
                rows.put("hall_id", rs.getString("hall_id"));
                rows.put("seat_column", rs.getString("seat_Column"));
                rows.put("seat_row", rs.getString("seat_row"));
                ticketInfo.add(rows);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ticketInfo;
    }

    public ArrayList getPlaylist() {

        ArrayList<Play> plays = new ArrayList<>();

        String query = "SELECT * FROM tandbud_project2.movie_playtimes t inner join movie_description d on t.movie_id =d.movie_id ;";
        try {

            PreparedStatement preparedStatement = conn.prepareStatement(query);

            ResultSet rs = getResultSet(preparedStatement);

            while (rs.next()) {
                Play play = new Play();
                play.setId(rs.getInt("playtime_id"));
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

                String query = "INSERT INTO `tandbud_project2`.`movie_playtimes` (`movie_id`, `hall_id`,`start_date`)" +
                        " VALUES (?,?,?);";


                PreparedStatement preparedStmt = conn.prepareStatement(query);

                preparedStmt.setInt(1, play.getMovie().getId());
                preparedStmt.setInt(2,1);
                preparedStmt.setString(3, play.getDate() + " " + play.getTime()+":00");


                preparedStmt.execute();
                st.close();


            }catch (Exception e){
                e.printStackTrace();

                System.out.println("Fejl: i anden halvdel");
            }

        }


    }


