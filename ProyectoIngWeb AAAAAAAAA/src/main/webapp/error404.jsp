<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Error 404!</title>

        <!-- Favicons -->
        <link href="assets/img/telepucp.png" rel="icon">
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

        <!-- Template Main CSS File -->
        <link href="assets/css/style.css" rel="stylesheet">

    </head>

    <body id="page-top" >
        <header id="header" class="d-flex align-items-center">
            <div class="container d-flex align-items-center justify-content-between">

                <h1 class="logo" style="color:#ffffff">Incidencias PUCP</h1>
            </div>
        </header>

        <section id="hero" class="d-flex align-items-center">
            <div class="container" data-aos="zoom-out" data-aos-delay="100">
                <div class="text-center">
                    <h1 >¡Error 404!<br></h1></div><br><br>
                <div class="row d-flex justify-content-center">

                    <img class="col-4" src="assets/img/quilla.png" alt="quilla" style="width: 350px">

                </div>
                <h1>¡Página no encontrada!<br><span>La página que estabas buscando no se
                                encuentra disponible.</span></h1>
                <br>
                <a class="nav-link  ">
                    <div class="d-flex">
                        <a href="<%=request.getContextPath()%>/" class="btn-get-started scrollto">Volver</a>
                    </div>
                </a>
            </div>


        </section>

        <!-- Page Wrapper -->


        <footer id="footer">
            <div class="container py-4">
                <div class="copyright">
                    &copy; Copyright <strong><span>Incidencias PUCP</span></strong>. Todos los derechos reservados
                </div>
                <div class="credits">
                    Desarrollado por TELEPUCP
                </div>
            </div>
        </footer>
        <!-- End Footer -->

        <div id="preloader"></div>

        <!-- Bootstrap core JavaScript-->
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