<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="model.Jugador" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
    <head>
        <title>Ver votos</title>
        <link href="estilos.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <h1>Votos actuales</h1>
        <table>
            <tr>
                <th>Jugador</th>
                <th>Votos</th>
            </tr>
            <c:forEach var="jugador" items="${votos}">
                <tr>
                    <td>${jugador.nombre}</td>
                    <td>${jugador.votos}</td>
                </tr>
        </table>
        <br>
        <br>
        <br> <a href="index.html"> Ir al comienzo</a>
    </body>
</html>