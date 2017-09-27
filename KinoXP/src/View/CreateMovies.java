package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CreateMovies {

    private Button addMovieButton = new Button("Tilføj film!");

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
        VBox vBox = new VBox();




        Label addMovieLabel = new Label();
        Label deleteLabel = new Label();

        borderPane.setMinSize(1500, 700);

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


        borderPane.setTop(hBoxTop);

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


        TableView tableView = new TableView();



        hBoxCenter.getChildren().add(tableView);


    }




}
