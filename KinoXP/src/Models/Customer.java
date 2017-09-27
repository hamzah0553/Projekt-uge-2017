package Models;

import DataAccessObject.Connector;

import java.sql.*;

public class Customer
{



    Statement st;
    Connection conn;

    /**
     *
     * @param email
     * @param phone
     */
    public Customer()
    {

        Connector connector = Connector.getInstance();
        conn = connector.getConnection();

    }

    /**
     *
     * @param email
     * @param phone
     */
    public void createCustomer(String email, int phone)
    {

        String query = "INSERT INTO `customers`(customer_email, customer_phonenumber) " +
                "VALUES ('" + email + "', '" + phone + "')";

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

    public int findOrFail(int phone)
    {


        String query = "SELECT * FROM tandbud_project2.customers WHERE customer_phonenumber = ?";
        try
        {
            PreparedStatement preparedStatement = conn.prepareStatement(query);

            preparedStatement.setInt(1, phone);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.first())
            {

                return rs.getInt("customer_id");

            }


        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("FEJL I SQL h");
        }

        return 0;

    }

    public int getLatestCustomerID()
    {

        return 30;

    }

}