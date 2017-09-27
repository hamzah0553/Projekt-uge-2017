package View;

import Controller.PlayListCrudController;
import System.View;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by den udvalgte on 27-09-2017.
 */
public class PlayListCrud extends View
{
    PlayListCrudController controller;

    public PlayListCrud(Stage primaryStage ){
        super();
        this.controller = new PlayListCrudController(this);

        setScene(layout(),new Button("Gå væk!"),primaryStage);

    }

    public VBox layout (){

        VBox layout = new VBox(10);


        return layout;

    }



}
