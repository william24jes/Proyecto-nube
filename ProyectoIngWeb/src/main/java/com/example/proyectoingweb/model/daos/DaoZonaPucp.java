package com.example.proyectoingweb.model.daos;

import com.example.proyectoingweb.model.beans.ZonaPucp;

import java.sql.*;
import java.util.ArrayList;

public class DaoZonaPucp {

    public ArrayList<ZonaPucp> obtenerListaZonaPucp(){
        ArrayList<ZonaPucp> listaZonaPucp = new ArrayList<>();

        try {
            String url = "jdbc:mysql://localhost:3306/mydb";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, "root", "root");
            Statement stmt = connection.createStatement();
            String sql = "select * from mydb.zonapucp";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                ZonaPucp zona = new ZonaPucp();
                zona.setIdZonaPucp(rs.getInt(1));
                zona.setNombreZona(rs.getString(2));
                listaZonaPucp.add(zona);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }


        return listaZonaPucp;
    }
}
