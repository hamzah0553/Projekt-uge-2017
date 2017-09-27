package System;

import DataAccessObject.PlaylistDAO;
import Models.Movie;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import View.*;

import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(new Pane(), 300, 275));
        new TrueLogin(primaryStage);

        primaryStage.show();

        Movie movie = new Movie("Batman","120",15 ,new Date(),new Date(),4);

        //PlayList playList = new PlayList(movie);


    }


    public static void main(String[] args) {
        launch(args);
    }
}
