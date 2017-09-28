package System;

import DataAccessObject.FetchMovieListDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import View.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("KINO XP");
        primaryStage.setScene(new Scene(new Pane(), 300, 275));
        new TrueLogin(primaryStage);

        primaryStage.show();
        FetchMovieListDAO dao = new FetchMovieListDAO();
        dao.getMovies();

        //Movie movie = new Movie("Batman","120",15 ,new Date(),new Date(),4);


        //PlayList playList = new PlayList(movie);



    }


    public static void main(String[] args) {
        launch(args);
    }
}
