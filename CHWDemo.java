package RemoteMySQLtest;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;

//Ahmad Day 3/28/2022

public class CHWDemo {


    public static void main(String[] args) throws SQLException {
        writetoCSVfile();
        display();
    }

    private static void writetoCSVfile() {
        Connection connection = null;
        Statement stmt = null;
        try {
            System.out.println("Connecting to the selected database...");
            Connection conn = DriverManager.getConnection("jdbc:mysql://68.178.217.12/CWHDemo", "CWHDemo", "Cwhdemo%123");
            System.out.println("database connected sucessfully!..");
            stmt = connection.createStatement();
            String sql = "SELECT * FROM fall2014";
            ResultSet rs = stmt.executeQuery(sql);
            String filename = "example.csv";
            String fileheader = "credit.subject,course,section,days,time";
            FileWriter fw = new FileWriter(filename);
            fw.append(fileheader);
            while (rs.next()) {
                int credit = rs.getInt("crn");
                String subject = rs.getString("Subject");
                String Course = rs.getString("Subject");
                String Section = rs.getString("Subject");
                Date date = rs.getDate("date");
                Time Time = rs.getTime("time");
                fw.append(String.valueOf(credit));
                fw.append(subject);
                fw.append(Course);
                fw.append(Section);
                fw.write(String.valueOf(date));
                fw.write(String.valueOf(Time));
            }
        } catch (SQLException | IOException se) {
            System.out.println(se);
        } finally {
            try {
                if (stmt != null)
                    connection.close();
            } catch (SQLException se) {
                System.out.println(se);
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public static void display() throws SQLException {

        Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            System.out.println("Connecting to a selected database...");
            Connection conn = DriverManager.getConnection("jdbc:mysql://68.178.217.12/CWHDemo", "CWHDemo", "Cwhdemo%123");
            System.out.println(" database connected successfully...");
            stmt = connection.createStatement();
// Query for the question What 4 credit courses are available on Tuesday and Thursday?
            String credit = "SELECT course FROM fall2014 where credit=4 and days='tuesday' or days='thursday'";
            ResultSet rs = stmt.executeQuery(credit);
            System.out.println("course");
            while (rs.next()) {
                String course = rs.getString("course");
                System.out.println(course);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}