import java.sql.*;

public class Task1 {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sdaexercises", "root", "0000")) {
//            System.out.println("SDAExercise table connection: " + connection.isValid(0));

            // Update statement
            PreparedStatement updatePs = connection.prepareStatement("update movies set genre = ? where id = ?");

            updatePs.setString(1, "Adventure");
            updatePs.setInt(2, 1000);
            int updateCount = updatePs.executeUpdate();
            System.out.println("Records were added: " + updateCount);

            // Delete statement
            PreparedStatement deletePS = connection.prepareStatement("delete from movies where id = ?");
            deletePS.setInt(1, 1002);
            int deleteCount = deletePS.executeUpdate();
            System.out.println("The number of records deleted: " + deleteCount);

            //  Display all records
            PreparedStatement displayPS = connection.prepareStatement("select * from movies");

            ResultSet resultSet = displayPS.executeQuery();

            while (resultSet.next()) {
                System.out.println(resultSet.getInt("id") + " " + resultSet.getString("title") + " " + resultSet.getString("genre") + " " + resultSet.getInt("yearOfRelease"));
            }


        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
