package com.example.proyectoingweb.servlets.Inicio;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletIniciarSesion", value = "/ServletIniciarSesion")
public class ServletIniciarSesion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        action = (action == null) ? "iniciarSesion" : action;
        RequestDispatcher requestDispatcher;

        switch (action){
            case "iniciarSesion":
                requestDispatcher = request.getRequestDispatcher("IniciarSesion.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "olvidoContraseña":
                requestDispatcher = request.getRequestDispatcher("OlvidoContraseña.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "inicioUsuario":
                requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "crearCuenta":
                requestDispatcher = request.getRequestDispatcher("InicioRegistrarse.jsp");
                requestDispatcher.forward(request,response);
                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


                String rol = daoUsuarios.obtenerRol(codigo, password, correo);

                switch (rol){
                    case "Usuario PUCP":
                        requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                        requestDispatcher.forward(request,response);
                        break;
                    case "Seguridad":
                        request.setAttribute("correo", correo);
                        requestDispatcher = request.getRequestDispatcher("DobleFactor.jsp");
                        requestDispatcher.forward(request,response);
                        break;
                    default:
                        requestDispatcher = request.getRequestDispatcher("IniciarSesion.jsp");
                        requestDispatcher.forward(request,response);
                        break;
                }


    }
}
