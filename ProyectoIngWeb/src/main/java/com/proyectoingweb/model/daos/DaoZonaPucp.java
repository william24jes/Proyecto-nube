package com.proyectoingweb.model.daos;

import com.proyectoingweb.model.beans.ZonaPucp;

import java.sql.*;
import java.util.ArrayList;

public class DaoZonaPucp {

    public ArrayList<ZonaPucp> obtenerListaZonaPucp(){
        ArrayList<ZonaPucp> zonasPucp = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "select * from mydb.zonapucp";

        try(Connection connection = DriverManager.getConnection(url, "root", "root");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                ZonaPucp zona = new ZonaPucp();

                zona.setIdZonaPucp(rs.getInt(1));
                zona.setNombreZona(rs.getString(2));

                zonasPucp.add(zona);
            }

        } catch (SQLException e) {
            throw new RuntimeException();
        }

        return zonasPucp;
    }
}
