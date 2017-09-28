package System;

import Models.Movie;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;


import View.*;
import Controller.*;

public class View {

    View previousWindow;

    protected Controller controller;

    private Scene mainScene;
    private boolean adminLogIn;

    public View(Controller controller)
    {
        this.controller = controller;
    }

    public View (){//View previousWindow){
      //  this.previousWindow = previousWindow;
    }

    public void setAdmin(boolean adminLogIn)
    {
        this.adminLogIn = adminLogIn;
    }

    public void setScene(Node center, Node bottom, Stage primaryStage)
    {


        final Label backLabel = new Label("Tilbage");

        final Label movieMovie = new Label("Forestillinger");

        final Label phoneLabel = new Label("Søg telefon nr.");
        final Menu menuOptions = new Menu("Indstillinger");
        final MenuItem logOut = new MenuItem("Log ud");

        final Menu menuBack = new Menu();
        menuBack.setGraphic(backLabel);

       // Imag image = new Image(getClass().getResourceAsStream("labels.jpg"));

        final Menu menuMovie = new Menu();
        menuMovie.setGraphic(movieMovie);


        final Menu menuSearchPhone = new Menu();
        menuSearchPhone.setGraphic(phoneLabel);

        menuOptions.getItems().addAll(logOut);
        MenuBar menuBar = new MenuBar();

        if(adminLogIn){
            Menu menuAdmin = new Menu("Admin");
            MenuItem createUser = new MenuItem("Opret login");
            MenuItem manageUser = new MenuItem("Se medarbejder liste");

            //actions for items
            createUser.setOnAction(event -> {
                
            });

            menuAdmin.getItems().addAll(createUser, manageUser);
            menuBar.getMenus().addAll(menuBack, menuMovie, menuOptions, menuSearchPhone, menuAdmin);
        }else
        {
            menuBar.getMenus().addAll(menuBack, menuMovie, menuOptions, menuSearchPhone);
        }

        menuBar.getStyleClass().add("menu");

        backLabel.getStyleClass().add("mlabel");

        //actions for menu
        backLabel.setOnMouseClicked(event -> {
            MovieList view = new MovieList(primaryStage);
            view.setScene(view.theWindow(), view.setBottom(primaryStage) , primaryStage);            //TODO: how to back?
        });

        logOut.setOnAction(event->{
            primaryStage.close();
            Stage newStage = new Stage();
            new TrueLogin(newStage);
        });

        phoneLabel.setOnMouseClicked(event->
        {
            final Stage stage = new Stage();

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(primaryStage);

            Label searchLabel = new Label("Indtast telefon nr:");

            Button searchButton = new Button("Søg");
            searchButton.getStyleClass().add("btn");
            searchButton.getStyleClass().add("btn-xl");
            searchButton.getStyleClass().add("full-width");
            searchButton.setMaxWidth(Double.MAX_VALUE);

            TextField searchBar = getSearchBar();

            searchBar.setMaxWidth(Double.MAX_VALUE);

            VBox searchBox = new VBox(20);

            searchBox.setStyle("-fx-background-color: white");

            searchBox.setPadding(new Insets(30,10,10,10));

            searchBox.setAlignment(Pos.CENTER);
            searchBox.getChildren().addAll(searchLabel, searchBar, searchButton);

            searchButton.setOnAction(event1 -> {
                new SearchView(new SearchController(new Model())).setSearchView(searchBar.getText(), stage);
            });

            Scene searchScene = new Scene(searchBox, 300, 200, Color.WHITE);
            stage.setScene(searchScene);
            searchScene.getStylesheets().add("css/style.css");
            stage.show();

        });

        //set layout
        BorderPane layout = new BorderPane();


        layout.setTop(menuBar);



        layout.setCenter(center);
        layout.setBottom(bottom);

        StackPane root = new StackPane();
        if(!root.getChildren().contains(layout)) {
            root.getChildren().addAll(layout);
        }

        //view has one scene only
        if(mainScene == null)
        {
            mainScene = new Scene(root, 1000, 700, Color.WHITE);
        }

        //scene within stage
        primaryStage.setTitle("KINO XP");

        mainScene.getStylesheets().add("css/style.css");


        primaryStage.setMinHeight(700);
        primaryStage.setMinWidth(1000);
        primaryStage.setScene(mainScene);
        primaryStage.show();
    }

    private TextField getSearchBar()
    {
        TextField searchBar = new TextField();
        searchBar.setMaxWidth(145);
        searchBar.setPrefHeight(45);
        searchBar.setPromptText("Telefon nummer..");

        searchBar.setStyle("-fx-alignment: center");

        /** limit to numbers only */
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                searchBar.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        /** limit to 8 digits */
        searchBar.textProperty().addListener((ov, oldValue, newValue) -> {
            if (searchBar.getText().length() > 8) {
                String s = searchBar.getText().substring(0, 8);
                searchBar.setText(s);
            }
        });

        return searchBar;
    }

}
