package Controller;


import Models.Customer;
import Models.Order;
import Models.Seat;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ReservationController
{

    private int customerPhone;
    private String customerEmail;

    private ArrayList<Seat> seatsChosen;

    public String validateReservation(int customerPhone, String customerEmail)
    {

        if(customerPhone == 0)
        {

            return "Kundens nr må ikke være tomt..";

        }


        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;

        return "OK";

    }

    public void setSeatsChosen(ArrayList<Seat> seatsChosen)
    {

        this.seatsChosen = seatsChosen;

    }

    /**
     * Creating the order and seat reservation.
     */
    public void createReservation()
    {

        Customer customer = new Customer();

        //if 0, no customer with the given phone exists.
        int customerID = customer.findOrFail(customerPhone);

        //create customer
        if(customerID == 0)
        {

            customer.createCustomer(customerEmail, customerPhone);
            customerID = customer.getLatestCustomerID();

        }

        //finally creating the order, with the given seats.
        Order order = new Order();
        order.createOrder(customerID,1, 299);
        order.createOrderSeatReservation(seatsChosen);

    }


}
