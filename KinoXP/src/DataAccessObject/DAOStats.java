package DataAccessObject;

import DataWrappers.DataWrapper;
import Models.Stats;
import Models.TableInformation_Stats;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by jakob on 29-09-2017.
 */
public class DAOStats extends DataWrapper {

    private Connection connection;
    private Statement st;
    public DAOStats (){this.connection = super.connection;}
    ArrayList<Stats> myStats = new ArrayList<>();
    public ObservableList<TableInformation_Stats> fetchStats (ObservableList<TableInformation_Stats> datalist){


        String query = "SELECT mov.movie_id, ord.order_id FROM `order` ord" +
                " \n INNER JOIN `movie_playtimes` mplt ON (ord.order_id = mplt.order_id) \n " +
                "INNER JOIN `movie_description` movdp ON (movdp.movie_id = mplt.movie_id) WHERE (ord.order_id = movdp.movie_id)";


        try {
        ResultSet rs = connection.createStatement().executeQuery(query);
        while (rs.next()){

            datalist.add(new TableInformation_Stats(rs.getString("movie_id"), rs.getString("order_id")));

        }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return datalist;
    }


}
