package com.example.usuarioproyecto.model.daos;

import com.example.usuarioproyecto.model.beans.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class DaoUsuario {

    public ArrayList<Usuario> obtenerlistaUsuarios() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/mydb";
        ArrayList<Usuario> lista = new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(url, "root", "123456");
            String sql = "SELECT * FROM usuarios";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                Usuario usuarios = new Usuario();
                usuarios.setUsuarioId(rs.getInt(1));
                usuarios.setNombres(rs.getString((2)));
                usuarios.setApellidos(rs.getString(3));
                usuarios.setDni(rs.getString(4));
                usuarios.setCelular(rs.getString(5));
                usuarios.setCodigoPucp(rs.getString(6));
                usuarios.setCorreoPucp(rs.getString(7));
                usuarios.setCategorias(rs.getString(8));
                lista.add(usuarios);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return lista;

    }
}