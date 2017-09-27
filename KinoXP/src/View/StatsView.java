package View;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by hamza on 27-09-2017.
 */
public class StatsView {

    public void setPage(Stage primaryStage)
    {
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane, 450, 450);
        primaryStage.setScene(scene);
    }

    public Pane centerPane()
    {
        GridPane gridPane = new GridPane();
        return gridPane;
    }
}
