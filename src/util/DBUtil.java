package util;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBUtil {
    private static Properties dbinfo = new Properties();

    static {
        try {
            dbinfo.load(new FileInputStream("D:\\woorifisa\\01.lab\\01.java\\FR\\src\\dbinfo.properties"));
            Class.forName(dbinfo.getProperty("db.driverClassName"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(dbinfo.getProperty("db.url"),
                dbinfo.getProperty("db.username"), dbinfo.getProperty("db.password"));
    }

    //DML용 자원반환
    public static void close(ResultSet rs, Statement stmt, Connection conn) {
        try{
            if(rs != null) {

                rs.close();
                rs = null;
            }
            if(stmt != null) {
                stmt.close();
                stmt = null;
            }
            if(conn != null) {
                conn.close();
                conn = null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }


    public static void close(Statement stmt, Connection conn) {
        try{
            if(stmt != null) {
                stmt.close();
                stmt = null;
            }
            if(conn != null) {
                conn.close();
                conn = null;
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}