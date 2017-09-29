package Models;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by jakob on 29-09-2017.
 */
public class TableInformation_Stats {


    private final SimpleStringProperty movie_id;
    private final SimpleStringProperty movie_sales;


    public TableInformation_Stats(String movie_id, String movie_sales) {
        this.movie_id = new SimpleStringProperty(movie_id);
        this.movie_sales = new SimpleStringProperty(movie_sales);
    }

    public String getMovie_id() {
        return movie_id.get();
    }

    public SimpleStringProperty movie_idProperty() {
        return movie_id;
    }

    public String getMovie_sales() {
        return movie_sales.get();
    }

    public SimpleStringProperty movie_salesProperty() {
        return movie_sales;
    }

    public void setMovie_id(String movie_id) {

        this.movie_id.set(movie_id);
    }

    public void setMovie_sales(String movie_sales) {
        this.movie_sales.set(movie_sales);
    }
}
