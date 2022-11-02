<%@ page import="com.example.proyectoingweb.servlets.model.beans.Usuarios" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Usuarios> listaUsuarios = (ArrayList<Usuarios>) request.getAttribute("lista");
%>
<html>
    <head>
        <title>Usuarios</title>
    </head>
    <body>
        <h1>Lista de Usuarios</h1>
        <table>

            <thead>
                <tr>
                    <th>idUsuario</th>
                    <th>Nombres</th>
                    <th>Apellidos</th>
                    <th>DNI</th>
                    <th>Celular</th>
                    <th>Codigo PUCP</th>
                    <th>Correo PUCP</th>
                    <th>Categoría</th>
                </tr>
            </thead>
            <tbody>


                <%
                    for (Usuarios usuario : listaUsuarios) {
                %>
                <tr>
                    <td><%=usuario.getIdUsuarios()%></td>
                    <td><%=usuario.getNombres()%></td>
                    <td><%=usuario.getApellidos()%></td>
                    <td><%=usuario.getDni()%></td>
                    <td><%=usuario.getCodigoPucp()%></td>
                    <td><%=usuario.getCorreoPucp()%></td>
                    <td><%=usuario.getCategorias()%></td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </body>
</html>
