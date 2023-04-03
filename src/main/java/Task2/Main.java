package Task2;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MovieDAOImpl movieDAO = new MovieDAOImpl();
       // movieDAO.createMovie(new Movie(1005, "The best film ever", "Fiction", 2035));
        movieDAO.updateMoviesTitle("New Friends", 1003);
        movieDAO.findMovieById(1000);
    }
}
