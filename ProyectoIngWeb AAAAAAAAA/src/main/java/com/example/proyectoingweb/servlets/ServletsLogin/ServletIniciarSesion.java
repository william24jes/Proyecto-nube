package com.example.proyectoingweb.servlets.ServletsLogin;

import com.example.proyectoingweb.servlets.model.beans.Credenciales;
import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.daos.DaoIncidencias;
import com.example.proyectoingweb.servlets.model.daos.DaoUsuarios;
import com.google.common.hash.Hashing;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@WebServlet(name = "ServletIniciarSesion", value = "/IniciarSesion")
public class ServletIniciarSesion extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "iniciarSesion" : request.getParameter("action");
        RequestDispatcher requestDispatcher;
        HttpSession session = request.getSession();

        DaoUsuarios daoUsuarios = new DaoUsuarios();

        switch (action) {
            case "iniciarSesion":
                /*
                if (session.getAttribute("usuario") != null) {
                    switch (((Usuarios) session.getAttribute("usuario")).getCategorias()) {
                        case "Administrador":
                            response.sendRedirect(request.getContextPath() + "/Admin");
                            break;
                        case "Seguridad":
                            response.sendRedirect(request.getContextPath() + "/Seguridad");
                            break;
                        case "Usuario Pucp":
                            response.sendRedirect(request.getContextPath() + "/Inicio");
                            break;
                    }
                }else {
                    response.sendRedirect(request.getContextPath()+"/IniciarSesion");
                }
                */
                if (session.getAttribute("usuarioSession") == null) {
                    if (session.getAttribute("seguridadSession") == null) {
                        if (session.getAttribute("userAdmin") == null) {
                            requestDispatcher = request.getRequestDispatcher("IniciarSesion.jsp");
                            requestDispatcher.forward(request, response);
                        } else {
                            if (session.getAttribute("userAdmin") != null) {
                                response.sendRedirect(request.getContextPath() + "/Admin");
                            }
                        }
                    } else {
                        if (session.getAttribute("seguridadSession") != null) {
                            response.sendRedirect(request.getContextPath() + "/Seguridad");
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

            case "reestablecerContraseña":
                

                break;

            case "registrarse":
                requestDispatcher = request.getRequestDispatcher("InicioRegistrarse.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "confirmaRegistro":

                requestDispatcher = request.getRequestDispatcher("CorreoConfirmaRegistro.jsp");
                requestDispatcher.forward(request, response);

                break;

            case "crearPassword":

                String token = request.getParameter("token");
                Usuarios usuario = daoUsuarios.buscarPorToken(token);

                // * Validar tiempo de expiracion *
                if (usuario != null && daoUsuarios.validarToken(usuario.getIdUsuarios())<0){

                    request.setAttribute("token", token);
                    requestDispatcher = request.getRequestDispatcher("CrearContraseña.jsp");
                    requestDispatcher.forward(request, response);
                }
                else {
                    // Vista de Token expirado o invalido
                    requestDispatcher = request.getRequestDispatcher("TokenInvalido.jsp");
                    requestDispatcher.forward(request, response);
                }

                break;

            case "passwordCreada":

                requestDispatcher = request.getRequestDispatcher("ContraseñaEstablecida.jsp");
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
            case "validar": //Clase 12 - 01:00:34

                String codigo = request.getParameter("codigo");
                password = request.getParameter("password");
                String correo = request.getParameter("correo");

                Credenciales credenciales = daoUsuarios.validarUsuarioPassword(codigo, password, correo);

                if (credenciales != null) {

                    Usuarios usuarioValido = daoUsuarios.buscarPorId(credenciales.getIdUsuario());

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
                            response.sendRedirect(request.getContextPath() + "/Seguridad");

                            /*
                            request.setAttribute("correo", correo);
                            requestDispatcher = request.getRequestDispatcher("DobleFactor.jsp");
                            requestDispatcher.forward(request,response);
                             */
                            break;
                        case "Administrador":
                            HttpSession sessionAdmin = request.getSession();
                            sessionAdmin.setAttribute("userAdmin", usuarioValido);
                            response.sendRedirect(request.getContextPath() + "/Admin");
                            break;
                        default:
                            request.getSession().setAttribute("error", "Error en usuario o contraseña");
                            response.sendRedirect(request.getContextPath() + "/IniciarSesion");
                            break;
                    }
                } else {
                    request.getSession().setAttribute("error", "Error en usuario o contraseña");
                    response.sendRedirect(request.getContextPath() + "/IniciarSesion");
                }
                break;

            case "registrarse":

                String correoPucp = request.getParameter("correoPucp");
                String codigoPucp = request.getParameter("codigoPucp");

                usuario = daoUsuarios.validarRegistro(correoPucp, codigoPucp);

                if (usuario != null) {

                    String[] caracteres = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "!", "#", "$", "%", "&", "/", "(", ")", "="};
                    Random random = new Random();
                    String token = Hashing.sha256().hashString(correoPucp + codigoPucp + caracteres[random.nextInt(caracteres.length)] + caracteres[random.nextInt(caracteres.length)], StandardCharsets.UTF_8).toString();

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date fechaActual = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fechaActual);
                    cal.add(Calendar.MINUTE, 5);

                    String fechaExpiracion = df.format(cal.getTime());

                    // Validar si ya ha recibido un correo antes
                    if (daoUsuarios.validarToken(usuario.getIdUsuarios())<0){
                        // No ha expirado: Preguntar si quiere recibir un correo nuevo

                        daoUsuarios.borrarToken(usuario.getIdUsuarios());

                        /*request.setAttribute("token", token);
                        request.setAttribute("fechaExpiracion", fechaExpiracion);
                        request.setAttribute("correoPucp", correoPucp);

                        requestDispatcher = request.getRequestDispatcher("TokenValidacion.jsp");
                        requestDispatcher.forward(request, response);*/

                    } else if (daoUsuarios.validarToken(usuario.getIdUsuarios())>0) {
                        // Ha expirado
                        daoUsuarios.borrarToken(usuario.getIdUsuarios());
                    }

                    daoUsuarios.guardarToken(usuario.getIdUsuarios(), token, fechaExpiracion);

                    String link = "http://localhost:8080"+request.getContextPath()+"/IniciarSesion?action=crearPassword&token=" + token;
                    String asunto = "Crea tu nueva contraseña";
                    String mensaje = "Tu registro está casi completo.\n\n" +
                            "Ingresa al siguiente link para crear tu contraseña:\n\n" +
                            "" + link + "\n\n" +
                            "Este enlace expirará en 5 minutos.";

                    daoUsuarios.enviarCorreo(correoPucp, asunto, mensaje);

                    response.sendRedirect(request.getContextPath() + "/IniciarSesion?action=confirmaRegistro");

                } else {
                    // Si no existe, enviar por sesion mensaje de error
                    HttpSession session = request.getSession();
                    if (daoUsuarios.validarUsuarioEnCredenciales(correoPucp, codigoPucp) != null){
                        session.setAttribute("msg", "El correo y código ya pertenecen a una cuenta");
                    }
                    else {
                        session.setAttribute("msg", "Correo y/o código inválido(s)");
                    }

                    response.sendRedirect(request.getContextPath() + "/IniciarSesion?action=registrarse");
                }

                break;

            /*case "enviarCorreo":

                String token1 = request.getParameter("token");
                String correoPucp1 = request.getParameter("correoPucp");
                String fechaExpiracion = request.getParameter("fechaExpiracion");

                daoUsuarios.borrarToken(daoUsuarios.buscarPorCorreo(correoPucp1).getIdUsuarios());
                daoUsuarios.guardarToken(daoUsuarios.buscarPorCorreo(correoPucp1).getIdUsuarios(), token1, fechaExpiracion);

                break;*/

            case "guardarPassword":

                String token = request.getParameter("token");
                String nuevaPassword1 = request.getParameter("nuevaPassword1");
                String nuevaPassword2 = request.getParameter("nuevaPassword2");

                if (nuevaPassword1.equals(nuevaPassword2)){

                    usuario = daoUsuarios.buscarPorToken(token);
                    if (usuario != null){
                        daoUsuarios.guardarPassword(usuario, nuevaPassword1);
                        daoUsuarios.borrarToken(usuario.getIdUsuarios());

                        response.sendRedirect(request.getContextPath()+"/IniciarSesion?action=passwordCreada");
                    }
                    else {
                        response.sendRedirect(request.getContextPath()+"/IniciarSesion");
                    }

                }
                else {
                    HttpSession session = request.getSession();
                    session.setAttribute("msgError", "Las contraseñas deben ser iguales");

                    response.sendRedirect(request.getContextPath()+"/IniciarSesion?action=crearPassword&token="+token);
                }

                break;

            case "olvidoContraseña":

                correoPucp = request.getParameter("correo");
                usuario = daoUsuarios.validarOlvidoContrasena(correoPucp);

                if (usuario != null){

                    String token2 = "";
                    String link = "http://localhost:8080"+request.getContextPath()+"/IniciarSesion?action=reestablecerContraseña&token="+token2;
                    String asunto = "Reestablece tu contraseña";
                    String mensaje = "";



                }
                else {
                    HttpSession session = request.getSession();
                    session.setAttribute("msg", "El correo ingresado no es válido");

                    response.sendRedirect(request.getContextPath()+"/IniciarSesion?action=olvidoContraseña");
                }

                break;

            case "dobleFactor":
                response.sendRedirect(request.getContextPath() + "/Seguridad");
                break;
        }
    }
}
