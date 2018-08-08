package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDAO {
    // 1.four connetion property 四大连接属性
    public static final String driver = "com.mysql.jdbc.Driver";
    public static final String url = "jdbc:mysql:///invoicingsystem?useUnicode=true&amp;characterEncoding=UTF-8";
    public static final String username = "root";
    public static final String password = "123456";
    // 1.2 三大接口
    public Connection con;// 负责打通和数据库的连接
    PreparedStatement ps; // 可以向Server发送SQL指令
    ResultSet rs; // 和DB建立了一个实时的读取数据的工具
    // 2.get connection method 获取连接的方法

    public Connection getConnection() throws Exception {
        Class.forName(driver);
        if (con == null || con.isClosed()) {
            con = DriverManager.getConnection(url, username, password);
        }
        return con;
    }


    // 3.close all resource 关闭资源的方法
    public void closeAllResources() throws Exception {
        // alt+上下箭头，调整code位置
        if (rs != null) {
            rs.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (con != null) {
            con.close();
        }
    }


    public int executeUpdate(String sql, Object... objs) throws Exception {
        getConnection();
        ps = con.prepareStatement(sql);
        for (int i = 0; i < objs.length; i++) {
            ps.setObject(i + 1, objs[i]);
        }
        int count = ps.executeUpdate();
        return count;
    }

    // 5. execute select method


    public ResultSet executeQuery(String sql, Object... objs) throws Exception {
        getConnection();
        ps = con.prepareStatement(sql);
        for (int i = 0; i < objs.length; i++) {
            ps.setObject(i + 1, objs[i]);
        }
        rs = ps.executeQuery();
        return rs;
    }

}
