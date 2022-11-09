package com.example.proyectoingweb.servlets.Usuario;
import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.daos.DaoIncidencias;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;


import java.io.IOException;
import java.util.Date;

@WebServlet(name = "ServletUsuarioInicio", value = "/Inicio")
public class ServletUsuarioInicio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        action = (action == null) ? "listar" : action;
        RequestDispatcher requestDispatcher;
        DaoIncidencias daoIncidencias = new DaoIncidencias();

        switch (action) {
            case "listar":
                request.setAttribute("listaIncidencias", daoIncidencias.obtenerlistaIncidencias());
                requestDispatcher = request.getRequestDispatcher("UsuarioInicio.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "perfil":
                requestDispatcher = request.getRequestDispatcher("UsuarioEditarPerfil.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "misIncidencias":
                request.setAttribute("listaIncidenciasDestacadas", daoIncidencias.obtenerlistaIncidenciasDestacadas());
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "registrarIncidencia":
                request.setAttribute("listaZonasPUCP", daoIncidencias.obtenerlistaZonasPUCP());
                requestDispatcher = request.getRequestDispatcher("UsuarioRegistrarIncidencia.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "cerrarSesion":
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "verIncidencia":
                requestDispatcher = request.getRequestDispatcher("UsuarioVerIncidencia.jsp");
                requestDispatcher.forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        DaoIncidencias daoIncidencias = new DaoIncidencias();
        switch (action) {
            case "guardar":
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String tipo = request.getParameter("tipo");
                String nivel = request.getParameter("nivel");
                String zona = request.getParameter("zona");

                Incidencias incidencias = new Incidencias();
                incidencias.setIdUsuario(3);
                incidencias.setNombre(nombre);
                incidencias.setDescripcion(descripcion);
                incidencias.setDestacado(1);
                incidencias.setTipo(tipo);
                incidencias.setUrgencia(nivel);
                incidencias.setIdZonaPucp(Integer.parseInt(zona));
                Date date = new Date();
                incidencias.setDatetime(date.toString());
                daoIncidencias.guardarIncidencias(incidencias);
                incidencias.setAnonimo(0);
                incidencias.setEstadoIncidencia("No registrado");

                response.sendRedirect(request.getContextPath() + "/Inicio?action=misIncidencias");
                break;
        }
    }
}

