package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.model.beans.Usuario;
import com.example.proyectoingweb.model.daos.DaoUsuario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletListaUsuarios", value = "/listaUsuarios")
public class ServletListaUsuarios extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DaoUsuario daoUsuario = new DaoUsuario();
        ArrayList<Usuario> list = daoUsuario.obtenerlistaUsuarios();
        request.setAttribute("lista",list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("listaUsuarios.jsp");
        requestDispatcher.forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}