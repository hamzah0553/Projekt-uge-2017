package Models;

import DataAccessObject.Connector;

import java.sql.*;
import java.util.ArrayList;

/**
 * Created by blerimcazimi on 26/09/2017.
 */
public class Order
{

    Statement st;
    Connection conn;

    public Order()
    {

        Connector connector = Connector.getInstance();
        conn = connector.getConnection();

    }

    /**
     *
     * @param customerID
     * @param movie_playtime_id
     * @param order_price
     * @param seats_reserved
     *
     */
    public void createOrder(int customerID, int movie_playtime_id, double order_price)
    {

        String query = "INSERT INTO `order`(customer_id, movie_playtime_id, order_price)" +
                "VALUES ('" + customerID + "', '" + movie_playtime_id + "', '" + order_price + "')";

        try
        {

            st = conn.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            st.execute(query);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }


    }

    /**
     * Settings seats reserved to the order.
     * @param seats
     */
    public void createOrderSeatReservation(ArrayList<Seat> seats)
    {

        int latestOrderID = getLatestOrderID();

        for(int i = 0; seats.size() > i; i++)
        {

            if(!seats.get(i).isReserved())
            {

                String query = "INSERT INTO `seats_reserved`(order_id, seat_row, seat_column) " +
                        "VALUES ('" + latestOrderID + "', '" + seats.get(i).getRow() + "', '" + seats.get(i).getColumn() + "')";

                try {

                    st = conn.createStatement();

                } catch (SQLException e) {
                    e.printStackTrace();
                }

                try {
                    st.execute(query);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }


        }

    }

    /**
     *
     * @return
     */
    public int getLatestOrderID()
    {

        String query = "SELECT order_id FROM `order` ORDER BY order_id DESC LIMIT 1";

        int results = 0;

        try {

            st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {

                results = rs.getInt("order_id");
            }

            return results;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return 0;

    }

}
