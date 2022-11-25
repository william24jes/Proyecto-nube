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

        String action = request.getParameter("action") == null? "iniciarSesion":request.getParameter("action");
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();

        switch (action) {
            case "iniciarSesion":
                // Verificacion de si ya existe sesion
                if (session.getAttribute("usuarioSession") == null) {
                    if (session.getAttribute("seguridadSession") == null) {
                        if (session.getAttribute("userAdmin") == null) {
                            requestDispatcher = request.getRequestDispatcher("IniciarSesion.jsp");
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

            case "olvidoContraseña":
                requestDispatcher = request.getRequestDispatcher("OlvidoContraseña.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "registrarse":
                requestDispatcher = request.getRequestDispatcher("InicioRegistrarse.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "confirmaRegistro":

                requestDispatcher = request.getRequestDispatcher("CorreoConfirmaRegistro.jsp");
                requestDispatcher.forward(request, response);

                break;

            case "crearContrasena":

                requestDispatcher = request.getRequestDispatcher("CrearContraseña.jsp");
                requestDispatcher.forward(request, response);

                break;

        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String post = request.getParameter("post");
        post = (post == null) ? "iniciosesion" : post;

        Usuarios usuario;
        String password;

        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        RequestDispatcher requestDispatcher;

        switch (post) {
            case "iniciosesion":
                usuario = (Usuarios) request.getSession().getAttribute("usuarioSession");
                if (usuario != null && usuario.getIdUsuarios() != 0) {
                    response.sendRedirect(request.getContextPath());
                } else {
                    requestDispatcher = request.getRequestDispatcher("IniciarSesion.jsp");
                    requestDispatcher.forward(request, response);
                }
                break;
            case "validar":
                String codigo = request.getParameter("codigo");
                password = request.getParameter("password");
                String correo = request.getParameter("correo");

                Usuarios usuarioValido = daoUsuarios.validarUsuarioPassword(codigo, password, correo);

                if (usuarioValido != null) {
                    switch (usuarioValido.getRol()) {
                        case "Usuario PUCP":
                            HttpSession sessionUsuario = request.getSession();
                            sessionUsuario.setAttribute("usuarioSession", usuarioValido);
                            response.sendRedirect(request.getContextPath() + "/Inicio");
                            /*
                            request.setAttribute("listaIncidencias", daoIncidencias.obtenerlistaIncidencias());
                            requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                            requestDispatcher.forward(request,response);
                            */
                            break;
                        case "Seguridad":
                            HttpSession sessionSeguridad = request.getSession();
                            sessionSeguridad.setAttribute("seguridadSession", usuarioValido);
                            response.sendRedirect(request.getContextPath() + "/SeguridadInicio");

                            /*
                            request.setAttribute("correo", correo);
                            requestDispatcher = request.getRequestDispatcher("DobleFactor.jsp");
                            requestDispatcher.forward(request,response);
                             */
                            break;
                        case "Administrador":
                            HttpSession sessionAdmin = request.getSession();
                            sessionAdmin.setAttribute("userAdmin", usuarioValido);
                            response.sendRedirect(request.getContextPath() + "/AdminServlet");
                            break;
                        default:
                            request.getSession().setAttribute("error", "Error en usuario o contraseña");
                            response.sendRedirect(request.getContextPath() + "/ServletIniciarSesion");
                            break;
                    }
                } else {
                    request.getSession().setAttribute("error", "Error en usuario o contraseña");
                    response.sendRedirect(request.getContextPath() + "/ServletIniciarSesion");
                }
                break;

            case "registrarse":

                String correoPucp = request.getParameter("correoPucp");
                String codigoPucp = request.getParameter("codigoPucp");

                usuario = daoUsuarios.validarRegistro(correoPucp, codigoPucp);

                if (usuario != null){
                    // Si existe, enviar correo para crear contraseña

                    String link = "";
                    String mensaje = "Tu registro está casi completo.\n\nIngresa al siguiente link para crear tu contraseña:\n"+link;
                    String asunto = "Crea tu nueva contraseña";

                    daoUsuarios.enviarCorreo(correoPucp, asunto, mensaje);

                    response.sendRedirect(request.getContextPath()+"/ServletIniciarSesion?action=confirmaRegistro");
                }
                else {
                    // Si no existe, enviar por sesion mensaje de error
                    HttpSession session = request.getSession();
                    session.setAttribute("msg", "Correo o código inválido(s)");

                    response.sendRedirect(request.getContextPath()+"/ServletIniciarSesion?action=registrarse");
                }

                break;

            case "guardarContrasena":

                password = request.getParameter("password");
                String passwordConfirmada = request.getParameter("passwordConfirmada");

                break;

            case "dobleFactor":
                response.sendRedirect(request.getContextPath() + "/SeguridadInicio");
                break;
        }
    }
}
