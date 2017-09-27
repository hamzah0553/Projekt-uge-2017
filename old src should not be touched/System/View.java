package System;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {

    protected Controller controller;

    private Scene mainScene;

    public View(Controller controller)
    {
        this.controller = controller;
    }

    public void setScene(Node top, Node center, Node bottom, Stage primaryStage)
    {
        final Label phoneLabel = new Label("Search phone number");
        final Menu menuOptions = new Menu("Options");
        final MenuItem logOut = new MenuItem("Log out");
        final Menu menuSearchPhone = new Menu();
        menuSearchPhone.setGraphic(phoneLabel);

        menuOptions.getItems().addAll(logOut);
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(menuOptions, menuSearchPhone);

        //actions for menu
        logOut.setOnAction(event->{
            System.out.println("LOG OUT NOW!"); //TODO LATER
        });

        phoneLabel.setOnMouseClicked(event->{
            final Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(primaryStage);

            Label searchLabel = new Label("Find reservation");
            Button searchButton = new Button("Search");
            searchButton.setStyle(
                    "-fx-background-color:#1fc714;"
            );

            VBox searchBox = new VBox(20);
            searchBox.setAlignment(Pos.CENTER);
            searchBox.getChildren().addAll(searchLabel, getSearchBar(), searchButton);

            Scene searchScene = new Scene(searchBox, 300, 200);
            stage.setScene(searchScene);
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
        searchBar.setPromptText("Enter phone...");

        searchBar.setStyle("-fx-alignment: center");

        /** limit to numbers only */
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    searchBar.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        /** limit to 8 digits */
        searchBar.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (searchBar.getText().length() > 8) {
                    String s = searchBar.getText().substring(0, 8);
                    searchBar.setText(s);
                }
            }
        });

        return searchBar;
    }

}
