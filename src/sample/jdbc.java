package sample;
import java.sql.*;

public class jdbc {
    private String testname;
    public jdbc (String DatabaseName) {
        testname = DatabaseName;
    }
    String contentPath = "src/sample//vikingraid.db";
    String url = "jdbc:sqlite:" + contentPath + "/";

    public Connection connectionOpener(){
        try {
            return DriverManager.getConnection(url+testname);
        }catch(SQLException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void getTblResults(ResultSet result, String element) throws SQLException {
        String newElement = null;
        while (result.next()) {
            newElement =  result.getString(element);
        }
    }


    public void viewAll(String tbl, String columnLabel) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
        result = stmt.executeQuery("SELECT * FROM "+tbl+ ";");
        while (result.next()) {
            String element =  result.getString(columnLabel);
            System.out.println(tbl);
            System.out.println(element);
        }
        conn.close();
    }

    public void updateTable(String tbl, String collumnName, String newEntity, String oldEntity) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
            String sql = ("UPDATE " + tbl + /* i.e tblwarrior */ " SET " + collumnName + /*i.e warriorname*/ " = '" + newEntity + /*new name e.g agathathemiffed*/
                    "' " + "WHERE " + collumnName + " = '" + oldEntity + "';");
        Boolean worked = stmt.execute(sql); //this statement takes the table and
        if (worked = true){
            System.out.println("Entity has been updated from " +oldEntity+ " to " +newEntity);
        }else{
            System.out.println("Update has failed");
        }
        conn.close();
    }

    public void alterTable(String tbl, String collumnName, String newColumn) throws SQLException {
        Connection conn = connectionOpener();
        Statement stmt = conn.createStatement();
        ResultSet result = null;
        String sql = ("ALTER TABLE " +tbl+ /* i.e tblwarrior */ " RENAME COLUMN " +collumnName+ /*i.e warriorname*/ " TO " +newColumn+ ";");/*new name e.g agathathemiffed*/
        Boolean worked = stmt.execute(sql); //this statement takes the table and
        if (worked = true){
            System.out.println("Collumn name has been updated from " +collumnName+ " to " +newColumn);
        }else{
            System.out.println("Update has failed");
        }
        conn.close();
    }

    public void joinTables(String tbl1, String tbl2) throws SQLException{
        Connection conn = connectionOpener();
        Statement stmnt = conn.createStatement();
        ResultSet result = null;
        result = stmnt.executeQuery("JOIN " +tbl1+ " ON (" +tbl2+".ID = "+tbl1+ ".ID)");
        System.out.println("JOIN " +tbl1+ " ON (" +tbl2+".ID = "+tbl1+ ".ID)");
        System.out.println(result);
        conn.close();
    }

    public void addNewColumn(String tbl, String newColumn, String dataType) throws SQLException{
        Connection conn = connectionOpener();
        Statement stmnt = conn.createStatement();
        ResultSet result = null;
        String sql = ("ALTER TABLE " +tbl+ " ADD COLUMN " +newColumn+ " " +dataType+ ";");
        System.out.println(sql);
        System.out.println("The column " +newColumn+ " has been added to " +tbl);
    }
}

