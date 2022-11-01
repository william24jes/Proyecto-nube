package com.example.proyectoingweb.model.daos;

import com.example.proyectoingweb.model.beans.Usuarios;

import java.sql.*;
import java.util.ArrayList;

public class DaoUsuarios {

    public ArrayList<Usuarios> obtenerlistaUsuarios(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "SELECT * FROM mydb.usuarios ORDER BY codigoPucp";
        ArrayList<Usuarios> listaUsuarios = new ArrayList<>();
        try(Connection connection = DriverManager.getConnection(url,"root","root");
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

    public void guardarlistaUsuarios(Usuarios usuarios){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "INSERT INTO usuarios (idUsuario, nombres, apellidos, dni, celular, codigoPucp, correoPucp, categoria, rol) VALUES (?,?,?,?,?,?,?,?,?)";
        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement pstmt=connection.prepareStatement(sql))
            {

            pstmt.setInt(1,usuarios.getIdUsuarios());
            pstmt.setString(2,usuarios.getNombres());
            pstmt.setString(3,usuarios.getApellidos());
            pstmt.setString(4,usuarios.getDni());
            pstmt.setString(5,usuarios.getCelular());
            pstmt.setString(6,usuarios.getCodigoPucp());
            pstmt.setString(7,usuarios.getCorreoPucp());
            pstmt.setString(8,usuarios.getCategorias());
            pstmt.setString(9,usuarios.getRol());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void borrarUsuario(String codigoPucp){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        String sql = "DELETE from usuarios WHERE codigoPucp=?";
        try(Connection connection = DriverManager.getConnection(url,"root","root");
            PreparedStatement pstmt=connection.prepareStatement(sql))
        {

            pstmt.setString(1,codigoPucp);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
