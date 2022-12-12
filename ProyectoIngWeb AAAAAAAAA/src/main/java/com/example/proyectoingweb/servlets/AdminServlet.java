package com.example.proyectoingweb.servlets;

import com.example.proyectoingweb.servlets.model.beans.Credenciales;
import com.example.proyectoingweb.servlets.model.beans.Usuarios;
import com.example.proyectoingweb.servlets.model.daos.DaoUsuarios;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

@WebServlet(name = "AdminServlet", value = "/Admin")
@MultipartConfig(maxFileSize = 16177215)
public class AdminServlet extends HttpServlet {

    private ArrayList<Usuarios> listaPermanente; //Lista de todos los usuarios
    private ArrayList<Usuarios> listaPaginada; //Lista de 15 usuarios
    private int centinelaSearch;
    private String search;
    private String opcion;
    private String orden;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        action = (action == null) ? "listar" : action;
        int centinela;
        String busqueda;
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        HttpSession session=request.getSession();
        RequestDispatcher requestDispatcher;
        Usuarios usuarios;
        String idUsuario;

        int idPage;

        ArrayList<String> categorias = null;

        switch (action) {
            case "listar":
                setCentinelaSearch(0);
                request.setAttribute("listaPaginada", daoUsuarios.obtenerlistaUsuarios());
                request.setAttribute("listaPermanente", daoUsuarios.obtenerlistaUsuariosCompleta());
                request.setAttribute("registrado", daoUsuarios.obtenerlistaUsuariosRegistrados());
                setListaPermanente(daoUsuarios.obtenerlistaUsuariosCompleta());
                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);
                break;
            case "crear":
                requestDispatcher = request.getRequestDispatcher("AdminAddUser.jsp");
                requestDispatcher.forward(request, response);

                break;
            case "perfil":
                categorias = new ArrayList<>();
                categorias.add("Alumno");
                categorias.add("Administrativo");
                categorias.add("Jefe de practica");
                categorias.add("Profesor");
                categorias.add("Egresado");

                request.setAttribute("categorias", categorias);
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

