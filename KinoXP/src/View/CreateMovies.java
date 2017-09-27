package View;

import DataAccessObject.DAOmovie;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;

public class CreateMovies {

    private Button addMovieButton = new Button("Tilføj film!");
    private Button deleteMovieButton = new Button("Slet film!");

    private TextField theaterIDInput = new TextField();
    private TextField movieNameInput = new TextField();
    private TextField movieLength = new TextField();
    private TextField movieAge = new TextField();
    private TextField movieRun = new TextField();

     DAOmovie daOmovie = new DAOmovie();


    public CreateMovies() {

        Stage window = new Stage();
        window.setTitle("CRUD Movies");


        BorderPane borderPane = new BorderPane();
        HBox hBoxTop = new HBox();
        HBox hBoxCenter = new HBox();


        Label addMovieLabel = new Label();
        Label deleteLabel = new Label();

        borderPane.setMinSize(500, 500);

        addMovieLabel.setText("Add a movie to the database: ");
        theaterIDInput.setPromptText("Input theater ID ");



        movieNameInput.setPromptText("Input movie name");
        movieLength.setPromptText("Input movie length");
        movieAge.setPromptText("Input movie age");
        movieRun.setPromptText("input if movie runs");


        hBoxTop.getChildren().addAll(addMovieLabel, theaterIDInput,movieNameInput,
                movieLength,movieAge,movieRun, addMovieButton);


        hBoxTop.setSpacing(10);
        hBoxTop.setPadding(new Insets(10,10,10,10));

        deleteLabel.setText("Delete movie from database: ");
        TextField deleteField = new TextField();

        deleteField.setPromptText("Input ID of movie");
        hBoxCenter.setPadding(new Insets(10,10,10,10));
        hBoxCenter.setSpacing(10);

        hBoxCenter.getChildren().addAll(deleteLabel,deleteField, deleteMovieButton);


        borderPane.setTop(hBoxTop);
        borderPane.setCenter(hBoxCenter);

        Scene scene = new Scene(borderPane);


        window.setScene(scene);
        window.show();


        addMovieButton.setOnAction(event -> {
            try {

                daOmovie.Createmovie(movieNameInput.getText(),movieLength.getText(), Integer.parseInt(movieAge.getText()), Integer.parseInt(movieRun.getText()), Integer.parseInt(theaterIDInput.getText()));
                System.out.println("fine..");

                theaterIDInput.clear();
                movieNameInput.clear();
                movieLength.clear();
                movieAge.clear();
                movieRun.clear();

            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("error");
            }
        });

        //clears form
        /*
        addMovieButton.setOnAction(event -> {

        });*/


        deleteMovieButton.setOnAction((ActionEvent event) -> {

            try {
                daOmovie.Delete(Integer.parseInt(deleteField.getText()));
            } catch (SQLException e) {
                e.printStackTrace();
            }

            deleteField.clear();
        });




    }




}
