<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean scope="request" id="correo" type="java.lang.String"/>
<!DOCTYPE html>
<html lang="en">

  <head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1.0" name="viewport">

    <title>Pages / Login - NiceAdmin Bootstrap Template</title>

    <!-- Favicons -->
    <link href="assets/img/favicon.png" rel="icon">

    <!-- Google Fonts -->
    <link href="https://fonts.gstatic.com" rel="preconnect">
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i" rel="stylesheet">

    <!-- Vendor CSS Files -->
    <link href="assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/vendor/bootstrap-icons/bootstrap-icons.css" rel="stylesheet">
    <link href="assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.snow.css" rel="stylesheet">
    <link href="assets/vendor/quill/quill.bubble.css" rel="stylesheet">
    <link href="assets/vendor/remixicon/remixicon.css" rel="stylesheet">
    <link href="assets/vendor/simple-datatables/style.css" rel="stylesheet">

    <!-- Template Main CSS File -->
    <link href="assets/css/style_inicio_sesion.css" rel="stylesheet">

  </head>

  <body>

    <main>
      <div class="container">

        <section class="section register min-vh-100 d-flex flex-column align-items-center justify-content-center py-4">
          <div class="container">

            <div class="row justify-content-center">
              <div class=" col-md-10 col-lg-6 col-xl-7 d-flex align-items-center order-2 order-lg-1">

                <img src="assets/img/fondo_inicio_sesion.png" class="img-fluid" alt="Sample image">

              </div>


              <div class=" col-md-10 col-lg-6 col-xl-5 order-1 order-lg-2">
                <div class="d-flex justify-content-center py-4">
                  <a href="index.html" class="logo d-flex align-items-center w-auto">
                    <img src="assets/img/telepucp.png" alt="">
                    <span class="d-none d-lg-block">Incidencias PUCP</span>
                  </a>
                </div><!-- End Logo -->



                <br>
                <div class="row g-3">
                  <div class="col-12 mx-auto my-auto">
                    <div class="card mb-3">
                      <div class="card-body px-lg-5 py-lg-5 text-center">
                        <div class="pt-4 pb-2">
                          <h5 class="card-title text-center pb-0 fs-4">Autenticación de doble factor</h5>
                          <br>
                          <div class="col-12 " >
                            <p>Se necesitará un paso extra para confirmar su identidad.</p>
                            <br>
                            <p>Se acaba de enviar un correo con un PIN de verificación de 4 dígitos al <%=correo%></p>

                          </div>
                        </div>
                        <form method="post" action="<%=request.getContextPath()%>/IniciarSesion?post=doblef">
                          <p>Ingrese el PIN:</p>

                          <div class="row mb-4">
                            <div class="col-lg-3 col-md-3 col-3 ps-0 pe-md-3">
                              <input type="text" class="form-control text-lg text-center" placeholder="_" aria-label="2fa">
                            </div>
                            <div class="col-lg-3 col-md-3 col-3 ps-0 pe-md-3">
                              <input type="text" class="form-control text-lg text-center" placeholder="_" aria-label="2fa">
                            </div>
                            <div class="col-lg-3 col-md-3 col-3 ps-0 pe-md-3">
                              <input type="text" class="form-control text-lg text-center" placeholder="_" aria-label="2fa">
                            </div>
                            <div class="col-lg-3 col-md-3 col-3 ps-0 pe-md-3">
                              <input type="text" class="form-control text-lg text-center" placeholder="_" aria-label="2fa">
                            </div>
                          </div>
                          <div class="col-12">
                            <button class="btn btn-primary w-100" type="submit">Continuar</button>
                          </div>
                        </form>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>

        </section>

      </div>
    </main><!-- End #main -->

    <a href="#" class="back-to-top d-flex align-items-center justify-content-center"><i class="bi bi-arrow-up-short"></i></a>

    <!-- Vendor JS Files -->
    <script src="assets/vendor/apexcharts/apexcharts.min.js"></script>
    <script src="assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="assets/vendor/chart.js/chart.min.js"></script>
    <script src="assets/vendor/echarts/echarts.min.js"></script>
    <script src="assets/vendor/quill/quill.min.js"></script>
    <script src="assets/vendor/simple-datatables/simple-datatables.js"></script>
    <script src="assets/vendor/tinymce/tinymce.min.js"></script>
    <script src="assets/vendor/php-email-form/validate.js"></script>

    <!-- Template Main JS File -->
    <script src="assets/js/main.js"></script>

  </body>

</html>
