package com.example.proyectoingweb.servlets.Usuario;

import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.beans.ZonaPucp;
import com.example.proyectoingweb.servlets.model.daos.DaoIncidencias;
import com.example.proyectoingweb.servlets.model.daos.DaoUsuarios;
import com.example.proyectoingweb.servlets.model.daos.DaoZonaPucp;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "ServletUsuarioInicio", value = "/Inicio")
@MultipartConfig(maxFileSize = 16177215)

public class ServletUsuarioInicio extends HttpServlet {

    private ArrayList<Incidencias> listaPermanente;
    private ArrayList<Incidencias> listaPaginada;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "listar" : action;
        RequestDispatcher requestDispatcher;
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();
        String idIncidencia;
        Incidencias incidencia;
        int idPage;
        HttpSession session = request.getSession();
        switch (action) {
            case "listar":
                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.obtenerlistaIncidencias());
                request.setAttribute("listaIncidenciasPermanente", daoIncidencias.obtenerlistaIncidenciasCompleta());
                setListaPermanente(daoIncidencias.obtenerlistaIncidenciasCompleta());
                requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                requestDispatcher.forward(request, response);

                break;
            case "perfil":
                requestDispatcher = request.getRequestDispatcher("UsuarioEditarPerfil.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "misIncidencias":
                HttpSession sessionUsuario = request.getSession();
                Usuarios user = (Usuarios) sessionUsuario.getAttribute("usuarioSession");
                request.setAttribute("listaIncidenciasDestacadas", daoIncidencias.incidenciasDestXUser("" + user.getIdUsuarios() + ""));
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "registrarIncidencia":
                request.setAttribute("listaZonasPUCP", daoZonaPucp.obtenerListaZonaPucp());
                requestDispatcher = request.getRequestDispatcher("UsuarioRegistrarIncidencia.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "cerrarSesion":
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                /*
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
                */
                break;
            case "verIncidencia":

                idIncidencia = request.getParameter("id");
                incidencia = daoIncidencias.buscarPorId(idIncidencia);

                if (incidencia != null) { //abro el form para editar
                    request.setAttribute("incidencia_send_jsp", incidencia);
                    requestDispatcher = request.getRequestDispatcher("UsuarioVerIncidencia.jsp");
                    requestDispatcher.forward(request, response);
                } else { //id no encontrado
                    response.sendRedirect(request.getContextPath() + "/Inicio?action=listar");
                }
                break;

            case "page":
                idPage = Integer.parseInt(request.getParameter("id"));
                setListaPaginada(daoIncidencias.paginarIncidencias(idPage));
                request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                request.setAttribute("listaIncidenciasPaginada", getListaPaginada());

                requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                requestDispatcher.forward(request, response);

                break;

        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        Incidencias incidencias = new Incidencias();
        Usuarios usuario;
        ZonaPucp zonaPucp;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        DaoZonaPucp daoZonaPucp = new DaoZonaPucp();
        ArrayList<Usuarios> lista_usuarios;
        switch (action) {
            case "guardar":
                InputStream inputStream; // input stream of the upload file
                String idusuario = request.getParameter("id");
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String tipo = request.getParameter("tipo");
                String nivel = request.getParameter("nivel");
                String zona = request.getParameter("zona");
                Part filePart = request.getPart("foto1");
                inputStream = filePart.getInputStream();
                if (filePart != null) {
                    // prints out some information for debugging
                    System.out.println(filePart.getContentType());

                    // obtains input stream of the upload file
                    inputStream = filePart.getInputStream();
                }
                usuario = daoUsuarios.buscarPorId(idusuario);
                incidencias.setUsuario(usuario);
                incidencias.setNombre(nombre);
                incidencias.setDescripcion(descripcion);
                incidencias.setDestacado(1);
                incidencias.setTipo(tipo);
                incidencias.setUrgencia(nivel);
                zonaPucp = daoZonaPucp.obtenerXId(zona);
                incidencias.setZonaPucp(zonaPucp);
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);
                incidencias.setDatetime(formattedDate);
                incidencias.setAnonimo(0);
                incidencias.setEstadoIncidencia("Registrado");
                daoIncidencias.guardarIncidencias(incidencias, inputStream);
                response.sendRedirect(request.getContextPath() + "/Inicio?action=misIncidencias");
                break;
            case "DestacarIncidencia": {
                String idUsuario2 = request.getParameter("id");
                String Cantidad_destacados = request.getParameter("Cantidad_destacados");
                String idIncidencia = request.getParameter("idIncidencia");
                Usuarios usuario2 = daoUsuarios.buscarPorId(idUsuario2);//indica el usuario de la sesion
                lista_usuarios = daoIncidencias.IdDeUsuariosQueDestacaron(idIncidencia);
                boolean validacion = Usuario_destaco_o_no(lista_usuarios, usuario2);
                if (validacion) {
                    daoIncidencias.destacarIncidencia_para_idUsuario_negativo(idUsuario2,idIncidencia);
                    daoIncidencias.destacar_en_tabla_incidencias_negativo(idIncidencia,Cantidad_destacados);
                    response.sendRedirect(request.getContextPath() + "/Inicio");
                    break;
                } else {
                    daoIncidencias.destacarIncidencia_para_idUsuario(idUsuario2, idIncidencia);
                    daoIncidencias.destacar_en_tabla_incidencias(idIncidencia, Cantidad_destacados);
                    response.sendRedirect(request.getContextPath() + "/Inicio");
                    break;
                }
            }
            case "CambiarTelefono":{
                String idUsuario3 = request.getParameter("id");
                String nuevo_celular = request.getParameter("phone");
                Usuarios user_a_cambiar = daoUsuarios.buscarPorId(idUsuario3);
                user_a_cambiar.setCelular(nuevo_celular);
                DaoUsuarios daoUsuarios1 = new DaoUsuarios();
                daoUsuarios1.actualizar_usuario_telefono(user_a_cambiar);
                response.sendRedirect(request.getContextPath() + "/Inicio?action=perfil");
            break;
            }
        }
    }

    public boolean Usuario_destaco_o_no(ArrayList<Usuarios> lista_users_que_destacan, Usuarios usuario_sesion) {
        for (Usuarios lista_destacaron : lista_users_que_destacan) {
            if (lista_destacaron.getIdUsuarios() == usuario_sesion.getIdUsuarios()) {
                //significa que el usuario ha destacado, entonces a volver a casa
                return true;
            }
        }
        return false;
    }

    public ArrayList<Incidencias> getListaPermanente() {
        return listaPermanente;
    }

    public void setListaPermanente(ArrayList<Incidencias> listaPermanente) {
        this.listaPermanente = listaPermanente;
    }

    public ArrayList<Incidencias> getListaPaginada() {
        return listaPaginada;
    }

    public void setListaPaginada(ArrayList<Incidencias> listaPaginada) {
        this.listaPaginada = listaPaginada;
    }
}

