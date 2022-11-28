package com.example.proyectoingweb.servlets.ServletsLogin;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletCorreoOlvidoContrasena", value = "/ServletCorreoOlvidoContrasena")
public class ServletCorreoOlvidoContrasena extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        action = (action == null) ? "correoConfirmacion" : action;
        RequestDispatcher requestDispatcher;

        switch (action){
            case "olvidoContraseña":
                requestDispatcher = request.getRequestDispatcher("OlvidoContraseña.jsp");
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
