package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.daos.DaoUsuarios;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", value = "/AdminServlet")
public class AdminServlet extends HttpServlet {

    private ArrayList<Usuarios> listaPermanente;
    private ArrayList<Usuarios> listaPaginada;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        action = (action == null) ? "listar" : action;

        DaoUsuarios daoUsuarios = new DaoUsuarios();
        RequestDispatcher requestDispatcher;
        Usuarios usuarios;
        String idUsuario;
        int idPage;

        switch (action) {
            case "listar":

                request.setAttribute("listaPaginada", daoUsuarios.obtenerlistaUsuarios());
                request.setAttribute("listaPermanente", daoUsuarios.obtenerlistaUsuariosCompleta());
                setListaPermanente(daoUsuarios.obtenerlistaUsuariosCompleta());
                System.out.println(getListaPermanente().size());
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
                usuarios = daoUsuarios.buscarPorId(idUsuario);

                if (usuarios != null) {
                    request.setAttribute("usuarioEditar", usuarios);
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

            case "page":
                idPage = Integer.parseInt(request.getParameter("id"));
                setListaPaginada(daoUsuarios.paginarUsuarios(idPage));
                request.setAttribute("listaPermanente",getListaPermanente());
                request.setAttribute("listaPaginada", getListaPaginada());

                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        Usuarios usuarios = new Usuarios();
        RequestDispatcher requestDispatcher;

        switch (action) {
            case "guardar":

                usuarios.setNombres(request.getParameter("Nombres"));
                usuarios.setApellidos(request.getParameter("Apellidos"));
                usuarios.setCorreoPucp(request.getParameter("Correo PUCP"));
                usuarios.setDni(request.getParameter("DNI"));
                usuarios.setCelular(request.getParameter("Celular"));
                usuarios.setCategorias(request.getParameter("Categoría"));
                usuarios.setRol(request.getParameter("Rol"));
                usuarios.setCodigoPucp(request.getParameter("Codigo"));

                daoUsuarios.guardarUsuario(usuarios);

                response.sendRedirect(request.getContextPath()+"/AdminServlet");
                break;

            case "actualizar":

                usuarios.setIdUsuarios(Integer.parseInt(request.getParameter("ID Usuario")));
                usuarios.setNombres(request.getParameter("Nombres"));
                usuarios.setApellidos(request.getParameter("Apellidos"));
                usuarios.setCorreoPucp(request.getParameter("Correo PUCP"));
                usuarios.setDni(request.getParameter("DNI"));
                usuarios.setCelular(request.getParameter("Celular"));
                usuarios.setCategorias(request.getParameter("Categoría"));
                usuarios.setRol(request.getParameter("Rol"));
                usuarios.setCodigoPucp(request.getParameter("Codigo"));

                daoUsuarios.actualizarUsuario(usuarios);

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

                ArrayList<Usuarios> lista = daoUsuarios.buscarUsuarios(searchText);
                request.setAttribute("lista", lista);

                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);

                break;
        }
    }

    public ArrayList<Usuarios> getListaPermanente() {
        return listaPermanente;
    }

    public void setListaPermanente(ArrayList<Usuarios> listaPermanente) {
        this.listaPermanente = listaPermanente;
    }

    public ArrayList<Usuarios> getListaPaginada() {
        return listaPaginada;
    }

    public void setListaPaginada(ArrayList<Usuarios> listaPaginada) {
        this.listaPaginada = listaPaginada;
    }
}
