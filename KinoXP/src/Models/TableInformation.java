package Models;

import javafx.beans.property.SimpleStringProperty;

public class TableInformation {

    private final SimpleStringProperty movieID;
    private final SimpleStringProperty movieTitle;
    private final SimpleStringProperty isMovieRunning;

    public TableInformation(String movieID, String movieTitle, String isMovieRunning) {
        this.movieID = new SimpleStringProperty(movieID);
        this.movieTitle = new SimpleStringProperty(movieTitle);
        this.isMovieRunning = new SimpleStringProperty(isMovieRunning);
    }

    //These getters are used in tableview, don't delete them, even if they're grey.
    public String getIsMovieRunning() {
        return isMovieRunning.get();
    }

    public SimpleStringProperty isMovieRunningProperty() {
        return isMovieRunning;
    }

    public String getMovieID() {
        return movieID.get();
    }

    public SimpleStringProperty movieIDProperty() {
        return movieID;
    }

    public String getMovieTitle() {
        return movieTitle.get();
    }

    public SimpleStringProperty movieTitleProperty() {
        return movieTitle;
    }
}
