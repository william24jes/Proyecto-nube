<%@ page import="com.example.proyectoingweb.model.beans.ZonaPucp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="lista" type="java.util.ArrayList<com.example.proyectoingweb.model.beans.ZonaPucp>" scope="request" />

<html>
    <head>
        <title>Title</title>
    </head>
    <body>
        <h1>Hola mundo</h1>
        <table>
            <thead>
                <tr>
                    <th>#</th>
                    <th>id</th>
                    <th>nombre Zona</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (ZonaPucp zonaPucp : lista) { %>
                <tr>
                    <td><%=zonaPucp.getIdZonaPucp()%>
                    </td>
                    <td><%=zonaPucp.getNombreZona()%>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </body>
</html>
