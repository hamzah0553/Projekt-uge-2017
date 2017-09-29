package Controller;

import DataAccessObject.DAOStats;
import Models.Stats;
import Models.TableInformation_Stats;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

/**
 * Created by jakob on 29-09-2017.
 */
public class StatController {
        DAOStats daoStats = new DAOStats();

        public ObservableList<TableInformation_Stats> getStats (ObservableList<TableInformation_Stats> datalist) {
            daoStats.fetchStats(datalist);
            return datalist;
        }

}
