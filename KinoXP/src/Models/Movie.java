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
    private Date startdate;
    private Date enddate;
    private int run;


    public Movie()
    {
    }

    public Movie(String name, String length, int age, Date startdate, Date enddate, int run){
        this.name=name;
        this.length=length;
        this.age=age;
        this.startdate=startdate;
        this.enddate=enddate;
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

    public Date getStartdate() {
        return startdate;
    }

    public void setStartdate(Date startdate) {
        this.startdate = startdate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
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
