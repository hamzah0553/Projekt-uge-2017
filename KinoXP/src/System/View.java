package System;

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

    protected Controller controller;

    private Scene mainScene;
    private SearchView searchView;

    public View(Controller controller)
    {
        this.controller = controller;
    }

    public View (){}

    public void setScene(Node center, Node bottom, Stage primaryStage)
    {
        final Label backLabel = new Label("Tilbage");
        final Label phoneLabel = new Label("Søg telefon nr.");
        final Menu menuOptions = new Menu("Indstillinger");
        final MenuItem logOut = new MenuItem("Log ud");

        final Menu menuBack = new Menu();
        menuBack.setGraphic(backLabel);
        final Menu menuSearchPhone = new Menu();
        menuSearchPhone.setGraphic(phoneLabel);

        menuOptions.getItems().addAll(logOut);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuBack, menuOptions, menuSearchPhone);

        //actions for menu
        backLabel.setOnMouseClicked(event -> {
            //TODO: how to back?
        });

        logOut.setOnAction(event->{
            Platform.exit();
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
                if(searchView == null)
                {
                    new SearchView(new SearchController(new Model())).setSearchView(searchBar.getText(), stage);
                }
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
