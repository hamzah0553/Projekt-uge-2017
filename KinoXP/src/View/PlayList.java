package View;

import DataAccessObject.PlaylistDAO;
import Models.Movie;
import Models.Play;
import System.View;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


/**
 * Created by den udvalgte on 26-09-2017.
 */
public class PlayList extends View {

    ArrayList<Play> plays;
    Stage stage;

    public PlayList(Movie movie, Stage primaryStage){
        stage = primaryStage;
        PlaylistDAO dao = new PlaylistDAO();

        ArrayList<Button> [] times = new ArrayList[7];


        Label[] days = new Label[7];
        Label [] dates = new Label[7];

        VBox[] dayAndDate = new VBox[7];

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(15,15,15,15));

        Label name = new Label(movie.getName() );
        GridPane.setConstraints(name,1,0);
        name.setStyle("-fx-font-weight: bold;");

        Label hall = new Label("Hall#");
        GridPane.setConstraints(hall,2,0);
        hall.setStyle("-fx-font-weight: bold;");

        String day;
        String today;

        for (int i = 0; i <days.length ; i++) {

            long dayTemp = TimeUnit.DAYS.toMillis(1);
            Date date = new Date(System.currentTimeMillis()+(dayTemp*i));
            today = getDate(i,date);


            day = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
            dayAndDate[i] = new VBox(5);
            dayAndDate[i].setAlignment(Pos.CENTER);
            days[i] = new Label(day);
            dates[i] = new Label(today);
            days[i].setStyle("-fx-font-weight: bold;");


            dayAndDate[i].getChildren().addAll(days[i],dates[i]);
            dayAndDate[i].setPadding(new Insets(10,10,10,10));

            GridPane.setConstraints(dayAndDate[i],i+1,2);

            times[i] = addPlays(today,gridPane,dao,movie);
        }

        for (int i = 0; i <times.length ; i++) {

            for (int j = 0; j < times[i].size(); j++) {
                GridPane.setConstraints(times[i].get(j), 1+i, 3 + j);
                //times[i].get(j).setPadding(new Insets(10,10,10,10));


                handleButton(times[i].get(j),movie);
            }
        }


        gridPane.getChildren().addAll(name,hall);
        gridPane.getChildren().addAll(dayAndDate);
        for (int i = 0; i < days.length ; i++) {

            gridPane.getChildren().addAll(times[i]);

        }


        setScene(gridPane, null, primaryStage);
    }



    private Button addSinglePlay(String time){

        Button add = new Button(time);

        return add;
    }

    private ArrayList<Button> addPlays(String date, GridPane gridPane, PlaylistDAO dao, Movie movie){

        plays = dao.getPlaylist();

        ArrayList<Button> buttons = new ArrayList<>();

        String today = date;
        for (Play p: plays) {

            if (p.getMovieName().equalsIgnoreCase(movie.getName())&&p.getDate().equals(today)){
                buttons.add(addSinglePlay(p.getTime()));
            }


        }

        return buttons;
    }


    public void handleButton(Button button, Movie movie){

        button.setOnAction(event -> {

            int theId=0;

            for (Play p : plays) {

                if (p.getMovie().getName().equalsIgnoreCase(movie.getName()) && p.getTime().equalsIgnoreCase(button.getText()) ){
                    theId= p.getId();

                }

            }
            Login login = new Login(stage);
            System.out.println(button.getText());
            System.out.println(movie.getName());
            System.out.println(theId);

        });


    }

    public String getDate(int i, Date date){
        String today;

        today = date.toString();
        today = today.substring(5,today.length());
        today = today.replace("-","/");
        return today.substring(today.length()-2,today.length()) + "/"+ today.substring(0,2);
    }
}
