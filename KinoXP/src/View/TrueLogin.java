package View;

import DataAccessObject.LoginDAO;
import System.Controller;
import System.Model;
import System.View;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.SQLException;

public class TrueLogin
{
    Stage window;
    public static MovieList movieList;

    ProgressBar prgbProgress;

    public TrueLogin(Stage primaryStage){

        LoginDAO loginDAO = new LoginDAO();


        movieList = new MovieList(primaryStage);
        this.window = primaryStage;

        window.setTitle("Login");

        GridPane layout = new GridPane();
        layout.setPadding(new Insets(30,10,10,10));

        layout.setVgap(8);
        layout.setHgap(5);

        TextField nameInput = new TextField();
        nameInput.setPromptText("Brugernavn");

        nameInput.getStyleClass().add("input");

        GridPane.setConstraints(nameInput,0,0);

        PasswordField passInput = new PasswordField();
        passInput.setPromptText("Adgangskode");
        GridPane.setConstraints(passInput,0,1);
        Button button = new Button("Login");

        Label labelError = new Label("");
        GridPane.setConstraints(labelError,0,3);

        passInput.setOnKeyReleased(event1 -> {

            try {
                if (event1.getCode() == KeyCode.ENTER && loginDAO.validate(nameInput.getText(),passInput.getText())){
                    View view = new View(new Controller(new Model()));
                    view.setAdmin(loginDAO.isAdmin(nameInput.getText(), passInput.getText()));
                    view.setScene(movieList.theWindow(), movieList.setBottom(primaryStage) , primaryStage);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
                System.out.println("lol");
            }
        });
        button.setOnAction((ActionEvent event) -> {
            //TODO: move to model later
            try
            {
                if (loginDAO.validate(nameInput.getText(), passInput.getText()))
                {
                    View view = new View(new Controller(new Model()));
                    view.setAdmin(loginDAO.isAdmin(nameInput.getText(), passInput.getText()));
                    view.setScene(movieList.theWindow(), movieList.setBottom(primaryStage) , primaryStage);
                }


            }catch (SQLException e) {
                System.err.println("FAIL");


            }
        });


        passInput.getStyleClass().add("input");
        button.getStyleClass().add("btn");
        button.getStyleClass().add("btn-xl");
        button.getStyleClass().add("full-width");

        button.setMaxWidth(Double.MAX_VALUE);

        GridPane.setConstraints(button,0,2);


        layout.getChildren().addAll(nameInput,passInput,button, labelError);

        layout.setStyle("-fx-background-color: white");

        layout.setAlignment(Pos.TOP_CENTER);

        layout.setGridLinesVisible(false);



        Scene scene = new Scene(layout,350, 230);
        scene.getStylesheets().add("css/style.css");



        window.setScene(scene);
        window.show();

        button.setOnAction(event -> {



            /*
            Platform.runLater(new Runnable()
            {


                @Override
                public void run()
                {

                    System.out.println("30");

                    VBox bx = new VBox();
                    bx.setAlignment(Pos.CENTER);

                    ProgressIndicator pi = new ProgressIndicator();
                    VBox box = new VBox(pi);
                    box.setAlignment(Pos.CENTER);

                    // Grey Background
                    bx.setDisable(true);
                    layout.getChildren().add(box);


                }

            }).start();*/


            scene.setCursor(Cursor.WAIT);


            //TODO: move to model later
            try
            {

                if (loginDAO.validate(nameInput.getText(), passInput.getText()))
                {

                    View view = new View(new Controller(new Model()));
                    view.setAdmin(loginDAO.isAdmin(nameInput.getText(), passInput.getText()));
                    view.setScene(movieList.theWindow(), movieList.setBottom(primaryStage) , primaryStage);

                    scene.setCursor(Cursor.HAND);


                }
                else {
                    labelError.setText("Wrong password.Please try again");}
            }catch (SQLException e) {
                System.err.println("FAIL");

                scene.setCursor(Cursor.HAND);




            }
        });



    }


 }



