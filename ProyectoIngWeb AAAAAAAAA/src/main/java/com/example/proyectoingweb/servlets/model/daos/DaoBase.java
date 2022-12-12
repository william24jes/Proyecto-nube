package com.example.proyectoingweb.servlets.model.daos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DaoBase {

    public Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

        String host = "34.162.216.123";
        String db = "mydb2";
        String user = "root";
        String pass = "123456";
        String url = "jdbc:mysql://"+host+":3306/"+db;

        return DriverManager.getConnection(url, user, pass);
    }
}
