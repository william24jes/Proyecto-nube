package com.example.proyectoingweb.servlets.Inicio;

import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "ServletHome", urlPatterns ={"/index", ""})
public class ServletHome extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        action = (action == null) ? "home" : action;
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();
        switch (action){
            case "home":
                if (session.getAttribute("usuarioSession") == null) {
                    if (session.getAttribute("seguridadSession") == null) {
                        if (session.getAttribute("userAdmin") == null) {
                            requestDispatcher = request.getRequestDispatcher("index.jsp");
                            requestDispatcher.forward(request, response);
                        }else{
                            if (session.getAttribute("userAdmin") != null) {
                                response.sendRedirect(request.getContextPath() + "/AdminServlet");
                            }
                        }
                    } else {
                        if (session.getAttribute("seguridadSession") != null) {
                            response.sendRedirect(request.getContextPath() + "/SeguridadInicio");
                        }
                    }
                } else {
                    if (session.getAttribute("usuarioSession") != null) {
                        response.sendRedirect(request.getContextPath() + "/Inicio");
                    }

                }
                break;
            case "iniciarSesion":
                requestDispatcher = request.getRequestDispatcher("IniciarSesion.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "registrarse":
                requestDispatcher = request.getRequestDispatcher("InicioRegistrarse.jsp");
                requestDispatcher.forward(request,response);
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
