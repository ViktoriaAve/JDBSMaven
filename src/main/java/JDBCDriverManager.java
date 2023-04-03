import java.sql.*;

public class JDBCDriverManager {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "0000")) {
            System.out.println("connection: " + connection.isValid(0));

            // CRUD = create, read, update, delete
            // Select (read) statement
            PreparedStatement preparedStatement = connection.prepareStatement("select * from employees where employeeNumber = ?");
            preparedStatement.setInt(1, 1102);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getString("firstname") + " " + resultSet.getString("lastname") + ", " + resultSet.getInt("employeeNumber"));
            }

            // Insert (create) statement
            PreparedStatement insertPs = connection.prepareStatement("insert into employees (employeenumber, lastname, firstname, extension, email, officecode, jobtitle) values (?, ?, ?, ?, ?, ?, ?)");

            insertPs.setInt(1, 131316);
            insertPs.setString(2, "The Thrill");
            insertPs.setString(3, "Bob");
            insertPs.setString(4, "Iambob");
            insertPs.setString(5,"bob@bob.com");
            insertPs.setString(6, "1");
            insertPs.setString(7, "VP Sales");
            int insertCount = insertPs.executeUpdate();
            System.out.println("records were updated: " + insertCount);


            // Update statement
            PreparedStatement updatePs = connection.prepareStatement("update employees set email = ? where employeenumber = ?");

            updatePs.setString(1, "newEmail@gmail.com");
            updatePs.setInt(2, 131315);
            int updateCount = updatePs.executeUpdate();
            System.out.println("records were added: " + updateCount);

            // Delete statement
            PreparedStatement deletePS = connection.prepareStatement("delete from employees where employeenumber = ?");

            deletePS.setInt(1, 131315);

            int deleteCount = deletePS.executeUpdate();
            System.out.println("the number of records deleted: " + deleteCount);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
