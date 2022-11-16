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
        HttpSession session=request.getSession();
        RequestDispatcher requestDispatcher;
        Usuarios usuarios;
        String idUsuario;
        int idPage;

        switch (action) {
            case "listar":

                request.setAttribute("listaPaginada", daoUsuarios.obtenerlistaUsuarios());
                request.setAttribute("listaPermanente", daoUsuarios.obtenerlistaUsuariosCompleta());
                setListaPermanente(daoUsuarios.obtenerlistaUsuariosCompleta());
                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);

                break;
            case "crear":

                requestDispatcher = request.getRequestDispatcher("AdminAddUser.jsp");
                requestDispatcher.forward(request, response);

                break;
            case "perfil":

                requestDispatcher = request.getRequestDispatcher("AdminPerfil.jsp");
                requestDispatcher.forward(request, response);

                break;
            case "editar":
                idUsuario = request.getParameter("id");
                usuarios = daoUsuarios.buscarPorId(idUsuario);

                if (usuarios != null) {

                    ArrayList<String> roles = new ArrayList<>();
                    roles.add("Usuario PUCP");
                    roles.add("Seguridad");

                    ArrayList<String> categorias = new ArrayList<>();
                    categorias.add("Alumno");
                    categorias.add("Administrativo");
                    categorias.add("Jefe de practica");
                    categorias.add("Profesor");
                    categorias.add("Egresado");

                    request.setAttribute("usuarioEditar", usuarios);
                    request.setAttribute("roles", roles);
                    request.setAttribute("categorias", categorias);
                    requestDispatcher = request.getRequestDispatcher("AdminEditUser.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    response.sendRedirect(request.getContextPath() + "/AdminServlet");
                }
                break;
            case "borrar":
                idUsuario = request.getParameter("id");
                daoUsuarios.borrarUsuario(idUsuario);
                session.setAttribute("msg","Usuario borrado exitosamente");


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
        HttpSession session=request.getSession();
        RequestDispatcher requestDispatcher;

        String idUsuario;

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

                if (daoUsuarios.guardarUsuario(usuarios)){
                    session.setAttribute("msg","Usuario creado exitosamente");
                    response.sendRedirect(request.getContextPath()+"/AdminServlet");
                }
                else {
                    requestDispatcher = request.getRequestDispatcher("AdminAddUser.jsp");
                    requestDispatcher.forward(request, response);
                }

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

                if (daoUsuarios.actualizarUsuario(usuarios)){
                    session.setAttribute("msg","Usuario editado correctamente");
                    response.sendRedirect(request.getContextPath() + "/AdminServlet");
                }
                else {
                    idUsuario = request.getParameter("id");
                    usuarios = daoUsuarios.buscarPorId(idUsuario);

                    if (usuarios != null){
                        ArrayList<String> roles = new ArrayList<>();
                        roles.add("Usuario PUCP");
                        roles.add("Seguridad");

                        ArrayList<String> categorias = new ArrayList<>();
                        categorias.add("Alumno");
                        categorias.add("Administrativo");
                        categorias.add("Jefe de practica");
                        categorias.add("Profesor");
                        categorias.add("Egresado");

                        request.setAttribute("usuarioEditar", usuarios);
                        request.setAttribute("roles", roles);
                        request.setAttribute("categorias", categorias);
                        requestDispatcher = request.getRequestDispatcher("AdminEditUser.jsp");
                        requestDispatcher.forward(request, response);
                    }
                    else {
                        response.sendRedirect(request.getContextPath() + "/AdminServlet");
                    }
                }

                break;
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
