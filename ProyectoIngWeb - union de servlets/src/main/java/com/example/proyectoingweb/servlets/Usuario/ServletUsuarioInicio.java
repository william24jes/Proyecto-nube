package com.example.proyectoingweb.servlets.Usuario;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletUsuarioInicio", value = "/Inicio")
public class ServletUsuarioInicio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "inicioUsuario" : action;
        RequestDispatcher requestDispatcher;

        switch (action){
            case "inicioUsuario":
                requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "perfil":
                requestDispatcher = request.getRequestDispatcher("UsuarioEditarPerfil.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "misIncidencias":
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "registrarIncidencia":
                requestDispatcher = request.getRequestDispatcher("UsuarioRegistrarIncidencia.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "cerrarSesion":
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

