package Task2;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieDAOImpl implements MovieDAO {
    final String url = "jdbc:mysql://localhost:3306/sdaexercises";
    final String user = "root";
    final String password = "0000";

    @Override
    public void createTable() {
        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {
            PreparedStatement createTablePS = connection.prepareStatement("CREATE TABLE IF NOT EXISTS MOVIES_NEW (id INTEGER AUTO_INCREMENT, " +
                    "title VARCHAR(255), " +
                    "genre VARCHAR(255), " +
                    "yearOfRelease INTEGER, " +
                    "PRIMARY KEY (id))");
            createTablePS.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void deleteTable() {
        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {
            PreparedStatement deleteTablePS = connection.prepareStatement("DROP TABLE MOVIES_NEW");
            deleteTablePS.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void createMovie(Movie movie) {
        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {
            PreparedStatement createMoviePS = connection.prepareStatement("INSERT INTO MOVIES (title, genre, yearOfRelease) VALUES (?, ?, ?)");
            createMoviePS.setString(1, movie.getTitle());
            createMoviePS.setString(2, movie.getGenre());
            createMoviePS.setInt(3, movie.getYearOfRelease());
            createMoviePS.executeUpdate();

        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void updateMoviesTitle(String newTitle, int id) {
        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {
            PreparedStatement updatePS = connection.prepareStatement("update movies set title = ? where id = ?");

            updatePS.setString(1, newTitle);
            updatePS.setInt(2, id);
            int updateCount = updatePS.executeUpdate();
            System.out.println("Records were added: " + updateCount);
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    public void deleteMovie(int id) {
        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {
            PreparedStatement deletePS = connection.prepareStatement("delete from movies where id = ?");
            deletePS.setInt(1, id);
            int deleteCount = deletePS.executeUpdate();
            System.out.println("The number of records deleted: " + deleteCount);
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }

    @Override
    public void findMovieById(int id) {
        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {
            PreparedStatement displayPS = connection.prepareStatement("select * from movies where id = ?");
            displayPS.setInt(1, id);

            ResultSet resultSet = displayPS.executeQuery();

            if (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title") + " " + resultSet.getString("genre") + " " + resultSet.getInt("yearOfRelease"));
            }
        } catch (SQLException ex) {
            throw new DatabaseActionException(ex);
        }
    }

    public List<Movie> findAll() {
        try (Connection connection = DriverManager.getConnection(
                url,
                user,
                password)) {
            PreparedStatement findAllMoviesPS = connection.prepareStatement("SELECT * FROM MOVIES");
            ResultSet moviesResultSet = findAllMoviesPS.executeQuery();
            List<Movie> movies = new ArrayList<>();
            while (moviesResultSet.next()) {
                Integer id = moviesResultSet.getInt(1);
                String title = moviesResultSet.getString(2);
                String genre = moviesResultSet.getString(3);
                Integer yearOfRelease = moviesResultSet.getInt(4);
                movies.add(new Movie(id, title, genre, yearOfRelease));
            }
            return movies;
        } catch (SQLException e) {
            throw new DatabaseActionException(e);
        }
    }
}
