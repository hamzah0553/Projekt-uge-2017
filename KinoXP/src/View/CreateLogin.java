package View;

import Controller.EmployeeController;
import Models.Employee;
import System.View;
import javafx.geometry.*;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.awt.*;

public class CreateLogin extends View
{

    private EmployeeController controller;

    public CreateLogin(EmployeeController controller){

        this.controller = controller;
    }

    public void setCreateLogin(Stage stage)
    {

        BorderPane borderPane = new BorderPane();

        GridPane gridPane = new GridPane();

        TextField username = new TextField();
        PasswordField password = new PasswordField();
        TextField name = new TextField();
        TextField theaterID = new TextField();
        TextField role = new TextField();

        gridPane.add(new Text("Opret medarbejder login"),0,0);

        gridPane.add(new Label("Brugernavn: "), 0,1);
        gridPane.add(username, 1,1);

        gridPane.add(new Label("Kodeord"), 0,2);
        gridPane.add(password, 1,2);

        gridPane.add(new Label("Navn: "),0,3);
        gridPane.add(name, 1,3);

        gridPane.add(new Label("Rolle: "),0,4);
        gridPane.add(role, 1,4);

        Button saveButton = new Button("Gem");

        saveButton.setOnAction(event -> {
            Employee employee = new Employee(0,     name.getText(), role.getText());
            controller.createLogin(username.getText(), password.getText(), employee);

        });

        gridPane.setAlignment(Pos.TOP_CENTER);
        gridPane.setPadding(new Insets(30,20,20,20));
        borderPane.setCenter(gridPane);
        setScene(borderPane, saveButton, stage);
    }
}
