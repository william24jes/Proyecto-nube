package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.servlets.model.beans.Comentarios;
import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.beans.ZonaPucp;
import com.example.proyectoingweb.servlets.model.daos.*;
import com.example.proyectoingweb.servlets.model.dtos.ReporteIncidenciasPDF;
import com.itextpdf.text.DocumentException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "ServletSeguridadInicio", value = "/Seguridad")
public class ServletSeguridadInicio extends HttpServlet {

    private ArrayList<Incidencias> listaPermanente;
    private ArrayList<Incidencias> listaPaginada;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "inicioSeguridad" : action;
        RequestDispatcher requestDispatcher;
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        Incidencias incidencia;
        Comentarios comentario;
        Usuarios usuario = new Usuarios();
        DaoComentarios daoComentarios = new DaoComentarios();
        HttpSession session = request.getSession();
        ReporteIncidenciasPDF reporte = new ReporteIncidenciasPDF();
        int idPage;
        switch (action) {
            case "inicioSeguridad":
                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.obtenerlistaIncidencias());
                request.setAttribute("listaIncidenciasPermanente", daoIncidencias.obtenerlistaIncidenciasCompleta());
                setListaPermanente(daoIncidencias.obtenerlistaIncidenciasCompleta());
                requestDispatcher = request.getRequestDispatcher("SeguridadInicio.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "perfil":
                requestDispatcher = request.getRequestDispatcher("SeguridadPerfil.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "misIncidencias":
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "detalles":

                Usuarios seguridad = (Usuarios) session.getAttribute("usuarioSession");
                String idIncidencia = request.getParameter("id");
                incidencia = daoIncidencias.buscarPorId(idIncidencia);
                request.setAttribute("incidencia", incidencia);
                request.setAttribute("usuarioSession", seguridad);
                comentario = daoComentarios.buscarporIdIncidencia_y_idUsuarioQueCreo(idIncidencia, String.valueOf(incidencia.getUsuario().getIdUsuarios()));
                request.setAttribute("comentario2", comentario);
                requestDispatcher = request.getRequestDispatcher("Seguridad_incidencias.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "incidenciasPDF":
                try {
                    reporte.crearDocumento();
                    reporte.abrirDOcumento();
                    reporte.agregarTitulo("REPORTE DE INCIDENCIAS PUCP");
                    reporte.agregarSaltoLiena();
                    reporte.agregarParrafo("Reporte actual de las incidencias PUCP 2022");
                    reporte.agregarSaltoLiena();
                    reporte.agregarTablaIncidencias();
                    reporte.cerrarDocumento();
                    response.sendRedirect(request.getContextPath() + "/Seguridad?action=inicioSeguridad");
                    System.out.printf("reporte completado");

                } catch (DocumentException e) {
                    throw new RuntimeException(e);
                }

                break;

            case "cerrarSesion":
                //session.invalidate();
                session.removeAttribute("usuarioSession");
                response.sendRedirect(request.getContextPath());
                /*
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request,response);
                */
                break;


            case "page":
                idPage = Integer.parseInt(request.getParameter("id"));
                setListaPaginada(daoIncidencias.paginarIncidencias(idPage));
                request.setAttribute("listaIncidenciasPermanente", getListaPermanente());
                request.setAttribute("listaIncidenciasPaginada", getListaPaginada());

                requestDispatcher = request.getRequestDispatcher("SeguridadInicio.jsp");
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
        DaoComentarios daoComentarios = new DaoComentarios();
        ArrayList<Usuarios> lista_usuarios;
        switch (action) {
            case "Atender_Incidencia": {
                String id_incidencia = request.getParameter("id_incidencia");
                String id_security = request.getParameter("id_security");
                String mensaje = request.getParameter("mensaje_aceptar");
                String id_usuario = request.getParameter("id_usuario");
                String ultima_columna_llena = request.getParameter("ultima_columna_llena");
                usuario = daoUsuarios.buscarPorId(id_security);
                Comentarios comment_user = daoComentarios.buscarporIdIncidencia_y_idUsuarioQueCreo(id_incidencia, id_usuario);
                int columna_para_llenar = Integer.parseInt(ultima_columna_llena) + 1;
                daoComentarios.actualizar_tabla_comentarios(comment_user, String.valueOf(columna_para_llenar), mensaje);
                daoComentarios.actualizarComentario_idSeguridad(Integer.parseInt(id_security),comment_user.getIdComentario());
                daoIncidencias.actualizar_estado("Atendido", id_incidencia);
                daoIncidencias.actualizar_idSecurity_from_incidencias(id_security,id_incidencia);
                response.sendRedirect(request.getContextPath() + "/Seguridad?action=detalles&id=" + id_incidencia);
                break;
            }
            case "Seguridad_Responder_Incidencia": {
                //               String user_validacion = request.getParameter("usuario_quiere_comentar");
                String id_incidencia = request.getParameter("id_incidencia");
                String id_security = request.getParameter("id_security");
                String id_usuario = request.getParameter("id_usuario");
                String ultima_columna_llena = request.getParameter("ultima_columna_llena");
                String mensaje = request.getParameter("mensaje_responder");

                Comentarios comment_user = daoComentarios.buscarporIdIncidencia_y_idUsuarioQueCreo(id_incidencia, id_usuario);
                int columna_para_llenar = Integer.parseInt(ultima_columna_llena) + 1;
                daoComentarios.actualizar_tabla_comentarios(comment_user, String.valueOf(columna_para_llenar), mensaje);
                response.sendRedirect(request.getContextPath() + "/Seguridad?action=detalles&id=" + id_incidencia);
                break;
            }
            case "AceptarResultado": {
                String id_incidencia = request.getParameter("id_incidencia");
                daoIncidencias.actualizar_estado("Resuelto", id_incidencia);
                response.sendRedirect(request.getContextPath() + "/Seguridad?action=detalles&id=" + id_incidencia);
                break;
            }

        }
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
