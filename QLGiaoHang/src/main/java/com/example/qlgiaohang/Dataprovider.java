package com.example.qlgiaohang;

import java.sql.*;

import static java.sql.ResultSet.CONCUR_READ_ONLY;
import static java.sql.ResultSet.TYPE_SCROLL_INSENSITIVE;

public class Dataprovider {
    public static Dataprovider Instance ;
    public static Statement Stmt;
    public static Dataprovider getInstance() {
        if(Instance == null) Instance = new Dataprovider();
        return Instance;
    }
    public static void setInstance(Dataprovider instance) {
        Instance = instance;
    }
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            + "database=QuanLyGiaoHang;"
            +"TrustServerCertificate=True;";
    private static String USER_NAME = "admin";
    private static String PASSWORD = "1234";
    private Dataprovider() { }

//    public static void main(String[] args) throws SQLException {
//        try (ResultSet rs = Dataprovider.DataTable("select * from HoaDonVanChuyen")) {
//            while (rs.next()) {
//                System.out.println(rs.getString(1) + "  " + rs.getString(2)
//                        + "  " + rs.getString(3)+ "  " + rs.getString(4)+ "  " + rs.getString(5)+ "  " + rs.getInt(6));
//            }
//        }
//    }
    public static void ConnecSQL() throws SQLException {
        Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
        Statement stmt = conn.createStatement( TYPE_SCROLL_INSENSITIVE, CONCUR_READ_ONLY);
        Stmt = stmt;
    }
    public static ResultSet DataTable(String query) throws SQLException {
        ResultSet rs = Stmt.executeQuery(query);
        return rs;
    }
    public static void EditData(String query) throws SQLException {
        Stmt.executeUpdate(query);
    }
    public static Connection getConnection(String dbURL, String userName,
                                           String password) {
        Connection conn = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(dbURL, userName, password);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return conn;
    }

}
