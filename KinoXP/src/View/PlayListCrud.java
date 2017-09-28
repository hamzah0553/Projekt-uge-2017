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

import java.util.ArrayList;

/**
 * Created by den udvalgte on 27-09-2017.
 */
public class PlayListCrud extends View
{
    PlayListCrudController controller;
    ChoiceBox<String> movies,times, hall;
    DatePicker date;
    ArrayList<Hall> halls;
    ArrayList<Movie> movieList;

    public PlayListCrud(Stage primaryStage)
                        //ArrayList<Hall> halls, ArrayList<Movie> movieList ){
    {
        super();
        this.controller = new PlayListCrudController(this);

        setScene(layout(),new Button("Gå væk!"),primaryStage);

        this.halls = halls;

        this.movieList = movieList;

    }

    public VBox layout (){

        VBox layout = new VBox(10);

        Label create = new Label("Create new playing of a movie!\nSelect movie");
        create.setStyle("-fx-font-weight: bold;");

        movies = new ChoiceBox<>();
        for (Movie m : movieList) {
            movies.getItems().add(m.getName());
        }

        Label chooseHall = new Label("Select which hall");


        hall = new ChoiceBox<>();
        for (Hall h : halls ) {
            hall.getItems().add(h.getHallID()+"");
        }

        Label time = new Label("Select date and time");

        date = new DatePicker();

        times = new ChoiceBox<>();
        times.getItems().addAll("09:00","12:30","16:00","19:30","23:00");

        Button save = new Button("Save");
        save.setOnAction(event -> {
            controller.createPlay();
        });


        layout.getChildren().addAll(create,movies,chooseHall,hall,date,time,times,save);

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

    public String getMovies()
    {
        return movies.getValue();
    }


    public String getTimes()
    {
        return times.getValue();
    }

    public String getDate()
    {
        return date.getValue().toString();
    }


}
