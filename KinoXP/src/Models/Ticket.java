package Models;

import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by jakob on 26-09-2017.
 */
public class Ticket {

    private int seatId;
    private int rowNr;
    private String ticketDate;
    private String movieTitle;


    public Ticket(int seatId, int rowNr, String ticketDate, String movieTitle) {
        this.seatId = seatId;
        this.rowNr = rowNr;
        this.ticketDate = ticketDate;
        this.movieTitle = movieTitle;
    }

    public int getSeatId() {
        return seatId;
    }

    public int getRowNr() {
        return rowNr;
    }

    public String getTicketDate() {
        return ticketDate;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    //Function to write tickets to file, for now it will not be used since I could not get it to work - C
    public void writeTicketToFileToAttachment(Ticket ticket) {

        try {
            FileWriter wt = new FileWriter(new File("test.txt"));
            int ticketnr = 0;

            wt.write("Your Order: " + "\r\n");
            do {

                wt.append("Movie title: " + ticket.ticketDate + ',' + " row number: " + ticket.rowNr + ',' + " Seat number: "
                        + ticket.seatId + '.' + "\r\n");
                wt.append("Your order number is " + "[insert order number from database here]");
                wt.write("------------------------------" + "\r\n");
                ticketnr++;
            } while (ticketnr != 1);


            System.out.println("Order is now written to " + "[Insert file name here]");
        } catch (IOException e) {
            System.out.println("Jeg støtte på denne fejl med at skrive fil " + e);

        }

    }

}
