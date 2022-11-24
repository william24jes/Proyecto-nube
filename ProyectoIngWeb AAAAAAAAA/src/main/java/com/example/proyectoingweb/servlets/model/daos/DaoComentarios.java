package com.example.proyectoingweb.servlets.model.daos;

import com.example.proyectoingweb.servlets.model.beans.Comentarios;
import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.beans.ZonaPucp;

import java.sql.*;
import java.util.ArrayList;

public class DaoComentarios extends DaoBase {
    public ArrayList<Comentarios> obtenerListaComentarios() {
        ArrayList<Comentarios> listaComentarios = new ArrayList<>();

        String sql = "SELECT * FROM comentarios ORDER BY idComentario";

        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Comentarios comentarios = new Comentarios();
                comentarios.setContenido1(rs.getString(1));
                comentarios.setContenido1(rs.getString(2));
                comentarios.setContenido1(rs.getString(3));
                comentarios.setContenido1(rs.getString(4));
                comentarios.setContenido1(rs.getString(5));
                comentarios.setContenido1(rs.getString(6));
                comentarios.setContenido1(rs.getString(7));
                comentarios.setContenido1(rs.getString(8));
                comentarios.setContenido1(rs.getString(9));
                comentarios.setContenido1(rs.getString(10));
                comentarios.setIdIncidencia(rs.getInt(11));
                comentarios.setIdComentario(rs.getInt(12));
                comentarios.setIdUsuario(rs.getInt(rs.getInt(13)));
                comentarios.setIdSeguridad(rs.getInt(rs.getInt(14)));
                listaComentarios.add(comentarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaComentarios;
    }

    public Comentarios buscarporIdIncidencia_y_idUsuarioQueCreo(String idIncidencias, String idSesion) {
        Comentarios comentarios = new Comentarios();
        String sql = "SELECT * FROM comentarios where idIncidencia = ? and idUsuario = ?";

        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, idIncidencias);
            pstmt.setString(2, idSesion);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    comentarios.setIdIncidencia(rs.getInt(11));
                    comentarios.setIdUsuario(rs.getInt(13));
                    comentarios.setIdComentario(rs.getInt(12));
                    comentarios.setIdSeguridad(rs.getInt(14));
                    comentarios.setContenido1(rs.getString(1));
                    comentarios.setContenido1(rs.getString(2));
                    comentarios.setContenido1(rs.getString(3));
                    comentarios.setContenido1(rs.getString(4));
                    comentarios.setContenido1(rs.getString(5));
                    comentarios.setContenido1(rs.getString(6));
                    comentarios.setContenido1(rs.getString(7));
                    comentarios.setContenido1(rs.getString(8));
                    comentarios.setContenido1(rs.getString(9));
                    comentarios.setContenido1(rs.getString(10));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comentarios;
    }
}
