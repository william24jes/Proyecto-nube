package com.example.proyectoingweb.servlets.model.daos;

import com.example.proyectoingweb.servlets.model.beans.Usuarios;

import java.sql.*;
import java.util.ArrayList;

public class DaoUsuarios {
    private String password = "root";

    public ArrayList<Usuarios> obtenerlistaUsuarios(){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM mydb.usuarios ORDER BY codigoPucp";

        try(Connection connection = DriverManager.getConnection(url,"root",password);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while(rs.next()){
                Usuarios usuarios = new Usuarios();

                usuarios.setIdUsuarios(rs.getInt(1));
                usuarios.setNombres(rs.getString(2));
                usuarios.setApellidos(rs.getString(3));
                usuarios.setDni(rs.getString(4));
                usuarios.setCelular(rs.getString(5));
                usuarios.setCodigoPucp(rs.getString(6));
                usuarios.setCorreoPucp(rs.getString(7));
                usuarios.setCategorias(rs.getString(8));
                usuarios.setRol(rs.getString(9));

                listaUsuarios.add(usuarios);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listaUsuarios;
    }

    public Usuarios buscarPorId(String idUsuario){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM usuarios WHERE idUsuario = ?";
        Usuarios usuarios = null;

        try(Connection conn = DriverManager.getConnection(url, "root", password);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, idUsuario);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    usuarios = new Usuarios();

                    usuarios.setIdUsuarios(rs.getInt(1));
                    usuarios.setNombres(rs.getString(2));
                    usuarios.setApellidos(rs.getString(3));
                    usuarios.setDni(rs.getString(4));
                    usuarios.setCelular(rs.getString(5));
                    usuarios.setCodigoPucp(rs.getString(6));
                    usuarios.setCorreoPucp(rs.getString(7));
                    usuarios.setCategorias(rs.getString(8));
                    usuarios.setRol(rs.getString(9));
                }
            }

        }
        catch (SQLException e){
            throw new RuntimeException();
        }

        return usuarios;
    }

    public void guardarUsuario(Usuarios usuarios){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "INSERT INTO usuarios (nombres, apellidos, dni, celular, codigoPucp, correoPucp, categoria, rol, contrasena) VALUES (?,?,?,?,?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,"root",password);
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,usuarios.getNombres());
            pstmt.setString(2,usuarios.getApellidos());
            pstmt.setString(3,usuarios.getDni());
            pstmt.setString(4,usuarios.getCelular());
            pstmt.setString(5,usuarios.getCodigoPucp());
            pstmt.setString(6,usuarios.getCorreoPucp());
            pstmt.setString(7,usuarios.getCategorias());
            pstmt.setString(8,usuarios.getRol());
            pstmt.setNull(9, Types.VARCHAR);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarUsuario(Usuarios usuarios){

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "UPDATE usuarios SET nombres = ?, apellidos = ?, dni = ?, celular = ?, codigoPucp = ?, correoPucp = ?, categoria = ?, rol = ? WHERE idUsuario = ?";

        try(Connection connection = DriverManager.getConnection(url,"root",password);
            PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,usuarios.getNombres());
            pstmt.setString(2,usuarios.getApellidos());
            pstmt.setString(3,usuarios.getDni());
            pstmt.setString(4,usuarios.getCelular());
            pstmt.setString(5,usuarios.getCodigoPucp());
            pstmt.setString(6,usuarios.getCorreoPucp());
            pstmt.setString(7,usuarios.getCategorias());
            pstmt.setString(8,usuarios.getRol());
            pstmt.setInt(9, usuarios.getIdUsuarios());

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

        try(Connection connection = DriverManager.getConnection(url,"root",password);
            PreparedStatement pstmt=connection.prepareStatement(sql))
        {

            pstmt.setString(1,idUsuario);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public ArrayList<Usuarios> buscarUsuarios(String nombreuser){
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM mydb.usuarios WHERE lower(nombres) like ? or lower(apellidos) like ? or dni like ? or codigoPucp like ?";

        try(Connection conn = DriverManager.getConnection(url, "root", password);
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, "%"+nombreuser+"%");
            pstmt.setString(2, "%"+nombreuser+"%");
            pstmt.setString(3, "%"+nombreuser+"%");
            pstmt.setString(4, "%"+nombreuser+"%");

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    Usuarios usuario = new Usuarios();

                    usuario.setIdUsuarios(rs.getInt(1));
                    usuario.setNombres(rs.getString(2));
                    usuario.setApellidos(rs.getString(3));
                    usuario.setDni(rs.getString(4));
                    usuario.setCelular(rs.getString(5));
                    usuario.setCodigoPucp(rs.getString(6));
                    usuario.setCorreoPucp(rs.getString(7));
                    usuario.setCategorias(rs.getString(8));
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
