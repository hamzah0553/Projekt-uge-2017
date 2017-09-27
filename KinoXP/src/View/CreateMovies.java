package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class CreateMovies {

    private Button addMovieButton = new Button("Tilføj film!");
    private Button deleteMovieButton = new Button("Slet film!");

    private TextField theaterIDInput = new TextField();
    private TextField movieNameInput = new TextField();
    private TextField movieLength = new TextField();
    private TextField movieAge = new TextField();
    private TextField movieRun = new TextField();

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


        //clears form
        addMovieButton.setOnAction(event -> {
            theaterIDInput.clear();
            movieNameInput.clear();
            movieLength.clear();
            movieAge.clear();
            movieRun.clear();
        });


        deleteMovieButton.setOnAction(event -> {
            deleteField.clear();
        });




    }




}
