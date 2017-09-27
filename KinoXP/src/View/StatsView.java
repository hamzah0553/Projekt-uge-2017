package View;

import System.View;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 * Created by hamza on 27-09-2017.
 */
public class StatsView extends View {

    public void setPage(Stage primaryStage)
    {

    }

    public Pane centerPane()
    {
        GridPane gridPane = new GridPane();

        for (int i = 0; i < 10; i++)
        {
            gridPane.add(new Text("Movie"), 0, i);
            gridPane.add(new Text("25"), 1, i);
        }

        gridPane.setPadding(new Insets(25, 25, 25, 25));
        gridPane.setAlignment(Pos.CENTER);
        return gridPane;
    }
}
