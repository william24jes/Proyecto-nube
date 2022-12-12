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

        Usuarios usuario = new Usuarios();
        String token;

        switch (action) {
            case "iniciarSesion":
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

            case "olvidoPassword":
                requestDispatcher = request.getRequestDispatcher("OlvidoContraseña.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "confirmaOlvidoPassword":

                requestDispatcher = request.getRequestDispatcher("CorreoOlvidoContraseña.jsp");
                requestDispatcher.forward(request, response);

                break;

            case "reestablecerPassword":

                token = request.getParameter("token");
                usuario = daoUsuarios.buscarPorToken(token);

                if (usuario != null && daoUsuarios.validarToken(usuario.getIdUsuarios()) < 0) {
                    request.setAttribute("token", token);
                    requestDispatcher = request.getRequestDispatcher("ReestablecerContraseña.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("TokenInvalido.jsp");
                    requestDispatcher.forward(request, response);
                }

                break;

            case "passwordReestablecida":

                requestDispatcher = request.getRequestDispatcher("ContraseñaReestablecida.jsp");
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

            case "crearPassword":

                token = request.getParameter("token");
                usuario = daoUsuarios.buscarPorToken(token);

                // * Validar tiempo de expiracion *
                if (usuario != null && daoUsuarios.validarToken(usuario.getIdUsuarios()) < 0) {

                    request.setAttribute("token", token);
                    requestDispatcher = request.getRequestDispatcher("CrearContraseña.jsp");
                    requestDispatcher.forward(request, response);
                } else {
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

        // Caracteres para variar un poco mas los tokens
        String[] caracteres = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "0", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "ñ", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "!", "#", "$", "%", "&", "/", "(", ")", "="};
        Usuarios usuario;
        String password;

        DaoUsuarios daoUsuarios = new DaoUsuarios();
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

                            break;
                        case "Seguridad":

                            // Generar codigo temporal

                            /*Random rnd = new Random();
                            int number = rnd.nextInt(999999);
                            String pin = String.format("%06d", number);

                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                            Date fechaActual = new Date();
                            Calendar cal = Calendar.getInstance();
                            cal.setTime(fechaActual);
                            cal.add(Calendar.MINUTE, 5);

                            String fechaExpiracion = df.format(cal.getTime());

                            daoUsuarios.guardarToken(usuarioValido.getIdUsuarios(), pin, fechaExpiracion);

                            // Enviar codigo mediante correo

                            String asunto = "PIN de Doble Factor";
                            String mensaje = "Tu código de verificación es :"+ pin +
                                    "Este código PIN expirará en 5 minutos.";

                            daoUsuarios.enviarCorreo(usuarioValido.getCorreoPucp(), asunto, mensaje);

                            request.setAttribute("pin", pin);
                            request.setAttribute("idUsuario", usuarioValido.getIdUsuarios());
                            requestDispatcher = request.getRequestDispatcher("DobleFactor.jsp");
                            requestDispatcher.forward(request, response);*/

                            HttpSession sessionSeguridad = request.getSession();
                            sessionSeguridad.setAttribute("seguridadSession", usuarioValido);
                            response.sendRedirect(request.getContextPath() + "/Seguridad");

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

                    Random random = new Random();
                    String token = Hashing.sha256().hashString(correoPucp + codigoPucp + caracteres[random.nextInt(caracteres.length)] + caracteres[random.nextInt(caracteres.length)], StandardCharsets.UTF_8).toString();

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date fechaActual = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fechaActual);
                    cal.add(Calendar.MINUTE, 5);

                    String fechaExpiracion = df.format(cal.getTime());

                    // Validar si ya ha recibido un correo antes
                    if (daoUsuarios.validarToken(usuario.getIdUsuarios()) < 0) {
                        // No ha expirado: Preguntar si quiere recibir un correo nuevo
                        daoUsuarios.borrarToken(usuario.getIdUsuarios());

                    } else if (daoUsuarios.validarToken(usuario.getIdUsuarios()) > 0) {
                        // Ha expirado
                        daoUsuarios.borrarToken(usuario.getIdUsuarios());
                    }

                    daoUsuarios.guardarToken(usuario.getIdUsuarios(), token, fechaExpiracion);

                    String link = "http://localhost:8080" + request.getContextPath() + "/IniciarSesion?action=crearPassword&token=" + token;
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
                    if (daoUsuarios.validarUsuarioEnCredenciales(correoPucp, codigoPucp) != null) {
                        session.setAttribute("msg", "El correo y código ya pertenecen a una cuenta");
                    } else {
                        session.setAttribute("msg", "Correo y/o código inválido(s)");
                    }

                    response.sendRedirect(request.getContextPath() + "/IniciarSesion?action=registrarse");
                }

                break;

            case "guardarPassword":

                String token = request.getParameter("token");
                String nuevaPassword1 = request.getParameter("nuevaPassword1");
                String nuevaPassword2 = request.getParameter("nuevaPassword2");

                usuario = daoUsuarios.buscarPorToken(token);

                if (usuario != null){
                    if (daoUsuarios.validarToken(usuario.getIdUsuarios()) < 0) {
                        if (nuevaPassword1.equals(nuevaPassword2)) {

                            usuario = daoUsuarios.buscarPorToken(token);

                            daoUsuarios.guardarPassword(usuario, nuevaPassword1);
                            daoUsuarios.borrarToken(usuario.getIdUsuarios());

                            response.sendRedirect(request.getContextPath() + "/IniciarSesion?action=passwordCreada");
                            
                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("msgError", "Las contraseñas deben ser iguales");

                            response.sendRedirect(request.getContextPath() + "/IniciarSesion?action=crearPassword&token=" + token);
                        }
                    } else if (daoUsuarios.validarToken(usuario.getIdUsuarios()) > 0) {
                        requestDispatcher = request.getRequestDispatcher("TokenInvalido.jsp");
                        requestDispatcher.forward(request, response);
                    }
                }
                else {

                    response.sendRedirect(request.getContextPath()+"/Error");

                }

                break;

            case "olvidoPassword":

                correoPucp = request.getParameter("correo");
                usuario = daoUsuarios.validarOlvidoContrasena(correoPucp);

                if (usuario != null) {

                    Random random = new Random();
                    String token2 = Hashing.sha256().hashString(correoPucp + caracteres[random.nextInt(caracteres.length)] + caracteres[random.nextInt(caracteres.length)] + caracteres[random.nextInt(caracteres.length)], StandardCharsets.UTF_8).toString();

                    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                    Date fechaActual = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(fechaActual);
                    cal.add(Calendar.MINUTE, 5);

                    String fechaExpiracion = df.format(cal.getTime());

                    daoUsuarios.guardarToken(daoUsuarios.buscarPorCorreo(correoPucp).getIdUsuarios(), token2, fechaExpiracion);

                    String link = "http://localhost:8080" + request.getContextPath() + "/IniciarSesion?action=reestablecerPassword&token=" + token2;
                    String asunto = "Reestablece tu contraseña";
                    String mensaje = "Ingresa al siguiente link para reestablecer tu contraseña:\n\n" +
                            "" + link + "\n\n" +
                            "Este enlace expirará en 5 minutos.";

                    daoUsuarios.enviarCorreo(correoPucp, asunto, mensaje);

                    response.sendRedirect(request.getContextPath() + "/IniciarSesion?action=confirmaOlvidoPassword");

                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("msg", "El correo ingresado no es válido");

                    response.sendRedirect(request.getContextPath() + "/IniciarSesion?action=olvidoContraseña");
                }

                break;

            case "reestablecerPassword":

                String token2 = request.getParameter("token");
                String nuevaPassword11 = request.getParameter("nuevaPassword1");
                String nuevaPassword22 = request.getParameter("nuevaPassword2");

                usuario = daoUsuarios.buscarPorToken(token2);

                if (usuario != null){
                    if (daoUsuarios.validarToken(usuario.getIdUsuarios())<0){
                        if (nuevaPassword11.equals(nuevaPassword22)) {

                            usuario = daoUsuarios.buscarPorToken(token2);

                            daoUsuarios.actualizarPassword(usuario.getIdUsuarios(), nuevaPassword11);
                            daoUsuarios.borrarToken(usuario.getIdUsuarios());

                            response.sendRedirect(request.getContextPath() + "/IniciarSesion?action=passwordReestablecida");

                        } else {
                            HttpSession session = request.getSession();
                            session.setAttribute("msgError", "Las contraseñas deben ser iguales");

                            response.sendRedirect(request.getContextPath() + "/IniciarSesion?action=crearPassword&token=" + token2);
                        }
                    }else if (daoUsuarios.validarToken(usuario.getIdUsuarios())>0){
                        requestDispatcher = request.getRequestDispatcher("TokenInvalido.jsp");
                        requestDispatcher.forward(request, response);
                    }

                }

                break;

            case "dobleFactor":

                String pin = request.getParameter("pin");
                int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));

                if (daoUsuarios.validarToken(idUsuario)<0){

                    String pin1 = request.getParameter("pin1");
                    String pin2 = request.getParameter("pin2");
                    String pin3 = request.getParameter("pin3");
                    String pin4 = request.getParameter("pin4");
                    String pin5 = request.getParameter("pin5");
                    String pin6 = request.getParameter("pin6");

                    String pinIngresado = pin1+pin2+pin3+pin4+pin5+pin6;

                    if (pinIngresado.equals(pin)){

                    }
                    else {

                        
                    }

                } else if (daoUsuarios.validarToken(idUsuario)>0) {

                }

                response.sendRedirect(request.getContextPath() + "/Seguridad");
                break;
        }
    }
}
