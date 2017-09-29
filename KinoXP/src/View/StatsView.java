package View;

import System.View;

import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.TableView;

/**
 * Created by hamza on 27-09-2017.
 */
public class StatsView extends View {

    public void setPage(Stage primaryStage)
    {

    }

    public Pane centerPane()
    {
        TableView table = new TableView();
        TableColumn movie = new TableColumn("Movies");
        TableColumn sales = new TableColumn("Sales");

        String test = "Test";


        table.getColumns().addAll(movie, sales);
        TableRow tableRow = new TableRow();
        tableRow.setText(test);

        Pane pane = new Pane();
        pane.getChildren().addAll(table);

        pane.setPadding(new Insets(25, 25, 25, 25));

        return pane;
    }
}
