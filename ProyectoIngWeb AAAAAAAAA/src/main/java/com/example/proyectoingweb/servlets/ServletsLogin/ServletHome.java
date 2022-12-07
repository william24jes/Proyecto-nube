package com.example.proyectoingweb.servlets.ServletsLogin;

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
                    if (session.getAttribute("usuarioSession") == null) {
                        if (session.getAttribute("usuarioSession") == null) {
                            requestDispatcher = request.getRequestDispatcher("index.jsp");
                            requestDispatcher.forward(request, response);
                        }else{
                            if (session.getAttribute("usuarioSession") != null) {
                                response.sendRedirect(request.getContextPath() + "/Admin");
                            }
                        }
                    } else {
                        if (session.getAttribute("usuarioSession") != null) {
                            response.sendRedirect(request.getContextPath() + "/Seguridad");
                        }
                    }
                } else {
                    if (session.getAttribute("usuarioSession") != null) {
                        response.sendRedirect(request.getContextPath() + "/Inicio");
                    }

                }
                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
