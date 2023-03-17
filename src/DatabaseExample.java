import javax.swing.*;
import java.sql.*;

public class DatabaseExample {
    public static void main(String[] args) {
        DatabaseLogin a = new DatabaseLogin();
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://" + a.getHost() + ":" + a.getPort() + "/" + a.getDatabase() + "? "+
                    "allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC", a.getUser(),a.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            Statement stmt = conn.createStatement();
            String SQLQuery = "SELECT * FROM nt19users";
            ResultSet result = stmt.executeQuery(SQLQuery);

            ResultSetMetaData metadata = result.getMetaData();

            int numCols = metadata.getColumnCount();
            for (int i = 1 ; i <= numCols ; i++) {
                System.out.println("Kolumn " + i + ": " + metadata.getColumnLabel(i));
            }

            while (result.next()) {
                String output = "";
                output += result.getInt("id") + ", " +
                        result.getString("name");
                System.out.println(output);
            }
            String thisname = "Korinth";

            //SQLQuery = "INSERT INTO nt19users(name) VALUES(thisname)"; Does not work!
            //stmt.executeUpdate(SQLQuery);

            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
