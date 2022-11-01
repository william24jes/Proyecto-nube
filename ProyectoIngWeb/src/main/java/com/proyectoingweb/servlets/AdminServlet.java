package com.proyectoingweb.servlets;

import com.proyectoingweb.model.beans.Usuario;
import com.proyectoingweb.model.daos.DaoUsuarios;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        action = (action == null) ? "listar" : action;

        DaoUsuarios daoUsuarios = new DaoUsuarios();
        RequestDispatcher requestDispatcher;
        Usuario usuario;
        String idUsuario;

        switch (action) {
            case "listar":

                request.setAttribute("lista", daoUsuarios.obtenerlistaUsuarios());
                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);

                break;
            case "crear":

                requestDispatcher = request.getRequestDispatcher("AdminAddUser.jsp");
                requestDispatcher.forward(request, response);

                break;
            /*case "perfil":

                request.setAttribute("admin", daoUsuarios.obtenerAdmin());
                requestDispatcher = request.getRequestDispatcher("AdminPerfil.jsp");
                requestDispatcher.forward(request, response);

                break;
            */
            case "editar":
                idUsuario = request.getParameter("id");
                usuario = daoUsuarios.buscarPorId(idUsuario);

                if (usuario != null) {
                    request.setAttribute("usuarioEditar", usuario);
                    requestDispatcher = request.getRequestDispatcher("AdminEditUser.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/AdminServlet");
                }
                break;
            /*case "editarParcial":
                jobId = request.getParameter("id");
                job = daoJob.buscarPorId(jobId);

                if (job != null) { //abro el form para editar
                    request.setAttribute("job", job);
                    requestDispatcher = request.getRequestDispatcher("jobs/formEditarParcial.jsp");
                    requestDispatcher.forward(request, response);
                } else { //id no encontrado
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                }
                break;

             */
            case "borrar":
                idUsuario = request.getParameter("id");
                daoUsuarios.borrarUsuario(idUsuario);

                response.sendRedirect(request.getContextPath() + "/AdminServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        Usuario usuario = new Usuario();
        RequestDispatcher requestDispatcher;

        switch (action) {
            case "guardar":

                usuario.setNombres(request.getParameter("Nombres"));
                usuario.setApellidos(request.getParameter("Apellidos"));
                usuario.setCorreoPucp(request.getParameter("Correo PUCP"));
                usuario.setDni(request.getParameter("DNI"));
                usuario.setCelular(request.getParameter("Celular"));
                usuario.setCategoria(request.getParameter("Categoría"));
                usuario.setRol(request.getParameter("Rol"));
                usuario.setCodigoPucp(request.getParameter("Codigo"));

                daoUsuarios.guardarUsuario(usuario);

                response.sendRedirect(request.getContextPath()+"/AdminServlet");
                break;

            case "actualizar":

                usuario.setIdUsuario(Integer.parseInt(request.getParameter("ID Usuario")));
                usuario.setNombres(request.getParameter("Nombres"));
                usuario.setApellidos(request.getParameter("Apellidos"));
                usuario.setCorreoPucp(request.getParameter("Correo PUCP"));
                usuario.setDni(request.getParameter("DNI"));
                usuario.setCelular(request.getParameter("Celular"));
                usuario.setCategoria(request.getParameter("Categoría"));
                usuario.setRol(request.getParameter("Rol"));
                usuario.setCodigoPucp(request.getParameter("Codigo"));

                daoUsuarios.actualizarUsuario(usuario);

                response.sendRedirect(request.getContextPath() + "/AdminServlet");

                break;

            /*case "actualizarParcial":
                String jobId2 = request.getParameter("jobId");
                String jobTitle2 = request.getParameter("jobTitle");
                String minSalaryStr2 = request.getParameter("minSalary");
                int minSalary2 = Integer.parseInt(minSalaryStr2);

                try {
                    daoJob.actualizarParcial(jobId2, jobTitle2, minSalary2);
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                } catch (SQLException e) {
                    job = daoJob.buscarPorId(jobId2);

                    if (job != null) { //abro el form para editar
                        request.setAttribute("job", job);
                        request.setAttribute("error","El texto no puede tener mas de 255 caractéres");
                        RequestDispatcher requestDispatcher = request.getRequestDispatcher("jobs/formEditarParcial.jsp");
                        requestDispatcher.forward(request, response);
                    } else { //id no encontrado
                        response.sendRedirect(request.getContextPath() + "/JobServlet");
                    }
                }

                break;
            */
            case "buscar":
                String searchText = request.getParameter("searchText");

                ArrayList<Usuario> lista = daoUsuarios.buscarUsuarios(searchText);
                request.setAttribute("lista", lista);

                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);

                break;
        }
    }
}
