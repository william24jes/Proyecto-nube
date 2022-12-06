<%--
  Created by IntelliJ IDEA.
  User: willi
  Date: 25/10/2022
  Time: 14:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userAdmin" scope="session" type="com.example.proyectoingweb.servlets.model.beans.Usuarios" class="com.example.proyectoingweb.servlets.model.beans.Usuarios"></jsp:useBean>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
              rel="stylesheet">
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
              rel="stylesheet">
        <script src="https://kit.fontawesome.com/600382161a.js" crossorigin="anonymous"></script>
        <link href="assets/css/style_administrador.css" rel="stylesheet">

        <!-- Favicons -->
        <link href="assets/img/favicon.png" rel="icon">

        <title>Añadir usuario / Incidencias PUCP</title>
    </head>

    <body>

        <!-- ======= Top Bar ======= -->
        <section id="topbar" class="d-flex align-items-center" style="background-color: crimson">

        </section>

        <!-- ======= Header ======= -->
        <header style="background-color:white " id="header" class="d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">

                <div class="d-md-flex align-items-center">
                    <div>
                        <h1 class="name" style="margin-top: 25px"><%=userAdmin.getNombreYApellido()%>
                            <br><h6>Administrador</h6><br>
                        </h1>
                    </div>
                </div>

                <nav id="navbar" class="navbar">
                    <ul>
                        <li><a class="nav-link active " href="<%=request.getContextPath()%>/Admin">Lista de
                            usuarios</a></li>
                        <li><a class="nav-link scrollto "
                               href="<%=request.getContextPath()%>/Admin?action=perfil">Perfil</a></li>
                        <li>
                            <div class="container-fluid">
                                <form method="post" class="d-flex input-group w-auto"
                                      action="<%=request.getContextPath()%>/Admin?action=buscar">
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
                        <li><a class="nav-link" href="<%=request.getContextPath()%>/Admin?action=cerrarSesion">Cerrar sesión</a></li>
                    </ul>

                    <i class="bi bi-list mobile-nav-toggle"></i>
                </nav><!-- .navbar -->

            </div>
        </header><!-- End Header -->

        <main id="main">

            <section class="section profile">
                <div class="container">
                    <div class="container-fluid">
                        <h4 class="hero">Completar datos del nuevo usuario:</h4>
                        <form method="post" action="<%=request.getContextPath()%>/Admin?action=guardar"
                              class="row g-3 needs-validation" novalidate>
                            <div class="col-lg-4">
                                <label for="Nombres" class="form-label">Nombres</label>
                                <input required type="text" class="form-control" id="Nombres" placeholder="Nombres"
                                       name="Nombres">
                                <div class="invalid-feedback">
                                    Ingresa un nombre
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <label for="Apellidos" class="form-label">Apellidos</label>
                                <input required type="text" class="form-control" id="Apellidos" placeholder="Apellidos"
                                       name="Apellidos">
                                <div class="invalid-feedback">
                                    Ingresa los apellidos
                                </div>
                            </div>
                            <div class="col-lg-4">
                                <label for="Correo PUCP" class="form-label">Correo PUCP</label>
                                <div class="input-group">
                                    <span class="input-group-text" id="inputGroupPrepend2">@</span>
                                    <input required type="email" class="form-control" id="Correo PUCP" placeholder="Correo PUCP"
                                           name="Correo PUCP" aria-describedby="inputGroupPrepend2">
                                    <div class="invalid-feedback">
                                        Ingresa un correo PUCP
                                    </div>
                                </div>
                            </div>
                            <div class="col-lg-2">
                                <label for="Codigo" class="form-label">Código</label>
                                <input required type="number" class="form-control" id="Codigo" placeholder="Codigo" name="Codigo">
                                <div class="invalid-feedback">
                                    Ingrese un código
                                </div>
                            </div>
                            <div class="col-lg-2">
                                <label for="DNI" class="form-label">DNI</label>
                                <input required type="number" class="form-control" id="DNI" placeholder="DNI" name="DNI">
                                <div class="invalid-feedback">
                                    Ingrese un DNI
                                </div>
                            </div>
                            <div class="col-lg-2">
                                <label for="Celular" class="form-label">Celular</label>
                                <input required type="number" class="form-control" id="Celular" placeholder="Celular"
                                       name="Celular">
                                <div class="invalid-feedback">
                                    Ingrese un número de celular
                                </div>
                            </div>
                            <div class="col-lg-3">
                                <label for="Categoría" class="form-label">Categoría</label>
                                <select required class="form-select" id="Categoría" placeholder="Categoría" name="Categoría">
                                    <option value="">Seleccione una opción</option>
                                    <option value="Alumno">Alumno</option>
                                    <option value="Administrativo">Administrativo</option>
                                    <option value="Jefe de practica">Jefe de práctica</option>
                                    <option value="Profesor">Profesor</option>
                                    <option value="Egresado">Egresado</option>
                                </select>
                                <div class="invalid-feedback">
                                    Seleccione una categoría
                                </div>
                            </div>

                            <div class="col-lg-3">
                                <label for="Rol" class="form-label">Rol</label>
                                <select required class="form-select" id="Rol" placeholder="Rol" name="Rol">
                                    <option value="">Seleccione una opción</option>
                                    <option value="Usuario PUCP">Usuario PUCP</option>
                                    <option value="Seguridad">Seguridad</option>
                                </select>
                                <div class="invalid-feedback">
                                    Seleccione un rol
                                </div>
                            </div>

                            <div class="d-grid gap-2 col-6 col-lg-4 col-xl-3 mx-auto">
                                <button class="btn btn-danger" type="submit">Registrar</button>
                            </div>

                            <div class="d-grid gap-2 col-3 col-lg-4 col-xl-3 mx-auto">
                                <a class="btn btn-secondary" href="<%=request.getContextPath()%>/Admin">Regresar</a>
                            </div>

                        </form>
                    </div>
                </div>
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

        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict'

                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.querySelectorAll('.needs-validation')

                // Loop over them and prevent submission
                Array.prototype.slice.call(forms)
                    .forEach(function (form) {
                        form.addEventListener('submit', function (event) {
                            if (!form.checkValidity()) {
                                event.preventDefault()
                                event.stopPropagation()
                            }

                            form.classList.add('was-validated')
                        }, false)
                    })
            })()
        </script>
    </body>
</html>