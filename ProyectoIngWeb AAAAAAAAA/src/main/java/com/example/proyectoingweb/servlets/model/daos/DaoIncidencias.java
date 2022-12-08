package com.example.proyectoingweb.servlets.model.daos;

import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.beans.ZonaPucp;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

public class DaoIncidencias extends DaoBase {
    private static String sql_imagenes = "select foto from mydb2.incidencias where idIncidencia=?";

    public ArrayList<Incidencias> obtenerlistaIncidencias() {
        ArrayList<Incidencias> listaIncidencias = new ArrayList<>();
        String sql = "SELECT * FROM incidencias ORDER BY idIncidencia LIMIT 0,16";

        Usuarios seguridad;
        Usuarios usuario;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();

        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Incidencias incidencias = new Incidencias();

                incidencias.setIdIncidencia(rs.getInt(1));
                usuario = daoUsuarios.buscarPorId(rs.getString(2));
                incidencias.setUsuario(usuario);
                seguridad = daoUsuarios.buscarPorId(rs.getString(3));
                incidencias.setSeguridad(seguridad);
                incidencias.setNombre(rs.getString(4));
                incidencias.setDescripcion(rs.getString(5));
                ZonaPucp zonaPucp = daoZonaPucp.obtenerXId("" + rs.getInt(6) + "");
                incidencias.setZonaPucp(zonaPucp);
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

    public ArrayList<Incidencias> buscarIncidencias(String incidencia){
        ArrayList<Incidencias> listaIncidencias = new ArrayList<>();
        String sql = "SELECT * FROM mydb2.incidencias WHERE lower(nombre) like ? or lower(descripcion) like ? limit 0,15";

        Usuarios seguridad;
        Usuarios usuario;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();
        try(Connection conn = this.getConnection();

            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+incidencia+"%");
            pstmt.setString(2, "%"+incidencia+"%");

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Incidencias incidencias = new Incidencias();

                    incidencias.setIdIncidencia(rs.getInt(1));
                    usuario = daoUsuarios.buscarPorId(rs.getString(2));
                    incidencias.setUsuario(usuario);
                    seguridad = daoUsuarios.buscarPorId(rs.getString(3));
                    incidencias.setSeguridad(seguridad);
                    incidencias.setNombre(rs.getString(4));
                    incidencias.setDescripcion(rs.getString(5));
                    ZonaPucp zonaPucp = daoZonaPucp.obtenerXId("" + rs.getInt(6) + "");
                    incidencias.setZonaPucp(zonaPucp);
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
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaIncidencias;
    }

    public ArrayList<Incidencias> obtenerlistaIncidenciasPDF() {
        ArrayList<Incidencias> listaIncidenciasPDF = new ArrayList<>();
        String sql = "SELECT * FROM incidencias ORDER BY idIncidencia LIMIT 0,16";

        Usuarios seguridad;
        Usuarios usuario;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();

        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Incidencias incidencias = new Incidencias();

                incidencias.setIdIncidencia(rs.getInt(1));


                incidencias.setNombre(rs.getString(4));
                incidencias.setDescripcion(rs.getString(5));
                ZonaPucp zonaPucp = daoZonaPucp.obtenerXId("" + rs.getInt(6) + "");
                incidencias.setZonaPucp(zonaPucp);
                incidencias.setTipo(rs.getString(7));



                incidencias.setDatetime(rs.getString(11));

                incidencias.setUrgencia(rs.getString(13));
                incidencias.setEstadoIncidencia(rs.getString(14));


                listaIncidenciasPDF.add(incidencias);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaIncidenciasPDF;
    }

    public ArrayList<Incidencias> paginarIncidencias(int i) {
        ArrayList<Incidencias> listaIncidencias = new ArrayList<>();
        int inicio = 16 * (i - 1);

        Usuarios seguridad;
        Usuarios usuario;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();
        String sql = "SELECT * FROM incidencias ORDER BY idIncidencia LIMIT " + inicio + "," + "16";

        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Incidencias incidencias = new Incidencias();

                incidencias.setIdIncidencia(rs.getInt(1));
                usuario = daoUsuarios.buscarPorId(rs.getString(2));
                incidencias.setUsuario(usuario);
                seguridad = daoUsuarios.buscarPorId(rs.getString(3));
                incidencias.setSeguridad(seguridad);
                incidencias.setNombre(rs.getString(4));
                incidencias.setDescripcion(rs.getString(5));
                ZonaPucp zonaPucp = daoZonaPucp.obtenerXId("" + rs.getInt(6) + "");
                incidencias.setZonaPucp(zonaPucp);
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

    public ArrayList<Incidencias> obtenerlistaIncidenciasCompleta() {
        ArrayList<Incidencias> listaIncidencias = new ArrayList<>();
        String sql = "SELECT * FROM incidencias ORDER BY idIncidencia";

        Usuarios seguridad;
        Usuarios usuario;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();

        try (Connection connection = this.getConnection();
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Incidencias incidencias = new Incidencias();

                incidencias.setIdIncidencia(rs.getInt(1));
                usuario = daoUsuarios.buscarPorId(rs.getString(2));
                incidencias.setUsuario(usuario);
                seguridad = daoUsuarios.buscarPorId(rs.getString(3));
                incidencias.setSeguridad(seguridad);
                incidencias.setNombre(rs.getString(4));
                incidencias.setDescripcion(rs.getString(5));
                ZonaPucp zonaPucp = daoZonaPucp.obtenerXId("" + rs.getInt(6) + "");
                incidencias.setZonaPucp(zonaPucp);
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

    /*
        public ArrayList<Incidencias> obtenerlistaIncidenciasDestacadas() {
            ArrayList<Incidencias> listaIncidenciasDestacadas = new ArrayList<>();
            DaoUsuarios daoUsuarios = new DaoUsuarios();
            DaoZonaPucp daoZonaPucp = new DaoZonaPucp();

            String sql = "SELECT incidencias.* , concat(users.nombres,' ',users.apellidos) as `Nombre de usuario` FROM mydb.incidencias incidencias , mydb.usuarios users where incidencias.destacado = 1 and  incidencias.idUsuario = users.idUsuario;\n";

            try (Connection connection = this.getConnection();
                 Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Incidencias incidencias = new Incidencias();

                    incidencias.setIdIncidencia(rs.getInt(1));
                    incidencias.setUsuario(daoUsuarios.buscarPorId(""+rs.getInt(2)+""));
                    incidencias.setSeguridad(daoUsuarios.buscarPorId(rs.getString(3)));
                    incidencias.setNombre(rs.getString(4));
                    incidencias.setDescripcion(rs.getString(5));
                    ZonaPucp zonaPucp = daoZonaPucp.obtenerXId(""+rs.getInt(6)+"");
                    incidencias.setZonaPucp(zonaPucp);
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
        */
    public void guardarIncidencias(Incidencias incidencias, InputStream file) {
        String sql = "INSERT INTO mydb2.incidencias (idUsuario,nombre,descripcion,destacado,tipo,urgencia,idzonaPucp,fechaHora,anonimo,estadoIncidencia,foto,longitud,latitud,idIncidencia) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, String.valueOf(incidencias.getUsuario().getIdUsuarios()));
            pstmt.setString(2, incidencias.getNombre());
            pstmt.setString(3, incidencias.getDescripcion());
            pstmt.setString(4, "0");
            pstmt.setString(5, incidencias.getTipo());
            pstmt.setString(6, incidencias.getUrgencia());
            pstmt.setString(7, String.valueOf(incidencias.getZonaPucp().getIdZonaPucp()));
            pstmt.setString(8, incidencias.getDatetime());
            pstmt.setString(9, String.valueOf(incidencias.getAnonimo()));
            pstmt.setString(10, "Registrado");
            pstmt.setString(12,incidencias.getLongitud());
            pstmt.setString(13,incidencias.getLatitud());
            pstmt.setString(14, String.valueOf(incidencias.getIdIncidencia()));

            if (file != null) {
                pstmt.setBlob(11, file);
            }
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Incidencias buscarPorId(String IDincidencias) {

        Incidencias incidencias = new Incidencias();
        Usuarios usuarios = null;
        Usuarios seguridad = null;

        DaoUsuarios daoUsuarios = new DaoUsuarios();

        String sql = "SELECT * FROM incidencias where idIncidencia = ?";
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, IDincidencias);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    incidencias = new Incidencias();
                    incidencias.setIdIncidencia(Integer.parseInt(IDincidencias));
                    usuarios = daoUsuarios.buscarPorId(rs.getString(2));
                    incidencias.setUsuario(usuarios);
                    incidencias.setNombreUsuarioQueDestaco(usuarios.getNombreYApellido());
                    incidencias.setIdUsuarioQueCreoIncidencia(rs.getInt(2));
                    seguridad = daoUsuarios.buscarPorId(rs.getString(3));
                    incidencias.setSeguridad(seguridad);
                    incidencias.setNombre(rs.getString(4));
                    incidencias.setDescripcion(rs.getString(5));
                    ZonaPucp zonaPucp = daoZonaPucp.obtenerXId("" + rs.getInt(6) + "");
                    incidencias.setZonaPucp(zonaPucp);
                    incidencias.setTipo(rs.getString(7));
                    incidencias.setUbicacion(rs.getString(8));
                    incidencias.setFoto(rs.getString(9));
                    incidencias.setDestacado(rs.getInt(10));
                    incidencias.setDatetime(rs.getString(11));
                    incidencias.setAnonimo(rs.getInt(12));
                    incidencias.setUrgencia(rs.getString(13));
                    incidencias.setEstadoIncidencia(rs.getString(14));
                    incidencias.setNumEstrellas(rs.getInt(15));
                    incidencias.setLatitud(rs.getString(16));
                    incidencias.setLongitud(rs.getString(17));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return incidencias;
    }

    public ArrayList<Incidencias> incidenciasDestXUser(String idUsuario) {
        ArrayList<Incidencias> lista = new ArrayList<>();
        Incidencias incidencias;
        String sql = "SELECT idIncidencia FROM incidencias_destacadas WHERE idUsuario = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, idUsuario);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    incidencias = this.buscarPorId(rs.getString(1));
                    lista.add(incidencias);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;
    }

    public void destacarIncidencia_para_idUsuario(String idUser, String idIncidencia) {
        String sql = "Insert into mydb2.incidencias_destacadas (idUsuario, idIncidencia) Values (?,?)";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, idUser);
            pstmt.setString(2, idIncidencia);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void destacar_en_tabla_incidencias(String idIncidencia,String Cantidad_destacados) {
        String sql = "update mydb2.incidencias set destacado = ? where idIncidencia = ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(2, idIncidencia);
            int cantidad_destacados_actualizada = Integer.parseInt(Cantidad_destacados)+1;
            pstmt.setString(1, String.valueOf(cantidad_destacados_actualizada));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Usuarios> IdDeUsuariosQueDestacaron(String idIncidencia) {
        ArrayList<Usuarios> lista_Usuarios = new ArrayList<>();
        Usuarios usuarios;
        DaoUsuarios daoUsers = new DaoUsuarios();
        String sql = "SELECT nombres,apellidos, incidencias.idIncidencia, usuarios.idUsuario,incidencias.nombre FROM mydb2.incidencias_destacadas, usuarios,incidencias\n" +
                " where usuarios.idUsuario = incidencias_destacadas.idUsuario and incidencias.idIncidencia = incidencias_destacadas.idIncidencia\n" +
                " and incidencias.idIncidencia=?\n" +
                " order by idIncidencia";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, idIncidencia);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    usuarios = daoUsers.buscarPorId(rs.getString(4));
                    lista_Usuarios.add(usuarios);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista_Usuarios;
    }
    public void destacarIncidencia_para_idUsuario_negativo(String idUser, String idIncidencia) {
        String sql = "DELETE from mydb2.incidencias_destacadas where idUsuario = ? and  idIncidencia = ?";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, idUser);
            pstmt.setString(2, idIncidencia);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void destacar_en_tabla_incidencias_negativo(String idIncidencia,String Cantidad_destacados) {
        String sql = "update mydb2.incidencias set destacado = ? where idIncidencia = ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(2, idIncidencia);
            int cantidad_destacados_actualizada = Integer.parseInt(Cantidad_destacados)-1;
            pstmt.setString(1, String.valueOf(cantidad_destacados_actualizada));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public void actualizar_estado(String nuevo_estado, String id_incidencia) {
        String sql = "update mydb2.incidencias set estadoIncidencia = ? where idIncidencia = ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, nuevo_estado);
            pstmt.setString(2, id_incidencia);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void actualizar_idSecurity_from_incidencias(String id_security, String id_incidencia) {
        String sql = "update mydb2.incidencias set idSeguridad = ? where idIncidencia = ?;";
        try (Connection connection = this.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id_security);
            pstmt.setString(2, id_incidencia);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
