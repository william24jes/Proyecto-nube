package com.example.proyectoingweb.servlets.Inicio;

import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.daos.DaoIncidencias;
import com.example.proyectoingweb.servlets.model.daos.DaoUsuarios;
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
        String post = request.getParameter("post");
        post = (post == null) ? "iniciosesion" : post;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        RequestDispatcher requestDispatcher;
        switch(post){
            case "iniciosesion":
                Usuarios usuario = (Usuarios) request.getSession().getAttribute("usuarioSession");
                if(usuario != null && usuario.getIdUsuarios() != 0){
                    response.sendRedirect(request.getContextPath());
                }else{
                    requestDispatcher = request.getRequestDispatcher("IniciarSesion.jsp");
                    requestDispatcher.forward(request,response);
                }
                break;
            case "validar":
                String codigo = request.getParameter("codigo");
                String password = request.getParameter("password");
                String correo = request.getParameter("correo");

                String rol = daoUsuarios.obtenerRol(codigo, password, correo);
                Usuarios usuarioValido = daoUsuarios.validarUsuarioPassword(codigo, password, correo);

                switch (rol){
                    case "Usuario PUCP":
                        if(usuarioValido != null){
                            HttpSession sessionUsuario = request.getSession();
                            sessionUsuario.setAttribute("usuarioSession", usuarioValido);
                            sessionUsuario.setAttribute("listaIncidencias", daoIncidencias.obtenerlistaIncidencias());
                            response.sendRedirect(request.getContextPath()+"/Inicio");
                        }else{
                            response.sendRedirect(request.getContextPath()+"/ServletIniciarSesion?error");
                        }
                        /*
                        request.setAttribute("listaIncidencias", daoIncidencias.obtenerlistaIncidencias());
                        requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                        requestDispatcher.forward(request,response);
                        */
                        break;
                    case "Seguridad":
                        if(usuarioValido != null){
                            HttpSession sessionSeguridad = request.getSession();
                            sessionSeguridad.setAttribute("listaIncidencias", daoIncidencias.obtenerlistaIncidencias());
                            sessionSeguridad.setAttribute("seguridadSession", usuarioValido);
                            response.sendRedirect(request.getContextPath()+"/SeguridadInicio");
                        }
                        /*
                        request.setAttribute("correo", correo);
                        requestDispatcher = request.getRequestDispatcher("DobleFactor.jsp");
                        requestDispatcher.forward(request,response);
                         */
                        break;
                    default:
                        requestDispatcher = request.getRequestDispatcher("IniciarSesion.jsp");
                        requestDispatcher.forward(request,response);
                        break;
                }
                break;
            case "doblef":
                response.sendRedirect(request.getContextPath()+"/SeguridadInicio");
                break;
        }
    }
}
