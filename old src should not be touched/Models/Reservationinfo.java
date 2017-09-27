package Models;

import Models.Customer;
import Models.Movie;
import Models.Ticket;

import java.util.ArrayList;

public class Reservationinfo {

    private int phone;
    private int reservationID;
    private Customer customer;
    ArrayList<Ticket> myArray;

    public Reservationinfo(){

    }

    public void setNummer(int nummer) {
        this.phone = nummer;
    }

    public int getNummer() {
        return phone;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ArrayList<Ticket> generateTickets (int numberoftickets) {
         myArray = new ArrayList<>();

        do {
            myArray.add(new Ticket(22, 22, "22", "Dummttest"));
            numberoftickets--;
        }while (numberoftickets != 0);

        return myArray;
    }
}
