/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Tung
 */
public class DBHelper {

    Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public DBHelper() {
        Properties p = new Properties();
        try {
            FileReader fin = new FileReader(new File("connection.properties"));
            p.load(fin);
            String host = p.getProperty("ServerID");
            String port = p.getProperty("Port");
            String dbname = p.getProperty("Database");
            String user = p.getProperty("Username");
            String pw = p.getProperty("Password");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://" + host + ":" + port + ";databaseName=" + dbname;
            con = DriverManager.getConnection(url, user, pw);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 100:: Không tìm thấy lớp");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 101:: Không thể kết nối đến máy chủ");
        } catch (NullPointerException e) {
            JOptionPane.showMessageDialog(null, "Lỗi 102:: Cấu hình bị trống");
        } catch (Exception e) {
        }
    }

}
