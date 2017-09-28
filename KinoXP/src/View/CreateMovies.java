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
import javafx.scene.layout.*;
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

    private TextField updateHall = new TextField();
    private TextField updateMovieName = new TextField();
    private TextField updateMovieLength = new TextField();
    private TextField updateMovieAge = new TextField();
    private TextField updateID = new TextField();

    private DAOmovie daOmovie = new DAOmovie();

    private TableView tableView = new TableView();
    private Region leftRegion = new Region();
    private Region rightRegion = new Region();

    private String movieRunning ="";
    GetMoviesDAO getMoviesDAO = new GetMoviesDAO();
    private int lastID =  Integer.parseInt(getMoviesDAO.getID());

    private Label updateLabel = new Label("Update movie information: ");

    private Button updateButton = new Button("Opdater film!");

    final ObservableList<TableInformation> data = FXCollections.observableArrayList();

    //TODO: Clean this method up, if there's time
    public CreateMovies(Stage primaryStage) {

        primaryStage.setTitle("CRUD Movies");


        BorderPane borderPane = new BorderPane();
        GridPane pane = new GridPane();

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
        updateID.setPromptText("Update ");

        deleteLabel.setText("Delete movie from database: ");
        TextField deleteField = new TextField();

        deleteField.setPromptText("Input ID of movie");

        updateID.setPromptText("ID of movie to update");
        updateHall.setPromptText("Update theater Hall");
        updateMovieAge.setPromptText("Update movie age");
        updateMovieLength.setPromptText("Update movie length");
        updateMovieName.setPromptText("Update movie name");

        pane.add(addMovieLabel, 0, 0);
        pane.add(theaterIDInput, 0, 1);
        pane.add(movieNameInput, 1, 1);
        pane.add(movieLength, 0, 2);
        pane.add(movieAge, 1, 2);
        pane.add(movieRun, 0, 3);
        pane.add(addMovieButton, 1, 3);

        pane.add(deleteLabel, 3, 0);
        pane.add(deleteField, 3, 1);
        pane.add(deleteMovieButton, 4, 1);

        pane.add(updateLabel, 3, 2);
        pane.add(updateID, 4,2);
        pane.add(updateHall,4,3);
        pane.add(updateMovieName,3,3);
        pane.add(updateMovieLength,3,4);
        pane.add(updateMovieAge,4,4);
        pane.add(updateButton, 5,4);

        pane.setPadding(new Insets(25, 25, 0, 10));
        pane.setHgap(15);
        pane.setVgap(25);


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
        borderPane.setTop(pane);
        borderPane.setBottom(hboxBottom);

        Scene scene = new Scene(borderPane);

        primaryStage.setScene(scene);
        primaryStage.show();




        // TODO: Move the actions on buttons to another class(?)
        addMovieButton.setOnAction(event -> {
            try {

                //adds the data inputted to the database
                daOmovie.Createmovie(movieNameInput.getText(),movieLength.getText(), Integer.parseInt(movieAge.getText()),
                        Integer.parseInt(movieRun.getText()), Integer.parseInt(theaterIDInput.getText()));



                //Shows "YES" or "NO" if you insert 0/1 in movie runs
                if (movieRun.getText().equalsIgnoreCase("0")) {
                    movieRunning = "No";
                }
                else
                    movieRunning = "Yes";

                //gets latest ID
                lastID++;

               String lastIDString =  String.valueOf(lastID);
                TableInformation entry = new TableInformation(lastIDString, movieNameInput.getText(), movieRunning);

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

        updateButton.setOnAction(event -> {
            try {
                daOmovie.Update(Integer.parseInt(updateID.getText()),Integer.parseInt(updateHall.getText())
                        ,updateMovieName.getText(),updateMovieLength.getText(),Integer.parseInt(updateMovieAge.getText()));
            }catch (SQLException e){
                e.printStackTrace();
            }
            updateID.clear();
            updateHall.clear();
            updateMovieName.clear();
            updateMovieLength.clear();
            updateMovieAge.clear();
        });
    }


}
