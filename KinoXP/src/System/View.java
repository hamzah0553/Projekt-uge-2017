package System;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class View {

    public void setScene(Stage primararyStage)
    {
        Stage stage = primararyStage;
        Pane pane = new Pane();
        Scene scene = new Scene(pane, 400, 400);
        stage.setScene(scene);
        primararyStage.show();
    }
}
