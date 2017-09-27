package Models;

/**
 * Created by den udvalgte on 26-09-2017.
 */
public class Play {

    private int id;
    private String movieName;
    private String date;
    private String time;

    private Movie movie;

    public Movie getMovie()
    {
        return movie;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public void setMovie(Movie movie)
    {
        this.movie = movie;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
