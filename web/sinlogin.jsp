<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Acceso Denegado</title>
    <!-- Custom fonts for this template-->
    <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">
    <!-- Custom styles for this template-->
    <link href="css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Custom CSS for error message -->
    <style>
        .container {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            text-align: center;
            color: #333;
        }
        .message-box {
            max-width: 600px;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            background-color: #fff;
        }
        .titulo {
            font-family: 'Nunito', sans-serif;
            font-size: 36px;
            font-weight: bold;
            margin-bottom: 10px;
        }
        .subtitulo {
            font-family: 'Nunito', sans-serif;
            font-size: 24px;
            color: #666;
        }
        .btn-primary {
            background-color: #4e73df;
            border: none;
            color: #fff;
        }
        .btn-primary:hover {
            background-color: #2e59d9;
        }
    </style>
</head>
<body class="bg-gradient-primary">
    <div class="container">
        <div class="message-box">
            <h1 class="titulo">Acceso Denegado</h1>
            <h2 class="subtitulo">Debe iniciar sesión para acceder a esta página.</h2>
            <a href="login.jsp" class="btn btn-primary mt-4">Ir al Login</a>
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
