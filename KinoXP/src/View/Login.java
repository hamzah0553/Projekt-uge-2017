package View;

import Controller.ReservationController;
import Models.Hall;
import Models.PlayTime;
import Models.Seat;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import System.View;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Login extends View
{
    Stage window;

    private ArrayList<Seat> seatsChosen = new ArrayList<Seat>();

    private int amountOfSeats = 0;

    private int amountOfReservedSeats;

    private int movie_playtime_id;

    private String movieName;

    private int hallID;

    private String startDate;

    private Text sæder = new Text("sæder ledige: " + amountOfReservedSeats + "/" + amountOfSeats);

    public Login(Stage primaryStage, int movie_playtime_id)
    {
        //visning af biograf pladser...
        this.window = primaryStage;

        this.movie_playtime_id = movie_playtime_id;

        window.setTitle("Reservation");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));

        layout.setVgap(8);
        layout.setHgap(10);

        layout.setGridLinesVisible(false);

        PlayTime playTime = new PlayTime( movie_playtime_id );

        this.hallID = playTime.getHallIDByPlayTime();

        Hall hall = new Hall(hallID);

        this.movieName = playTime.getMovieNameByPlayTime();

        this.startDate = playTime.getPlayStartDate();

        //a list of the type of seats...
        int rows = hall.getSeatsRow();
        int columnsPerRow = hall.getSeatsColumn();

        //2d array holding seats...
        Seat seats[][] = new Seat[rows][columnsPerRow];

        //set current row and column...
        int row = 1;
        int column = 1;


        //iterate through amount of seats...

        int amountOfSeats = hall.amountOfSeats();

        this.amountOfSeats = hall.amountOfSeats();
        this.amountOfReservedSeats = 0;


        for(int i = 1; amountOfSeats >= i; i++)
        {

            //System.out.println("row: " + row + " column: " + column);
            //-1 as array starts with zero index.

            seats[row - 1][column - 1] = new Seat(row - 1, column - 1, 1);
            Seat seat = seats[row - 1][column - 1];

            //add button...
            if(seat.isAvailable())
            {

                Button button = new Button();
                button.setPrefSize(40.0, 10.0);

                if(!seat.isReserved())
                {

                    button.setStyle("-fx-border-color: #2ecc71; -fx-border-width: 1px; -fx-background-color: #2ecc71");

                    button.setOnAction(e -> {

                        if(!seat.checkIfClicked())
                        {

                            seatsChosen.add(seat);
                            amountOfReservedSeats++;
                            sæder.setText("sæder ledige: " + amountOfReservedSeats + "/" + amountOfSeats);
                            button.setStyle("-fx-border-color: #e67e22; -fx-border-width: 1px; -fx-background-color: #e67e22");

                        } else {

                            //remove it from our arraylist..
                            for(int i1 = 0; seatsChosen.size() > i1; i1++)
                            {

                                if(seatsChosen.get(i1).getRow() == seat.getRow()
                                        && seatsChosen.get(i1).getColumn() == seat.getColumn())
                                {

                                    amountOfReservedSeats--;
                                    sæder.setText("sæder ledige: " + amountOfReservedSeats + "/" + amountOfSeats);
                                    seatsChosen.remove(i1);
                                    break;

                                }

                            }

                            //set button style..
                            button.setStyle("-fx-border-color: #2ecc71; -fx-border-width: 1px; -fx-background-color: #2ecc71");

                        }

                        seat.updateHasBeenClicked();

                    });

                } else {
                    amountOfReservedSeats++;
                    sæder.setText("sæder ledige: " + amountOfReservedSeats + "/" + amountOfSeats);
                    button.setStyle("-fx-border-color: #e74c3c; -fx-border-width: 1px; -fx-background-color: #e74c3c");
                }

                layout.setAlignment(Pos.CENTER);
                layout.add(button, column, row);

            }

            //if columnsPerRow == column, go to next row and reset column.
            if(columnsPerRow == column)
            {

                row++;
                column = 1;

            } else {

                column++;

            }

        }

        HBox hBox = new HBox(15);
        Button greenButton = new Button();
        greenButton.setPrefSize(40, 10);
        greenButton.setStyle("-fx-border-color: #2ecc71; -fx-border-width: 1px; -fx-background-color: #2ecc71");
        Text green = new Text("Ledig sæder");
        green.setStyle("-fx-font-family: sans-serif");

        Button redButton = new Button();
        redButton.setPrefSize(40, 10);
        redButton.setStyle("-fx-border-color: #e74c3c; -fx-border-width: 1px; -fx-background-color: #e74c3c");
        Text red = new Text("Optaget sæder");
        red.setStyle("-fx-font-family: sans-serif");

        Button yellowButton = new Button();
        yellowButton.setPrefSize(40, 10);
        yellowButton.setStyle("-fx-border-color: #e67e22; -fx-border-width: 1px; -fx-background-color: #e67e22");
        Text yellow = new Text("Valgte sæder");
        yellow.setStyle("-fx-font-family: sans-serif");


        hBox.getChildren().addAll(greenButton, green , redButton, red, yellowButton, yellow);
        hBox.setAlignment(Pos.CENTER);



        VBox vBox = new VBox(15);
        Line line1 = new Line(0, 50, 400, 50);

        vBox.getChildren().addAll(layout, line1, hBox);
        vBox.setAlignment(Pos.TOP_CENTER);

        BorderPane borderPane = new BorderPane(vBox, createTopPane(), null, createBottomPane(), null);

        setScene(borderPane, null, window);
        //end...
    }

    public BorderPane createTopPane()
    {

        Text title = new Text(movieName);
        title.setStyle("-fx-font-family: sans-serif");
        title.setStyle("-fx-font-size: 35px");

        Text playtime = new Text("Spille tider: " + startDate);
        playtime.setStyle("-fx-font-family: sans-serif");

        Text theater = new Text("Teater: " + hallID);
        theater.setStyle("-fx-font-family: sans-serif");

        Text biograf = new Text("Biograf: KinoXP");
        biograf.setStyle("-fx-font-family: sans-serif");


        sæder.setText("sæder ledige: " + amountOfReservedSeats + "/" + amountOfSeats);
        sæder.setStyle("-fx-font-family: sans-serif");

        GridPane leftPane = new GridPane();
        leftPane.add(playtime, 0, 0);
        leftPane.add(theater, 0, 1);
        leftPane.setPadding(new Insets(30.0, 25.0, 10.0, 0.0));

        GridPane rightPane = new GridPane();
        rightPane.setPadding(new Insets(30.0, 25.0, 10.0, 0.0));

        rightPane.add(biograf, 0, 0);
        rightPane.add(sæder, 0, 1);

        BorderPane borderPane = new BorderPane(null, title, rightPane, null, leftPane);
        borderPane.setPadding(new Insets(15.0, 10.0, 15.0, 25.0));

        return borderPane;

    }

    public GridPane createBottomPane()
    {
        HBox hBox = new HBox();
        Button buttonRes = new Button("Reserver billetter");
        //clicked on "reserver billetter"
        buttonRes.setOnAction(e -> {

            final Stage dialog = new Stage();

            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(window);
            VBox dialogVbox = new VBox(20);

            TextField phoneInput = new TextField();
            phoneInput.setPromptText("Telefon nr på kunden");

            TextField emailInput = new TextField();
            emailInput.setPromptText("Email på kunden");

            Label messageLabel = new Label();

            Button createReservationButton = new Button("Opret reservation");

            dialogVbox.getChildren().addAll(phoneInput, emailInput, createReservationButton, messageLabel);
            dialogVbox.setPadding(new Insets(25, 25, 0, 25));
            dialogVbox.setAlignment(Pos.CENTER);

            //clicked on the dialog create reservation...
            createReservationButton.setOnAction(event -> {
                ReservationController reservationController = new ReservationController();

                String responseMessage = reservationController.validateReservation(
                        Integer.parseInt(phoneInput.getText()), emailInput.getText());

                //validate..
                if(responseMessage.equals("OK"))
                {

                    reservationController.setSeatsChosen(seatsChosen);
                    reservationController.createReservation();
                    messageLabel.setText("Kundens billet er nu reserveret.");
                    dialogVbox.getChildren().removeAll(phoneInput, emailInput, createReservationButton);

                } else {
                    messageLabel.setText("Fejl: " + responseMessage);
                }
            });

            Scene dialogScene = new Scene(dialogVbox, 300, 200);
            dialog.setScene(dialogScene);
            dialog.show();
        });

        GridPane pane = new GridPane();

        pane.add(buttonRes, 0,0);
        pane.setAlignment(Pos.BASELINE_RIGHT);
        pane.setPadding(new Insets(0.0, 25.0, 15.0, 0.0));
        return pane;
    }
}
