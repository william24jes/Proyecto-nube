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
import java.util.ArrayList;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
@WebServlet(name = "ServletUsuarioInicio", value = "/Inicio")
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
                request.setAttribute("listaIncidenciasDestacadas", daoIncidencias.incidenciasDestXUser(""+user.getIdUsuarios()+""));
                requestDispatcher = request.getRequestDispatcher("UsuarioMisIncidencias.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "registrarIncidencia":
                request.setAttribute("listaZonasPUCP", daoZonaPucp.obtenerListaZonaPucp());
                requestDispatcher = request.getRequestDispatcher("UsuarioRegistrarIncidencia.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "cerrarSesion":
                HttpSession session = request.getSession();
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
                request.setAttribute("listaIncidenciasPermanente",getListaPermanente());
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
        switch (action) {
            case "guardar":
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String tipo = request.getParameter("tipo");
                String nivel = request.getParameter("nivel");
                String zona = request.getParameter("zona");


                usuario = daoUsuarios.buscarPorId("3");
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
                daoIncidencias.guardarIncidencias(incidencias);
                response.sendRedirect(request.getContextPath() + "/Inicio?action=misIncidencias");
                break;
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

