package View;

import Controller.PlayListCrudController;
import Models.Hall;
import Models.Movie;
import System.View;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Date;
import java.util.ArrayList;

/**
 * Created by den udvalgte on 27-09-2017.
 */
public class PlayListCrud extends View
{
    PlayListCrudController controller;
    String selectedDate, selectedMovie, selectedTime;
    ArrayList<Hall> halls;
    ArrayList<Movie> movieList;



    public PlayListCrud(Stage primaryStage, ArrayList<Movie> movieList)
                        //ArrayList<Hall> halls, ArrayList<Movie> movieList ){
    {
        super();
        this.controller = new PlayListCrudController(this);

        this.movieList = movieList;

        Hall hall = new Hall();

        this.halls = hall.getHalls();

        setScene(layout(),new Button("Gå væk!"),primaryStage);




    }

    public VBox layout (){
        ChoiceBox<String> movies,times, hall;

        VBox layout = new VBox(10);

        Label create = new Label("Opret ny spilletid");
        Label mov = new Label("Vælg hvilken film");
        create.setStyle("-fx-font-weight: bold;");

        movies = new ChoiceBox<>();
        for (Movie m : movieList) {
            if (m.getRun() == 1)
            {
                movies.getItems().add(m.getName());
            }
        }

        Label chooseHall = new Label("Vælg hvilken hal");


        hall = new ChoiceBox<>();
        for (Hall h : halls ) {
            hall.getItems().add(h.getHallID()+"");
        }

        Label time = new Label("Vælg tid og dato");

       DatePicker date = new DatePicker();
        date.setOnAction(event ->{
            selectedDate = date.getValue().toString();
        });

        times = new ChoiceBox<>();
        times.getItems().addAll("09:00","12:30","16:00","19:30","23:00");

        Button save = new Button("Gem");
        save.setOnAction(event -> {
            selectedMovie = movies.getValue().toString();
            selectedTime = times.getValue().toString();
            controller.createPlay();
        });


        layout.getChildren().addAll(create,mov,movies,chooseHall,hall,date,time,times,save);

        layout.setAlignment(Pos.CENTER);
        return layout;

    }

    public ArrayList<Hall> getHalls()
    {
        return halls;
    }

    public ArrayList<Movie> getMovieList()
    {
        return movieList;
    }



    public String getDate()
    {
        return selectedDate;
    }


    public String getSelectedMovie()
    {
        return selectedMovie;
    }

    public String getSelectedTime()
    {
        return selectedTime;
    }



}
