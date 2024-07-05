<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Ingreso Clínica - Error</title>
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Custom CSS for background image and form container -->
    <style>
        .bg-custom-image {
            background-image: url(https://upload.wikimedia.org/wikipedia/commons/d/db/Escudo_de_San_Juan_de_Miraflores.png); /* Actualiza el path a tu imagen */
            
             background-size: contain; /* O 'cover' dependiendo de tus necesidades */
    background-position: center;
    background-repeat: no-repeat;
    height: 100%;
    width: 100%;
        }
        .login-container {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            background-color: rgba(255, 255, 255, 0.9);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .titulo {
            font-family: 'Nunito', sans-serif;
            font-size: 36px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }
        .subtitulo {
            font-family: 'Nunito', sans-serif;
            font-size: 24px;
            color: #666;
        }
    </style>
</head>
<body class="bg-gradient-primary">
    <div class="container">
        <!-- Outer Row -->
        <div class="row justify-content-center">
            <div class="col-xl-10 col-lg-12 col-md-9">
                <div class="card o-hidden border-0 shadow-lg my-5">
                    <div class="card-body p-0">
                        <!-- Nested Row within Card Body -->
                        <div class="row">
                            <div class="col-lg-6 d-none d-lg-block bg-custom-image"></div>
                            <div class="col-lg-6">
                                <div class="p-5 login-container">
                                    <div class="text-center">
                                        <h1 class="titulo">Ingreso Clínica</h1>
                                        <h2 class="subtitulo">Nombre de usuario o contraseña incorrecto</h2>
                                    </div>
                                    <div class="text-center mt-4">
                                        <a href="login.jsp" class="btn btn-primary btn-user btn-block">Regresar a Login</a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <!-- Core plugin JavaScript-->
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin-2.min.js"></script>
</body>
</html>
