package com.example.proyectoingweb.servlets.model.daos;

import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.beans.ZonaPucp;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DaoImagen extends DaoBase {
    private static String sql_imagenes = "select foto from mydb2.incidencias where idIncidencia=?";
    private static String sql_imagenes_perfil = "select fotoPerfil from mydb2.usuarios where idUsuario=?";

    public byte[] obtenerimagenes(int id) {
        byte[] content = null;
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql_imagenes);
        ) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    content = rs.getBytes("foto");
                } else{
                    content = new byte[]{(byte) 1};
                }
                return content;
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return content;
    }
    public byte[] obtenerimagenes_perfil(int id) {
        byte[] content = null;
        try (Connection conn = this.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql_imagenes_perfil);
        ) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery();) {
                if (rs.next()) {
                    content = rs.getBytes("fotoPerfil");
                } else{
                    content = new byte[]{(byte) 1};
                }
                return content;
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }
        return content;
    }


}
