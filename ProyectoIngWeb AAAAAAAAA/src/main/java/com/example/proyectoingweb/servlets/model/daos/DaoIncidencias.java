package com.example.proyectoingweb.servlets.model.daos;

import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.beans.ZonaPucp;

import java.sql.*;
import java.util.ArrayList;

public class DaoIncidencias {
    public ArrayList<Incidencias> obtenerlistaIncidencias() {
        ArrayList<Incidencias> listaIncidencias = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM mydb.incidencias";

        try (Connection connection = DriverManager.getConnection(url, "root", "123456");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Incidencias incidencias = new Incidencias();

                incidencias.setIdIncidencia(rs.getInt(1));
                incidencias.setIdUsuario(rs.getInt(2));
                incidencias.setIdSeguridad(rs.getInt(3));
                incidencias.setNombre(rs.getString(4));
                incidencias.setDescripcion(rs.getString(5));
                incidencias.setIdZonaPucp(rs.getInt(6));
                incidencias.setTipo(rs.getString(7));
                incidencias.setUbicacion(rs.getString(8));
                incidencias.setFoto(rs.getString(9));
                incidencias.setDestacado(rs.getInt(10));
                incidencias.setDatetime(rs.getString(11));
                incidencias.setAnonimo(rs.getInt(12));
                incidencias.setUrgencia(rs.getString(13));
                incidencias.setEstadoIncidencia(rs.getString(14));
                incidencias.setNumEstrellas(rs.getInt(15));

                listaIncidencias.add(incidencias);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaIncidencias;
    }

    public ArrayList<Incidencias> obtenerlistaIncidenciasDestacadas() {
        ArrayList<Incidencias> listaIncidenciasDestacadas = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT incidencias.* , concat(users.nombres,' ',users.apellidos) as `Nombre de usuario` FROM mydb.incidencias incidencias , mydb.usuarios users where incidencias.destacado = 1 and  incidencias.idUsuario = users.idUsuario;\n";

        try (Connection connection = DriverManager.getConnection(url, "root", "123456");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Incidencias incidencias = new Incidencias();

                incidencias.setIdIncidencia(rs.getInt(1));
                incidencias.setIdUsuario(rs.getInt(2));
                incidencias.setIdSeguridad(rs.getInt(3));
                incidencias.setNombre(rs.getString(4));
                incidencias.setDescripcion(rs.getString(5));
                incidencias.setIdZonaPucp(rs.getInt(6));
                incidencias.setTipo(rs.getString(7));
                incidencias.setUbicacion(rs.getString(8));
                incidencias.setFoto(rs.getString(9));
                incidencias.setDestacado(rs.getInt(10));
                incidencias.setDatetime(rs.getString(11));
                incidencias.setAnonimo(rs.getInt(12));
                incidencias.setUrgencia(rs.getString(13));
                incidencias.setEstadoIncidencia(rs.getString(14));
                incidencias.setNumEstrellas(rs.getInt(15));
                incidencias.setNombreUsuarioQueDestaco(rs.getString(16));



                listaIncidenciasDestacadas.add(incidencias);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaIncidenciasDestacadas;
    }
    public void guardarIncidencias(Incidencias incidencias){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "INSERT INTO incidencias (idUsuario,nombre,descripcion,destacado,tipo,urgencia,idzonaPucp,fechaHora,anonimo,estadoIncidencia) VALUES (?,?,?,?,?,?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,"root","123456");
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,String.valueOf(incidencias.getIdUsuario()));
            pstmt.setString(2,incidencias.getNombre());
            pstmt.setString(3,incidencias.getDescripcion());
            pstmt.setString(4,String.valueOf(incidencias.getDestacado()));
            pstmt.setString(5,incidencias.getTipo());
            pstmt.setString(6,incidencias.getUrgencia());
            pstmt.setString(7,String.valueOf(incidencias.getIdZonaPucp()));
            pstmt.setString(8,incidencias.getDatetime());
            pstmt.setString(9,String.valueOf(incidencias.getAnonimo()));
            pstmt.setString(10,incidencias.getEstadoIncidencia());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public ArrayList<ZonaPucp> obtenerlistaZonasPUCP() {
        ArrayList<ZonaPucp> listaZonaPUCP = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM mydb.zonapucp;";

        try (Connection connection = DriverManager.getConnection(url, "root", "123456");
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ZonaPucp zonas = new ZonaPucp();

                zonas.setIdZonaPucp(rs.getInt(1));
                zonas.setNombreZona(rs.getString(2));
                listaZonaPUCP.add(zonas);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaZonaPUCP;
    }
}
