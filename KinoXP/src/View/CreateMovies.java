package View;

import DataAccessObject.DAOmovie;
import DataAccessObject.GetMoviesDAO;
import Models.TableInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class CreateMovies {

    private Button addMovieButton = new Button("Tilføj film!");
    private Button deleteMovieButton = new Button("Slet film!");

    private TextField theaterIDInput = new TextField();
    private TextField movieNameInput = new TextField();
    private TextField movieLength = new TextField();
    private TextField movieAge = new TextField();
    private TextField movieRun = new TextField();

    private DAOmovie daOmovie = new DAOmovie();

    private TableView tableView = new TableView();
    private Region leftRegion = new Region();
    private Region rightRegion = new Region();

    final ObservableList<TableInformation> data = FXCollections.observableArrayList();

//TODO: Clean this method up, if there's time
    public CreateMovies() {

        Stage window = new Stage();
        window.setTitle("CRUD Movies");


        BorderPane borderPane = new BorderPane();
        HBox hBoxTop = new HBox();
        HBox hBoxCenter = new HBox();
        HBox hboxBottom = new HBox();


        Label addMovieLabel = new Label();
        Label deleteLabel = new Label();

        borderPane.setMinSize(400, 500);

        addMovieLabel.setText("Add a movie to the database: ");
        theaterIDInput.setPromptText("Input theater ID ");



        movieNameInput.setPromptText("Input movie name");
        movieLength.setPromptText("Input movie length");
        movieAge.setPromptText("Input movie age");
        movieRun.setPromptText("input if movie runs(0/1)");


        hBoxTop.getChildren().addAll(addMovieLabel, theaterIDInput, movieNameInput,
                movieLength, movieAge, movieRun, addMovieButton);


        hBoxTop.setSpacing(10);
        hBoxTop.setPadding(new Insets(10, 10, 10, 10));

        deleteLabel.setText("Delete movie from database: ");
        TextField deleteField = new TextField();

        deleteField.setPromptText("Input ID of movie");
        hBoxCenter.setPadding(new Insets(10, 10, 10, 10));
        hBoxCenter.setSpacing(10);

        hBoxCenter.getChildren().addAll(deleteLabel, deleteField, deleteMovieButton);


        TableColumn movieIDCol = new TableColumn("Movie ID");
        movieIDCol.setMinWidth(200);
        movieIDCol.setCellValueFactory(new PropertyValueFactory<>("movieID"));

        TableColumn movieNameCol = new TableColumn("Movie title");
        movieNameCol.setMinWidth(200);
        movieNameCol.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));

        TableColumn movieViewable = new TableColumn("Is movie showing in theaters?");
        movieViewable.setMinWidth(300);
        movieViewable.setCellValueFactory(new PropertyValueFactory<>("isMovieRunning"));

        //sets the data
        GetMoviesDAO getMoviesDAO = new GetMoviesDAO();
        tableView.setItems(getMoviesDAO.getMovieData(data));


        tableView.getColumns().addAll(movieIDCol,movieNameCol, movieViewable);

        tableView.setMinSize(500, 10);
        hboxBottom.setPadding(new Insets(10,10,10,10));
        hboxBottom.setSpacing(10);

        //moves the tableview to the middle of the screen
        hboxBottom.setHgrow(leftRegion, Priority.ALWAYS);
        hboxBottom.setHgrow(rightRegion, Priority.ALWAYS);
        hboxBottom.getChildren().addAll(leftRegion,tableView,rightRegion);



        //sets the scenes
        borderPane.setTop(hBoxTop);
        borderPane.setCenter(hBoxCenter);
        borderPane.setBottom(hboxBottom);

        Scene scene = new Scene(borderPane);


        window.setScene(scene);
        window.show();


        // TODO: Move the actions on buttons to another class(?)
        addMovieButton.setOnAction(event -> {
            try {

                //adds the data inputted to the database
                daOmovie.Createmovie(movieNameInput.getText(),movieLength.getText(), Integer.parseInt(movieAge.getText()),
                        Integer.parseInt(movieRun.getText()), Integer.parseInt(theaterIDInput.getText()));

                //TODO: Generate the ID once a new movie gets added in the tableview.
                TableInformation entry = new TableInformation("", movieNameInput.getText(), "1");

                System.out.println("fine..");

                data.add(entry);
                tableView.refresh();


                //clears textfields
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


        //TODO: Update tableview, when movie gets deleted
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
