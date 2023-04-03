package Task2;

import java.util.List;

public interface MovieDAO {
   void createTable();
   void deleteTable();
   void createMovie(final Movie movie);
   public void deleteMovie(int id);
   public void updateMoviesTitle(String newTitle, int id);
   public void findMovieById(int id);
   public List<Movie> findAll();
}
