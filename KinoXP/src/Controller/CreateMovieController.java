package Controller;


import Models.Movie;

/**
 * Created by jakob on 28-09-2017.
 */
public class CreateMovieController {
    private int movie_id;
    private int hall_id;
    private String movie_name;
    private int movie_length;
    private int movie_age;
    private int movie_run;

    public String validateMovieCreation (int hall_id, String movie_name, int movie_length, int movie_age, int movie_run) {
        String check = ("" + " " + hall_id + " " + movie_name + ',' + " " + movie_length + ',' + movie_age + ',' + movie_run);
        if (hall_id == 0 || movie_name == null || movie_length == 0 || movie_age == 0) {

            return "The information you entered appeared to have one or more missing data, please try again. \n These things cannot be empty:" +
                    " MovieId, HallId, Movie name, Movie Length, Movie Age";

        }

        if (!check.matches(".*\\d.*")) {
            return "You made a wrong input in one of the fields where you only need to input numbers";
        }

        check = (""+hall_id + ',' + movie_length + ',' + movie_age + ',' + movie_run);

        if (check.matches("/[a-zA-Z]*/")) {

            return "You fucked up";

        } else {


            this.hall_id = hall_id;
            this.movie_name = movie_name;
            this.movie_length = movie_length;
            this.movie_age = movie_age;
            this.movie_run = movie_run;


            return "ok";

        }
    }


    public void createMovie () {
        Movie movie = new Movie();

        movie.addMovie(hall_id, movie_name, String.valueOf(movie_length), movie_age, movie_run);


    }

}
