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
                comentarios.setContenido2(rs.getString(2));
                comentarios.setContenido3(rs.getString(3));
                comentarios.setContenido4(rs.getString(4));
                comentarios.setContenido5(rs.getString(5));
                comentarios.setContenido6(rs.getString(6));
                comentarios.setContenido7(rs.getString(7));
                comentarios.setContenido8(rs.getString(8));
                comentarios.setContenido9(rs.getString(9));
                comentarios.setContenido10(rs.getString(10));
                comentarios.setIdIncidencia(rs.getInt(11));
                comentarios.setIdComentario(rs.getInt(12));
                comentarios.setIdUsuario(rs.getInt(rs.getInt(13)));
                comentarios.setIdSeguridad(rs.getInt(rs.getInt(14)));
                comentarios.setValidacion_usuario_comentar(rs.getInt(15));
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
                    comentarios = new Comentarios();
                    comentarios.setIdIncidencia(rs.getInt(11));
                    comentarios.setIdUsuario(rs.getInt(13));
                    comentarios.setIdComentario(rs.getInt(12));
                    comentarios.setIdSeguridad(rs.getInt(14));
                    comentarios.setContenido1(rs.getString(1));
                    comentarios.setContenido2(rs.getString(2));
                    comentarios.setContenido3(rs.getString(3));
                    comentarios.setContenido4(rs.getString(4));
                    comentarios.setContenido5(rs.getString(5));
                    comentarios.setContenido6(rs.getString(6));
                    comentarios.setContenido7(rs.getString(7));
                    comentarios.setContenido8(rs.getString(8));
                    comentarios.setContenido9(rs.getString(9));
                    comentarios.setContenido10(rs.getString(10));
                    comentarios.setValidacion_usuario_comentar(rs.getInt(15));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return comentarios;
    }

    public void actualizarComentario_subida(Comentarios comment){
        String sql = "UPDATE comentarios SET validacion_usuario = ? where idIncidencia = ? and idUsuario = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int comentario_validacion_subida = comment.getValidacion_usuario_comentar()+1;
            pstmt.setString(1, String.valueOf(comentario_validacion_subida));
            pstmt.setString(2, String.valueOf(comment.getIdIncidencia()));
            pstmt.setString(3, String.valueOf(comment.getIdUsuario()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizarComentario_bajada(Comentarios comment){
        String sql = "UPDATE comentarios SET validacion_usuario = ? where idIncidencia = ? and idUsuario = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            int comentario_validacion_subida = comment.getValidacion_usuario_comentar()-1;
            pstmt.setString(1, String.valueOf(comentario_validacion_subida));
            pstmt.setString(2, String.valueOf(comment.getIdIncidencia()));
            pstmt.setString(3, String.valueOf(comment.getIdUsuario()));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void actualizar_tabla_comentarios(Comentarios comment,String columna_a_llenar,String mensaje){

        if(Integer.parseInt(columna_a_llenar) == 2){
            String sql = "UPDATE comentarios SET contenido2  = ? ,validacion_usuario = ? where idIncidencia = ? and idUsuario = ?";
            try (Connection connection = this.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                int comentario_validacion_subida = comment.getValidacion_usuario_comentar()-1;
                pstmt.setString(1,mensaje);
                pstmt.setString(2, String.valueOf(comentario_validacion_subida));
                pstmt.setString(3, String.valueOf(comment.getIdIncidencia()));
                pstmt.setString(4, String.valueOf(comment.getIdUsuario()));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(Integer.parseInt(columna_a_llenar) == 4){
            String sql = "UPDATE comentarios SET contenido4  = ? ,validacion_usuario = ? where idIncidencia = ? and idUsuario = ?";
            try (Connection connection = this.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                int comentario_validacion_subida = comment.getValidacion_usuario_comentar()-1;
                pstmt.setString(1,mensaje);
                pstmt.setString(2, String.valueOf(comentario_validacion_subida));
                pstmt.setString(3, String.valueOf(comment.getIdIncidencia()));
                pstmt.setString(4, String.valueOf(comment.getIdUsuario()));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if(Integer.parseInt(columna_a_llenar) == 6){
            String sql = "UPDATE comentarios SET contenido6  = ? ,validacion_usuario = ? where idIncidencia = ? and idUsuario = ?";
            try (Connection connection = this.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                int comentario_validacion_subida = comment.getValidacion_usuario_comentar()-1;
                pstmt.setString(1,mensaje);
                pstmt.setString(2, String.valueOf(comentario_validacion_subida));
                pstmt.setString(3, String.valueOf(comment.getIdIncidencia()));
                pstmt.setString(4, String.valueOf(comment.getIdUsuario()));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        if(Integer.parseInt(columna_a_llenar) == 8){
            String sql = "UPDATE comentarios SET contenido8  = ? ,validacion_usuario = ? where idIncidencia = ? and idUsuario = ?";
            try (Connection connection = this.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                int comentario_validacion_subida = comment.getValidacion_usuario_comentar()-1;
                pstmt.setString(1,mensaje);
                pstmt.setString(2, String.valueOf(comentario_validacion_subida));
                pstmt.setString(3, String.valueOf(comment.getIdIncidencia()));
                pstmt.setString(4, String.valueOf(comment.getIdUsuario()));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
        if(Integer.parseInt(columna_a_llenar) == 10){

            String sql = "UPDATE comentarios SET contenido10  = ? ,validacion_usuario = ? where idIncidencia = ? and idUsuario = ?";
            try (Connection connection = this.getConnection();
                 PreparedStatement pstmt = connection.prepareStatement(sql)) {
                int comentario_validacion_subida = comment.getValidacion_usuario_comentar()-1;
                pstmt.setString(1,mensaje);
                pstmt.setString(2, String.valueOf(comentario_validacion_subida));
                pstmt.setString(3, String.valueOf(comment.getIdIncidencia()));
                pstmt.setString(4, String.valueOf(comment.getIdUsuario()));
                pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
