<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="componentes/header.jsp"%>
<%@include file="componentes/bodyprimer.jsp"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Busqueda Construcciones</title>
        <link href="iconos/css.css" rel="stylesheet" type="text/css">
        <style>
            .formaboton {
                border: 1px outset #F8F8F8;
                font-weight: bold;
                font-size: 7pt;
                cursor: pointer;
                color: #ffffff;
                font-family: Verdana, Helvetica, sans-serif;
                background-color: #6387A2;
            }
        </style>
        <script>
            function enviar() {
                const npiso = document.querywindow.npiso.value;
                const fechac = document.querywindow.fechac.value;
                const mep = document.querywindow.mep.value;
                const edc = document.querywindow.edc.value;
                const muro = document.querywindow.muro.value;
                const techo = document.querywindow.techo.value;
                const pisos = document.querywindow.pisos.value;
                const puert = document.querywindow.puert.value;
                const reves = document.querywindow.reves.value;
                const bano = document.querywindow.bano.value;
                const inses = document.querywindow.inses.value;
                const arema = document.querywindow.arema.value;
                const areme = document.querywindow.areme.value;

                if (parseFloat(areme) <= parseFloat(arema)) {
                    alert("Área 'Mayor que' debe ser menor que el área 'Menor que'");
                    return;
                }

                const ruta = `busca_consok.php?npiso=${npiso}&fechac=${fechac}&mep=${mep}&edc=${edc}&muro=${muro}&techo=${techo}&pisos=${pisos}&puert=${puert}&reves=${reves}&bano=${bano}&inses=${inses}&arema=${arema}&areme=${areme}`;
                window.open(ruta);
                window.close();
            }

            function keyPressed(e) {
                if (e.keyCode === 46 || e.keyCode === 9) {
                    return true;
                }
                return false;
            }
        </script>
    </head>
    <body>
        <form name="querywindow" action="busca_consok.php" method="post">
            <font face="verdana" color="000000" size="2">
                &nbsp;&nbsp;&nbsp;<b>Buscar por Construcciones</b>
            </font>
            <div align="right"><br>
                <input type="button" class="formaboton" value="&nbsp;&nbsp;&nbsp;Buscar&nbsp;&nbsp;&nbsp;" onclick="enviar();">
                &nbsp;&nbsp;&nbsp;&nbsp;
                <input type="button" class="formaboton" value="&nbsp;&nbsp;&nbsp;Cerrar&nbsp;&nbsp;&nbsp;" onclick="window.close();">
                &nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div align="left">
                <table cellpadding="0" cellspacing="0" border="0" width="360" align="center">
                    <tr>
                        <td colspan="3"><hr></td>
                    </tr>
                    <tr>
                        <td><b>Nº de Pisos</b></td>
                        <td>:</td>
                        <td>
                            <select name="npiso">
                                <option value="">Seleccionar</option>
                                <option value="1">01</option>
                                <option value="2">02</option>
                                <option value="3">03</option>
                                <option value="4">04</option>
                                <option value="5">05</option>
                                <option value="6">06</option>
                                <option value="7">07</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td><b>Fecha de Construcción</b>&nbsp;&nbsp;
                            <a style="cursor:pointer;" onclick="alert('Seleccionar fecha')">
                                <img src="iconos/Calendar.gif" border="0" style="padding-top:10px" align="absmiddle">
                            </a>
                        </td>
                        <td>:</td>
                        <td>
                            <input type="text" name="fechac" maxlength="15" size="13" onclick="this.select()" onfocus="this.select()" onkeydown='return(keyPressed(event));'>
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td><b>Material Estru. Predom.</b></td>
                        <td>:</td>
                        <td>
                            <select name="mep">
                                <option value="">Seleccionar</option>
                                <option value="1">Concreto</option>
                                <option value="2">Ladrillo</option>
                                <option value="3">Adobe</option>
                                <option value="4">Otros</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td><b>Estado de Conservación</b></td>
                        <td>:</td>
                        <td>
                            <select name="edc">
                                <option value="">Seleccionar</option>
                                <option value="1">Muy Bueno</option>
                                <option value="2">Bueno</option>
                                <option value="3">Regular</option>
                                <option value="4">Malo</option>
                                <option value="5">Muy Malo</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td colspan="3"><b>Estructura</b></td>
                    </tr>
                    <tr>
                        <td>Muro</td>
                        <td>:</td>
                        <td>
                            <select name="muro">
                                <option value="">Seleccionar</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                                <option value="E">E</option>
                                <option value="F">F</option>
                                <option value="G">G</option>
                                <option value="H">H</option>
                                <option value="I">I</option>
                                <option value="J">J</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Techo</td>
                        <td>:</td>
                        <td>
                            <select name="techo">
                                <option value="">Seleccionar</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                                <option value="E">E</option>
                                <option value="F">F</option>
                                <option value="G">G</option>
                                <option value="H">H</option>
                                <option value="I">I</option>
                                <option value="J">J</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Pisos</td>
                        <td>:</td>
                        <td>
                            <select name="pisos">
                                <option value="">Seleccionar</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                                <option value="E">E</option>
                                <option value="F">F</option>
                                <option value="G">G</option>
                                <option value="H">H</option>
                                <option value="I">I</option>
                                <option value="J">J</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Puerta</td>
                        <td>:</td>
                        <td>
                            <select name="puert">
                                <option value="">Seleccionar</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                                <option value="E">E</option>
                                <option value="F">F</option>
                                <option value="G">G</option>
                                <option value="H">H</option>
                                <option value="I">I</option>
                                <option value="J">J</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Revest.</td>
                        <td>:</td>
                        <td>
                            <select name="reves">
                                <option value="">Seleccionar</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                                <option value="E">E</option>
                                <option value="F">F</option>
                                <option value="G">G</option>
                                <option value="H">H</option>
                                <option value="I">I</option>
                                <option value="J">J</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Baño</td>
                        <td>:</td>
                        <td>
                            <select name="bano">
                                <option value="">Seleccionar</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                                <option value="E">E</option>
                                <option value="F">F</option>
                                <option value="G">G</option>
                                <option value="H">H</option>
                                <option value="I">I</option>
                                <option value="J">J</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td>Instal. Sanit.</td>
                        <td>:</td>
                        <td>
                            <select name="inses">
                                <option value="">Seleccionar</option>
                                <option value="A">A</option>
                                <option value="B">B</option>
                                <option value="C">C</option>
                                <option value="D">D</option>
                                <option value="E">E</option>
                                <option value="F">F</option>
                                <option value="G">G</option>
                                <option value="H">H</option>
                                <option value="I">I</option>
                                <option value="J">J</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td colspan="3"><b>Área (m²)</b></td>
                    </tr>
                    <tr>
                        <td>Mayor que</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="arema" size="13" maxlength="10">
                        </td>
                    </tr>
                    <tr>
                        <td>Menor que</td>
                        <td>:</td>
                        <td>
                            <input type="text" name="areme" size="13" maxlength="10">
                        </td>
                    </tr>
                    <tr>
                        <td><br></td>
                    </tr>
                    <tr>
                        <td colspan="3"><hr></td>
                    </tr>
                </table>
            </div>
        </form>
    </body>
</html>

<%@include file="componentes/bodyfinal.jsp"%>
