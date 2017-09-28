package Models;

import DataAccessObject.Connector;
import DataAccessObject.DAOmovie;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

/**
 * Created by User on 26-09-2017.
 */
public class Movie
{

    Statement st;
    Connection conn;

    private int id;
    private String name;
    private String length;
    private int age;
    private int run;


    public Movie()
    {
        Connector connector = Connector.getInstance();
        conn = connector.getConnection();
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Movie(int id, String name, String length, int age, int run){
        this.id = id;
        this.name=name;
        this.length=length;
        this.age=age;

        this.run=run;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public void setRun(int run) {
        this.run = run;
    }

    public int getRun() {
        return run;
    }


    //Adding the movie to the database

    public void addMovie (int hall_id, String movie_name, String movie_length, int movie_run, int movie_age) {

        //New dataacces objekt
        DAOmovie newMovie = new DAOmovie();

        //try catch for the dataacces objekt.
        try {
            newMovie.Createmovie(movie_name, movie_length, movie_age, movie_run, hall_id);
        } catch (SQLException e) {
            System.out.println("Something went wrong");
        }


    }

}


