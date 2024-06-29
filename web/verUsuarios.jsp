<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/bodyprimer.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="controller.UsuarioJpaController"%>

<div class="container-fluid">

    <!-- Page Heading -->
    <h1 class="h3 mb-2 text-gray-800">Ver Usuarios</h1>
    <p class="mb-4">A continuación podrá visualizar la lista completa de usuarios</p>

    <!-- DataTales Example -->
    <div class="card shadow mb-4">
        <div class="card-header py-3">
            <h6 class="m-0 font-weight-bold text-primary">Usuarios</h6>
        </div>
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                    <thead>
                        <tr>
                            <th>Id</th>
                            <th>Nombre</th>
                            <th>Usuario</th>
                            <th>Contrasena</th>
                            <th>Acciones</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="Usuario" items="${sessionScope.listaUsuarios}">
                            <tr>
                                <td>${Usuario.idUser}</td>
                                <td>${Usuario.nombre}</td>
                                <td>${Usuario.usuario}</td>
                                <td>${Usuario.password}</td>
                                <td>
                                    <a href="EditarUsuario?id=${Usuario.idUser}" class="btn btn-primary btn-sm">Modificar</a>
                                    <a href="EliminarUsuario?id=${Usuario.idUser}" class="btn btn-danger btn-sm">Eliminar</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                    
                </table>
            </div>
        </div>
    </div>

</div>
<!-- /.container-fluid -->

<%@include file="componentes/bodyfinal.jsp"%>

