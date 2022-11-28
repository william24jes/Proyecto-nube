package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.servlets.model.beans.Incidencias;
import com.example.proyectoingweb.servlets.model.daos.DaoBase;
import com.example.proyectoingweb.servlets.model.daos.DaoIncidencias;
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
        int idPage;

        switch (action){
            case "inicioSeguridad":
                request.setAttribute("listaIncidenciasPaginada", daoIncidencias.obtenerlistaIncidencias());
                request.setAttribute("listaIncidenciasPermanente", daoIncidencias.obtenerlistaIncidenciasCompleta());
                setListaPermanente(daoIncidencias.obtenerlistaIncidenciasCompleta());
                requestDispatcher = request.getRequestDispatcher("SeguridadInicio.jsp");
                requestDispatcher.forward(request,response);
                break;

            case "perfil":
                requestDispatcher = request.getRequestDispatcher("SeguridadPerfil.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "misIncidencias":
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request,response);
                break;
            case "detalles":
                String idIncidencia = request.getParameter("id");
                incidencia = daoIncidencias.buscarPorId(idIncidencia);
                request.setAttribute("incidencia", incidencia);
                requestDispatcher = request.getRequestDispatcher("Seguridad_incidencias.jsp");
                requestDispatcher.forward(request,response);
                break;

            case "cerrarSesion":
                HttpSession session = request.getSession();
                session.invalidate();
                response.sendRedirect(request.getContextPath());
                /*
                requestDispatcher = request.getRequestDispatcher("index.jsp");
                requestDispatcher.forward(request,response);
                */
                break;


            case "page":
                idPage = Integer.parseInt(request.getParameter("id"));
                setListaPaginada(daoIncidencias.paginarIncidencias(idPage));
                request.setAttribute("listaIncidenciasPermanente",getListaPermanente());
                request.setAttribute("listaIncidenciasPaginada", getListaPaginada());

                requestDispatcher = request.getRequestDispatcher("SeguridadInicio.jsp");
                requestDispatcher.forward(request, response);

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

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
