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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

@WebServlet(name = "DestacarIncidenciaServlet", value = "/DestacarIncidenciaServlet")
public class DestacarIncidenciaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") == null ? "" : request.getParameter("action");
        DaoIncidencias daoIncidencias = new DaoIncidencias();
        switch (action) {
            case "destacarIncidencia":
                String idIncidencia;
                ArrayList<Usuarios> usuarios_que_incidieron = null;
                idIncidencia = request.getParameter("id");
                usuarios_que_incidieron = daoIncidencias.IdDeUsuariosQueDestacaron(idIncidencia);
                response.sendRedirect(request.getContextPath() + "/DestacarIncidenciaServlet?action=Inicio_con_destacados");
                break;
        }
    }
}
