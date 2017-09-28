package System;

import DataAccessObject.FetchMovieListDAO;
import Models.Movie;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import Controller.SearchController;
import View.MovieList;
import View.SearchView;
import View.TrueLogin;
import View.PlayListCrud;
import View.CreateMovies;
import View.StatsView;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class View {

    View previousWindow;

    protected Controller controller;

    private Scene mainScene;
    private boolean adminLogIn;
    MovieList view;

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
        BorderPane borderPane = new BorderPane();
        FlowPane flowPane = new FlowPane();

        final Label backLabel = new Label();
        Image backIMG = new Image("/img/back.png");
        ImageView backView = new ImageView(backIMG);

        backView.setFitWidth(26);
        backView.setFitHeight(26);

        backLabel.setGraphic(backView);

        final Label movieMovie = new Label("Forestillinger");

        final Label phoneLabel = new Label("Find reservation");

        final Menu menuOptions = new Menu("Indstillinger");
        final MenuItem logOut = new MenuItem("Log ud");




        final Menu menuBack = new Menu();
        menuBack.setGraphic(backLabel);

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
            menuBar.getMenus().addAll(menuMovie, menuOptions, menuSearchPhone, menuAdmin);
        }else
        {
            menuBar.getMenus().addAll(menuMovie, menuOptions, menuSearchPhone);
        }

        MenuBar barBack = new MenuBar(menuBack);


        borderPane.setStyle("-fx-background-color: white");

        borderPane.setCenter(menuBar);
        borderPane.setLeft(barBack);

        borderPane.getStyleClass().add("headerPane");
        borderPane.setAlignment(menuBar, Pos.CENTER);

        barBack.getStyleClass().add("menu");
        menuBar.getStyleClass().add("menu");


        //...

        menuBack.setStyle("-fx-background-color: #fff !important; -fx-cursor: pointer;");


        menuBack.getStyleClass().add("icon");
        backLabel.getStyleClass().add("icon");

        System.out.println(menuBack.getStyleClass());

        //actions for menu
        backLabel.setOnMouseClicked(event -> {
            MovieList view = new MovieList(primaryStage);
            view.setScene(view.theWindow(), view.setBottom(primaryStage) , primaryStage);
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
                new SearchView(new SearchController(new Model())).setSearchView(searchBar.getText(), stage, primaryStage);
            });

            Scene searchScene = new Scene(searchBox, 300, 200, Color.WHITE);
            stage.setScene(searchScene);
            searchScene.getStylesheets().add("css/style.css");
            stage.show();

        });

        //set layout
        BorderPane layout = new BorderPane();

        layout.setTop(borderPane);



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

        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getWidth() - 1000) / 2);
        primaryStage.setY((screenBounds.getHeight() - 700) / 2);

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

    public FlowPane setBottom(Stage primaryStage)
    {
        FlowPane flowPane = new FlowPane();

        Button newPlay = new Button("Opret Spilletid");
        Button showSale = new Button("Statistikker");
        Button newMovie = new Button("Opret film");
        Button back = new Button("back");


        newPlay.getStyleClass().add("btn");
        newPlay.getStyleClass().add("non");



        showSale.getStyleClass().add("btn");
        showSale.getStyleClass().add("non");

        newMovie.getStyleClass().add("btn");
        newMovie.getStyleClass().add("non");

        flowPane.setAlignment(Pos.CENTER);

        //flowPane.setStyle("-fx-background-color: white");

        flowPane.getStyleClass().add("bottomPane");

        flowPane.setHgap(5.0);

        flowPane.getStylesheets().add("css/style.css");

        flowPane.getChildren().addAll(newPlay, showSale, newMovie);


        newPlay.setOnAction(event ->
        {
            PlayListCrud playListCrud = new PlayListCrud(primaryStage, view.getMovieList());
            playListCrud.setScene(playListCrud.layout(), null, primaryStage);
        });

        showSale.setOnAction(event ->
        {
            StatsView statsView = new StatsView();
            statsView.setScene(statsView.centerPane(), null, primaryStage);
        });

        newMovie.setOnAction(event ->
        {
            FetchMovieListDAO dao = new FetchMovieListDAO();
            dao.getMovies();
            CreateMovies createMovies = new CreateMovies(primaryStage);
        });


        return flowPane;
    }

}
