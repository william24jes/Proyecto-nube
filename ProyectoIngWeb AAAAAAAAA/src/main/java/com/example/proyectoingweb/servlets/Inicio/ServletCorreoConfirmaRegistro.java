package com.example.proyectoingweb.servlets.Inicio;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletCorreoConfirmaRegistro", value = "/ServletCorreoConfirmaRegistro")
public class ServletCorreoConfirmaRegistro extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        action = (action == null) ? "confirmaRegistro" : action;
        RequestDispatcher requestDispatcher;

        switch (action){
            case "confirmaRegistro":
                requestDispatcher = request.getRequestDispatcher("CorreoConfirmaRegistro.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "recibirCorreo":
                requestDispatcher = request.getRequestDispatcher("CorreoOlvidoContraseña.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "cancelar":
                requestDispatcher = request.getRequestDispatcher("IniciarSesion.jsp");
                requestDispatcher.forward(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
