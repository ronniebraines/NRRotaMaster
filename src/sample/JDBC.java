package sample;

import java.sql.*;
import java.util.ArrayList;

public class JDBC {


    static String contentPath = "src/sample//rota.db";
    static String url = "JDBC:sqlite:" + contentPath + "/";

    public static Connection connectionOpener() {
        try {
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void getTblResults(ResultSet result, String element) throws SQLException {
        String newElement = null;
        while (result.next()) {
            newElement = result.getString(element);
        }
    }

    public static int countTableRows(String tbl) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
        result = stmt.executeQuery("SELECT count(*) FROM " + tbl + ";");
        int numberOfRows = Integer.parseInt(result.getObject(1).toString());
        return numberOfRows;
    }


    public static void viewAll(String tbl, String columnLabel) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
        result = stmt.executeQuery("SELECT * FROM " + tbl + ";");
        System.out.println("Listing elements from " + tbl);
        while (result.next()) {
            String element = result.getString(columnLabel);
            System.out.println(element);
        }
        conn.close();
    }

    public static ArrayList<Signalmen> getSignalmenList() throws SQLException {
        Connection conn = connectionOpener();
        MainPageController mainPageController = new MainPageController();
        Statement stmt = conn.createStatement();
        ArrayList<Signalmen> list = new ArrayList<Signalmen>();
        ResultSet result = null;
        result = stmt.executeQuery("SELECT * FROM SignalmenTBL;");
        while (result.next()) {
            Signalmen signalman = new Signalmen(result.getString("ID"), result.getString("Firstname"), result.getString("Lastname"), result.getString("email"), result.getString("mobilenumber"));
            list.add(signalman);
        }
        conn.close();
        return list;
    }


    public static void updateTable(String tbl, String collumnName, String newEntity, String oldEntity) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
        String sql = ("UPDATE " + tbl + /* i.e tblwarrior */ " SET " + collumnName + /*i.e warriorname*/ " = '" + newEntity + /*new name e.g agathathemiffed*/
                "' " + "WHERE " + collumnName + " = '" + oldEntity + "';");
        Boolean worked = stmt.execute(sql); //this statement takes the table and
        if (worked = true) {
            System.out.println("Entity has been updated from " + oldEntity + " to " + newEntity);
        } else {
            System.out.println("Update has failed");
        }
        conn.close();
    }

    public static void removeElement(String tbl, String column,  String element) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
        String sql = ("DELETE FROM " +tbl+ " WHERE " +column+ " = '" +element + "'");
        Boolean worked = stmt.execute(sql); //this statement takes the table and
        if (worked = true) {
            System.out.println(element + " has been removed from " +tbl);
        } else {
            System.out.println("Update has failed");
        }
        conn.close();
    }

    public static void addSignalman() throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
        int id;
        id = 0;
        String firstname, lastname, email, mobilenumber;
        firstname = "Enter Name";
        lastname = "Enter Lastname";
        email = "Enter Email";
        mobilenumber = "Enter number";
        String sql = ("INSERT INTO SignalmenTBL (Firstname, Lastname, Email, mobilenumber) " +
                "VALUES ('" + firstname + "','" + lastname + "','" + email + "','" + mobilenumber + "');");
        Boolean worked = stmt.execute(sql); //this statement takes the table and
        ResultSet keys = stmt.getGeneratedKeys();
        if (worked = true) {
            System.out.println(firstname + "has been added to the table");
        } else {
            System.out.println("Update has failed");
        }
        conn.close();
    }

    public static void addHoliday() throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
        int id;
        id = 0;
        String dateOfHoliday = "2004/06/30";
        int daysOfHolidayUsed, lengthOfHoliday;
        daysOfHolidayUsed = lengthOfHoliday = 0;
        String sql = ("INSERT INTO HolidaysTBL (DaysOfHolidaysUsed, Lengthofholiday, DateOfHoliday) " +
                "VALUES ('" + daysOfHolidayUsed + "','" + lengthOfHoliday + "','" + dateOfHoliday + "');");
        Boolean worked = stmt.execute(sql); //this statement takes the table and
        ResultSet keys = stmt.getGeneratedKeys();
        System.out.println(sql);
        if (worked = true) {
            System.out.println("Holiday on " + dateOfHoliday + " has been added to the table");
        } else {
            System.out.println("Update has failed");
        }
        conn.close();
    }

    public static void alterTable(String tbl, String collumnName, String newColumn) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
        String sql = ("ALTER TABLE " + tbl + /* i.e tblwarrior */ " RENAME COLUMN " + collumnName + /*i.e warriorname*/ " TO " + newColumn + ";");/*new name e.g agathathemiffed*/
        Boolean worked = stmt.execute(sql); //this statement takes the table and
        if (worked = true) {
            System.out.println("Collumn name has been updated from " + collumnName + " to " + newColumn);
        } else {
            System.out.println("Update has failed");
        }
        conn.close();
    }

    public static void joinTables(String tbl1, String tbl2) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmnt = conn.createStatement();
        ResultSet result = null;
        result = stmnt.executeQuery("JOIN " + tbl1 + " ON (" + tbl2 + ".ID = " + tbl1 + ".ID)");
        System.out.println("JOIN " + tbl1 + " ON (" + tbl2 + ".ID = " + tbl1 + ".ID)");
        System.out.println(result);
        conn.close();
    }

    public static void addNewColumn(String tbl, String newColumn, String dataType) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmnt = conn.createStatement();
        ResultSet result = null;
        String sql = ("ALTER TABLE " + tbl + " ADD COLUMN " + newColumn + " " + dataType + ";");
        System.out.println(sql);
        System.out.println("The column " + newColumn + " has been added to " + tbl);
    }
}

