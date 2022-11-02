<%@ page import="com.proyectoingweb.model.beans.Usuario" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.proyectoingweb.servlets.AdminServlet" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Usuario> listaUsuarios = (ArrayList<Usuario>) request.getAttribute("lista");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Lista de usuarios / Incidencias PUCP</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">


        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
              rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
        <script src="https://kit.fontawesome.com/600382161a.js" crossorigin="anonymous"></script>

        <!-- Template Main CSS File -->
        <link href="assets/css/style_administrador.css" rel="stylesheet">

    </head>

    <body>
        <!-- ======= Top Bar ======= -->
        <section id="topbar" class="d-flex align-items-center">
            <div class="container d-flex justify-content-center justify-content-md-between">
                <div class="contact-info d-flex align-items-center">
                </div>
            </div>
        </section>

        <!-- ======= Header ======= -->
        <header id="header" class="d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">

                <div class="d-md-flex align-items-center">
                    <div>
                        <h1 class="name" style="margin-top: 25px">Juan Perez
                            <br><h6>Administrador</h6><br>
                        </h1>
                    </div>
                </div>

                <nav id="navbar" class="navbar">
                    <ul>
                        <li><a class="nav-link scrollto active" href="<%=request.getContextPath()%>/AdminServlet">Lista
                            de usuarios</a></li>
                        <li><a class="nav-link scrollto"
                               href="<%=request.getContextPath()%>/AdminServlet?action=perfil">Perfil</a></li>
                        <li>
                            <div class="container-fluid">
                                <form method="post" class="d-flex input-group w-auto"
                                      action="<%=request.getContextPath()%>/AdminServlet?action=buscar">
                                    <input type="text"
                                           name="searchText"
                                           class="form-control rounded"
                                           placeholder="Buscar usuario"
                                           id="floatingInput" aria-label="Search" aria-describedby="search-addon"/>
                                    <span class="input-group-text border-0" id="search-addon"><i
                                            class="fas fa-search"></i></span>
                                </form>
                            </div>
                        </li>
                        <li><a class="nav-link scrollto" href="<%=request.getContextPath()%>">Cerrar sesión</a></li>
                    </ul>

                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav>
                <!-- .navbar -->
            </div>
        </header>
        <!-- End Header -->

        <main id="main" data-aos="fade-up">
            <section class="section profile" style="padding-top: 30px">
                <div class="container">
                    <div class="container-fluid">
                        <div class="container"><h4 class="hero">Usuarios guardados:</h4>
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <a class="btn btn-danger" href="<%=request.getContextPath()%>/AdminServlet?action=crear"
                                   role="button">Añadir nuevo usuario</a>
                            </div>
                        </div>
                        <br>
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr style="align-content: center">
                                    <th>N°</th>
                                    <th>Código</th>
                                    <th>Nombres</th>
                                    <th>Apellidos</th>
                                    <th scope="col">Correo</th>
                                    <th scope="col">DNI</th>
                                    <th scope="col">Rol</th>
                                    <th scope="col"></th>
                                    <th scope="col"></th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    int i = 1;
                                    for (Usuario usuario : listaUsuarios) {
                                %>
                                <tr>
                                    <td scope="row"><%=i%>
                                    </td>
                                    <td><%=usuario.getCodigoPucp()%>
                                    </td>
                                    <td><%=usuario.getNombres()%>
                                    </td>
                                    <td><%=usuario.getApellidos()%>
                                    </td>
                                    <td><%=usuario.getCorreoPucp()%>
                                    </td>
                                    <td><%=usuario.getDni()%>
                                    </td>
                                    <td><%=usuario.getRol()%>
                                    </td>
                                    <td>
                                        <a href="<%=request.getContextPath()%>/AdminServlet?action=editar&id=<%=usuario.getIdUsuario()%>"><u
                                                style="align-content: center;color: blue">Ver más</u></a>
                                    </td>
                                    <td>
                                        <a type="button" class="btn btn-danger"
                                           onclick="return confirm('¿Estas seguro(a) que deseas borrar?')"
                                           href="<%=request.getContextPath()%>/AdminServlet?action=borrar&id=<%=usuario.getIdUsuario()%>">
                                            <i class="bi bi-trash"></i></a>

                                    </td>
                                </tr>
                                <%
                                        i++;
                                    }
                                %>

                            </tbody>
                        </table>
                    </div>
                </div>

                <nav aria-label="Page navigation example" style="margin-top: 20px;">
                    <ul class="pagination justify-content-center">
                        <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">Previous</a></li>
                        <li class="page-item disabled"><a class="page-link" href="#">1</a></li>
                        <li class="page-item"><a class="page-link" href="#">2</a></li>
                        <li class="page-item"><a class="page-link" href="#">3</a></li>
                        <li class="page-item"><a class="page-link" href="#">Next</a></li>
                    </ul>
                </nav>
            </section>

        </main><!-- End #main -->

        <div id="preloader"></div>

        <!-- Vendor JS Files -->
        <script src="assets/vendor/purecounter/purecounter_vanilla.js"></script>
        <script src="assets/vendor/aos/aos.js"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="assets/vendor/glightbox/js/glightbox.min.js"></script>
        <script src="assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="assets/vendor/swiper/swiper-bundle.min.js"></script>
        <script src="assets/vendor/waypoints/noframework.waypoints.js"></script>
        <script src="assets/vendor/php-email-form/validate.js"></script>

        <!-- Template Main JS File -->
        <script src="assets/js/main.js"></script>

    </body>

</html>
