package View;

import DataAccessObject.FetchMovieListDAO;
import Models.Movie;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
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

    public Pane theWindow()    {


        GridPane layout = new GridPane();
        window.setTitle("Movies");

        layout.setPadding(new Insets(2,10,10,10));
        layout.setVgap(8);
        layout.setHgap(8);


        Label head = new Label("Movies:");
        head.setFont(Font.font ("Verdana", 20));

        FetchMovieListDAO dao = new FetchMovieListDAO();
        ArrayList<Button> buttons = new ArrayList();
        ArrayList<Movie> movies = dao.getMovies();

        for (Movie m :
                 movies) {

            if (m.getRun()==1)
            buttons.add(new Button(m.getName()));
        }



        GridPane.setConstraints(head,0,0);


        Button button_return = new Button("Return");
        GridPane.setConstraints(button_return,0,1);

        button_return.setOnAction(e->window.close());

//        layout.getChildren().addAll();

        ScrollBar sc = new ScrollBar();
        sc.setMin(0);
        sc.setMax(100);
        sc.setValue(50);


        for (int i = 0; i < buttons.size() ; i++) {

            buttons.get(i).setMinHeight(160);
            buttons.get(i).setMaxHeight(160);
            buttons.get(i).setMinWidth(108);
            buttons.get(i).setMaxWidth(108);

            int vertical = i/3;
            int horizontal = i%3;

            GridPane.setConstraints(buttons.get(i), horizontal,vertical);

            layout.getChildren().addAll(buttons.get(i));
        }
        Pane pane = new Pane(layout);
      return pane;
    }}