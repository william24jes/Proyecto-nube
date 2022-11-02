package com.proyectoingweb.model.daos;

import com.proyectoingweb.model.beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class DaoUsuarios {

    public ArrayList<Usuario> obtenerlistaUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM mydb.usuarios ORDER BY codigoPucp";

        try(Connection connection = DriverManager.getConnection(url,"root","root");
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Usuario usuario = new Usuario();

                usuario.setIdUsuario(rs.getInt(1));
                usuario.setNombres(rs.getString(2));
                usuario.setApellidos(rs.getString(3));
                usuario.setDni(rs.getString(4));
                usuario.setCelular(rs.getString(5));
                usuario.setCodigoPucp(rs.getString(6));
                usuario.setCorreoPucp(rs.getString(7));
                usuario.setCategoria(rs.getString(8));
                usuario.setRol(rs.getString(9));

                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }

    public Usuario buscarPorId(String idUsuario){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        Usuario usuario = null;

        try(Connection conn = DriverManager.getConnection(url, "root", "root");
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idUsuario);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    usuario = new Usuario();

                    usuario.setIdUsuario(rs.getInt(1));
                    usuario.setNombres(rs.getString(2));
                    usuario.setApellidos(rs.getString(3));
                    usuario.setDni(rs.getString(4));
                    usuario.setCelular(rs.getString(5));
                    usuario.setCodigoPucp(rs.getString(6));
                    usuario.setCorreoPucp(rs.getString(7));
                    usuario.setCategoria(rs.getString(8));
                    usuario.setRol(rs.getString(9));
                }
            }

        }
        catch (SQLException e){
            throw new RuntimeException();
        }

        return usuario;
    }

    public void guardarUsuario(Usuario usuario){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "INSERT INTO usuarios (nombres, apellidos, dni, celular, codigoPucp, correoPucp, categoria, rol, contrasena) VALUES (?,?,?,?,?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,usuario.getNombres());
            pstmt.setString(2,usuario.getApellidos());
            pstmt.setString(3,usuario.getDni());
            pstmt.setString(4,usuario.getCelular());
            pstmt.setString(5,usuario.getCodigoPucp());
            pstmt.setString(6,usuario.getCorreoPucp());
            pstmt.setString(7,usuario.getCategoria());
            pstmt.setString(8,usuario.getRol());
            pstmt.setNull(9, Types.VARCHAR);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarUsuario(Usuario usuario){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "UPDATE usuarios SET nombres = ?, apellidos = ?, dni = ?, celular = ?, codigoPucp = ?, correoPucp = ?, categoria = ?, rol = ? WHERE idUsuario = ?";

        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,usuario.getNombres());
            pstmt.setString(2,usuario.getApellidos());
            pstmt.setString(3,usuario.getDni());
            pstmt.setString(4,usuario.getCelular());
            pstmt.setString(5,usuario.getCodigoPucp());
            pstmt.setString(6,usuario.getCorreoPucp());
            pstmt.setString(7,usuario.getCategoria());
            pstmt.setString(8,usuario.getRol());
            pstmt.setInt(9, usuario.getIdUsuario());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void borrarUsuario(String idUsuario){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "DELETE from usuarios WHERE idUsuario = ?";

        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement pstmt=connection.prepareStatement(sql))
        {

            pstmt.setString(1,idUsuario);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Usuario> buscarUsuarios(String nombreuser){
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM mydb.usuarios WHERE lower(nombres) like ? or lower(apellidos) like ? ";

        try(Connection conn = DriverManager.getConnection(url, "root", "root");
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+nombreuser+"%");
            pstmt.setString(2, "%"+nombreuser+"%");

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Usuario usuario = new Usuario();

                    usuario.setIdUsuario(rs.getInt(1));
                    usuario.setNombres(rs.getString(2));
                    usuario.setApellidos(rs.getString(3));
                    usuario.setDni(rs.getString(4));
                    usuario.setCelular(rs.getString(5));
                    usuario.setCodigoPucp(rs.getString(6));
                    usuario.setCorreoPucp(rs.getString(7));
                    usuario.setCategoria(rs.getString(8));
                    usuario.setRol(rs.getString(9));

                    listaUsuarios.add(usuario);

                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaUsuarios;
    }
}
