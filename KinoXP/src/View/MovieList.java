package View;

import DataAccessObject.FetchMovieListDAO;
import Models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import System.View;

import java.util.ArrayList;


/**
 * Created by bruger on 27-09-2017.
 */
public class MovieList extends View{
    Stage window;

    public MovieList(Stage primaryStage){
        super();
        this.window = primaryStage;
    }

    public ScrollPane theWindow()
    {
        GridPane gridPane = new GridPane();
        ArrayList<ImageView> imageViews = new ArrayList<>();
        ArrayList<Button> buttons = new ArrayList<>();

        for (int i = 0; i < 10; i++)
        {
            imageViews.add(new ImageView(new Image("https://images-na.ssl-images-amazon.com/images/M/MV5BMTMxNTMwODM0NF5BMl5BanBnXkFtZTcwODAyMTk2Mw@@._V1_UY1200_CR90,0,630,1200_AL_.jpg")));
            buttons.add(new Button("Find spilletider"));
            imageViews.get(i).setFitHeight(455);
            imageViews.get(i).setFitWidth(238.875);
        }

        gridPane.setHgap(25);
        gridPane.setVgap(15);

        window.setTitle("Movies");

        FetchMovieListDAO dao = new FetchMovieListDAO();
        ArrayList<Movie> movies = dao.getMovies();

        for (Movie m :
                movies) {

            if (m.getRun()==1)
            {

            }
        }

        int row = 0;
        int column = 0;

        int movieRow = 1;
        int movieColumn = 0;

        for (int i = 0; i < 10; i++)
        {
            gridPane.add(imageViews.get(i), column, row);

            column++;

            if(column == 3)
            {
                row = row + 2;
                column = 0;
            }

            gridPane.add(buttons.get(i), movieColumn, movieRow);

            movieColumn++;

            if(movieColumn == 3)
            {
                movieRow = movieRow + 2;
                movieColumn = 0;
            }
            buttons.get(i).setOnAction(event -> {
                PlayList playList = new PlayList(new Movie(1, "Batman", "120", 25, 1), window);
            });
        }

        gridPane.setAlignment(Pos.CENTER);


        ScrollPane pane = new ScrollPane(gridPane);

        pane.setPadding(new Insets(25, 0, 25, 0));


        pane.setFitToHeight(true);
        pane.setFitToWidth(true);


        return pane;
    }

    public FlowPane setBottom(Stage primaryStage)
    {
        FlowPane flowPane = new FlowPane();

        Button newPlay = new Button("Opret Spilletid");
        Button showSale = new Button("Statistikker");
        Button newMovie = new Button("Opret film");

        newPlay.getStyleClass().add("btn");
        newPlay.getStyleClass().add("non");



        showSale.getStyleClass().add("btn");
        showSale.getStyleClass().add("non");

        newMovie.getStyleClass().add("btn");
        newMovie.getStyleClass().add("non");

        flowPane.setAlignment(Pos.CENTER);

        //flowPane.setStyle("-fx-background-color: white");

        flowPane.getStyleClass().add("bottomPane");

        flowPane.setHgap(5.0);

        flowPane.getStylesheets().add("css/style.css");

        flowPane.getChildren().addAll(newPlay, showSale, newMovie);

        newPlay.setOnAction(event ->
        {
            PlayListCrud playListCrud = new PlayListCrud(primaryStage);
            playListCrud.setScene(playListCrud.layout(), null, primaryStage);
        });

        showSale.setOnAction(event ->
        {
            StatsView statsView = new StatsView();
            statsView.setScene(statsView.centerPane(), null, primaryStage);
        });

        newMovie.setOnAction(event ->
        {
            FetchMovieListDAO dao = new FetchMovieListDAO();
            dao.getMovies();
            CreateMovies createMovies = new CreateMovies(primaryStage);
        });


        return flowPane;
    }
}