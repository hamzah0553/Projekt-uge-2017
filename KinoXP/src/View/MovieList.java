package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import System.View;
/**
 * Created by bruger on 27-09-2017.
 */
public class MovieList extends View{

    
    Stage window;

    public MovieList() {

    }

    public void theWindow(String user)
    {
        GridPane layout = new GridPane();
        window.setTitle("user: " + user);

        layout.setPadding(new Insets(10,10,10,10));
        layout.setVgap(8);
        layout.setHgap(6);



        Label head = new Label("Movies");
        GridPane.setConstraints(head,0,1);

        Button button_return = new Button("Return");
        GridPane.setConstraints(button_return,0,1);

        button_return.setOnAction(e->window.close());

        layout.getChildren().addAll(head, button_return);

        Scene scene = new Scene(layout, 230, 100);
        window.setScene(scene);

       window.show();
    }}