package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.model.beans.Usuarios;
import com.example.proyectoingweb.model.daos.DaoUsuarios;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminListaUsersServlet", urlPatterns = "/AdminListaUsersServlet")
public class AdminListaUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaoUsuarios daoUsuarios = new DaoUsuarios();
        ArrayList<Usuarios> listaUsuarios = daoUsuarios.obtenerlistaUsuarios();

        request.setAttribute("lista", listaUsuarios);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
