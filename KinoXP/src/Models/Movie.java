package Models;

import java.util.Date;

/**
 * Created by User on 26-09-2017.
 */
public class Movie
{
    private String name;
    private String length;
    private int age;
    private int run;
    private int hallID;

    public Movie(String name,String length,int age,int run,int hallIn){
        this.name=name;
        this.length=length;
        this.age=age;
        this.run=run;
        this.hallID=hallIn;
    }

    public int getHallIn() {
        return hallID;
    }

    public void setHallIn(int hallIn) {
        this.hallID = hallIn;
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
}
