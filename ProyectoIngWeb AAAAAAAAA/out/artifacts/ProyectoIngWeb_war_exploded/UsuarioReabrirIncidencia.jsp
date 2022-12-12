<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String searchText = (String) request.getAttribute("searchText");
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Incidencia / Incidencias PUCP</title>

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">

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
                        <h1 class="name" style="margin-top: 25px">José Bustamante
                            <br><h6>Usuario PUCP</h6></br>
                        </h1>
                    </div>
                </div>
                <nav id="navbar" class="navbar">

                    <ul>
                        <li>
                            <div class="container-fluid">
                                <form method="post" class="d-flex input-group w-auto"
                                      action="<%=request.getContextPath()%>/Inicio?action=buscar">
                                    <input required type="text"
                                           name="searchText"
                                           class="form-control rounded"
                                           placeholder="Buscar incidencia"
                                           id="floatingInput" aria-label="Search" aria-describedby="search-addon"
                                           value="<%=searchText != null? searchText:""%>"/>
                                    <span class="input-group-text border-0" id="search-addon"><i
                                            class="fas fa-search"></i></span>
                                </form>
                            </div>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=listar">Inicio</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=perfil">Perfil</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=misIncidencias">Mis Incidencias</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=registrarIncidencia">Registrar Incidencia</a></li>
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Inicio?action=cerrarSesion">Cerrar sesión</a></li>
                    </ul>

                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav><!-- .navbar -->

            </div>
        </header><!-- End Header -->

        <main id="main" data-aos="fade-up">


            <!-- ======= Portfolio Details Section ======= -->
            <section id="portfolio-details" class="portfolio-details">
                <div class="container">
                    <div class="container-fluid">

                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Mis Incidencias</h1>
                        </div>

                        <div class="container">

                            <div class="col-lg-12 mx-auto">
                                <!-- Dropdown Card Example -->
                                <div class="card shadow mb-4 ">
                                    <!-- Card Header - Dropdown -->
                                    <div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
                                        <img src="assets/img/profile_upload.png" height="70px"/>
                                        <br>
                                        <div></div>
                                        <h4 class="m-0 font-weight-bold text-primary" style="width:75%">Pérdida de carnet
                                            universitario #ayuda
                                            #cachimbo #2022-2
                                            <div style="color:darkgray;display: flex;flex-direction: column"><h6>Frank
                                                Einstein </h6><h6>Registrado hoy</h6></div>
                                            <div><a href="#" class="btn btn-warning btn-circle">
                                                <i class="fas fa-exclamation-triangle"> 10</i>
                                            </a></div>
                                        </h4>
                                        <button type="button" class="btn btn-primary btn-sm">Atendido</button>


                                    </div>


                                    <!-- Card Body -->

                                    <div class="card-body">
                                        <div><h4>Descripción:</h4></div>
                                        Dropdown menus can be placed in the card header in order to extend the functionality
                                        of a basic card. In this dropdown card example, the Font Awesome vertical ellipsis
                                        icon in the card header can be clicked on in order to toggle a dropdown menu.
                                        <div><br><h4>Zona PUCP:</h4></div>
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad, assumenda autem corporis
                                        doloribus error ex facere itaque iusto neque nobis non optio quaerat quam quas, quisquam
                                        ut vero voluptate voluptatibus!
                                        <div><br><h4>Ubicación: </h4></div>
                                        <button type="button" class="btn btn-primary btn-lg"
                                                style="background-color: red;border:red">Ver en el mapa
                                        </button>
                                        <div><br><h4>Fotos:</h4></div>
                                        <div class="col-12">
                                            <br>
                                            <div class="row">
                                                <div class="col-auto">
                                                    <img src="assets/img/facultad_matematicas_pucp.jpg" alt="logo"
                                                         class="img-fluid d-block mx-auto"
                                                         style="height: 200px;border-radius: 1em">
                                                </div>
                                                <div class="col-auto">
                                                    <img src="assets/img/facultad_derecho_pucp.jpg" alt="logo"
                                                         class="img-fluid d-block mx-auto"
                                                         style="height: 200px;border-radius: 1em"></div>
                                            </div>
                                            <br>


                                        </div>
                                        <div><br><h4>Tipo de incidencia: </h4></div>
                                        <div>
                                            <a href="#" class="btn btn-primary btn-lg disabled" role="button"
                                               aria-disabled="true">Objetos Perdidos</a>
                                        </div>



                                        <div><br><h4>Nivel de urgencia: </h4></div>
                                        <div>
                                            <a href="#" class="btn btn-primary btn-lg disabled" role="button"
                                               aria-disabled="true">Leve</a>
                                        </div>

                                        <div><br><h5>Comentario de Seguridad: </h5>Lorem ipsum dolor sit amet, consectetur
                                            adipisicing elit. Ab accusantium alias amet
                                            dolore doloremque ea, et exercitationem impedit itaque iure maxime neque nobis odit
                                            praesentium ratione rem sit vitae voluptates! Lorem ipsum dolor sit amet,
                                            consectetur
                                            adipisicing elit. Impedit iste nesciunt nulla perspiciatis, quaerat rem
                                            reprehenderit
                                            saepe! Accusantium consectetur, delectus deserunt eum hic nihil provident quos sunt
                                            tempore tenetur veniam?
                                        </div>

                                        <div style="text-align: right"><br><h5>Comentario de Frank Einstein: </h5>Lorem ipsum dolor
                                            sit amet, consectetur adipisicing elit. Ab accusantium alias amet
                                            dolore doloremque ea, et exercitationem impedit itaque iure maxime neque nobis odit
                                            praesentium ratione rem sit vitae voluptates! Lorem ipsum dolor sit amet,
                                            consectetur
                                            adipisicing elit. Impedit iste nesciunt nulla perspiciatis, quaerat rem
                                            reprehenderit
                                            saepe! Accusantium consectetur, delectus deserunt eum hic nihil provident quos sunt
                                            tempore tenetur veniam?
                                        </div>


                                        <div><br><h5>Comentario de Seguridad: </h5>
                                            Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ab accusantium alias amet
                                            dolore doloremque ea, et exercitationem impedit itaque iure maxime neque nobis odit
                                            praesentium ratione rem sit vitae voluptates! Lorem ipsum dolor sit amet,
                                            consectetur
                                            adipisicing elit.
                                        </div>
                                        <div class="row">
                                            <div class="col-auto">
                                                <br>
                                                <div><button type="button" class="btn btn-primary btn-lg">Aceptar</button></div>

                                            </div>
                                            <div class="col-auto">
                                                <br>
                                                <div><button type="button" class="btn btn-primary btn-lg">Reabrir (4)</button></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>


                </div>
            </section><!-- End Portfolio Details Section -->
        </main>

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