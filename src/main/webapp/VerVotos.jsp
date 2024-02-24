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
            <c:forEach var="voto" items="${votos}">
                <tr>
                    <td>${voto.nombre}</td>
                    <td>${voto.votos}</td>
                </tr>
            </c:forEach>
        </table>
        <br>
        <br>
        <br> <a href="index.html"> Ir al comienzo</a>
    </body>
</html>