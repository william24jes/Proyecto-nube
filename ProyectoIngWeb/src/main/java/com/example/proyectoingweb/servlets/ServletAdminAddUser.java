package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.model.beans.Usuarios;
import com.example.proyectoingweb.model.daos.DaoUsuarios;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletAdminAddUser", value = "/add_user")
public class ServletAdminAddUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminAddUser.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoUsuarios daoUsuarios=new DaoUsuarios();


        String nombres= request.getParameter("Nombres");
        String apellidos=request.getParameter("Apellidos");
        String correo=request.getParameter("Correo PUCP");
        String dni=request.getParameter("DNI");
        String celular=request.getParameter("Celular");
        String categoria=request.getParameter("Categoría");
        String rol=request.getParameter("Rol");
        String codigo=request.getParameter("Codigo");
        int idUsuario=Integer.parseInt(dni);

        Usuarios usuarios=new Usuarios();
        usuarios.setIdUsuarios(idUsuario);
        usuarios.setNombres(nombres);
        usuarios.setApellidos(apellidos);
        usuarios.setCorreoPucp(correo);
        usuarios.setDni(dni);
        usuarios.setCelular(celular);
        usuarios.setCategorias(categoria);
        usuarios.setRol(rol);
        usuarios.setCodigoPucp(codigo);
        daoUsuarios.guardarlistaUsuarios(usuarios);

        response.sendRedirect(request.getContextPath()+"/AdminListaUsersServlet");



    }
}
