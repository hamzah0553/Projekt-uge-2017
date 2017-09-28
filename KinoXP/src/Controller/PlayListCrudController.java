package Controller;

import Models.Movie;
import Models.Play;
import View.PlayListCrud;

/**
 * Created by den udvalgte on 27-09-2017.
 */
public class PlayListCrudController
{
    PlayListCrud gui;
    public PlayListCrudController(PlayListCrud playListCrud){
        this.gui = playListCrud;
    }


    public void createPlay(){

        Play play = new Play();
        Movie movie = null;
        for (Movie m : gui.getMovieList()) {
            if (m.getName().equalsIgnoreCase(gui.getMovies())){
                movie = m;
            }
        }
        play.setMovie(movie);
        play.setDate(gui.getDate());
        play.setTime(gui.getTimes());
    }
}
