<%@ page import="com.example.proyectoingweb.model.beans.Usuarios" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    ArrayList<Usuarios> listaUsuarios = (ArrayList<Usuarios>) request.getAttribute("lista");
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

        <!-- =======================================================
        * Template Name: BizLand - v3.9.0
        * Template URL: https://bootstrapmade.com/bizland-bootstrap-business-template/
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
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
                            <br><h6>Administrador</h6></br>
                        </h1>
                    </div>
                </div>
                <!-- Uncomment below if you prefer to use an image logo -->
                <!-- <a href="index.html" class="logo"><img src="assets/img/logo.png" alt=""></a>-->

                <nav id="navbar" class="navbar">


                    <ul>

                        <li><a class="nav-link scrollto active" href="<%=request.getContextPath()%>/AdminListaUsersServlet">Lista de usuarios</a></li>
                        <li><a class="nav-link scrollto" href="<%=request.getContextPath()%>/perfil">Perfil</a></li>
                        <li><div class="container-fluid">
                            <form class="d-flex input-group w-auto">
                                <input
                                        type="search"
                                        class="form-control rounded"
                                        placeholder="Buscar usuario"
                                        aria-label="Search"
                                        aria-describedby="search-addon"
                                />
                                <span class="input-group-text border-0" id="search-addon">
        <i class="fas fa-search"></i>
      </span>
                            </form>
                        </div></li>
                        <li><a class="nav-link scrollto" href="home.html">Cerrar sesión</a></li>
                    </ul>

                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav><!-- .navbar -->

            </div>

        </header><!-- End Header -->

        <main id="main" data-aos="fade-up">

            <section class="section profile">
                <div class="container">
                    <div class="container-fluid">
                        <div class="container"><h4 class="hero">Usuarios guardados:</h4>
                            <div class="d-grid gap-2 d-md-flex justify-content-md-end">
                                <a class="btn btn-danger" href="<%=request.getContextPath()%>/add_user" role="button">Añadir nuevo usuario</a>

                            </div></div>

                        <br>
                        <table class="table table-striped table-hover">
                            <thead>
                                <tr>
                                    <th scope="col">N°</th>
                                    <th scope="col">Código</th>
                                    <th scope="col">Apellido</th>
                                    <th scope="col">Nombre</th>
                                    <th scope="col">Correo</th>
                                    <th scope="col">DNI</th>
                                    <th scope="col">Categoría</th>
                                    <th scope="col">Rol</th>
                                    <th scope="col">...</th>
                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    int i = 1;
                                    for (Usuarios usuario : listaUsuarios) {
                                %>
                                <tr>
                                    <th scope="row"><%=i%></th>
                                    <td><%=usuario.getCodigoPucp()%></td>
                                    <td><%=usuario.getApellidos()%></td>
                                    <td><%=usuario.getNombres()%></td>
                                    <td><%=usuario.getCorreoPucp()%></td>
                                    <td><%=usuario.getDni()%></td>
                                    <td><%=usuario.getCategorias()%></td>
                                    <td><%=usuario.getRol()%></td>
                                    <td><a href="AdminEditUser.jsp"><u style="align-content: center;color: blue">Ver más</u></a></td>
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
        <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i
                class="bi bi-arrow-up-short"></i></a>

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
