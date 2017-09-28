package View;

import DataAccessObject.AddMovieDAO;
import Models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
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
            imageViews.get(i).setFitHeight(480);
            imageViews.get(i).setFitWidth(252);
        }

        gridPane.setHgap(25);
        gridPane.setVgap(15);


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

        pane.setHvalue(0.5);
        pane.setVvalue(0.5);

        return pane;
    }

    public FlowPane setBottom(Stage primaryStage)
    {
        FlowPane flowPane = new FlowPane();

        Button newPlay = new Button("Ny Spilletid");
        Button showSale = new Button("Stats for film");
        Button newMovie = new Button("Ny film");

        flowPane.setHgap(5.0);

        flowPane.getChildren().addAll(newPlay, showSale, newMovie);

        newPlay.setOnAction(event -> {
            PlayListCrud playListCrud = new PlayListCrud(primaryStage);
            playListCrud.setScene(playListCrud.layout(), null, primaryStage);
        });

        showSale.setOnAction(event -> {
            StatsView statsView = new StatsView();
            statsView.setScene(statsView.centerPane(), null,primaryStage);
        });

        newMovie.setOnAction(event -> {
            AddMovieDAO dao = new AddMovieDAO();
            dao.getMovies();
            CreateMovies createMovies = new CreateMovies(primaryStage);
        });


        return flowPane;
    }
}
