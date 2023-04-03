import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JDBCDataSource {
    public static void main(String[] args) {
        DataSource dataSource = createDataSource();

        try (Connection connection = dataSource.getConnection()) {
            System.out.println("connection: " + connection.isValid(0));

            // CRUD = create, read, update, delete
            // Select (read)

            PreparedStatement preparedStatement = connection.prepareStatement("select * from employees where employeenumber = ?");
            preparedStatement.setInt(1, 1216);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstname") + " " + resultSet.getString("lastname") + ", " + resultSet.getInt ("employeenumber"));
            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static DataSource createDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setJdbcUrl("jdbc:mysql://localhost/classicmodels");
        hikariDataSource.setUsername("root");
        hikariDataSource.setPassword("0000");
        return hikariDataSource;
    }
}
