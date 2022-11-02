package com.example.proyectoingweb.servlets;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletSeguridadInicio", value = "/SeguridadInicio")
public class ServletSeguridadInicio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "inicioSeguridad" : action;
        RequestDispatcher requestDispatcher;

        switch (action){
            case "inicioSeguridad":
                requestDispatcher = request.getRequestDispatcher("SeguridadInicio.jsp");
                requestDispatcher.forward(request,response);
                break;

            case "perfil":
                requestDispatcher = request.getRequestDispatcher("SeguridadPerfil.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "misIncidencias":
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "detalles":
                requestDispatcher = request.getRequestDispatcher("Seguridad_incidencias.jsp");
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
