<%-- 
    Document   : login
    Created on : 24 abr. 2024, 20:59:09
    Author     : User
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>SISTEMA CATASTRAL</title>
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Custom CSS for background image and form container -->
    <style>
        .bg-custom-image {
            background-image: url('https://media.licdn.com/dms/image/C4E0BAQFhZVpWRBXLFA/company-logo_200_200/0/1674133326693/municipalidad_de_san_juan_de_miraflores_logo?e=2147483647&v=beta&t=44AVLAzhC3VBRn9Erz5BWg3INEdPDPYhD6uC7nlgwkA');
            background-size: cover;
            background-position: center;
        }
        .login-container {
            max-width: 600px; /* Cambia este valor según el tamaño que desees */
            margin: 0 auto;
            padding: 20px;
            border-radius: 10px;
            background-color: rgba(255, 255, 255, 0.9);
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .titulo {
            font-family: 'Nunito', sans-serif; /* Cambia 'Nunito' por la fuente que desees */
            font-size: 36px;
            font-weight: bold;
            color: #333;
            margin-bottom: 10px;
        }
        .subtitulo {
            font-family: 'Nunito', sans-serif; /* Cambia 'Nunito' por la fuente que desees */
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
                                        <h1 class="titulo">MUNICIPALIDAD SAN JUAN DE MIRAFLORES</h1>
                                        <h2 class="subtitulo">SISTEMA CATASTRAL</h2>
                                    </div>
                                    <form  class="user" action="loginServlet" method="post"> <!-- Cambiar action y method según corresponda -->
                                        <div class="form-group">
                                            <select class="form-control" name="account_type">
                                                <option value="user">Usuario</option>
                                                <option value="admin">Administrador</option>
                                            </select>
                                        </div>
                                         <div class="form-group">
                                             <input type="text" class="form-control form-control-user" id="exampleInputPassword" placeholder="Correo" name="Correo">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control form-control-user" id="exampleInputPassword" placeholder="Password" name="password">
                                        </div>
                                        <div class="form-group">
                                            <div class="custom-control custom-checkbox small">
                                                <input type="checkbox" class="custom-control-input" id="customCheck">
                                                <label class="custom-control-label" for="customCheck">Remember Me</label>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-primary btn-user btn-block">Ingrear</button>
                                    </form>
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




