package View;

import Controller.StatController;
import Models.TableInformation_Stats;
import System.View;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

/**
 * Created by hamza on 27-09-2017.
 */
public class StatsView extends View {

    public void setPage(Stage primaryStage)
    {

    }

    final ObservableList<TableInformation_Stats> myData = FXCollections.observableArrayList();
    StatController statController = new StatController();
    public Pane centerPane()
    {
        TableView table = new TableView();
        TableColumn movie = new TableColumn("Movies");
        movie.setCellValueFactory(new PropertyValueFactory<>("Movie_title"));
        TableColumn sales = new TableColumn("Sales");
        movie.setCellValueFactory(new PropertyValueFactory<>("Movie_sales"));

        String test = "Test";


        table.getColumns().addAll(movie, sales);
        TableRow tableRow = new TableRow();
        tableRow.setText(test);


        Pane pane = new Pane();
        pane.getChildren().addAll(table);

        pane.setPadding(new Insets(25, 25, 25, 25));
        statController.getStats(myData);
        return pane;
    }
}
