package Controller;

import DataAccessObject.PlaylistDAO;
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


        Movie movie = null;
        for (Movie m : gui.getMovieList()) {
            if (m.getName().equalsIgnoreCase(gui.getSelectedMovie())){
                System.out.println("HIT!: " + m);
                movie = m;
            }
        }

        PlaylistDAO dao = new PlaylistDAO();
        Play play = new Play();
        play.setMovie(movie);
        play.setDate(gui.getDate());
        play.setTime(gui.getSelectedTime());
        dao.createPlay(play);

    }
}
