import java.sql.*;

public class SQLComm {
    Connection conn = null;
    public SQLComm() {
        //url and login items for SQL database
        String url = "jdbc:sqlserver://localhost\\SQLEXPRESS;Database=master;Trusted_Connection=True;integratedSecurity=true;";
        //driver to connect to SQL database to
        String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            //connect to the database, throwing an exception if login fails
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertSingle(String dateCopy, int numDays, int cost) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //SQL query to insert the room
        String command = "INSERT into single values ('" + dateCopy + "', " + numDays + ", " + cost + ")";
        try {
            boolean resultSet = statement.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertDouble(String dateCopy, int numDays, int cost) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //SQL query to insert the room
        String command = "INSERT into double values ('" + dateCopy + "', " + numDays + ", " + cost + ")";
        try {
            boolean resultSet = statement.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void insertDeluxe(String dateCopy, int numDays, int cost) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //SQL query to insert the room
        String command = "INSERT into deluxe values ('" + dateCopy + "', " + numDays + ", " + cost + ")";
        try {
            boolean resultSet = statement.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCustomer(String name, String isDisabled) {
        Statement statement = null;
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //SQL query to insert the room
        String command = "INSERT into customer values ('" + name + "', '" + isDisabled + "')";
        try {
            boolean resultSet = statement.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
