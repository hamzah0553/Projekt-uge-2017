package View;

import System.*;
import Controller.SearchController;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.scene.control.TableColumn.CellDataFeatures;

import java.util.ArrayList;
import java.util.HashMap;


public class SearchView extends View
{
    private SearchController controller;

    //center
    private TableView tableView;

    private String orderID;
    private boolean itemSelected = false;

    public SearchView(SearchController controller)
    {
        super();
        this.controller = controller;
    }

    public void setSearchView(String searchWord, Stage newStage, Stage primaryStage)
    {
        tableView = getReservationTable(searchWord);
        tableView.setPlaceholder(new Label("No reservations"));

        Button deleteButton = new Button("Slet");

        deleteButton.setOnAction(event -> {
            if(itemSelected) {
                MovieList view = new MovieList(primaryStage);
                view.setScene(view.theWindow(), view.setBottom(primaryStage) , primaryStage);
                controller.deleteOrder(orderID);

            }

        });

        newStage.close();
        setScene(tableView, deleteButton, primaryStage);

        tableView.setOnMouseClicked(event ->
        {
            if(event.getClickCount() == 1)
            {
                HashMap<String, String> item = (HashMap<String, String>) tableView.getSelectionModel().getSelectedItem();
                setOrderID(item.get("order_id"));
                itemSelected = true;
            }

            if(event.getClickCount() == 2)
            {
                System.out.println("table item clicked: " + tableView.getSelectionModel().getSelectedItem());

                //if(tableView.getSelectionModel().getSelectedItem() instanceof HashMap) System.out.println("LOOOOOOOOL");

                HashMap<String, String> item = (HashMap<String, String>) tableView.getSelectionModel().getSelectedItem();

                Button closeButton = new Button("OK");

                closeButton.setOnAction(event1 -> {
                    newStage.close();
                    MovieList view = new MovieList(primaryStage);
                    view.setScene(view.theWindow(), view.setBottom(primaryStage) , primaryStage);
                });

                GridPane gridPane = new GridPane();
                gridPane.add(new Text("Billet reservation info"), 0, 0);
                gridPane.add(new Label("Telefon nr:"), 0, 1);
                gridPane.add(new Label(item.get(("customer_phonenumber"))),1,1);

                gridPane.add(new Label("Email:"), 0, 2);
                gridPane.add(new Label(item.get("customer_email")), 1, 2);

                gridPane.add(new Label("Film:"), 0, 3);
                gridPane.add(new Label(item.get("movie_name")), 1, 3);

                gridPane.add(new Label("Tid:"), 0, 4);
                gridPane.add(new Label(item.get("start_date")), 1, 4);

                gridPane.add(new Label("Sal:"), 0, 5);
                gridPane.add(new Label(item.get("hall_name")), 1, 5);

                gridPane.add(new Label("Sæderække:"), 0, 6);
                gridPane.add(new Label(item.get("seat_row")), 1, 6);

                gridPane.add(new Label("Sædenr.:"), 0, 7);
                gridPane.add(new Label(item.get("seat_column")), 1, 7);

                gridPane.add(new Label("Pris:"), 0, 8);
                gridPane.add(new Label(item.get("order_price")), 1, 8);

                gridPane.add(closeButton, 0, 9);

                gridPane.setAlignment(Pos.CENTER);
                gridPane.setVgap(20);
                gridPane.setHgap(20);

                Scene ticketInfo = new Scene(gridPane, 300, 400, Color.WHITE);
                primaryStage.setScene(ticketInfo);
            }

        });
    }


    private TableView getReservationTable(String searchPhone)
    {
        TableView table = new TableView();
        table.setStyle("-fx-background-color: #ffffff");
        table.setEditable(false);

        TableColumn movieCol = new TableColumn("Film");
        movieCol.setCellValueFactory((Callback<CellDataFeatures<HashMap<String, String>, String>, ObservableValue>)
                param -> new ReadOnlyObjectWrapper<>(param.getValue().get("movie_name")));
        movieCol.setMinWidth(50);

        TableColumn playTimeCol = new TableColumn("Spilletid");
        playTimeCol.setCellValueFactory((Callback<CellDataFeatures<HashMap<String, String>, String>, ObservableValue>)
                param -> new ReadOnlyObjectWrapper<>(param.getValue().get("start_date").toString()));
        playTimeCol.setMinWidth(90);

        TableColumn reservationCol = new TableColumn("Reservation");
        reservationCol.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>("Vis oplysninger"));
        reservationCol.setMinWidth(50);

        ArrayList<HashMap<String, String>> list = controller.getPlays(searchPhone);
        for (int i = 0; i < list.size(); i++)
        {
            if(list.get(i).get("deleted").equalsIgnoreCase("1")) list = new ArrayList<HashMap<String, String>>();
        }
        ObservableList<HashMap<String,String>> movieTimeList = FXCollections.observableArrayList(list);

        //set items and columns
        table.setItems(movieTimeList);
        table.getColumns().addAll(movieCol, playTimeCol, reservationCol);

        // setting column size to fit depending on column name
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        return table;
    }

    public void setOrderID(String orderID)
    {
        this.orderID = orderID;
    }
}
