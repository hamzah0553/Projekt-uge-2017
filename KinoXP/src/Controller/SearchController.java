package Controller;

import Models.Play;
import System.*;

import java.util.ArrayList;


public class SearchController extends Controller
{

    public SearchController(Model model)
    {
        super(model);
    }

    public ArrayList<Play> getPlays(String searchPhone)
    {
        //return new PlaylistDAO().getPlaylist(searchPhone);
        return null;
    }
}