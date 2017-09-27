package DataAccessObject;

import DataWrappers.DataWrapper;

import java.sql.*;

/**
 * Created by hamza on 26-09-2017.
 */
public class AddMovieDAO extends DataWrapper {

    private Connection conn;

    public AddMovieDAO () {
        this.conn = super.connection;

    }


}
