package View;

        import DataAccessObject.DAOmovie;
        import DataAccessObject.FetchMovieListDAO;
        import DataAccessObject.GetMoviesDAO;
        import Models.Movie;
        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
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

        import java.awt.*;
        import java.sql.SQLException;
        import java.util.ArrayList;

public class TrueLogin
{
    Stage window;

    public TrueLogin(Stage primaryStage){

        LoginDAO loginDAO = new LoginDAO();


        MovieList movieList = new MovieList(primaryStage);
        this.window = primaryStage;

        window.setTitle("Login");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(30,10,10,10));

        layout.setVgap(8);
        layout.setHgap(5);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Brugernavn");

        nameInput.getStyleClass().add("input");

        GridPane.setConstraints(nameInput,0,0);


        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Adgangskode");
        GridPane.setConstraints(passInput,0,1);
        Button button = new Button("Login");
        button.setOnAction(event -> {
            //TODO: move to model later
            try
            {
                if (loginDAO.validate(nameInput.getText(), passInput.getText()))
                {
                    View view = new View(new Controller(new Model()));
                    view.setScene(movieList.theWindow(), movieList.setBottom(primaryStage) , primaryStage);
                }
            }catch (SQLException e) {
                System.err.println("FAIL");
            }
        });

        passInput.getStyleClass().add("input");
        button.getStyleClass().add("btn");
        button.getStyleClass().add("btn-xl");
        button.getStyleClass().add("full-width");

        button.setMaxWidth(Double.MAX_VALUE);

        GridPane.setConstraints(button,0,2);

        layout.getChildren().addAll(nameInput,passInput,button);

        layout.setStyle("-fx-background-color: white");

        layout.setAlignment(Pos.TOP_CENTER);

        layout.setGridLinesVisible(false);

        Scene scene = new Scene(layout,350, 230);


        scene.getStylesheets().add("css/style.css");

        window.setScene(scene);
        window.show();

    }


}
