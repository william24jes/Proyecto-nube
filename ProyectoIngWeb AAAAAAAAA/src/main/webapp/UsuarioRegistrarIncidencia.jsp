<%@ page import="com.example.proyectoingweb.servlets.model.beans.ZonaPucp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="listaZonasPUCP" scope="request"
             type="java.util.ArrayList<com.example.proyectoingweb.servlets.model.beans.ZonaPucp>"/>
<jsp:useBean id="usuarioSession" scope="session" type="com.example.proyectoingweb.servlets.model.beans.Usuarios"
             class="com.example.proyectoingweb.servlets.model.beans.Usuarios"/>

<!DOCTYPE html>
<html lang=com.example.proyectoingweb.servlets.model.daos.DaoIncidencias"en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Registrar incidencia / Incidencias PUCP</title>

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
        <link href="assets/css/style_usuario.css" rel="stylesheet">

    </head>

    <body>

        <!-- ======= Top Bar ======= -->
        <section id="topbar" class="d-flex align-items-center">
            <div class="container d-flex justify-content-center justify-content-md-between">
            </div>
        </section>

        <!-- ======= Header ======= -->
        <header id="header" class="d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">

                <div class="d-md-flex align-items-center">
                    <div>
                        <h1 class="name" style="margin-top: 25px"><%=usuarioSession.getNombreYApellido()%>
                            <br><h6><%=usuarioSession.getRol()%>
                            </h6></br>
                        </h1>
                    </div>
                </div>

                <nav id="navbar" class="navbar">


                    <ul>
                        <li>
                            <div class="container-fluid">
                                <form class="d-flex input-group w-auto">
                                    <input
                                            type="search"
                                            class="form-control rounded"
                                            placeholder="Buscar Incidencia"
                                            aria-label="Search"
                                            aria-describedby="search-addon"
                                    />
                                    <span class="input-group-text border-0" id="search-addon">
        <i class="fas fa-search"></i>
      </span>
                                </form>
                            </div>
                        </li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=listar">Inicio</a>
                        </li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=perfil">Perfil</a>
                        </li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=misIncidencias">Mis
                            Incidencias</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=registrarIncidencia">Registrar
                            Incidencia</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=cerrarSesion">Cerrar
                            sesión</a></li>
                    </ul>

                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav><!-- .navbar -->

            </div>
        </header>

        <main id="main" data-aos="fade-up">

            <!-- ======= Breadcrumbs Section ======= -->
            <!-- Breadcrumbs Section -->

            <!-- ======= Portfolio Details Section ======= -->
            <section id="portfolio-details" class="portfolio-details">
                <div class="container">
                    <div class="container-fluid">

                        <div class="container-xl px-4 mt-4">

                            <!-- Account page navigation-->
                            <hr class="mt-7 mb-7">
                            <div class="row">

                                <div class="col-xl-11">
                                    <!-- Account details card-->
                                    <div class="card mb-4">
                                        <div class="card-header fw-bold"> Registrar Incidencia</div>
                                        <div class="card-body">
                                            <form method="post" action="<%=request.getContextPath()%>/Inicio?action=guardar" enctype="multipart/form-data">
                                                <!-- Form Group (username)-->
                                                <div class="mb-3">
                                                    <input class="form-control " id="idUsuario" type="hidden"
                                                           name="id" value="<%=usuarioSession.getIdUsuarios()%>">
                                                </div>
                                                <div class="mb-3">
                                                    <label for="inputUsername">Nombre de la Incidencia: </label>
                                                    <input class="form-control " id="inputUsername" type="text"
                                                           name="nombre">
                                                </div>

                                                <div class="form-group mb-3">
                                                    <label for="exampleFormControlTextarea1">Descripción:</label>
                                                    <textarea class="form-control" id="exampleFormControlTextarea1"
                                                              rows="3" name="descripcion"></textarea>
                                                </div>

                                                <!-- Form Row-->
                                                <div class="row mb-3">
                                                    <label class="col-sm-2 col-form-label"><h5>Tipo de incidencia </h5>
                                                    </label>
                                                    <div class="col-sm-4">
                                                        <select class="form-select" aria-label="Default select example"
                                                                name="tipo">
                                                            <option selected>Seleccione...</option>
                                                            <option value="Reporte de robos">Reporte de robos</option>
                                                            <option value="Objetos perdidos">Objetos perdidos</option>
                                                            <option value="Infraestructura en mal estado">
                                                                Infraestructura en mal estado
                                                            </option>
                                                            <option value="Ambulancia PUCP">Ambulancia PUCP</option>
                                                            <option value="Accidente y Otros">Accidente y Otros</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="row mb-3">
                                                    <label class="col-sm-2 col-form-label"><h5>Nivel de Urgencia </h5>
                                                    </label>
                                                    <div class="col-sm-4">
                                                        <select class="form-select" aria-label="Default select example"
                                                                name="nivel">
                                                            <option selected>Seleccione...</option>
                                                            <option value="Leve">Leve</option>
                                                            <option value="Moderado">Moderado</option>
                                                            <option value="Crítico">Crítico</option>
                                                        </select>
                                                    </div>
                                                </div>

                                                <div class="row mb-3">
                                                    <label class="col-sm-2 col-form-label"><h5>Zona PUCP</h5></label>
                                                    <div class="col-sm-4">
                                                        <select class="form-select" aria-label="Default select example"
                                                                name="zona">
                                                            <option selected>Seleccione...</option>
                                                            <% for (ZonaPucp zonas : listaZonasPUCP) { %>
                                                            <option value="<%=zonas.getIdZonaPucp()%>"><%=zonas.getNombreZona()%>
                                                            </option>
                                                            <% } %>

                                                        </select>
                                                    </div>
                                                </div>
                                                <!-- Form Row        -->


                                                <div class="row gx-3 mb-3">
                                                    <!-- Form Group (first name)-->

                                                    <div class="col-md-6">
                                                        <label for="map-container-google-2">Ubicacion</label>
                                                        <div id="map-container-google-2"
                                                             class="z-depth-1-half map-container"
                                                             style="height: 200px">
                                                            <iframe src="https://maps.google.com/maps?q=chicago&t=&z=13&ie=UTF8&iwloc=&output=embed"
                                                                    frameborder="0"
                                                                    style="border:0" allowfullscreen></iframe>
                                                        </div>
                                                    </div>
                                                    <!-- Form Group (last name)-->

                                                </div>

                                                <div class="mb-3">
                                                    <label for="customFile">Subir fotos de la
                                                        incidencia: </label>
                                                    <input type="file" class="form-control" style="width: 350px"
                                                           id="customFile" name="foto1" />
                                                </div>
                                                <!-- Form Group (location)-->
                                                <!-- Form Group (email address)-->
                                                <!-- Form Row-->
                                                <!-- Save changes button-->
                                                <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                                    <button type="submit" class="btn btn-primary">Guardar
                                                    </button>
                                                    <button class="btn btn-primary"
                                                            href="<%=request.getContextPath()%>/Inicio">Cancelar
                                                    </button>


                                                    <div class="form-check form-switch">
                                                        <input class="form-check-input" type="checkbox"
                                                               role="switch" id="flexSwitchCheckDefault">
                                                        <label class="form-check-label"
                                                               for="flexSwitchCheckDefault">Activar publicación
                                                            anónima</label>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>


                    </div>


                </div>
            </section><!-- End Portfolio Details Section -->

        </main>

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
