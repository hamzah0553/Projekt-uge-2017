package View;

import DataAccessObject.FetchMovieListDAO;
import Models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import System.View;

import java.util.ArrayList;


/**
 * Created by bruger on 27-09-2017.
 */
public class MovieList extends View{
    Stage window;
    ArrayList<Movie> movieList;

    public MovieList(Stage primaryStage){
        super();
        this.window = primaryStage;
    }

    public ScrollPane theWindow()
    {

        FetchMovieListDAO dao = new FetchMovieListDAO();
        ArrayList<Movie> movies = dao.getMovies();

        GridPane gridPane = new GridPane();
        ArrayList<ImageView> imageViews = new ArrayList<>();
        ArrayList<Button> buttons = new ArrayList<>();

        System.out.println(movies.size());

        int count = 0;

        for (int i = 0; i < movies.size(); i++) {

            int t = i + 1;
            imageViews.add(new ImageView(new Image("/img/" + t  + ".jpg", true)));

            Button button = new Button(movies.get(i).getName());

            button.getStyleClass().add("linkButton");

            buttons.add(button);

            count++;
            imageViews.get(i).setFitHeight(380);
            imageViews.get(i).setFitWidth(230);

        }

        gridPane.setHgap(5);
        gridPane.setVgap(5);

        window.setTitle("Movies");
        movieList = movies;

        int row = 0;
        int column = 0;

        int buttonRow = 2;
        int buttonColumn = 0;

        for (int i = 0; i < movies.size(); i++)
        {
            gridPane.add(imageViews.get(i), column, row);

            column++;

            if(column == 3)
            {
                row = row + 3;
                column = 0;
            }


            gridPane.add(buttons.get(i), buttonColumn, buttonRow);

            buttonColumn++;

            if(buttonColumn == 3)
            {
                buttonRow = buttonRow + 3;
                buttonColumn = 0;
            }

            int finalI = i;
            buttons.get(i).setOnAction(event -> {

                PlayList playList = new PlayList(movies.get(finalI), window);
            });

        }

        gridPane.setAlignment(Pos.CENTER);

        gridPane.setStyle("-fx-background-color: white");

        ScrollPane pane = new ScrollPane(gridPane);

        pane.getStylesheets().add("css/style.css");

        pane.setStyle("-fx-background-color: white");


        pane.setPadding(new Insets(0, 0, 0, 0));

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
            PlayListCrud playListCrud = new PlayListCrud(primaryStage,movieList);
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