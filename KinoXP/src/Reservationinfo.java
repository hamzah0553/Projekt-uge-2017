import Models.Customer;

public class Reservationinfo {

    private int phone;
    private int reservationID;
    private Customer customer;


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
}
