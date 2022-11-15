package com.example.proyectoingweb.servlets.model.daos;

import com.example.proyectoingweb.servlets.model.beans.ZonaPucp;

import java.sql.*;
import java.util.ArrayList;

public class DaoZonaPucp extends DaoBase{

    public ArrayList<ZonaPucp> obtenerListaZonaPucp(){
        ArrayList<ZonaPucp> listaZonaPucp = new ArrayList<>();
        String sql = "select * from mydb.zonapucp";

        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                ZonaPucp zona = new ZonaPucp();
                zona.setIdZonaPucp(rs.getInt(1));
                zona.setNombreZona(rs.getString(2));
                listaZonaPucp.add(zona);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listaZonaPucp;
    }
}
