<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Página principal</title>

        <!-- Favicons -->
        <link href="assets/img/telepucp.png" rel="icon">
        <link href="assets/img/apple-touch-icon.png" rel="apple-touch-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Roboto:300,300i,400,400i,500,500i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="assets/vendor/aos/aos.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
        <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="assets/vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
        <link href="assets/vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">

    </head>

    <body>
        <!-- ======= Header ======= -->
        <header id="header" class="d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">
                <h1 class="logo"> <a>Incidencias PUCP</a></h1>

            </div>
        </header><!-- End Header -->

        <!-- ======= Hero Section ======= -->
        <section id="hero" class="d-flex align-items-center">
            <div class="container" data-aos="zoom-out" data-aos-delay="100">
                <div class="row d-flex align-items-center">
                    <div class="col-lg-2 col-md-3 col-sm-3">
                        <img src="assets/img/telepucp.png" width="100%" alt="logo">
                    </div>
                </div>
                <h1>Registra tu primera</br><span> incidencia</span></h1>
                <h2>y ayudemos a nuestra comunidad PUCP a mantener todo en orden</h2>
                <div class="d-flex">
                    <a href="<%=request.getContextPath()%>/Login" class="btn-get-started scrollto">Inicia sesión</a>
                    <a href="<%=request.getContextPath()%>/Login?action=signin" class=" btn-watch-video"><span>Regístrate</span></a>
                </div>
            </div>

        </section><!-- End Hero -->

        <!-- ======= Footer ======= -->
        <footer id="footer">
            <div class="container py-4">
                <div class="copyright">
                    &copy; Copyright <strong><span>Incidencias PUCP</span></strong>. Todos los derechos reservados
                </div>
                <div class="credits">
                    Desarrollado por TELEPUCP
                </div>
            </div>
        </footer><!-- End Footer -->

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
