package View;

        import DataAccessObject.FetchMovieListDAO;
        import javafx.geometry.Insets;
        import javafx.scene.Scene;
        import javafx.scene.control.Button;
        import javafx.scene.control.Label;
        import javafx.scene.layout.*;
        import javafx.stage.Stage;

import DataAccessObject.LoginDAO;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import System.View;
import System.Controller;
import System.Model;

import java.sql.SQLException;

public class TrueLogin
{
    Stage window;

    public TrueLogin(Stage primaryStage){

        LoginDAO loginDAO = new LoginDAO();
        MovieList movieList = new MovieList(primaryStage);
        this.window = primaryStage;

        window.setTitle("Window title");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(10,10,10,10));

        layout.setVgap(8);
        layout.setHgap(10);

        Label name = new Label("Username");
        GridPane.setConstraints(name,0,0);

        TextField nameInput = new TextField();
        nameInput.setPromptText("username");
        GridPane.setConstraints(nameInput,1,0);

        Label password = new Label("Password");
        GridPane.setConstraints(password,0,1);

        PasswordField passInput = new PasswordField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput,1,1);
        Button button = new Button("Login");
        button.setOnAction(event -> {
            //TODO: move to model later
            try
            {
                if (loginDAO.validate(nameInput.getText(), passInput.getText()))
                {
                    View view = new View(new Controller(new Model()));
                    view.setScene(movieList.theWindow(),setBottom(primaryStage) , primaryStage);
                }
            }catch (SQLException e) {
                System.err.println("FAIL");
            }
        });

        GridPane.setConstraints(button,1,2);

        layout.getChildren().addAll(name,nameInput,password,passInput,button);

        layout.setGridLinesVisible(false);

        Scene scene = new Scene(layout,250, 250);
        window.setScene(scene);
        window.show();

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
            FetchMovieListDAO dao = new FetchMovieListDAO();
            dao.getMovies();
            CreateMovies createMovies = new CreateMovies(primaryStage);
        });


        return flowPane;
    }
}
