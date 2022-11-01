package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.model.beans.Usuarios;
import com.example.proyectoingweb.model.daos.DaoUsuarios;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        action = (action == null) ? "listar" : action;
        RequestDispatcher requestDispatcher;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        String codigoPucp;
        Usuarios usuarios;

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
            /*case "editar":
                jobId = request.getParameter("id");
                job = daoJob.buscarPorId(jobId);

                if (job != null) { //abro el form para editar
                    request.setAttribute("job", job);
                    requestDispatcher = request.getRequestDispatcher("jobs/formEditar.jsp");
                    requestDispatcher.forward(request, response);
                } else { //id no encontrado
                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                }
                break;
            case "editarParcial":
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
            case "borrar":  // JobServlet?action=borrar&id=50
                codigoPucp = request.getParameter("codigoPucp");
                daoUsuarios.borrarUsuario(codigoPucp);

                response.sendRedirect(request.getContextPath() + "/AdminServlet");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        DaoUsuarios daoUsuarios = new DaoUsuarios();

        switch (action) {
            case "guardar":
                String nombres= request.getParameter("Nombres");
                String apellidos=request.getParameter("Apellidos");
                String correo=request.getParameter("Correo PUCP");
                String dni=request.getParameter("DNI");
                String celular=request.getParameter("Celular");
                String categoria=request.getParameter("Categoría");
                String rol=request.getParameter("Rol");
                String codigo=request.getParameter("Codigo");
                int idUsuario=Integer.parseInt(dni);

                Usuarios usuarios=new Usuarios();
                usuarios.setIdUsuarios(idUsuario);
                usuarios.setNombres(nombres);
                usuarios.setApellidos(apellidos);
                usuarios.setCorreoPucp(correo);
                usuarios.setDni(dni);
                usuarios.setCelular(celular);
                usuarios.setCategorias(categoria);
                usuarios.setRol(rol);
                usuarios.setCodigoPucp(codigo);
                daoUsuarios.guardarlistaUsuarios(usuarios);

                response.sendRedirect(request.getContextPath()+"/AdminListaUsersServlet");
                break;
            /*case "actualizar":
                String jobId1 = request.getParameter("jobId");
                String jobTitle1 = request.getParameter("jobTitle");
                String minSalaryStr1 = request.getParameter("minSalary");
                String maxSalaryStr1 = request.getParameter("maxSalary");
                try {
                    int minSalary1 = Integer.parseInt(minSalaryStr1);
                    int maxSalary1 = Integer.parseInt(maxSalaryStr1);
                    daoJob.actualizar(jobId1, jobTitle1, minSalary1, maxSalary1);

                    response.sendRedirect(request.getContextPath() + "/JobServlet");
                } catch (NumberFormatException e) {
                    response.sendRedirect(request.getContextPath() + "/JobServlet?action=editar&id=" + jobId1);
                }
                break;
            case "actualizarParcial":
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
            case "buscar":
                String searchText = request.getParameter("searchText");

                ArrayList<Job> lista = daoJob.buscarPorJobTitle(searchText);
                request.setAttribute("lista", lista);
                request.setAttribute("searchText",searchText);

                RequestDispatcher requestDispatcher = request.getRequestDispatcher("jobs/lista.jsp");
                requestDispatcher.forward(request, response);
                break;*/
        }
    }
}
