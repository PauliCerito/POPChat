<%-- 
    Document   : paginaPrincipalPOPChat
    Created on : 1 ago 2024, 21:32:53
    Author     : Usuario
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Consulta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>POPChat</title>
    </head>
    <body>
        <h1>Ingrese su pregunta</h1>
            <form action="SVPagPrincipal" method="POST">
            <p><label for>Pregunta:</label><input type="text" name="pregunta"></p>
            <button type="submit">Enviar</button>
            </form>
        
 <%
            String pregunta = (String) request.getAttribute("pregun");
            List<Consulta> respuesta = (List<Consulta>) request.getAttribute("respuesta");
            if (respuesta != null && !respuesta.isEmpty()) {
                Consulta ultimaRespuesta = respuesta.get(respuesta.size()-1);
        %>
            <h2>Pregunta: </h2>
            <p><%= pregunta%></p>
            <h2>Respuesta:</h2>
            <ul>
                    <p><%= ultimaRespuesta.getRespuesta() %></p>
                <%
                    }
                %>
            </ul>

    </body>
</html>
