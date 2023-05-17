package ptit.wibulord.webfilm.dto;

import java.sql.Connection;
import java.sql.DriverManager;

public class Databasehelper {
    public static Connection openConnectionWithAccout(String account, String password) throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connect = "jdbc:sqlserver://localhost:1433;databaseName=WEBPHIM;user="+account+";password="+password+";encrypt=false";
        Connection con = DriverManager.getConnection(connect);
        return con;
    }

    public static Connection openConnection() throws Exception {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connect = "jdbc:sqlserver://localhost:1433;databaseName=WEBPHIM;user=sa;password=123456;encrypt=false";
        Connection con = DriverManager.getConnection(connect);
        return con;
    }
}
