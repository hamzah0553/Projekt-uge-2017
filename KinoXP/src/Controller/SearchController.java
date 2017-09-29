package Controller;

import DataAccessObject.PlaylistDAO;
import System.Controller;
import System.Model;

import java.util.ArrayList;
import java.util.HashMap;


public class SearchController extends Controller
{

    public SearchController(Model model)
    {
        super(model);
    }

    public ArrayList<HashMap<String, String>> getPlays(String searchPhone)
    {
        return new PlaylistDAO().getTicketInfo(searchPhone);
    }

    public void deleteOrder(String orderID) {
        new PlaylistDAO().removeOrder(orderID);
    }
}