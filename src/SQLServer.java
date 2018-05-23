import java.sql.*;

/**
 * SQLServer: Helper class to connect to database and preform operations and queries on the database
 */

public class SQLServer {
    Connection conn = null;
    Statement statement = null;
    public SQLServer() {
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
    //Method to insert into the table single in the SQL database
    //Input: date(String of the date checking in), numDays(Int of the number of days staying), cost(Cost of the room)
    public void insertSingle(String date, int numDays, int cost) {
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //SQL query to insert the room
        String command = "INSERT into single values ('" + date + "', " + numDays + ", " + cost + ")";
        try {
            boolean resultSet = statement.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Method to insert into the table dou in the SQL database
    //Input: date(String of the date checking in), numDays(Int of the number of days staying), cost(Cost of the room)
    public void insertDouble(String date, int numDays, int cost) {
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //SQL query to insert the room
        String command = "INSERT into dou values ('" + date + "', " + numDays + ", " + cost + ")";
        try {
            boolean resultSet = statement.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Method to insert into the table deluxe in the SQL database
    //Input: date(String of the date checking in), numDays(Int of the number of days staying), cost(Cost of the room)
    public void insertDeluxe(String date, int numDays, int cost) {
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //SQL query to insert the room
        String command = "INSERT into deluxe values ('" + date + "', " + numDays + ", " + cost + ")";
        try {
            boolean resultSet = statement.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //Method to insert into the table customer in the SQL database
    //Input: name(Name of customer), isDisabled(Whether or not the customer is disabled)
    public void insertCustomer(String name, String isDisabled) {
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
    public void printSingle() {
        try {
            statement = conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String command = "Select * from single";
        try {
            ResultSet resultSet = statement.executeQuery(command);
            while (resultSet.next()) {
                System.out.println(resultSet.getString("date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
