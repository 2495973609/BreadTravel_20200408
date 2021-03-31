package com.breadTravel.util;

import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static Connection connection_kbt = creatConnection_kbt();
    private static Connection creatConnection_kbt() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String sql_kbt = "jdbc:sqlserver://localhost:1433;database=DB_BreadTravel;user=sa;password=123456";
            connection_kbt = DriverManager.getConnection(sql_kbt);
            if (connection_kbt != null) {
                System.out.println("Connection is ok!!!");
            } else {
                System.out.println("failed");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection_kbt;
    }
    public Connection getConnection_kbt()
    {
        return connection_kbt;
    }
    @Test
    public void Test(){
        getConnection_kbt();
    }
}
