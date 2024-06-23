<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%
    String username = (String) request.getSession(false).getAttribute("nombre");
    
    
%>

<!DOCTYPE html>

<html lang="en">

<%@include file ="componentes/header.jsp"%>
<%@include file ="componentes/bodyprimer.jsp"%>                       
<img src="https://st4.depositphotos.com/3613639/26525/i/450/depositphotos_265255158-stock-photo-hand-drawing-an-imaginary-cadastral.jpg" alt="alt"/>
<%@include file ="componentes/bodyfinal.jsp"%>

