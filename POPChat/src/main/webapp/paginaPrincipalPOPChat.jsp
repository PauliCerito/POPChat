<%-- 
    Document   : paginaPrincipalPOPChat
    Created on : 1 ago 2024, 21:32:53
    Author     : Paula Castillo
--%>

<%@page import="java.util.List"%>
<%@page import="Modelo.Consulta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>POPChat</title>
        <link rel="stylesheet" href="paginaPrincipalCSS.css" type="text/css">
    </head>
    <body>
        <h1 class="titulo">POP CHAT</h1>
        
        <div class="chat">
            <div class="pregunta-respuesta">
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
            </div>
                
            <div class="formulario-contenido">
            <form class="formulario">
                <input type="text" placeholder="Escribe tu mensaje..." class="input">
                <button class="boton-enviar">Enviar</button>
            </form>
            <button id="newChat">New chat</button>
        </div>    
                
                
                
                
            <div class="input-contenido">
                <form action="SVPagPrincipal" method="POST" class="formulario">
                    <input type="text" placeholder="Escribe tu pregunta..." class="input" name="pregunta">
                    <button type="submit" class="boton-enviar">Enviar</button>
                </form>
                <button id="newChat">New chat</button>
            </div>
        </div>
<!--            <form action="SVPagPrincipal" method="POST">
            <p><label for>Pregunta:</label><input type="text" name="pregunta"></p>
            <button type="submit">Enviar</button>
            </form>-->
        
        <div class="pop-categoria" id="popup">
            <div class="popup-contenido">
                <h3>Ingrese la categoría</h3>
                <button class="boton-cocina" id="botonCoc">Cocina</button>
                <button class="boton-plomeria" id="botonPlo">Plomería</button>
                <button class="boton-electr" id="botonElec">Electricidad</button>
            </div>
        </div>  
        </<script src="paginaPrincipalJS.js"></script>
    </body>
</html>

