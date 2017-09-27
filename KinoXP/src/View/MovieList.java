package View;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import System.View;

import java.util.ArrayList;


/**
 * Created by bruger on 27-09-2017.
 */
public class MovieList extends View{
    Stage window;

    public MovieList(Stage primaryStage){
        super();
        this.window = primaryStage;
    }

    public void theWindow()    {


        GridPane layout = new GridPane();
        window.setTitle("Movies");

        layout.setPadding(new Insets(2,10,10,10));
        layout.setVgap(8);
        layout.setHgap(8);


        Label head = new Label("Movies:");
        head.setFont(Font.font ("Verdana", 20));

        ArrayList<Button> moviesArraylist = new ArrayList();
        moviesArraylist.add(new Button("batman1"));
        moviesArraylist.add(new Button("batman2"));
        moviesArraylist.add(new Button("batman3"));
        moviesArraylist.add(new Button("batman4"));
        moviesArraylist.add(new Button("batman5"));
        moviesArraylist.add(new Button("batman6"));
        moviesArraylist.add(new Button("batman7"));
        moviesArraylist.add(new Button("batwoman"));
        moviesArraylist.add(new Button("batman8"));
        moviesArraylist.add(new Button("batman9"));
        moviesArraylist.add(new Button("batman10"));
        moviesArraylist.add(new Button("batman11"));
        moviesArraylist.add(new Button("batman12"));
        moviesArraylist.add(new Button("batman13"));
        moviesArraylist.add(new Button("batman14"));
        moviesArraylist.add(new Button("superman"));
        moviesArraylist.add(new Button("batman VS spuerman"));
        moviesArraylist.add(new Button("the man"));



        GridPane.setConstraints(head,0,0);


        Button button_return = new Button("Return");
        GridPane.setConstraints(button_return,0,1);

        button_return.setOnAction(e->window.close());

//        layout.getChildren().addAll();

        ScrollBar sc = new ScrollBar();
        sc.setMin(0);
        sc.setMax(100);
        sc.setValue(50);


        for (int i = 0; i < moviesArraylist.size() ; i++) {

            moviesArraylist.get(i).setMinHeight(160);
            moviesArraylist.get(i).setMaxHeight(160);
            moviesArraylist.get(i).setMinWidth(108);
            moviesArraylist.get(i).setMaxWidth(108);

            int vertical = i/3;
            int horizontal = i%3;

            GridPane.setConstraints(moviesArraylist.get(i), horizontal,vertical);

            layout.getChildren().addAll(moviesArraylist.get(i));
        }


        Scene scene = new Scene(layout, 400, 500);
        window.setScene(scene);

       window.show();
    }}