                    categorias = new ArrayList<>();
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
                    response.sendRedirect(request.getContextPath() + "/Admin");
                }
                break;
            case "borrar":
                idUsuario = request.getParameter("id");
                daoUsuarios.borrarUsuario(idUsuario);
                session.setAttribute("msg", "Usuario borrado exitosamente");
                response.sendRedirect(request.getContextPath() + "/Admin");
                break;
            case "page":
                idPage = Integer.parseInt(request.getParameter("id"));
                request.setAttribute("registrado", daoUsuarios.obtenerlistaUsuariosRegistrados());
                centinela = getCentinelaSearch();
                busqueda = getSearch();

                if (centinela == 0) {
                    setListaPaginada(daoUsuarios.paginarUsuarios(idPage));
                    request.setAttribute("listaPermanente", getListaPermanente());
                    request.setAttribute("listaPaginada", getListaPaginada());
                } else if (centinela == 1) {
                    setListaPaginada(daoUsuarios.paginarUsuariosBuscados(idPage, busqueda));
                    request.setAttribute("listaPermanente", getListaPermanente());
                    request.setAttribute("listaPaginada", getListaPaginada());
                }
                else if (centinela == 2) {
                    setListaPaginada(daoUsuarios.paginarUsuariosOrdenados(idPage, getOpcion(),getOrden()));
                    request.setAttribute("listaPermanente", getListaPermanente());
                    request.setAttribute("listaPaginada", getListaPaginada());
                }
                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);
                break;
                //añadido cerrar sesión
            case "cerrarSesion":
                //session.invalidate();
                session.removeAttribute("userAdmin");
                response.sendRedirect(request.getContextPath());
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        DaoUsuarios daoUsuarios = new DaoUsuarios();
        Usuarios usuarios = new Usuarios();
        HttpSession session = request.getSession();
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
                    response.sendRedirect(request.getContextPath()+"/Admin");
                }
                else {
                    requestDispatcher = request.getRequestDispatcher("AdminAddUser.jsp");
                    requestDispatcher.forward(request, response);
                }

                break;
            case "CambiarDatos": {
                InputStream inputStream2; // input stream of the upload file
                Part filePart2 = request.getPart("foto_subida");
                String idUsuario3 = request.getParameter("id");
                String nuevo_codigo = request.getParameter("Codigo");
                String nuevo_Nombre = request.getParameter("Nombres");
                String nuevo_Apellido = request.getParameter("Apellidos");
                String nuevo_Correo = request.getParameter("Correo PUCP");
                String nuevo_DNI =request.getParameter("DNI");
                String nuevo_celular = request.getParameter("Celular");


                Usuarios user_a_cambiar = daoUsuarios.buscarPorId(idUsuario3);
                user_a_cambiar.setCodigoPucp(nuevo_codigo);
                user_a_cambiar.setNombres(nuevo_Nombre);
                user_a_cambiar.setApellidos(nuevo_Apellido);
                user_a_cambiar.setCorreoPucp(nuevo_Correo);
                user_a_cambiar.setDni(nuevo_DNI);
                user_a_cambiar.setCelular(nuevo_celular);

                // obtains input stream of the upload file
                System.out.println(filePart2);
                inputStream2 = filePart2.getInputStream();
                if(filePart2.getSize() != 0){
                    daoUsuarios.actualizar_userAdmin_datos_foto(user_a_cambiar, inputStream2);
                }else if(filePart2.getSize() == 0){
                    daoUsuarios.actualizar_userAdmin_datos(user_a_cambiar);
                }
                HttpSession session2 = request.getSession();
                session2.setAttribute("userAdmin", user_a_cambiar);
                session2.setAttribute("msg", "Usuario editado correctamente");
                response.sendRedirect(request.getContextPath() + "/Admin?action=perfil");
                break;
            }
            case "Borrar_Foto_Perfil": {
                String idUsuario3 = request.getParameter("id");
                /*String filePath = getServletContext();
                 */
                ServletContext context = getServletContext();
                String fullPath = context.getRealPath("/assets/img/fotosPerfil/perfilDefault.png");
                System.out.println(fullPath);

                InputStream inputStream3;
                byte[] bytes = Files.readAllBytes(Paths.get(fullPath));
                inputStream3 = new ByteArrayInputStream(bytes);
                Usuarios user_a_cambiar = daoUsuarios.buscarPorId(idUsuario3);
                daoUsuarios.eliminar_fotoPerfil(user_a_cambiar, inputStream3);
                HttpSession session3 = request.getSession();
                session3.setAttribute("userAdmin", user_a_cambiar);
                response.sendRedirect(request.getContextPath() + "/Admin?action=perfil");
                break;
            }

            case "actualizar":
                try {
                    int dni = Integer.parseInt(request.getParameter("DNI"));

                    usuarios.setIdUsuarios(Integer.parseInt(request.getParameter("ID Usuario")));
                    usuarios.setNombres(request.getParameter("Nombres"));
                    usuarios.setApellidos(request.getParameter("Apellidos"));
                    usuarios.setCorreoPucp(request.getParameter("Correo PUCP"));
                    usuarios.setDni(request.getParameter("DNI"));
                    usuarios.setCelular(request.getParameter("Celular"));
                    usuarios.setCategorias(request.getParameter("Categoría"));
                    usuarios.setRol(request.getParameter("Rol"));
                    usuarios.setCodigoPucp(request.getParameter("Codigo"));
                    if(dni>9999999 && dni <99999999 && !request.getParameter("Nombres").equals("") &&
                    !request.getParameter("Apellidos").equals("")){

                        if (daoUsuarios.actualizarUsuario(usuarios)) {

                            session.setAttribute("msg", "Usuario editado correctamente");
                            response.sendRedirect(request.getContextPath() + "/Admin");
                        } else {
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
                                response.sendRedirect(request.getContextPath() + "/Admin");
                            }
                        }
                    }

                }catch (NumberFormatException e){
                    session.setAttribute("msg", "No se pudo actualizar los datos del usuario");
                    response.sendRedirect(request.getContextPath() + "/Admin");
                }

                break;

            case "actualizarPassword":

                usuarios = (Usuarios) session.getAttribute("userAdmin");
                String password = request.getParameter("password");

                Credenciales credenciales = daoUsuarios.validarCambioPassword(usuarios.getIdUsuarios(), password);

                if (credenciales != null){
                    if (request.getParameter("nuevaPassword1").equals(request.getParameter("nuevaPassword2")) && !request.getParameter("nuevaPassword1").equals("")
                    && request.getParameter("nuevaPassword1").length()>=5){

                        daoUsuarios.actualizarPassword(Integer.parseInt(credenciales.getIdUsuario()), request.getParameter("nuevaPassword1"));

                        session.setAttribute("msg","Contraseña cambiada correctamente");
                        response.sendRedirect(request.getContextPath() + "/Admin");
                    }
                    else {
                        session.setAttribute("msgError", "Las contraseñas deben ser iguales");
                        response.sendRedirect(request.getContextPath()+"/Admin?action=perfil");
                    }
                }
                else {
                    session.setAttribute("msgError", "La contraseña actual es incorrecta");
                    response.sendRedirect(request.getContextPath()+"/Admin?action=perfil");
                }

                break;

            case "buscar":
                setCentinelaSearch(1);
                String searchText = request.getParameter("searchText");
                setSearch(searchText);

                ArrayList<Usuarios> lista = daoUsuarios.buscarUsuariosCompleto(searchText);
                request.setAttribute("registrado", daoUsuarios.obtenerlistaUsuariosRegistrados());
                request.setAttribute("listaPaginada", daoUsuarios.buscarUsuarios(searchText)); //editar
                request.setAttribute("listaPermanente", lista);

                setListaPermanente(lista);
                request.setAttribute("searchText", searchText);

                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);
                break;

            case "order":
                setCentinelaSearch(2);
                String opcionjsp = request.getParameter("tipo");
                String ordenamiento= request.getParameter("orden");
                setOpcion(opcionjsp);
                setOrden(ordenamiento);
                request.setAttribute("registrado", daoUsuarios.obtenerlistaUsuariosRegistrados());
                request.setAttribute("listaPaginada", daoUsuarios.paginarUsuariosOrdenados(1,opcionjsp,ordenamiento));
                request.setAttribute("listaPermanente", daoUsuarios.obtenerlistaUsuariosCompleta());

                setListaPermanente(daoUsuarios.obtenerlistaUsuariosCompletaOrdenada(opcionjsp,ordenamiento));

                requestDispatcher = request.getRequestDispatcher("AdminListaUsers.jsp");
                requestDispatcher.forward(request, response);
                break;
        }
    }
    public boolean Admin_tiene_foto_null(ArrayList<Usuarios> lista_users_que_destacan, int usuario_sesion_id) {
        for (Usuarios lista_foto_null : lista_users_que_destacan) {
            if (lista_foto_null.getIdUsuarios() == usuario_sesion_id) {
                //significa que el usuario ha destacado, entonces a volver a casa
                return true;
            }
        }
        return false;
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

    public int getCentinelaSearch() {
        return centinelaSearch;
    }

    public void setCentinelaSearch(int centinelaSearch) {
        this.centinelaSearch = centinelaSearch;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }
    public String getOpcion() {
        return opcion;
    }

    public void setOpcion(String opcion) {
        this.opcion = opcion;
    }

    public String getOrden() {
        return orden;
    }

    public void setOrden(String orden) {
        this.orden = orden;
    }
}
