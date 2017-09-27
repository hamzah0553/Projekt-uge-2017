package View;

import Models.Customer;
import Models.Movie;
import Models.Play;
import System.*;
import Controller.SearchController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;

import java.util.HashMap;


public class SearchView extends View
{
    private SearchController controller;

    //center
    private TableView tableView;

    //bottom
    private Button backButton;

    public SearchView(SearchController controller)
    {
        super();
        this.controller = controller;
    }

    public void setSearchView(String searchWord, Stage stage)
    {
        tableView = getReservationTable(searchWord);
        tableView.setPlaceholder(new Label("No reservations"));

        tableView.setOnMouseClicked(event ->
        {
            System.out.println("table item clicked: " + tableView.getSelectionModel().getSelectedItem());
        });

        backButton = new Button("Back");

        setScene(tableView,backButton, stage);
    }


    private TableView getReservationTable(String searchPhone)
    {
        TableView table = new TableView();
        table.setStyle("-fx-background-color: #ffffff");
        table.setEditable(false);

        TableColumn movieCol = new TableColumn("Movie title");
        movieCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashMap<String,String>, String>, ObservableValue>() {
            @Override
            public ObservableValue call(CellDataFeatures<HashMap<String,String>, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().get("movie_name"));
            }
        });
        movieCol.setMinWidth(50);

        TableColumn playTimeCol = new TableColumn("Playtime");
        playTimeCol.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<HashMap<String,String>, String>, ObservableValue>() {
            @Override
            public ObservableValue call(CellDataFeatures<HashMap<String,String>, String> param) {
                return new ReadOnlyObjectWrapper<>(param.getValue().get("start_date").toString());
            }
        });
        playTimeCol.setMinWidth(90);

        TableColumn reservationCol = new TableColumn("Reservation");
        reservationCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>("Show reservation"));
        reservationCol.setMinWidth(50);

        ObservableList<HashMap<String,String>> movieTimeList = FXCollections.observableArrayList(controller.getPlays(searchPhone));

        //set items and columns
        table.setItems(movieTimeList);
        table.getColumns().addAll(movieCol, playTimeCol, reservationCol);

        // setting column size to fit depending on column name
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }
}
