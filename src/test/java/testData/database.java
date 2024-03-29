package testData;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class database {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:src/test/java/testData/sample.db");
            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            // create a new table
            statement.executeUpdate("DROP TABLE IF EXISTS hotel_booking");
            statement.executeUpdate("CREATE TABLE hotel_booking (username STRING, password STRING, location STRING, hotel STRING, room_type STRING, number_of_rooms STRING, check_in_date STRING, check_out_date STRING, adults_per_room STRING, children_per_room STRING)");

            // insert the data
            statement.executeUpdate("INSERT INTO hotel_booking values('okuhleMru', 'FetFle2000!', 'Sydney', 'Hotel Creek', 'Standard', '1 - One', '28/03/2024', '30/03/2024', '2 - Two', '4 - Four')");
        } catch(SQLException e) {
            System.err.println(e.getMessage());
        } finally {
            try {
                if(connection != null)
                    connection.close();
            } catch(SQLException e) {
                // connection close failed.
                System.err.println(e);
            }
        }
    }
}
