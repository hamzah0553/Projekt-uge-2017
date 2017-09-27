package DataAccessObject;

import java.sql.*;

public class CustomerData {

    Statement st;
    Connection connection;

    public CustomerData() {

        Connector connector = Connector.getInstance();

        connection = connector.getConnection();
    }


    //when we have a textfield that asks for the buyers email, use this method to store the data in a database.
    public void insertMail(String email) {

        String query = "INSERT INTO customers(customer_email) VALUES ('" + email + "')";

        try {

            st = connection.createStatement();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            st.execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //this method returns the latest email inserted in the database
    public String getLatestMail() {


        String query = "SELECT customer_email FROM customers ORDER BY customer_id DESC LIMIT 1";

        String results = "";

        try {

            st = connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            if (rs.next()) {

                results = rs.getString("customer_email");
            }

         return results;

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return "";


    }
}