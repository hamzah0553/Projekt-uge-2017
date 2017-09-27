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
    private String startdate;
    private String enddate;
    private int run;
    private int hallID;

    public Movie(String name,String length,int age,String startdate, String enddate,int run,int hallIn){
        this.name=name;
        this.length=length;
        this.age=age;
        this.startdate=startdate;
        this.enddate=enddate;
        this.run=run;
        this.hallID=hallIn;
    }

    public int getHallIn() {
        return hallID;
    }

    public void setHallIn(int hallIn) {
        this.hallId = hallIn;
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

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
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
